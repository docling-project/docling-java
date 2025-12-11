package ai.docling.serve.client;

import ai.docling.serve.api.DoclingServeClearApi;
import ai.docling.serve.api.clear.request.ClearRequest;
import ai.docling.serve.api.clear.response.ClearResponse;
import ai.docling.serve.api.util.ValidationUtils;

/**
 * Base class for clear API operations. Provides functionality for managing and cleaning up
 * converters and stale data retained by the service.
 */
final class ClearOperations implements DoclingServeClearApi {
  private final HttpOperations httpOperations;

  ClearOperations(HttpOperations httpOperations) {
    this.httpOperations = httpOperations;
  }

  /**
   * Clears all registered converters associated with the API.
   */
  public ClearResponse clearConverters() {
    return this.httpOperations.executeGet("/v1/clear/converters", ClearResponse.class);
  }

  /**
   * Clears stale results retained by the service, based on the specified threshold duration.
   * Results older than the duration specified in the {@link ClearRequest} parameter will be removed.
   *
   * @param request the {@link ClearRequest} object containing the threshold duration for clearing results;
   *                must not be null.
   * @return a {@link ClearResponse} object representing the result of the clear operation,
   *         including the status of the operation.
   */
  public ClearResponse clearResults(ClearRequest request) {
    ValidationUtils.ensureNotNull(request, "request");

    return this.httpOperations.executeGet("/v1/clear/results?older_then=%d".formatted(request.getOlderThen().toSeconds()), ClearResponse.class);
  }
}
