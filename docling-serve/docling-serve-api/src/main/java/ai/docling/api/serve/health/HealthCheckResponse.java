package ai.docling.api.serve.health;

import org.jspecify.annotations.Nullable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Simple health check response returned by the API.
 *
 * <p>Serialization uses {@link JsonInclude.Include#NON_EMPTY} so nulls and empty strings
 * are omitted from JSON.</p>
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@tools.jackson.databind.annotation.JsonDeserialize(builder = HealthCheckResponse.Builder.class)
@lombok.extern.jackson.Jacksonized
@lombok.Builder(toBuilder = true)
@lombok.Getter
@lombok.ToString
public class HealthCheckResponse {
  @JsonProperty("status")
  @Nullable
  private String status;

  @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
  public static class Builder { }
}
