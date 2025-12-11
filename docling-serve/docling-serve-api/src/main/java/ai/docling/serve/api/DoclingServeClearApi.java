package ai.docling.serve.api;

import ai.docling.serve.api.clear.request.ClearRequest;
import ai.docling.serve.api.clear.response.ClearResponse;

/**
 * Interface representing the Docling Serve Clear API. This API provides functionality
 * for managing and cleaning up converters and stale data retained by the service.
 * It includes methods for clearing registered converters and stored results based
 * on specified thresholds or default configurations.
 */
public interface DoclingServeClearApi {
  /**
   * Clears all registered converters associated with the API.
   * This method removes any previously configured or cached converters,
   * effectively resetting the converter state to an uninitialized state.
   * After invoking this method, no converters will be available until new ones are added or configured.
   */
  ClearResponse clearConverters();

  /**
   * Clears stored results based on the specified {@link ClearRequest}.
   * This method removes results that match the criteria provided in the
   * request, such as results older than a specified duration.
   *
   * @param request an instance of {@link ClearRequest} containing the criteria
   *                for clearing stored results, including the duration threshold
   *                or other parameters.
   * @return a {@link ClearResponse} object indicating the status of the clear
   *         operation, such as success or failure.
   */
  ClearResponse clearResults(ClearRequest request);
}
