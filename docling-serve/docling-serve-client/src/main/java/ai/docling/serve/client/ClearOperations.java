package ai.docling.serve.client;

import ai.docling.serve.api.DoclingServeClearApi;
import ai.docling.serve.api.clear.request.ClearResultsRequest;
import ai.docling.serve.api.clear.response.ClearResponse;

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
   * Clears previously stored results based on the criteria provided in the request.
   *
   * @param request the {@link ClearResultsRequest} containing the criteria for clearing results.
   * @return a {@link ClearResponse} indicating the outcome of the clear operation.
   */
  public ClearResponse clearResults(ClearResultsRequest request) {
    return this.httpOperations.executeGet("/v1/clear/results?older_then=%d".formatted(request.getOlderThan().toSeconds()), ClearResponse.class);
  }
}
