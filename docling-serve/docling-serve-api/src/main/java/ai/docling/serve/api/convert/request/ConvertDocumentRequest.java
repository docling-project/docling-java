package ai.docling.serve.api.convert.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import ai.docling.serve.api.convert.request.options.ConvertDocumentOptions;
import ai.docling.serve.api.request.DocumentRequest;

/**
 * Represents a request to convert a document. The request includes the source(s) of the document,
 * options for the conversion process, and the target specification for the converted output.
 *
 * <p>This class is serialized into JSON to conform to the API specification using
 * {@link JsonProperty} annotations. Fields with {@code null} values or empty collections
 * are omitted from the serialized JSON using {@link JsonInclude}.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@tools.jackson.databind.annotation.JsonDeserialize(builder = ConvertDocumentRequest.BuilderImpl.class)
@lombok.extern.jackson.Jacksonized
@lombok.experimental.SuperBuilder(toBuilder = true)
@lombok.Getter
@lombok.ToString(callSuper = true)
public final class ConvertDocumentRequest extends DocumentRequest {
  /**
   * Options controlling the document conversion process.
   * Includes settings for OCR, output formats, processing pipelines, and more.
   *
   * @param options the conversion options
   * @return the conversion options
   */
  @JsonProperty("options")
  @lombok.NonNull
  @lombok.Builder.Default
  private ConvertDocumentOptions options = ConvertDocumentOptions.builder().build();

  @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
  public abstract static class ConvertDocumentRequestBuilder<C extends ConvertDocumentRequest, B extends ConvertDocumentRequestBuilder<C, B>> extends DocumentRequest.DocumentRequestBuilder<C, B> {
  }
}
