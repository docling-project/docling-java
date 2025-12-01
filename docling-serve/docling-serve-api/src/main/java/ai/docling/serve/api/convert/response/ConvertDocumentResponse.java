package ai.docling.serve.api.convert.response;

import java.util.List;
import java.util.Map;

import org.jspecify.annotations.Nullable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

/**
 * Response returned by the Convert API for a single conversion request.
 *
 * <p>Serialization uses {@link JsonInclude.Include#NON_EMPTY}, so nulls and empty
 * collections/strings are omitted from JSON output.</p>
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@tools.jackson.databind.annotation.JsonDeserialize(builder = ConvertDocumentResponse.Builder.class)
@lombok.extern.jackson.Jacksonized
@lombok.Builder(toBuilder = true)
@lombok.Getter
@lombok.ToString
public class ConvertDocumentResponse {
  @JsonProperty("document")
  @Nullable
  private DocumentResponse document;

  @JsonProperty("errors")
  @JsonSetter(nulls = Nulls.AS_EMPTY)
  @lombok.Singular
  private List<ErrorItem> errors;

  @JsonProperty("processing_time")
  @Nullable
  private Double processingTime;

  @JsonProperty("status")
  @Nullable
  private String status;

  @JsonProperty("timings")
  @JsonSetter(nulls = Nulls.AS_EMPTY)
  @lombok.Singular
  private Map<String, Object> timings;

  @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
  public static class Builder { }
}
