package ai.docling.serve.api.convert.request;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

import ai.docling.serve.api.convert.request.options.ConvertDocumentOptions;
import ai.docling.serve.api.convert.request.target.Target;
import ai.docling.serve.api.request.DocumentRequest;

/**
 * Represents a request to batch convert document sources. The batch endpoint processes multiple
 * documents asynchronously and returns a task ID for tracking progress. Sources can be HTTP URLs
 * or S3 buckets, and results are delivered to a presigned URL or S3 target.
 *
 * <p>Unlike {@link ConvertDocumentRequest}, the {@linkplain #getTarget() target} is required
 * for batch requests — it must be either a
 * {@link ai.docling.serve.api.convert.request.target.PresignedUrlTarget} or
 * {@link ai.docling.serve.api.convert.request.target.S3Target}.
 *
 * <p>This class is serialized into JSON to conform to the API specification using
 * {@link JsonProperty} annotations. Fields with {@code null} values or empty collections
 * are omitted from the serialized JSON using {@link JsonInclude}.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@tools.jackson.databind.annotation.JsonDeserialize(builder = BatchConvertDocumentRequest.BuilderImpl.class)
@lombok.extern.jackson.Jacksonized
@lombok.experimental.SuperBuilder(toBuilder = true)
@lombok.Getter
@lombok.ToString(callSuper = true)
public final class BatchConvertDocumentRequest extends DocumentRequest {
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

  /**
   * Webhook callbacks for receiving progress notifications during batch processing.
   *
   * @param callbacks the list of callback specifications
   * @return the list of callback specifications
   */
  @JsonProperty("callbacks")
  @JsonSetter(nulls = Nulls.AS_EMPTY)
  @lombok.Singular
  private List<CallbackSpec> callbacks;

  /**
   * Returns the output target, which is required for batch requests.
   *
   * @return the output target, never null
   */
  @Override
  public Target getTarget() {
    return Objects.requireNonNull(super.getTarget(), "target is marked non-null but is null");
  }

  @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
  public abstract static class BatchConvertDocumentRequestBuilder<C extends BatchConvertDocumentRequest, B extends BatchConvertDocumentRequestBuilder<C, B>> extends DocumentRequest.DocumentRequestBuilder<C, B> {
  }
}
