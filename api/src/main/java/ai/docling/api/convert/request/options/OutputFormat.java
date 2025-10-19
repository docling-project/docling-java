package ai.docling.api.convert.request.options;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Document formats supported for output in Docling.
 */
public enum OutputFormat {

  @JsonProperty("doctags") DOCTAGS,
  @JsonProperty("html") HTML,
  @JsonProperty("html_split_page") HTML_SPLIT_PAGE,
  @JsonProperty("json") JSON,
  @JsonProperty("md") MARKDOWN,
  @JsonProperty("text") TEXT

}
