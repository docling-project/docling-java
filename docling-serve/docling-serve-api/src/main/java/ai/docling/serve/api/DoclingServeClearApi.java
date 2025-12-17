package ai.docling.serve.api;

import ai.docling.serve.api.clear.request.ClearConvertersRequest;
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
   * Clears all currently configured converters within the Docling Serve API.
   * This operation removes any registered converters, effectively resetting
   * the system to a state without active converter configurations.
   *
   * @param request an instance of {@link ClearConvertersRequest} containing
   *                the authentication details required to authorize this operation.
   * @return a {@link ClearResponse} object indicating the status of the clear
   *         operation, such as success or failure.
   */
  ClearResponse clearConverters(ClearConvertersRequest request);

  /**
   * Clears stored results based on the specified {@link ClearResultsRequest}.
   * This method removes results that match the criteria provided in the
   * request, such as results older than a specified duration.
   *
   * @param request an instance of {@link ClearResultsRequest} containing the criteria
   *                for clearing stored results, including the duration threshold
   *                or other parameters.
   * @return a {@link ClearResponse} object indicating the status of the clear
   *         operation, such as success or failure.
   * @throws ai.docling.serve.api.validation.ValidationException If request validation fails for any reason.
   */
  ClearResponse clearResults(ClearResultsRequest request);
}
