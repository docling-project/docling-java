package ai.docling.serve.api;

import java.util.concurrent.CompletableFuture;

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

  /**
   * Asynchronously processes the provided document source(s) by converting and chunking them
   * into smaller documents using the hierarchical chunker. This operation allows for handling
   * large document processing tasks without blocking the caller thread.
   *
   * @param request the request containing the document source(s) and options for hierarchical chunking
   * @return a CompletableFuture that resolves to a {@link ChunkDocumentResponse}, which contains
   *         the processed chunks, optionally the converted document, and processing metadata
   */
  CompletableFuture<ChunkDocumentResponse> chunkSourceWithHierarchicalChunkerAsync(HierarchicalChunkDocumentRequest request);

  /**
   * Asynchronously processes the provided document source(s) by converting and chunking them
   * into smaller documents using the hybrid chunker. This operation facilitates non-blocking
   * processing of large document tasks by leveraging a hybrid chunking strategy.
   *
   * @param request the request containing the document source(s), options for conversion,
   *                hybrid chunking parameters, and optional specifications for output targets
   * @return a CompletableFuture that resolves to a {@link ChunkDocumentResponse}, which includes
   *         the processed chunks, optionally the converted document, and relevant processing metadata
   */
  CompletableFuture<ChunkDocumentResponse> chunkSourceWithHybridChunkerAsync(HybridChunkDocumentRequest request);
}
