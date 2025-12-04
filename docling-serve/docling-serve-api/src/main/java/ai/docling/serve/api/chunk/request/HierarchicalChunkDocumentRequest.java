package ai.docling.serve.api.chunk.request;

import java.util.List;

import org.jspecify.annotations.Nullable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

import ai.docling.serve.api.chunk.request.options.HierarchicalChunkerOptions;
import ai.docling.serve.api.convert.request.options.ConvertDocumentOptions;
import ai.docling.serve.api.convert.request.source.Source;
import ai.docling.serve.api.convert.request.target.Target;

/**
 * Represents a request to convert a document and chunk it into smaller documents
 * using the Docling hierarchical chunker.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@tools.jackson.databind.annotation.JsonDeserialize(builder = HierarchicalChunkDocumentRequest.Builder.class)
@lombok.extern.jackson.Jacksonized
@lombok.Builder(toBuilder = true)
@lombok.Getter
@lombok.ToString
public class HierarchicalChunkDocumentRequest {

  @JsonProperty("sources")
  @JsonSetter(nulls = Nulls.AS_EMPTY)
  @lombok.Singular
  private List<Source> sources;

  @JsonProperty("options")
  @lombok.NonNull
  @lombok.Builder.Default
  private ConvertDocumentOptions options = ConvertDocumentOptions.builder().build();

  @JsonProperty("target")
  @Nullable
  private Target target;

  @JsonProperty("include_converted_doc")
  private boolean includeConvertedDoc;

  @JsonProperty("chunking_options")
  @lombok.NonNull
  @lombok.Builder.Default
  private HierarchicalChunkerOptions chunkingOptions = HierarchicalChunkerOptions.builder().build();

  @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
  public static class Builder { }

}
