package ai.docling.serve.client.operations;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ai.docling.serve.api.DoclingServeConvertApi;
import ai.docling.serve.api.convert.request.ConvertDocumentRequest;
import ai.docling.serve.api.convert.response.ConvertDocumentResponse;
import ai.docling.serve.api.task.request.TaskResultRequest;
import ai.docling.serve.api.task.request.TaskStatusPollRequest;
import ai.docling.serve.api.task.response.TaskStatus;
import ai.docling.serve.api.task.response.TaskStatusPollResponse;
import ai.docling.serve.api.util.ValidationUtils;

/**
 * Base class for document conversion API operations. Provides access to document
 * conversion functionality including both synchronous and asynchronous operations.
 */
public final class ConvertOperations implements DoclingServeConvertApi {
  private static final Logger LOG = LoggerFactory.getLogger(ConvertOperations.class);

  private final HttpOperations httpOperations;
  private final TaskOperations taskOperations;
  private final Duration asyncPollInterval;
  private final Duration asyncTimeout;

  /**
   * Creates a new ConvertOperations instance.
   *
   * @param httpOperations the HTTP operations handler for executing requests
   * @param taskOperations the task operations handler for polling and retrieving results
   * @param asyncPollInterval the interval between status polls for async operations
   * @param asyncTimeout the maximum time to wait for async operations to complete
   */
  public ConvertOperations(HttpOperations httpOperations, TaskOperations taskOperations,
                           Duration asyncPollInterval, Duration asyncTimeout) {
    this.httpOperations = httpOperations;
    this.taskOperations = taskOperations;
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

    CompletableFuture<ConvertDocumentResponse> resultFuture = new CompletableFuture<>();

    ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(r -> {
      Thread t = new Thread(r, "docling-async-poller");
      t.setDaemon(true);
      return t;
    });

    // Start the async conversion
    CompletableFuture.supplyAsync(() ->
        this.httpOperations.executePost(createAsyncRequestContext("/v1/convert/source/async", request))
    ).thenAccept(taskResponse -> {
      var taskId = taskResponse.getTaskId();
      LOG.info("Started async conversion with task ID: {}", taskId);

      long startTime = System.currentTimeMillis();

      // Schedule the polling task
      Runnable pollTask = new Runnable() {
        @Override
        public void run() {
          try {
            // Check if we've timed out
            if (System.currentTimeMillis() - startTime > asyncTimeout.toMillis()) {
              resultFuture.completeExceptionally(
                  new RuntimeException("Async conversion timed out after " + asyncTimeout + " for task: " + taskId));
              scheduler.shutdown();
              return;
            }

            // Use the existing pollTaskStatus method from TaskOperations
            var pollRequest = TaskStatusPollRequest.builder().taskId(taskId).build();
            var statusResponse = taskOperations.pollTaskStatus(pollRequest);
            var status = statusResponse.getTaskStatus();

            LOG.debug("Task {} status: {}", taskId, status);

            if (status == TaskStatus.SUCCESS) {
              LOG.info("Task {} completed successfully", taskId);
              // Use the existing convertTaskResult method from TaskOperations
              var resultRequest = TaskResultRequest.builder().taskId(taskId).build();
              try {
                var result = taskOperations.convertTaskResult(resultRequest);
                resultFuture.complete(result);
              } catch (Exception e) {
                resultFuture.completeExceptionally(e);
              }
              scheduler.shutdown();
            } else if (status == TaskStatus.FAILURE) {
              String errorMessage = "Task failed";
              if (statusResponse.getTaskStatusMetadata() != null) {
                errorMessage = "Task failed: " + statusResponse.getTaskStatusMetadata();
              }
              resultFuture.completeExceptionally(
                  new RuntimeException("Async conversion failed for task " + taskId + ": " + errorMessage));
              scheduler.shutdown();
            } else {
              // Still in progress (PENDING or STARTED), schedule next poll
              scheduler.schedule(this, asyncPollInterval.toMillis(), TimeUnit.MILLISECONDS);
            }
          } catch (Exception e) {
            resultFuture.completeExceptionally(e);
            scheduler.shutdown();
          }
        }
      };

      // Start polling immediately
      scheduler.schedule(pollTask, 0, TimeUnit.MILLISECONDS);
    }).exceptionally(e -> {
      resultFuture.completeExceptionally(e);
      scheduler.shutdown();
      return null;
    });

    return resultFuture;
  }
}
