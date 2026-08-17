package ai.docling.serve.api.chunk.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import ai.docling.serve.api.convert.request.options.ConvertDocumentOptions;
import ai.docling.serve.api.request.DocumentRequest;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@tools.jackson.databind.annotation.JsonDeserialize(builder = ChunkDocumentRequest.ChunkDocumentRequestBuilder.class)
@lombok.experimental.SuperBuilder(toBuilder = true)
@lombok.Getter
@lombok.ToString(callSuper = true)
public sealed abstract class ChunkDocumentRequest extends DocumentRequest
    permits HierarchicalChunkDocumentRequest, HybridChunkDocumentRequest {
  /**
   * Conversion options.
   *
   * @param options the conversion options
   * @return the conversion options
   */
  @JsonProperty("convert_options")
  @lombok.NonNull
  @lombok.Builder.Default
  private ConvertDocumentOptions options = ConvertDocumentOptions.builder().build();

  /**
   * If true, the output will include both the chunks and the converted document.
   *
   * @param includeConvertedDoc true if the converted document should be included, false otherwise
   * @return true if the converted document should be included, false otherwise
   */
  @JsonProperty("include_converted_doc")
  private boolean includeConvertedDoc;

  @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
  public abstract static class ChunkDocumentRequestBuilder<C extends ChunkDocumentRequest, B extends ChunkDocumentRequestBuilder<C, B>> extends DocumentRequest.DocumentRequestBuilder<C, B> {
  }
}
