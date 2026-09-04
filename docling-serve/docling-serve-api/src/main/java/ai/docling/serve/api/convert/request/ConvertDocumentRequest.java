package ai.docling.serve.api.convert.request;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import ai.docling.serve.api.convert.request.options.ConvertDocumentOptions;
import ai.docling.serve.api.convert.request.source.Source;
import ai.docling.serve.api.convert.request.target.Target;
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

  /**
   * Builder for {@link ConvertDocumentRequest}.
   *
   * <p>The {@code source}, {@code sources}, {@code clearSources}, and {@code target} mutators are
   * redeclared here (delegating to the base builder) so that they are members of this concrete
   * builder type rather than being inherited only from {@link DocumentRequest.Builder}. This keeps
   * fluent calls such as {@code ConvertDocumentRequest.builder().source(...)} resolvable under
   * GraalVM native image {@code --link-at-build-time}, where a virtual call whose declared owner is
   * this subtype must be found on the subtype itself.
   */
  public abstract static class Builder<C extends ConvertDocumentRequest, B extends Builder<C, B>> extends DocumentRequest.Builder<C, B> {
    @Override
    public B source(Source source) {
      return super.source(source);
    }

    @Override
    public B sources(Collection<? extends Source> sources) {
      return super.sources(sources);
    }

    @Override
    public B clearSources() {
      return super.clearSources();
    }

    @Override
    public B target(Target target) {
      return super.target(target);
    }
  }
}
