package ai.docling.serve.client.operations;

import ai.docling.serve.api.DoclingServeClearApi;
import ai.docling.serve.api.clear.request.ClearConvertersRequest;
import ai.docling.serve.api.clear.request.ClearResultsRequest;
import ai.docling.serve.api.clear.response.ClearResponse;
import ai.docling.serve.api.util.ValidationUtils;

/**
 * Base class for clear API operations. Provides functionality for managing and cleaning up
 * converters and stale data retained by the service.
 */
public final class ClearOperations implements DoclingServeClearApi {
  private final HttpOperations httpOperations;

  public ClearOperations(HttpOperations httpOperations) {
    this.httpOperations = httpOperations;
  }

  /**
   * Clears all configured converters using the Docling Serve API.
   *
   * This method performs an HTTP GET request to reset or clear any converters that have
   * been configured in the current session. The request may include authentication
   * details required to authorize the operation.
   *
   * @param request the {@link ClearConvertersRequest} object containing the parameters
   *                for the clear operation. This request object must not be null.
   * @return a {@link ClearResponse} object representing the result of the clear operation,
   *         including details such as the status of the operation.
   */
  public ClearResponse clearConverters(ClearConvertersRequest request) {
    return this.httpOperations.executeGet(createRequestContext("/v1/clear/converters", request));
  }

  /**
   * Clears stale results retained by the service, based on the specified threshold duration.
   * Results older than the duration specified in the {@link ClearResultsRequest} parameter will be removed.
   *
   * @param request the {@link ClearResultsRequest} object containing the threshold duration for clearing results;
   *                must not be null.
   * @return a {@link ClearResponse} object representing the result of the clear operation,
   *         including the status of the operation.
   */
  public ClearResponse clearResults(ClearResultsRequest request) {
    ValidationUtils.ensureNotNull(request, "request");
    return this.httpOperations.executeGet(createRequestContext("/v1/clear/results?older_then=%d".formatted(request.getOlderThen().toSeconds()), request));
  }

  private <I> RequestContext<I, ClearResponse> createRequestContext(String uri, I request) {
    return RequestContext.<I, ClearResponse>builder()
        .request(request)
        .responseType(ClearResponse.class)
        .uri(uri)
        .build();
  }
}
