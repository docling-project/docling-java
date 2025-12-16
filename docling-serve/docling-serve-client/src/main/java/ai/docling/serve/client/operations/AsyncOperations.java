package ai.docling.serve.client.operations;

import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ai.docling.serve.api.DoclingServeTaskApi;
import ai.docling.serve.api.task.request.TaskResultRequest;
import ai.docling.serve.api.task.request.TaskStatusPollRequest;
import ai.docling.serve.api.task.response.TaskStatusPollResponse;
import ai.docling.serve.api.util.ValidationUtils;

/**
 * Abstract base class for managing asynchronous operations, providing methods to execute
 * tasks asynchronously and poll for their status until completion. Subclasses must implement
 * specific logic for retrieving task results.
 */
public abstract class AsyncOperations {
  private static final Logger LOG = LoggerFactory.getLogger(AsyncOperations.class);

  private final HttpOperations httpOperations;
  private final DoclingServeTaskApi taskApi;
  private final Duration asyncPollInterval;
  private final Duration asyncTimeout;

  protected AsyncOperations(HttpOperations httpOperations, DoclingServeTaskApi taskApi, Duration asyncPollInterval, Duration asyncTimeout) {
    this.httpOperations = httpOperations;
    this.taskApi = taskApi;
    this.asyncPollInterval = asyncPollInterval;
    this.asyncTimeout = asyncTimeout;
  }

  /**
   * Retrieves the result of a task based on the provided task result request.
   *
   * This method is abstract and meant to be implemented by subclasses.
   * It uses the information provided in the {@code TaskResultRequest}
   * to obtain the result of the task execution.
   *
   * @param <O> the type of the result object returned
   * @param taskResultRequest the request containing the details, including the task ID,
   *                          required to retrieve the task result
   * @return the result of the task execution, of the type {@code O}
   */
  protected abstract <O> O getTaskResult(TaskResultRequest taskResultRequest);

  /**
   * Executes an asynchronous operation by sending a request to the specified URI
   * and polling for the task completion. This method uses an HTTP POST operation
   * to start the task and then repeatedly polls the task status to determine
   * when the operation is complete.
   *
   * @param <I> the type of the request object being sent
   * @param <O> the type of the response object returned upon completion
   * @param request the request object containing the data necessary to initialize the task
   * @param uri the endpoint URI to which the request will be sent
   * @return a {@link CompletableFuture} that will be completed with the result of the asynchronous operation
   */
  protected <I, O> CompletableFuture<O> executeAsync(I request, String uri) {
    ValidationUtils.ensureNotNull(request, "request");

    // Start the async conversion and chain the polling logic
    return CompletableFuture.supplyAsync(() ->
        this.httpOperations.executePost(createAsyncRequestContext(uri, request))
    ).thenCompose(taskResponse -> {
      LOG.info("Started async conversion with task ID: {}", taskResponse.getTaskId());

      long startTime = System.currentTimeMillis();
      return pollTaskUntilComplete(taskResponse, startTime);
    });
  }

  private <I> RequestContext<I, TaskStatusPollResponse> createAsyncRequestContext(String uri, I request) {
    return RequestContext.<I, TaskStatusPollResponse>builder()
        .request(request)
        .responseType(TaskStatusPollResponse.class)
        .uri(uri)
        .build();
  }

  private <O> CompletionStage<O> pollTaskUntilComplete(TaskStatusPollResponse statusPollResponse, long startTime) {
    var taskId = statusPollResponse.getTaskId();

    // Check if we've timed out
    if (System.currentTimeMillis() - startTime > this.asyncTimeout.toMillis()) {
      return CompletableFuture.failedFuture(
          new RuntimeException("Async conversion timed out after %s for task: %s".formatted(this.asyncTimeout, taskId))
      );
    }

    // Poll the task status
    var pollRequest = TaskStatusPollRequest.builder()
        .taskId(taskId)
        .build();

    return CompletableFuture.supplyAsync(() -> this.taskApi.pollTaskStatus(pollRequest))
        .thenCompose(statusResponse -> pollTaskStatus(statusResponse, startTime));
  }

  private <O> CompletionStage<O> pollTaskStatus(TaskStatusPollResponse statusResponse, long startTime) {
    var status = statusResponse.getTaskStatus();
    var taskId = statusResponse.getTaskId();
    LOG.debug("Task {} status: {}", taskId, status);

    return switch (status) {
      case SUCCESS -> {
        LOG.info("Task {} completed successfully", taskId);

        // Retrieve the result
        var taskResult = TaskResultRequest.builder()
            .taskId(statusResponse.getTaskId())
            .build();

        yield CompletableFuture.supplyAsync(() -> getTaskResult(taskResult));
      }

      case FAILURE -> {
        var errorMessage = Optional.ofNullable(statusResponse.getTaskStatusMetadata())
            .map(metadata -> "Task failed: %s".formatted(metadata))
            .orElse("Task failed");

        yield CompletableFuture.failedStage(
            new RuntimeException("Async conversion failed for task %s: %s".formatted(taskId, errorMessage)));
      }

      default ->
        // Still in progress (PENDING or STARTED), schedule next poll after delay
        CompletableFuture.supplyAsync(
            () -> null,
            CompletableFuture.delayedExecutor(this.asyncPollInterval.toMillis(), TimeUnit.MILLISECONDS)
        ).thenCompose(v -> pollTaskUntilComplete(statusResponse, startTime));
    };
  }
}
