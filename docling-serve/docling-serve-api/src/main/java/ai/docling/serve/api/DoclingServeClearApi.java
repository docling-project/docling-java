package ai.docling.serve.api;

import ai.docling.serve.api.clear.request.ClearResultsRequest;
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
   * Clears previously stored results based on the criteria provided in the request.
   * This method removes stale results or data that meet the threshold specified
   * in the {@link ClearResultsRequest}. It is typically used to clean up older
   * or unused data retained by the service.
   *
   * @param request the {@link ClearResultsRequest} containing the criteria for clearing
   *        results, such as the threshold duration for identifying stale data.
   * @return a {@link ClearResponse} indicating the outcome of the clear operation,
   *         including status or potential errors, if applicable.
   */
  ClearResponse clearResults(ClearResultsRequest request);
}
