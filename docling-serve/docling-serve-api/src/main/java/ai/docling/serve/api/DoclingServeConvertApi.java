package ai.docling.serve.api;

import java.util.concurrent.CompletableFuture;

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

  /**
   * Initiates an asynchronous conversion of the provided document source(s) and returns a
   * {@link CompletableFuture} that completes when the conversion is done.
   *
   * <p>This method starts the conversion, polls the status in the background, and completes
   * the future with the result when the conversion finishes.
   *
   * <p>Example usage:
   * <pre>{@code
   * client.convertSourceAsync(request)
   *     .thenApply(response -> response.getDocument().getMarkdownContent())
   *     .thenAccept(markdown -> System.out.println(markdown))
   *     .exceptionally(ex -> { ex.printStackTrace(); return null; });
   * }</pre>
   *
   * @param request the {@link ConvertDocumentRequest} containing the source(s) and conversion options.
   * @return a {@link CompletableFuture} that completes with the {@link ConvertDocumentResponse}
   *         when the conversion is finished, or completes exceptionally if the conversion fails
   *         or times out.
   */
  CompletableFuture<ConvertDocumentResponse> convertSourceAsync(ConvertDocumentRequest request);
}
