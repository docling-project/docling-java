package ai.docling.api;

import ai.docling.api.convert.request.ConvertDocumentRequest;
import ai.docling.api.convert.response.ConvertDocumentResponse;
import ai.docling.api.health.HealthCheckResponse;

/**
 * Docling API interface.
 */
public interface DoclingApi {

  HealthCheckResponse health();

  ConvertDocumentResponse convertSource(ConvertDocumentRequest request);

}
