package ai.docling.serve.client;

import java.time.Duration;
import java.util.Optional;

import org.jspecify.annotations.Nullable;

import ai.docling.serve.api.DoclingServeTaskApi;
import ai.docling.serve.api.chunk.response.ChunkDocumentResponse;
import ai.docling.serve.api.convert.response.ConvertDocumentResponse;
import ai.docling.serve.api.task.response.TaskStatusPollResponse;
import ai.docling.serve.api.util.ValidationUtils;

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
   * Polls the current status of a specified task.
   *
   * This method sends a request to the server to retrieve the current status
   * of the task identified by the given {@code taskId}. Optionally, a {@code waitTime}
   * can be specified to indicate how long the server should wait for a status change
   * before responding.
   *
   * @param taskId the unique identifier of the task whose status is being polled.
   *               Must not be blank or null.
   * @param waitTime an optional {@link Duration} specifying how long the server should
   *                 wait for a status change before responding. If null, no wait is applied.
   * @return a {@link TaskStatusPollResponse} containing the current status details of the task.
   * @throws IllegalArgumentException if {@code taskId} is blank or null.
   */
  public TaskStatusPollResponse pollTaskStatus(String taskId, @Nullable Duration waitTime) {
    ValidationUtils.ensureNotBlank(taskId, "taskId");

    var waitTimeSeconds = Optional.ofNullable(waitTime)
        .orElse(DEFAULT_STATUS_POLL_WAIT_TIME)
        .toSeconds();

    return this.httpOperations.executeGet("/v1/status/poll/%s?wait=%d".formatted(taskId, waitTimeSeconds), TaskStatusPollResponse.class);
  }

  /**
   * Retrieves the result of a completed task identified by the specified task ID.
   *
   * This method sends a GET request to the server to fetch the result of the task.
   * The returned response includes details about the converted document and any
   * potential errors or processing metadata associated with the task.
   *
   * @param taskId the unique identifier of the task whose result is being fetched.
   *               Must not be blank or null.
   * @return a {@link ConvertDocumentResponse} containing the details of the converted document,
   *         processing time, status, and any errors encountered during processing.
   * @throws IllegalArgumentException if {@code taskId} is blank or null.
   */
  public ConvertDocumentResponse convertTaskResult(String taskId) {
    ValidationUtils.ensureNotBlank(taskId, "taskId");
    return this.httpOperations.executeGet("/v1/result/%s".formatted(taskId), ConvertDocumentResponse.class);
  }

  /**
   * Retrieves the result of a completed task in chunks, identified by the specified task ID.
   *
   * This method sends a GET request to fetch the result of the task, providing the output
   * in a chunked format. The response includes details about the chunks, related documents,
   * processing time, and other metadata related to task completion.
   *
   * @param taskId the unique identifier of the task whose chunked result is being fetched.
   *               Must not be blank or null.
   * @return a {@link ChunkDocumentResponse} containing information about the chunks,
   *         related documents, processing time, and any additional task metadata.
   * @throws IllegalArgumentException if {@code taskId} is blank or null.
   */
  public ChunkDocumentResponse chunkTaskResult(String taskId) {
    ValidationUtils.ensureNotBlank(taskId, "taskId");
    return this.httpOperations.executeGet("/v1/result/%s".formatted(taskId), ChunkDocumentResponse.class);
  }
}
