package ai.docling.serve.api;

import java.nio.file.Path;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

import org.jspecify.annotations.Nullable;

import ai.docling.serve.api.chunk.request.HierarchicalChunkDocumentRequest;
import ai.docling.serve.api.chunk.request.HybridChunkDocumentRequest;
import ai.docling.serve.api.chunk.response.ChunkDocumentResponse;
import ai.docling.serve.api.util.FileUtils;
import ai.docling.serve.api.util.ValidationUtils;

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
   * Processes and chunks the specified files into smaller, structured pieces
   * using a hierarchical chunker. This method internally delegates the processing
   * to another overloaded method with default options for the hierarchical chunker.
   *
   * @param files the files to be processed and chunked using the hierarchical chunker
   * @return a {@link ChunkDocumentResponse} containing the processed chunks, optionally the
   *         converted documents, and associated metadata
   */
  default ChunkDocumentResponse chunkFilesWithHierarchicalChunker(Path... files) {
    return chunkFilesWithHierarchicalChunker(null, files);
  }

  /**
   * Processes and chunks the specified files into smaller, structured pieces
   * using a hierarchical chunker. This method utilizes a provided hierarchical
   * chunk request, applying additional configurations if needed during the
   * creation of the chunking request.
   *
   * @param request the request containing configurations and options for hierarchical
   *                chunking. It may include settings for conversion, chunking parameters,
   *                and optional output specifications. Can be null to use default options.
   * @param files   the files to be processed and chunked using the hierarchical chunker.
   * @return a {@link ChunkDocumentResponse} containing the processed chunks, optionally the
   *         converted documents, and associated metadata.
   */
  default ChunkDocumentResponse chunkFilesWithHierarchicalChunker(@Nullable HierarchicalChunkDocumentRequest request, Path... files) {
    return chunkSourceWithHierarchicalChunker(createHierarchicalChunkRequest(request, files));
  }

  /**
   * Processes and chunks the provided document source(s) into smaller documents
   * using a hybrid chunking strategy. The method utilizes the specified hybrid
   * chunker options to split and process the input request.
   *
   * @param request the request containing the document source(s), conversion options, hybrid
   *                chunker configurations, and optional specifications for output targets
   * @return a {@link ChunkDocumentResponse} containing the processed chunks, optionally the
   *         converted document, and other relevant metadata
   */
  ChunkDocumentResponse chunkSourceWithHybridChunker(HybridChunkDocumentRequest request);

  /**
   * Processes and chunks the specified files into smaller, structured pieces
   * using a hybrid chunking strategy. This method delegates the processing
   * to another overloaded method with default options for the hybrid chunker.
   *
   * @param files the files to be processed and chunked using the hybrid chunker
   * @return a {@link ChunkDocumentResponse} containing the processed chunks,
   *         optionally the converted documents, and associated metadata
   */
  default ChunkDocumentResponse chunkFilesWithHybridChunker(Path... files) {
    return chunkFilesWithHybridChunker(null, files);
  }

  /**
   * Processes and chunks the specified files into smaller, structured pieces
   * using a hybrid chunking strategy. The method converts the input files into
   * a hybrid chunk request and processes them to generate a structured representation
   * of the content.
   *
   * @param request the request containing configurations for processing, including
   *                conversion options, hybrid chunking parameters, and optional
   *                specifications for output targets. Can be null to use default options.
   * @param files   the files to be processed and chunked using the hybrid chunking strategy.
   * @return a {@code ChunkDocumentResponse} containing the processed chunks, optionally the
   *         converted documents, and associated metadata.
   */
  default ChunkDocumentResponse chunkFilesWithHybridChunker(@Nullable HybridChunkDocumentRequest request, Path... files) {
    return chunkSourceWithHybridChunker(createHybridChunkRequest(request, files));
  }

  /**
   * Asynchronously processes the provided document source(s) by converting and chunking them
   * into smaller documents using the hierarchical chunker. This operation allows for handling
   * large document processing tasks without blocking the caller thread.
   *
   * @param request the request containing the document source(s) and options for hierarchical chunking
   * @return a {@link CompletionStage} that resolves to a {@link ChunkDocumentResponse}, which contains
   *         the processed chunks, optionally the converted document, and processing metadata
   */
  CompletionStage<ChunkDocumentResponse> chunkSourceWithHierarchicalChunkerAsync(HierarchicalChunkDocumentRequest request);

  /**
   * Asynchronously processes and chunks the specified files into smaller, structured pieces
   * using a hierarchical chunker. This method delegates the processing to another overloaded
   * method with default options for the hierarchical chunker, leveraging non-blocking
   * asynchronous execution.
   *
   * @param files the files to be processed and chunked using the hierarchical chunker
   * @return a {@link CompletionStage} resolving to a {@link ChunkDocumentResponse}, which
   *         includes the processed chunks, optionally the converted documents, and associated
   *         metadata
   */
  default CompletionStage<ChunkDocumentResponse> chunkFilesWithHierarchicalChunkerAsync(Path... files) {
    return chunkFilesWithHierarchicalChunkerAsync(null, files);
  }

  /**
   * Asynchronously processes and chunks the specified files into smaller, structured pieces
   * using a hierarchical chunker. This method allows for non-blocking execution by delegating
   * the processing to an underlying method that handles hierarchical chunking configurations
   * and file chunking.
   *
   * @param request the request object containing configurations, options for hierarchical
   *                chunking, and optional specifications for output targets. Can be null
   *                to use default options for processing.
   * @param files   the files to be processed and chunked using the hierarchical chunker.
   * @return a {@link CompletionStage} that resolves to a {@link ChunkDocumentResponse},
   *         which includes the processed chunks, optionally the converted documents,
   *         and associated metadata.
   */
  default CompletionStage<ChunkDocumentResponse> chunkFilesWithHierarchicalChunkerAsync(@Nullable HierarchicalChunkDocumentRequest request, Path... files) {
    return chunkSourceWithHierarchicalChunkerAsync(createHierarchicalChunkRequest(request, files));
  }

  /**
   * Asynchronously processes the provided document source(s) by converting and chunking them
   * into smaller documents using the hybrid chunker. This operation facilitates non-blocking
   * processing of large document tasks by leveraging a hybrid chunking strategy.
   *
   * @param request the request containing the document source(s), options for conversion,
   *                hybrid chunking parameters, and optional specifications for output targets
   * @return a {@link CompletionStage} that resolves to a {@link ChunkDocumentResponse}, which includes
   *         the processed chunks, optionally the converted document, and relevant processing metadata
   */
  CompletionStage<ChunkDocumentResponse> chunkSourceWithHybridChunkerAsync(HybridChunkDocumentRequest request);

  /**
   * Asynchronously processes and chunks the provided files using a hybrid chunking strategy.
   *
   * @param files An array of file paths to be processed and chunked. Each path should represent
   *              a valid file location.
   * @return A CompletionStage that, when completed, holds a ChunkDocumentResponse containing
   *         the results of the chunking operation.
   */
  default CompletionStage<ChunkDocumentResponse> chunkFilesWithHybridChunkerAsync(Path... files) {
    return chunkFilesWithHybridChunkerAsync(null, files);
  }

  /**
   * Asynchronously processes and chunks the given files using a hybrid chunking mechanism.
   *
   * @param request An optional {@code HybridChunkDocumentRequest} containing configuration details for chunking.
   *                If {@code null}, default settings will be applied.
   * @param files   A varargs array of {@code Path} objects representing the files to be chunked.
   *                Must not be null or empty.
   * @return A {@code CompletionStage<ChunkDocumentResponse>} that completes with the resulting
   *         {@code ChunkDocumentResponse} once the chunking operation is finished.
   */
  default CompletionStage<ChunkDocumentResponse> chunkFilesWithHybridChunkerAsync(@Nullable HybridChunkDocumentRequest request, Path... files) {
    return chunkSourceWithHybridChunkerAsync(createHybridChunkRequest(request, files));
  }

  private HierarchicalChunkDocumentRequest createHierarchicalChunkRequest(@Nullable HierarchicalChunkDocumentRequest request, Path... files) {
    ValidationUtils.ensureNotEmpty(files, "files");

    var builder = Optional.ofNullable(request)
        .map(HierarchicalChunkDocumentRequest::toBuilder)
        .orElseGet(HierarchicalChunkDocumentRequest::builder);

    FileUtils.createFileSources(files)
        .forEach(builder::source);

    return builder.build();
  }

  private HybridChunkDocumentRequest createHybridChunkRequest(@Nullable HybridChunkDocumentRequest request, Path... files) {
    ValidationUtils.ensureNotEmpty(files, "files");

    var builder = Optional.ofNullable(request)
        .map(HybridChunkDocumentRequest::toBuilder)
        .orElseGet(HybridChunkDocumentRequest::builder);

    FileUtils.createFileSources(files)
        .forEach(builder::source);

    return builder.build();
  }
}
