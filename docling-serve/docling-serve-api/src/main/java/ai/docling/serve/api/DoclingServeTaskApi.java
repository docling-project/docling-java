package ai.docling.serve.api;

import java.time.Duration;

import org.jspecify.annotations.Nullable;

import ai.docling.serve.api.chunk.response.ChunkDocumentResponse;
import ai.docling.serve.api.convert.response.ConvertDocumentResponse;
import ai.docling.serve.api.task.response.TaskStatusPollResponse;

/**
 * Defines the interface for the Docling Task API, which provides operations for
 * managing and querying the status of asynchronous tasks.
 *
 * This interface supports task status polling with configurable wait durations
 * and a default polling mechanism. It serves as the base for specific implementations
 * such as {@link DoclingServeApi}.
 */
public interface DoclingServeTaskApi {
  /**
   * The default wait time between status polling attempts for a task.
   * <p>
   * This value is used when no explicit wait time is specified in a
   * {@code TaskStatusPollRequest} instance. It is set to {@link Duration#ZERO},
   * meaning there is no delay by default between consecutive polling attempts.
   * </p>
   */
  Duration DEFAULT_STATUS_POLL_WAIT_TIME = Duration.ZERO;

  /**
   * Polls the status of a task asynchronously and retrieves its current state.
   * Allows for configurable wait time between polling attempts.
   * If the wait time is {@code }, the default wait time ({@link #DEFAULT_STATUS_POLL_WAIT_TIME}) is used.
   *
   * @param taskId the unique identifier of the task whose status is being polled
   * @param waitTime the duration to wait before polling the status, or null to use the default polling interval
   * @return a {@link TaskStatusPollResponse} containing the current status of the task and associated metadata
   */
  TaskStatusPollResponse pollTaskStatus(String taskId, @Nullable Duration waitTime);

  /**
   * Polls the status of a task asynchronously using the default wait time.
   * This convenience method delegates to {@link #pollTaskStatus(String, Duration)}
   * with {@code DEFAULT_STATUS_POLL_WAIT_TIME} as the wait time.
   *
   * @param taskId the unique identifier of the task whose status is being polled
   * @return a {@link TaskStatusPollResponse} containing the current status of the task
   *         and associated metadata
   */
  default TaskStatusPollResponse pollTaskStatus(String taskId) {
    return pollTaskStatus(taskId, DEFAULT_STATUS_POLL_WAIT_TIME);
  }

  /**
   * Converts the completed task result identified by the provided task ID into a document response.
   * This method processes the task data associated with the given ID and generates a response
   * encapsulating the converted document details.
   *
   * @param taskId the unique identifier of the task whose result needs to be converted into a document response
   * @return a {@link ConvertDocumentResponse} containing the details of the converted document, processing metadata,
   *         errors (if any), and other relevant information
   */
  ConvertDocumentResponse convertTaskResult(String taskId);

  /**
   * Processes the results of a completed task identified by the given task ID and generates a
   * response containing chunked document details. This method is used to break down the document
   * associated with the task into manageable chunks, making it suitable for subsequent processing
   * or analysis.
   *
   * @param taskId the unique identifier of the task whose result is to be processed and chunked into
   *        a {@link ChunkDocumentResponse}
   * @return a {@link ChunkDocumentResponse} containing the chunked document details and related metadata
   */
  ChunkDocumentResponse chunkTaskResult(String taskId);
}
