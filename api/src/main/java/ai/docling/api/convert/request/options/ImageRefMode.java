package ai.docling.api.convert.request.options;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The mode for how to handle image references in the document.
 */
public enum ImageRefMode {

  @JsonProperty("embedded") EMBEDDED,
  @JsonProperty("placeholder") PLACEHOLDER,
  @JsonProperty("referenced") REFERENCED

}
