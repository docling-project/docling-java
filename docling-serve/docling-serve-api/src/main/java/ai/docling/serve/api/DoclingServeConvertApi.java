package ai.docling.serve.api;

import ai.docling.serve.api.convert.request.ConvertDocumentRequest;
import ai.docling.serve.api.convert.response.ConvertDocumentResponse;

/**
 * Interface representing the Docling Serve Convert API.
 * This API is responsible for processing and converting document source(s) into
 * a structured or processed document format based on the specified conversion options.
 */
public interface DoclingServeConvertApi {
    /**
   * Converts the provided document source(s) into a processed document based on the specified options.
   *
   * @param request the {@link ConvertDocumentRequest} containing the source(s), conversion options, and optional target.
   * @return a {@link ConvertDocumentResponse} containing the processed document data, processing details, and any errors.
   */
  ConvertDocumentResponse convertSource(ConvertDocumentRequest request);
}
