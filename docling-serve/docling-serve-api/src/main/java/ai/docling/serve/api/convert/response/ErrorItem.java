package ai.docling.serve.api.convert.response;

import org.jspecify.annotations.Nullable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an individual error produced during document conversion.
 *
 * <p>Serialization uses {@link JsonInclude.Include#NON_EMPTY}, so nulls and empty
 * strings are omitted from JSON output.</p>
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@tools.jackson.databind.annotation.JsonDeserialize(builder = ErrorItem.Builder.class)
@lombok.extern.jackson.Jacksonized
@lombok.Builder(toBuilder = true)
@lombok.Getter
@lombok.ToString
public class ErrorItem {
  @JsonProperty("component_type")
  @Nullable
  private String componentType;

  @JsonProperty("error_message")
  @Nullable
  private String errorMessage;

  @JsonProperty("module_name")
  @Nullable
  private String moduleName;

  @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
  public static class Builder { }
}
