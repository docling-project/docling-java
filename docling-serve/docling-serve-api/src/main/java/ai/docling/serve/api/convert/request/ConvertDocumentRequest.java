package ai.docling.serve.api.convert.request;

import java.util.List;

import org.jspecify.annotations.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

import ai.docling.serve.api.auth.AuthenticatedRequest;
import ai.docling.serve.api.auth.Authentication;
import ai.docling.serve.api.convert.request.options.ConvertDocumentOptions;
import ai.docling.serve.api.convert.request.source.Source;
import ai.docling.serve.api.convert.request.target.Target;

/**
 * Represents a request to convert a document. The request includes the source(s) of the document,
 * options for the conversion process, and the target specification for the converted output.
 *
 * <p>This class is serialized into JSON to conform to the API specification using
 * {@link JsonProperty} annotations. Fields with {@code null} values or empty collections
 * are omitted from the serialized JSON using {@link JsonInclude}.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@tools.jackson.databind.annotation.JsonDeserialize(builder = ConvertDocumentRequest.Builder.class)
@lombok.extern.jackson.Jacksonized
@lombok.Builder(toBuilder = true)
@lombok.Getter
@lombok.ToString
public class ConvertDocumentRequest implements AuthenticatedRequest {
  @JsonProperty("sources")
  @JsonSetter(nulls = Nulls.AS_EMPTY)
  @lombok.Singular
  private List<Source> sources;

  @JsonProperty("options")
  @lombok.NonNull
  @lombok.Builder.Default
  private ConvertDocumentOptions options = ConvertDocumentOptions.builder().build();

  @JsonProperty("target")
  @Nullable
  private Target target;

  @JsonIgnore
  @lombok.NonNull
  @lombok.Builder.Default
  private Authentication authentication = Authentication.builder().build();

  @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
  public static class Builder { }
}
