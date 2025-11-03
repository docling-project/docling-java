package ai.docling.api.serve.convert.request.source;

import java.net.URI;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

/**
 * Represents an HTTP-based data source.
 * This class is a concrete implementation of {@link Source} and provides
 * functionality for configuring an HTTP endpoint with optional headers.
 *
 * <p>JSON serialization/deserialization:
 * <ul>
 *   <li>{@code url} - Represents the URI of the HTTP source.</li>
 *   <li>{@code headers} - A map of custom HTTP headers for the request.</li>
 * </ul>
 *
 * <p>By default, JSON includes non-empty fields only.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@tools.jackson.databind.annotation.JsonDeserialize(builder = HttpSource.Builder.class)
@lombok.extern.jackson.Jacksonized
@lombok.Builder(toBuilder = true)
@lombok.Getter
@lombok.ToString
public final class HttpSource extends Source {
  @JsonProperty("url")
  @lombok.NonNull
  private URI url;

  @JsonProperty("headers")
  @JsonSetter(nulls = Nulls.AS_EMPTY)
  @lombok.Singular
  private Map<String, Object> headers;

  @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
  public static class Builder { }
}
