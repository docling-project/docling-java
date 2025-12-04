package ai.docling.serve.api.chunk.request.options;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Abstract options for configuring a document chunker.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "chunker"
)
@JsonSubTypes({
    @Type(value = HierarchicalChunkerOptions.class, name = "hierarchical"),
    @Type(value = HybridChunkerOptions.class, name = "hybrid")
})
@lombok.Getter
@lombok.ToString
public sealed abstract class ChunkerOptions permits HierarchicalChunkerOptions, HybridChunkerOptions {

  enum Chunker {
    @JsonProperty("hierarchical") HIERARCHICAL,
    @JsonProperty("hybrid") HYBRID
  }

}
