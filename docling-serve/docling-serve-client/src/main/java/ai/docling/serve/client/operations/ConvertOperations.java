package ai.docling.serve.client.operations;

import ai.docling.serve.api.DoclingServeConvertApi;
import ai.docling.serve.api.convert.request.ConvertDocumentRequest;
import ai.docling.serve.api.convert.response.ConvertDocumentResponse;
import ai.docling.serve.api.util.ValidationUtils;

/**
 * Base class for document conversion API operations. Provides access to document
 * conversion functionality.
 */
public final class ConvertOperations implements DoclingServeConvertApi {
  private final HttpOperations httpOperations;

  public ConvertOperations(HttpOperations httpOperations) {
    this.httpOperations = httpOperations;
  }

  /**
   * Converts the provided document source(s) into a processed document based on the specified options.
   *
   * @param request the {@link ConvertDocumentRequest} containing the source(s), conversion options, and optional target.
   * @return a {@link ConvertDocumentResponse} containing the processed document data, processing details, and any errors.
   */
  public ConvertDocumentResponse convertSource(ConvertDocumentRequest request) {
    ValidationUtils.ensureNotNull(request, "request");
    return this.httpOperations.executePost(createRequestContext("/v1/convert/source", request));
  }

  private <I> RequestContext<I, ConvertDocumentResponse> createRequestContext(String uri, I request) {
    return RequestContext.<I, ConvertDocumentResponse>builder()
        .request(request)
        .responseType(ConvertDocumentResponse.class)
        .uri(uri)
        .build();
  }
}
