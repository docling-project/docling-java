package ai.docling.serve.client.operations;

import java.time.Duration;
import java.util.concurrent.CompletionStage;

import ai.docling.serve.api.DoclingServeConvertApi;
import ai.docling.serve.api.DoclingServeTaskApi;
import ai.docling.serve.api.convert.request.BatchConvertDocumentRequest;
import ai.docling.serve.api.convert.request.ConvertDocumentRequest;
import ai.docling.serve.api.convert.request.target.PresignedUrlTarget;
import ai.docling.serve.api.convert.request.target.PutTarget;
import ai.docling.serve.api.convert.request.target.S3Target;
import ai.docling.serve.api.convert.request.target.ZipTarget;
import ai.docling.serve.api.convert.response.ConvertDocumentResponse;
import ai.docling.serve.api.convert.response.ZipArchiveConvertDocumentResponse;
import ai.docling.serve.api.task.request.TaskResultRequest;
import ai.docling.serve.api.task.response.TaskStatusPollResponse;
import ai.docling.serve.api.util.Utils;
import ai.docling.serve.api.util.ValidationUtils;

/**
 * Base class for document conversion API operations. Provides access to document
 * conversion functionality including both synchronous and asynchronous operations.
 */
public final class ConvertOperations extends AsyncOperations implements DoclingServeConvertApi {
  private final HttpOperations httpOperations;
  private final DoclingServeTaskApi taskApi;

  /**
   * Creates a new ConvertOperations instance.
   *
   * @param httpOperations    the HTTP operations handler for executing requests
   * @param taskApi           the task operations handler for polling and retrieving results
   * @param asyncPollInterval the interval between status polls for async operations
   * @param asyncTimeout      the maximum time to wait for async operations to complete
   */
  public ConvertOperations(HttpOperations httpOperations, DoclingServeTaskApi taskApi,
                           Duration asyncPollInterval, Duration asyncTimeout) {
    super(httpOperations, taskApi, asyncPollInterval, asyncTimeout);
    this.httpOperations = httpOperations;
    this.taskApi = taskApi;
  }

  @Override
  protected ConvertDocumentResponse getTaskResult(TaskResultRequest taskResultRequest) {
    return this.taskApi.convertTaskResult(taskResultRequest);
  }

  @Override
  public ConvertDocumentResponse convertSource(ConvertDocumentRequest request) {
    ValidationUtils.ensureNotNull(request, "request");
    final var uri = "/v1/convert/source";

    boolean hasMultipleSources = !Utils.isNullOrEmpty(request.getSources()) ?
                                 request.getSources().size() > 1: Boolean.FALSE;
    boolean isRemoteTarget = request.getTarget() instanceof S3Target || request.getTarget() instanceof PutTarget
                             || request.getTarget() instanceof PresignedUrlTarget;
    boolean isZipTarget = request.getTarget() instanceof ZipTarget;

    if((hasMultipleSources && !isRemoteTarget) || isZipTarget) {
      StreamResponse response = this.httpOperations
          .executePostWithStreamResponse(createRequestContext(uri, request,
              StreamResponse.class));
      String fileName = response.getHeaders().getFileName().orElse("converted_docs.zip");
      return ZipArchiveConvertDocumentResponse
          .builder().fileName(fileName)
          .inputStream(response.getBody())
          .build();
    } else {
      return this.httpOperations.executePost(createRequestContext(uri, request,
          ConvertDocumentResponse.class));
    }
  }

  private <I, O> RequestContext<I, O> createRequestContext(String uri, I request, Class<O> responseType) {
    return RequestContext.<I, O>builder()
        .request(request)
        .responseType(responseType)
        .uri(uri)
        .build();
  }

  @Override
  public CompletionStage<ConvertDocumentResponse> convertSourceAsync(ConvertDocumentRequest request) {
    return executeAsync(request, "/v1/convert/source/async");
  }

  @Override
  public TaskStatusPollResponse convertSourceBatch(BatchConvertDocumentRequest request) {
    ValidationUtils.ensureNotNull(request, "request");
    return this.httpOperations.executePost(
        createRequestContext("/v1/convert/source/batch", request, TaskStatusPollResponse.class));
  }

  @Override
  public CompletionStage<ConvertDocumentResponse> convertSourceBatchAsync(BatchConvertDocumentRequest request) {
    return executeAsync(request, "/v1/convert/source/batch");
  }
}
