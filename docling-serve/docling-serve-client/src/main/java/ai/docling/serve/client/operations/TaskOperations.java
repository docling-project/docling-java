package ai.docling.serve.client.operations;

import ai.docling.serve.api.DoclingServeTaskApi;
import ai.docling.serve.api.chunk.response.ChunkDocumentResponse;
import ai.docling.serve.api.convert.response.ConvertDocumentResponse;
import ai.docling.serve.api.task.request.TaskResultRequest;
import ai.docling.serve.api.task.request.TaskStatusPollRequest;
import ai.docling.serve.api.task.response.TaskStatusPollResponse;
import ai.docling.serve.api.util.ValidationUtils;

/**
 * Base class for task API operations. Provides operations for managing and querying
 * the status of asynchronous tasks.
 */
public final class TaskOperations implements DoclingServeTaskApi {
  private final HttpOperations httpOperations;

  public TaskOperations(HttpOperations httpOperations) {
    this.httpOperations = httpOperations;
  }

  /**
   * Polls the current status of a specified task.
   *
   * This method sends a GET request to the task status API endpoint to retrieve
   * the current status of a task. The request includes the task ID and an optional
   * wait time between polling attempts. The response contains details about the
   * task's status, position in the queue, and any relevant metadata.
   *
   * @param request an instance of {@link TaskStatusPollRequest} containing the
   *                unique task identifier and optional wait time for polling.
   *                Must not be null.
   * @return a {@link TaskStatusPollResponse} containing the current status of
   *         the task, its position in the queue, and any associated metadata.
   * @throws IllegalArgumentException if the {@code request} is null.
   */
  public TaskStatusPollResponse pollTaskStatus(TaskStatusPollRequest request) {
    ValidationUtils.ensureNotNull(request, "request");

    return this.httpOperations.executeGet(
        "/v1/status/poll/%s?wait=%d".formatted(
            request.getTaskId(),
            request.getWaitTime().toSeconds()
        ),
        TaskStatusPollResponse.class
    );
  }

  /**
   * Retrieves the result of a completed task identified by the specified task ID.
   *
   * This method sends a GET request to fetch the result of a task that has been processed.
   * The response includes details about the converted document, processing time, status,
   * and any potential errors or additional metadata related to the task.
   *
   * @param request an instance of {@link TaskResultRequest} containing the unique task
   *                identifier. Must not be null.
   * @return a {@link ConvertDocumentResponse} containing details about the converted
   *         document, processing time, status, and any associated errors or metadata.
   * @throws IllegalArgumentException if {@code request} is null.
   */
  public ConvertDocumentResponse convertTaskResult(TaskResultRequest request) {
    ValidationUtils.ensureNotNull(request, "request");
    return this.httpOperations.executeGet("/v1/result/%s".formatted(request.getTaskId()), request, ConvertDocumentResponse.class);
  }

  /**
   * Retrieves the chunked result of a completed task identified by the specified task ID.
   *
   * This method sends a GET request to fetch the chunked result of a task that has been processed.
   * The response includes details about the chunks, associated documents, processing time,
   * and any relevant metadata.
   *
   * @param request an instance of {@link TaskResultRequest} containing the unique task
   *                identifier. Must not be null.
   * @return a {@link ChunkDocumentResponse} containing details about the chunks,
   *         documents, processing time, and any associated metadata.
   * @throws IllegalArgumentException if {@code request} is null.
   */
  public ChunkDocumentResponse chunkTaskResult(TaskResultRequest request) {
    ValidationUtils.ensureNotNull(request, "request");
    return this.httpOperations.executeGet("/v1/result/%s".formatted(request.getTaskId()), request, ChunkDocumentResponse.class);
  }
}
