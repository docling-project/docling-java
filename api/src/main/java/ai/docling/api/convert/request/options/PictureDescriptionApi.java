package ai.docling.api.convert.request.options;

import java.net.URI;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.jspecify.annotations.Nullable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PictureDescriptionApi(

    @JsonProperty("url") URI url,

    @JsonProperty("headers") @Nullable Map<String, Object> headers,

    @JsonProperty("params") @Nullable Map<String, Object> params,

    @JsonProperty("timeout") @Nullable Duration timeout,

    @JsonProperty("concurrency") @Nullable Integer concurrency,

    @JsonProperty("prompt") @Nullable String prompt

) {

  public PictureDescriptionApi {
    if (url == null) {
      throw new IllegalArgumentException("url cannot be null");
    }

    if (headers != null) {
      headers = new HashMap<>(headers);
    }
    if (params != null) {
      params = new HashMap<>(params);
    }
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {

    @Nullable
    private URI url;
    @Nullable
    private Map<String, Object> headers;
    @Nullable
    private Map<String, Object> params;
    @Nullable
    private Duration timeout;
    @Nullable
    private Integer concurrency;
    @Nullable
    private String prompt;

    private Builder() {
    }

    /**
     * Endpoint which accepts OpenAI API compatible requests.
     */
    public Builder url(URI url) {
      this.url = url;
      return this;
    }

    /**
     * Headers used for calling the API endpoint.
     * For example, it could include authentication headers.
     */
    public Builder headers(@Nullable Map<String, Object> headers) {
      this.headers = headers;
      return this;
    }

    /**
     * Model parameters.
     */
    public Builder params(@Nullable Map<String, Object> params) {
      this.params = params;
      return this;
    }

    /**
     * Timeout for the API request.
     */
    public Builder timeout(@Nullable Duration timeout) {
      this.timeout = timeout;
      return this;
    }

    /**
     * Maximum number of concurrent requests to the API.
     */
    public Builder concurrency(@Nullable Integer concurrency) {
      this.concurrency = concurrency;
      return this;
    }

    /**
     * Prompt used when calling the vision-language model.
     */
    public Builder prompt(@Nullable String prompt) {
      this.prompt = prompt;
      return this;
    }

    public PictureDescriptionApi build() {
      return new PictureDescriptionApi(url, headers, params, timeout, concurrency, prompt);
    }

  }

}
