package ai.docling.serve.api;

import ai.docling.serve.api.chunk.request.HierarchicalChunkDocumentRequest;
import ai.docling.serve.api.chunk.request.HybridChunkDocumentRequest;
import ai.docling.serve.api.chunk.response.ChunkDocumentResponse;

/**
 * Represents the Docling Serve Chunk API, providing methods for processing document sources
 * by splitting them into smaller chunks using various chunking strategies. This interface
 * ensures flexibility by supporting both hierarchical and hybrid chunking mechanisms.
 */
public interface DoclingServeChunkApi {
  /**
   * Converts and chunks the provided document source(s) into a processed document based on the specified options
   * and using a hierarchical chunker for splitting the document into smaller chunks.
   */
  ChunkDocumentResponse chunkSourceWithHierarchicalChunker(HierarchicalChunkDocumentRequest request);

  /**
   * Converts and chunks the provided document source(s) into a processed document based on the specified options
   * and using a hybrid chunker for splitting the document into smaller chunks.
   */
  ChunkDocumentResponse chunkSourceWithHybridChunker(HybridChunkDocumentRequest request);
}
