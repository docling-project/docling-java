package ai.docling.serve.client.operations;

import java.time.Duration;
import java.util.concurrent.CompletionStage;

import ai.docling.serve.api.DoclingServeConvertApi;
import ai.docling.serve.api.DoclingServeTaskApi;
import ai.docling.serve.api.convert.request.ConvertDocumentRequest;
import ai.docling.serve.api.convert.response.ConvertDocumentResponse;
import ai.docling.serve.api.task.request.TaskResultRequest;
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
   * @param httpOperations the HTTP operations handler for executing requests
   * @param taskApi the task operations handler for polling and retrieving results
   * @param asyncPollInterval the interval between status polls for async operations
   * @param asyncTimeout the maximum time to wait for async operations to complete
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
    return this.httpOperations.executePost(createRequestContext("/v1/convert/source", request));
  }

  private <I> RequestContext<I, ConvertDocumentResponse> createRequestContext(String uri, I request) {
    return RequestContext.<I, ConvertDocumentResponse>builder()
        .request(request)
        .responseType(ConvertDocumentResponse.class)
        .uri(uri)
        .build();
  }

  @Override
  public CompletionStage<ConvertDocumentResponse> convertSourceAsync(ConvertDocumentRequest request) {
    return executeAsync(request, "/v1/convert/source/async");
  }
}
