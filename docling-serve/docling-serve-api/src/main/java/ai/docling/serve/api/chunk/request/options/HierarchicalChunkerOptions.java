package ai.docling.serve.api.chunk.request.options;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Options for configuring the hierarchical chunker.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@tools.jackson.databind.annotation.JsonDeserialize(builder = HierarchicalChunkerOptions.Builder.class)
@lombok.extern.jackson.Jacksonized
@lombok.Builder(toBuilder = true)
@lombok.Getter
@lombok.ToString
public final class HierarchicalChunkerOptions extends ChunkerOptions {

  @JsonProperty("use_markdown_tables")
  private boolean useMarkdownTables;

  @JsonProperty("include_raw_text")
  private boolean includeRawText;

  @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
  public static class Builder { }

}
