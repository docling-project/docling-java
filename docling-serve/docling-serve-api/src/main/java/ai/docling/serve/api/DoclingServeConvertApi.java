package ai.docling.serve.api;

import java.nio.file.Path;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

import org.jspecify.annotations.Nullable;

import ai.docling.serve.api.convert.request.ConvertDocumentRequest;
import ai.docling.serve.api.convert.response.ConvertDocumentResponse;
import ai.docling.serve.api.util.FileUtils;
import ai.docling.serve.api.util.ValidationUtils;

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
   * @throws ai.docling.serve.api.validation.ValidationException If request validation fails for any reason.
   */
  ConvertDocumentResponse convertSource(ConvertDocumentRequest request);

  /**
   * Converts the specified files into a processed document using default options.
   * This is a convenience method that delegates to {@link #convertFiles(ConvertDocumentRequest, Path...)}
   * with a null request.
   *
   * @param files an array of {@link Path} objects representing the file paths to be converted
   * @return a {@link ConvertDocumentResponse} containing the processed document data, metadata, and any errors
   * @throws ai.docling.serve.api.validation.ValidationException If request validation fails for any reason.
   */
  default ConvertDocumentResponse convertFiles(Path... files) {
    return convertFiles(null, files);
  }

  /**
   * Converts the specified files into a processed document based on the options provided in the request.
   * If the request is null, default conversion options are applied.
   *
   * @param request an optional {@link ConvertDocumentRequest} specifying conversion settings and parameters
   * @param files an array of {@link Path} objects representing the file paths to be converted
   * @return a {@link ConvertDocumentResponse} containing the processed document data, any errors encountered,
   *         and additional processing metadata
   * @throws ai.docling.serve.api.validation.ValidationException If request validation fails for any reason.
   */
  default ConvertDocumentResponse convertFiles(@Nullable ConvertDocumentRequest request, Path... files) {
    return convertSource(createRequest(request, files));
  }

  /**
   * Initiates an asynchronous conversion of the provided document source(s) and returns a
   * {@link CompletionStage} that completes when the conversion is done.
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
   * @return a {@link CompletionStage} that completes with the {@link ConvertDocumentResponse}
   *         when the conversion is finished, or completes exceptionally if the conversion fails
   *         or times out.
   * @throws ai.docling.serve.api.validation.ValidationException If request validation fails for any reason.
   */
  CompletionStage<ConvertDocumentResponse> convertSourceAsync(ConvertDocumentRequest request);

  /**
   * Initiates an asynchronous conversion of the provided files into a processed document
   * using default conversion options.
   *
   * @param files an array of {@link Path} objects representing the file paths to be converted
   * @return a {@link CompletionStage} that completes with the {@link ConvertDocumentResponse}
   *         when the conversion finishes, or completes exceptionally if the conversion fails
   *         or times out
   * @throws ai.docling.serve.api.validation.ValidationException If request validation fails for any reason.
   */
  default CompletionStage<ConvertDocumentResponse> convertFilesAsync(Path... files) {
    return convertFilesAsync(null, files);
  }

  /**
   * Initiates an asynchronous conversion of the specified files into a processed document
   * using the provided conversion request options. If the request is null, default conversion
   * options are applied.
   *
   * @param request an optional {@link ConvertDocumentRequest} containing conversion settings
   *                and parameters, or null to use default options
   * @param files   an array of {@link Path} objects representing the file paths to be converted
   * @return a {@link CompletionStage} that completes with the {@link ConvertDocumentResponse}
   *         when the conversion finishes, or completes exceptionally if the conversion fails
   *         or times out
   * @throws ai.docling.serve.api.validation.ValidationException If request validation fails for any reason.
   */
  default CompletionStage<ConvertDocumentResponse> convertFilesAsync(@Nullable ConvertDocumentRequest request, Path... files) {
    return convertSourceAsync(createRequest(request, files));
  }

  private ConvertDocumentRequest createRequest(@Nullable ConvertDocumentRequest request, Path... files) {
    ValidationUtils.ensureNotEmpty(files, "files");

    var builder = Optional.ofNullable(request)
        .map(ConvertDocumentRequest::toBuilder)
        .orElseGet(ConvertDocumentRequest::builder);

    FileUtils.createFileSources(files)
        .forEach(builder::source);

    return builder.build();
  }
}
