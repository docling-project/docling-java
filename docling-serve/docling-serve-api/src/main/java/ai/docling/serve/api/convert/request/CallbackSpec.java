package ai.docling.serve.api.convert.request;

import java.net.URI;
import java.util.Map;

import org.jspecify.annotations.Nullable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

/**
 * Represents a webhook callback specification for batch conversion progress notifications.
 * When configured, the server sends POST requests to the specified URL with progress updates
 * as documents are processed.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@tools.jackson.databind.annotation.JsonDeserialize(builder = CallbackSpec.Builder.class)
@lombok.extern.jackson.Jacksonized
@lombok.Builder(toBuilder = true)
@lombok.Getter
@lombok.ToString
public class CallbackSpec {
  /**
   * The webhook URL that receives POST progress updates.
   *
   * @param url the webhook URL
   * @return the webhook URL
   */
  @JsonProperty("url")
  @lombok.NonNull
  private URI url;

  /**
   * Additional headers sent with callback requests.
   *
   * @param headers the additional headers
   * @return the additional headers
   */
  @JsonProperty("headers")
  @JsonSetter(nulls = Nulls.AS_EMPTY)
  @lombok.Singular
  private Map<String, Object> headers;

  /**
   * Custom CA certificate (PEM) for endpoint verification.
   *
   * @param caCert the CA certificate in PEM format
   * @return the CA certificate in PEM format
   */
  @JsonProperty("ca_cert")
  @Nullable
  private String caCert;

  @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
  public static class Builder { }
}
