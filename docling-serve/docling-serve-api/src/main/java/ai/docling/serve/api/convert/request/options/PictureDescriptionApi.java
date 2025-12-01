package ai.docling.serve.api.convert.request.options;

import java.net.URI;
import java.time.Duration;
import java.util.Map;

import org.jspecify.annotations.Nullable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

/**
 * Configuration options for picture description API integration.
 * This class encapsulates settings for making API calls to describe pictures,
 * including URL, headers, parameters, timeout, concurrency, and custom prompts.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@tools.jackson.databind.annotation.JsonDeserialize(builder = PictureDescriptionApi.Builder.class)
@lombok.extern.jackson.Jacksonized
@lombok.Builder(toBuilder = true)
@lombok.Getter
@lombok.ToString
public class PictureDescriptionApi {
  @JsonProperty("url")
  @lombok.NonNull
  private URI url;

  @JsonProperty("headers")
  @JsonSetter(nulls = Nulls.AS_EMPTY)
  @lombok.Singular
  private Map<String, Object> headers;

  @JsonProperty("params")
  @JsonSetter(nulls = Nulls.AS_EMPTY)
  @lombok.Singular
  private Map<String, Object> params;

  @JsonProperty("timeout")
  @Nullable
  private Duration timeout;

  @JsonProperty("concurrency")
  @Nullable
  private Integer concurrency;

  @JsonProperty("prompt")
  @Nullable
  private String prompt;

  @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
  public static class Builder { }
}
