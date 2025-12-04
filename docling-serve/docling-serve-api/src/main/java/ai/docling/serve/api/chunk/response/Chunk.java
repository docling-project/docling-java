package ai.docling.serve.api.chunk.response;

import java.util.List;
import java.util.Map;

import org.jspecify.annotations.Nullable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

/**
 * Represents a chunk of a document after it has been processed by a Docling chunker.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@tools.jackson.databind.annotation.JsonDeserialize(builder = Chunk.Builder.class)
@lombok.extern.jackson.Jacksonized
@lombok.Builder(toBuilder = true)
@lombok.Getter
@lombok.ToString
public class Chunk {

  @JsonProperty("filename")
  private String filename;

  @JsonProperty("chunk_index")
  private int chunkIndex;

  @JsonProperty("text")
  private String text;

  @JsonProperty("raw_text")
  @Nullable
  private String rawText;

  @JsonProperty("num_tokens")
  private Integer numTokens;

  @JsonProperty("headings")
  @JsonSetter(nulls = Nulls.AS_EMPTY)
  @lombok.Singular
  private List<String> headings;

  @JsonProperty("captions")
  @JsonSetter(nulls = Nulls.AS_EMPTY)
  @lombok.Singular
  private List<String> captions;

  @JsonProperty("doc_items")
  @JsonSetter(nulls = Nulls.AS_EMPTY)
  @lombok.Singular
  private List<String> docItems;

  @JsonProperty("page_numbers")
  @JsonSetter(nulls = Nulls.AS_EMPTY)
  @lombok.Singular
  private List<Integer> pageNumbers;

  @JsonProperty("metadata")
  @JsonSetter(nulls = Nulls.AS_EMPTY)
  @lombok.Singular("metadata")
  private Map<String, Object> metadata;

  @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
  public static class Builder { }

}
