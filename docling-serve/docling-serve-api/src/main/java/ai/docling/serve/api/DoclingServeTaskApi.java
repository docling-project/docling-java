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
   * Polls the status of a task using the provided request object.
   * This method allows querying the current status of an asynchronous task
   * in progress or completed, based on its unique identifier and other
   * optional parameters such as wait time.
   *
   * @param request the {@link TaskStatusPollRequest} containing the details
   *        for polling, including the task identifier and optional wait duration.
   * @return a {@link TaskStatusPollResponse} containing the task's current status,
   *         including progress, position in the queue, and other metadata,
   *         if available.
   */
  TaskStatusPollResponse pollTaskStatus(TaskStatusPollRequest request);

  /**
   * Converts the task result from a completed process into a document conversion response.
   * This method processes the provided task result request, retrieves conversion data, and
   * returns the resulting document conversion details.
   *
   * @param request the {@link TaskResultRequest} containing the task identifier for which
   *                the result is being retrieved and converted.
   * @return a {@link ConvertDocumentResponse} containing the details of the converted document,
   *         such as the document data, processing time, status, and any associated errors.
   */
  ConvertDocumentResponse convertTaskResult(TaskResultRequest request);

  /**
   * Processes the result of a completed task request by transforming the task data into
   * a chunked document response. This method retrieves conversion data specific to the
   * requested task and generates a response containing the chunked document details.
   *
   * @param request the {@link TaskResultRequest} containing the unique task identifier
   *                for which the result is being processed into chunks.
   * @return a {@link ChunkDocumentResponse} containing the details of the chunked document,
   *         including the generated chunks, associated documents, processing time, and any
   *         relevant metadata.
   */
  ChunkDocumentResponse chunkTaskResult(TaskResultRequest request);
}
