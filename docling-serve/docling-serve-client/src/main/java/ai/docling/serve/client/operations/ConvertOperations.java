package ai.docling.serve.client.operations;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ai.docling.serve.api.DoclingServeConvertApi;
import ai.docling.serve.api.DoclingServeTaskApi;
import ai.docling.serve.api.convert.request.ConvertDocumentRequest;
import ai.docling.serve.api.convert.response.ConvertDocumentResponse;
import ai.docling.serve.api.task.request.TaskResultRequest;
import ai.docling.serve.api.task.request.TaskStatusPollRequest;
import ai.docling.serve.api.task.response.TaskStatusPollResponse;
import ai.docling.serve.api.util.ValidationUtils;

/**
 * Base class for document conversion API operations. Provides access to document
 * conversion functionality including both synchronous and asynchronous operations.
 */
public final class ConvertOperations implements DoclingServeConvertApi {
  private static final Logger LOG = LoggerFactory.getLogger(ConvertOperations.class);

  private final HttpOperations httpOperations;
  private final DoclingServeTaskApi taskApi;
  private final Duration asyncPollInterval;
  private final Duration asyncTimeout;

  /**
   * Creates a new ConvertOperations instance.
   *
   * @param httpOperations the HTTP operations handler for executing requests
   * @param taskApi the task operations handler for polling and retrieving results
   * @param asyncPollInterval the interval between status polls for async operations
   * @param asyncTimeout the maximum time to wait for async operations to complete
   */
  public ConvertOperations(HttpOperations httpOperations, DoclingServeTaskApi taskApi,
                           Duration asyncPollInterval, Duration asyncTimeout) {
    this.httpOperations = httpOperations;
    this.taskApi = taskApi;
    this.asyncPollInterval = asyncPollInterval;
    this.asyncTimeout = asyncTimeout;
  }

  @Override
  public ConvertDocumentResponse convertSource(ConvertDocumentRequest request) {
    ValidationUtils.ensureNotNull(request, "request");
    return this.httpOperations.executePost(createRequestContext("/v1/convert/source", request));
  }

  private <I> RequestContext<I, ConvertDocumentResponse> createRequestContext(String uri, I request) {
    return RequestContext.<I, ConvertDocumentResponse>builder()
        .request(request)
        .responseType(ConvertDocumentResponse.class)
        .uri(uri)
        .build();
  }

  private <I> RequestContext<I, TaskStatusPollResponse> createAsyncRequestContext(String uri, I request) {
    return RequestContext.<I, TaskStatusPollResponse>builder()
        .request(request)
        .responseType(TaskStatusPollResponse.class)
        .uri(uri)
        .build();
  }

    @Override
  public CompletableFuture<ConvertDocumentResponse> convertSourceAsync(ConvertDocumentRequest request) {
    ValidationUtils.ensureNotNull(request, "request");

    // Start the async conversion and chain the polling logic
    return CompletableFuture.supplyAsync(() ->
        this.httpOperations.executePost(createAsyncRequestContext("/v1/convert/source/async", request))
    ).thenCompose(taskResponse -> {
      var taskId = taskResponse.getTaskId();
      LOG.info("Started async conversion with task ID: {}", taskId);

      long startTime = System.currentTimeMillis();
      return pollTaskUntilComplete(taskId, startTime);
    });
  }

  /**
   * Recursively polls a task until it completes, fails, or times out.
   *
   * @param taskId the ID of the task to poll
   * @param startTime the timestamp when polling started (for timeout calculation)
   * @return a CompletableFuture that completes with the conversion result
   */
  private CompletableFuture<ConvertDocumentResponse> pollTaskUntilComplete(String taskId, long startTime) {
    // Check if we've timed out
    if (System.currentTimeMillis() - startTime > asyncTimeout.toMillis()) {
      return CompletableFuture.failedFuture(
          new RuntimeException("Async conversion timed out after " + asyncTimeout + " for task: " + taskId));
    }

    // Poll the task status
    var pollRequest = TaskStatusPollRequest.builder().taskId(taskId).build();

    return CompletableFuture.supplyAsync(() -> taskApi.pollTaskStatus(pollRequest))
        .thenCompose(statusResponse -> pollTaskStatus(statusResponse, startTime));
  }

  private CompletionStage<ConvertDocumentResponse> pollTaskStatus(TaskStatusPollResponse statusResponse, long startTime) {
    var status = statusResponse.getTaskStatus();
    var taskId = statusResponse.getTaskId();
    LOG.debug("Task {} status: {}", taskId, status);

    return switch (statusResponse.getTaskStatus()) {
      case SUCCESS -> {
        LOG.info("Task {} completed successfully", taskId);
        // Retrieve the result
        var resultRequest = TaskResultRequest.builder().taskId(taskId).build();
        yield CompletableFuture.supplyAsync(() -> taskApi.convertTaskResult(resultRequest));
      }
      case FAILURE -> {
        var errorMessage = "Task failed";
        if (statusResponse.getTaskStatusMetadata()!=null) {
          errorMessage = "Task failed: " + statusResponse.getTaskStatusMetadata();
        }
        yield CompletableFuture.failedFuture(
            new RuntimeException("Async conversion failed for task " + taskId + ": " + errorMessage));
      }
      default ->
        // Still in progress (PENDING or STARTED), schedule next poll after delay
        CompletableFuture.supplyAsync(
            () -> null,
            CompletableFuture.delayedExecutor(asyncPollInterval.toMillis(), TimeUnit.MILLISECONDS)
        ).thenCompose(v -> pollTaskUntilComplete(taskId, startTime));
    };
  }
}
