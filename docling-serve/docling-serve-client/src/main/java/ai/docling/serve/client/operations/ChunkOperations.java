package ai.docling.serve.client.operations;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

import ai.docling.serve.api.DoclingServeChunkApi;
import ai.docling.serve.api.DoclingServeTaskApi;
import ai.docling.serve.api.chunk.request.HierarchicalChunkDocumentRequest;
import ai.docling.serve.api.chunk.request.HybridChunkDocumentRequest;
import ai.docling.serve.api.chunk.response.ChunkDocumentResponse;
import ai.docling.serve.api.task.request.TaskResultRequest;
import ai.docling.serve.api.util.ValidationUtils;

/**
 * Base class for document chunking API operations. Provides access to document chunking
 * functionality with both hierarchical and hybrid strategies.
 */
public final class ChunkOperations extends AsyncOperations implements DoclingServeChunkApi {
  private final HttpOperations httpOperations;
  private final DoclingServeTaskApi taskApi;

  public ChunkOperations(HttpOperations httpOperations, DoclingServeTaskApi taskApi,
                         Duration asyncPollInterval, Duration asyncTimeout) {
    super(httpOperations, taskApi, asyncPollInterval, asyncTimeout);
    this.httpOperations = httpOperations;
    this.taskApi = taskApi;
  }

  @Override
  protected ChunkDocumentResponse getTaskResult(TaskResultRequest taskResultRequest) {
    return this.taskApi.chunkTaskResult(taskResultRequest);
  }

  @Override
  public ChunkDocumentResponse chunkSourceWithHierarchicalChunker(HierarchicalChunkDocumentRequest request) {
    ValidationUtils.ensureNotNull(request, "request");
    return this.httpOperations.executePost(createRequestContext("/v1/chunk/hierarchical/source", request));
  }

  @Override
  public ChunkDocumentResponse chunkSourceWithHybridChunker(HybridChunkDocumentRequest request) {
    ValidationUtils.ensureNotNull(request, "request");
    return this.httpOperations.executePost(createRequestContext("/v1/chunk/hybrid/source", request));
  }

  @Override
  public CompletableFuture<ChunkDocumentResponse> chunkSourceWithHierarchicalChunkerAsync(HierarchicalChunkDocumentRequest request) {
    return executeAsync(request, "/v1/chunk/hierarchical/source/async");
  }

  @Override
  public CompletableFuture<ChunkDocumentResponse> chunkSourceWithHybridChunkerAsync(HybridChunkDocumentRequest request) {
    return executeAsync(request, "/v1/chunk/hybrid/source/async");
  }

  private <I> RequestContext<I, ChunkDocumentResponse> createRequestContext(String uri, I request) {
    return RequestContext.<I, ChunkDocumentResponse>builder()
        .request(request)
        .responseType(ChunkDocumentResponse.class)
        .uri(uri)
        .build();
  }
}
