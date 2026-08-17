package ai.docling.serve.api.request;

import java.util.List;

import org.jspecify.annotations.Nullable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

import ai.docling.serve.api.chunk.request.ChunkDocumentRequest;
import ai.docling.serve.api.convert.request.BatchConvertDocumentRequest;
import ai.docling.serve.api.convert.request.ConvertDocumentRequest;
import ai.docling.serve.api.convert.request.source.Source;
import ai.docling.serve.api.convert.request.target.Target;

/**
 * Abstract base class for all document processing requests. Provides the common fields shared
 * across conversion, chunking, and batch conversion requests: document {@linkplain #getSources()
 * sources} and an optional output {@linkplain #getTarget() target}.
 *
 * <p>This is a {@code sealed} class — the only permitted subtypes are
 * {@link ConvertDocumentRequest}, {@link BatchConvertDocumentRequest}, and
 * {@link ChunkDocumentRequest} — enabling exhaustive pattern matching:
 *
 * <pre>{@code
 * switch (request) {
 *   case ConvertDocumentRequest r    -> client.convertSource(r);
 *   case BatchConvertDocumentRequest r -> client.convertSourceBatch(r);
 *   case HierarchicalChunkDocumentRequest r -> client.chunkSourceWithHierarchicalChunker(r);
 *   case HybridChunkDocumentRequest r       -> client.chunkSourceWithHybridChunker(r);
 * }
 * }</pre>
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@tools.jackson.databind.annotation.JsonDeserialize(builder = DocumentRequest.DocumentRequestBuilder.class)
@lombok.experimental.SuperBuilder(toBuilder = true)
@lombok.Getter
@lombok.ToString
public abstract sealed class DocumentRequest
    permits ConvertDocumentRequest, BatchConvertDocumentRequest, ChunkDocumentRequest {
  /**
   * List of document sources to be processed.
   * Each source can be a file (base64-encoded), an HTTP URL, or an S3 reference.
   *
   * @param sources the list of document sources
   * @return the list of document sources
   */
  @JsonProperty("sources")
  @JsonSetter(nulls = Nulls.AS_EMPTY)
  @lombok.Singular
  private List<Source> sources;

  /**
   * Specification for the type of output target.
   * If not specified, the result is returned in the response body.
   *
   * @param target the output target specification, or null if not specified
   * @return the output target specification, or null if not specified
   */
  @JsonProperty("target")
  @Nullable
  private Target target;

  @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
  public abstract static class DocumentRequestBuilder<C extends DocumentRequest, B extends DocumentRequestBuilder<C, B>> {
  }
}
