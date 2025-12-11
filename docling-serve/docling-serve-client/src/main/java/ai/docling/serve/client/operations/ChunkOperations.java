package ai.docling.serve.client.operations;

import ai.docling.serve.api.DoclingServeChunkApi;
import ai.docling.serve.api.chunk.request.HierarchicalChunkDocumentRequest;
import ai.docling.serve.api.chunk.request.HybridChunkDocumentRequest;
import ai.docling.serve.api.chunk.response.ChunkDocumentResponse;
import ai.docling.serve.api.util.ValidationUtils;

/**
 * Base class for document chunking API operations. Provides access to document chunking
 * functionality with both hierarchical and hybrid strategies.
 */
public final class ChunkOperations implements DoclingServeChunkApi {
  private final HttpOperations httpOperations;

  public ChunkOperations(HttpOperations httpOperations) {
    this.httpOperations = httpOperations;
  }

  /**
   * Converts and chunks the provided document source(s) into a processed document based on the specified options
   * and using a hierarchical chunker for splitting the document into smaller chunks.
   */
  public ChunkDocumentResponse chunkSourceWithHierarchicalChunker(HierarchicalChunkDocumentRequest request) {
    ValidationUtils.ensureNotNull(request, "request");
    return this.httpOperations.executePost("/v1/chunk/hierarchical/source", request, ChunkDocumentResponse.class);
  }

  /**
   * Converts and chunks the provided document source(s) into a processed document based on the specified options
   * and using a hybrid chunker for splitting the document into smaller chunks.
   */
  public ChunkDocumentResponse chunkSourceWithHybridChunker(HybridChunkDocumentRequest request) {
    ValidationUtils.ensureNotNull(request, "request");
    return this.httpOperations.executePost("/v1/chunk/hybrid/source", request, ChunkDocumentResponse.class);
  }
}
