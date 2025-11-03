package ai.docling.api.serve.convert.request.options;

import java.net.URI;
import java.time.Duration;
import java.util.Map;

import org.jspecify.annotations.Nullable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

/**
 * API details for using a vision-language model for the VLM pipeline.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@tools.jackson.databind.annotation.JsonDeserialize(builder = VlmModelApi.Builder.class)
@lombok.extern.jackson.Jacksonized
@lombok.Builder(toBuilder = true)
@lombok.Getter
@lombok.ToString
public class VlmModelApi {
    @JsonProperty("url")
    @Nullable
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

    @JsonProperty("scale")
    @Nullable
    private Integer scale;

    @JsonProperty("response_format")
    @Nullable
    private ResponseFormat responseFormat;

    @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
    public static class Builder { }
}
