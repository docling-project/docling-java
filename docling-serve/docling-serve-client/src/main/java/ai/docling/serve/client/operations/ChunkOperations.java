package ai.docling.serve.client.operations;

import java.time.Duration;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;

import org.jspecify.annotations.Nullable;

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

  /**
   * Creates a new ChunkOperations instance whose async operations run on the default async executor of
   * {@link java.util.concurrent.CompletableFuture}.
   *
   * @param httpOperations    the HTTP operations handler for executing requests
   * @param taskApi           the task operations handler for polling and retrieving results
   * @param asyncPollInterval the interval between status polls for async operations
   * @param asyncTimeout      the maximum time to wait for async operations to complete
   */
  public ChunkOperations(HttpOperations httpOperations, DoclingServeTaskApi taskApi, Duration asyncPollInterval, Duration asyncTimeout) {
    this(httpOperations, taskApi, asyncPollInterval, asyncTimeout, null);
  }

  /**
   * Creates a new ChunkOperations instance whose async operations run on the given executor.
   *
   * @param httpOperations    the HTTP operations handler for executing requests
   * @param taskApi           the task operations handler for polling and retrieving results
   * @param asyncPollInterval the interval between status polls for async operations
   * @param asyncTimeout      the maximum time to wait for async operations to complete
   * @param asyncExecutor     the executor to run async operations on, or {@code null} to use the
   *                          default async executor of {@link java.util.concurrent.CompletableFuture}
   */
  public ChunkOperations(HttpOperations httpOperations, DoclingServeTaskApi taskApi, Duration asyncPollInterval, Duration asyncTimeout, @Nullable Executor asyncExecutor) {
    super(httpOperations, taskApi, asyncPollInterval, asyncTimeout, asyncExecutor);
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
  public CompletionStage<ChunkDocumentResponse> chunkSourceWithHierarchicalChunkerAsync(HierarchicalChunkDocumentRequest request) {
    return executeAsync(request, "/v1/chunk/hierarchical/source/async");
  }

  @Override
  public CompletionStage<ChunkDocumentResponse> chunkSourceWithHybridChunkerAsync(HybridChunkDocumentRequest request) {
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
