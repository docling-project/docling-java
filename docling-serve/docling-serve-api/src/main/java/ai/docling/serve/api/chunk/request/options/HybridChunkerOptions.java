package ai.docling.serve.api.chunk.request.options;

import org.jspecify.annotations.Nullable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Options for configuring the hybrid chunker.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@tools.jackson.databind.annotation.JsonDeserialize(builder = HybridChunkerOptions.Builder.class)
@lombok.extern.jackson.Jacksonized
@lombok.Builder(toBuilder = true)
@lombok.Getter
@lombok.ToString
public final class HybridChunkerOptions extends ChunkerOptions {

  @JsonProperty("use_markdown_tables")
  private boolean useMarkdownTables;

  @JsonProperty("include_raw_text")
  private boolean includeRawText;

  @JsonProperty("max_tokens")
  @Nullable
  private Integer maxTokens;

  @JsonProperty("tokenizer")
  @Nullable
  private String tokenizer;

  @JsonProperty("merge_peers")
  private Boolean mergePeers;

  @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
  public static class Builder { }

}
