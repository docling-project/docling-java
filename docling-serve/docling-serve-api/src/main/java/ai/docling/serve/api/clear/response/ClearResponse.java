package ai.docling.serve.api.clear.response;

import org.jspecify.annotations.Nullable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the response returned when performing a "clear" operation
 * within the Docling API. This response typically communicates the status
 * of the clear operation, indicating success, failure, or other relevant
 * information.
 *
 * The response includes a status field, which may provide additional details
 * about the operation's outcome. The status field is optional and may be
 * null or empty if no specific status message is provided.
 *
 * This class is immutable and is constructed using the {@link Builder}.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@tools.jackson.databind.annotation.JsonDeserialize(builder = ClearResponse.Builder.class)
@lombok.extern.jackson.Jacksonized
@lombok.Builder(toBuilder = true)
@lombok.Getter
@lombok.ToString
public class ClearResponse {
  @JsonProperty("status")
  @Nullable
  private String status;

  @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
  public static class Builder { }
}
