package ai.docling.serve.client;

import ai.docling.serve.api.DoclingServeTaskApi;
import ai.docling.serve.api.chunk.response.ChunkDocumentResponse;
import ai.docling.serve.api.convert.response.ConvertDocumentResponse;
import ai.docling.serve.api.task.request.TaskResultRequest;
import ai.docling.serve.api.task.request.TaskStatusPollRequest;
import ai.docling.serve.api.task.response.TaskStatusPollResponse;

/**
 * Base class for task API operations. Provides operations for managing and querying
 * the status of asynchronous tasks.
 */
final class TaskOperations implements DoclingServeTaskApi {
  private final HttpOperations httpOperations;

  TaskOperations(HttpOperations httpOperations) {
    this.httpOperations = httpOperations;
  }

  /**
   * Polls the status of a task using the provided request object.
   *
   * @param request the {@link TaskStatusPollRequest} containing the details for polling.
   * @return a {@link TaskStatusPollResponse} containing the task's current status.
   */
  public TaskStatusPollResponse pollTaskStatus(TaskStatusPollRequest request) {
    return this.httpOperations.executeGet("/v1/status/poll/%s?wait=%d".formatted(request.getTaskId(), request.getWaitTime().toSeconds()), TaskStatusPollResponse.class);
  }

  /**
   * Converts the task result from a completed process into a document conversion response.
   *
   * @param request the {@link TaskResultRequest} containing the task identifier.
   * @return a {@link ConvertDocumentResponse} containing the details of the converted document.
   */
  public ConvertDocumentResponse convertTaskResult(TaskResultRequest request) {
    return this.httpOperations.executeGet("/v1/result/%s".formatted(request.getTaskId()), ConvertDocumentResponse.class);
  }

  /**
   * Processes the result of a completed task request by transforming the task data into
   * a chunked document response.
   *
   * @param request the {@link TaskResultRequest} containing the unique task identifier.
   * @return a {@link ChunkDocumentResponse} containing the details of the chunked document.
   */
  public ChunkDocumentResponse chunkTaskResult(TaskResultRequest request) {
    return this.httpOperations.executeGet("/v1/result/%s".formatted(request.getTaskId()), ChunkDocumentResponse.class);
  }
}
