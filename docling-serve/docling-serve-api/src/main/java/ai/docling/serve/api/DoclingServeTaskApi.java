package ai.docling.serve.api;

import ai.docling.serve.api.chunk.response.ChunkDocumentResponse;
import ai.docling.serve.api.convert.response.ConvertDocumentResponse;
import ai.docling.serve.api.task.request.TaskResultRequest;
import ai.docling.serve.api.task.request.TaskStatusPollRequest;
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
   * Polls the status of an asynchronous task based on the provided request.
   *
   * This method retrieves the current status of a task using the task identifier specified
   * in the request object. It allows monitoring of a task's progress, position in the queue,
   * and detailed status metadata, if available. The polling behavior can be influenced by
   * optional configurations such as the wait time between polling attempts provided in the request.
   *
   * @param request the {@link TaskStatusPollRequest} containing the task identifier and optional
   *                wait time between polling attempts
   * @return a {@link TaskStatusPollResponse} encapsulating the current status, position in the
   *         queue, and optional metadata for the specified task
   * @throws ai.docling.serve.api.validation.ValidationException If request validation fails for any reason.
   */
  TaskStatusPollResponse pollTaskStatus(TaskStatusPollRequest request);

  /**
   * Converts the result of a completed task into a document format as specified in the request.
   *
   * This method processes the task result identified by the unique task ID provided in
   * the request, performs any necessary transformation, and returns a response
   * containing the converted document and related details.
   *
   * @param request the {@link TaskResultRequest} containing the unique task identifier
   *                for which the result is to be converted
   * @return a {@link ConvertDocumentResponse} encapsulating the converted document,
   *         processing status, timings, and potential errors
   * @throws ai.docling.serve.api.validation.ValidationException If request validation fails for any reason.
   */
  ConvertDocumentResponse convertTaskResult(TaskResultRequest request);

  /**
   * Processes the result of a completed task and splits it into smaller chunks as per
   * the provided request specifications.
   *
   * The method uses the unique task identifier, provided in the request, to locate the
   * task result. It then analyzes the result and breaks it into chunks, which are
   * included in the response along with any relevant metadata.
   *
   * @param request the {@link TaskResultRequest} containing the unique identifier of
   *                the task whose result is to be processed and chunked
   * @return a {@link ChunkDocumentResponse} containing the chunked result,
   *         associated documents, processing time, and additional relevant metadata
   * @throws ai.docling.serve.api.validation.ValidationException If request validation fails for any reason.
   */
  ChunkDocumentResponse chunkTaskResult(TaskResultRequest request);
}
