package ai.docling.api.serve.convert.request.options;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Modes for the TableFormer model.
 */
public enum TableFormerMode {

  @JsonProperty("accurate") ACCURATE,
  @JsonProperty("fast") FAST

}
