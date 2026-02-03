package ai.docling.serve.client;

import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.matchingJsonPath;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.awaitility.Awaitility.await;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.atomic.AtomicReference;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.extension.TestWatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;

import ai.docling.core.DoclingDocument;
import ai.docling.core.DoclingDocument.DocItemLabel;
import ai.docling.serve.api.DoclingServeApi;
import ai.docling.serve.api.chunk.request.HierarchicalChunkDocumentRequest;
import ai.docling.serve.api.chunk.request.HybridChunkDocumentRequest;
import ai.docling.serve.api.chunk.request.options.HierarchicalChunkerOptions;
import ai.docling.serve.api.chunk.request.options.HybridChunkerOptions;
import ai.docling.serve.api.chunk.response.Chunk;
import ai.docling.serve.api.chunk.response.ChunkDocumentResponse;
import ai.docling.serve.api.clear.request.ClearConvertersRequest;
import ai.docling.serve.api.clear.request.ClearResultsRequest;
import ai.docling.serve.api.clear.response.ClearResponse;
import ai.docling.serve.api.convert.request.ConvertDocumentRequest;
import ai.docling.serve.api.convert.request.options.ConvertDocumentOptions;
import ai.docling.serve.api.convert.request.options.OutputFormat;
import ai.docling.serve.api.convert.request.options.TableFormerMode;
import ai.docling.serve.api.convert.request.source.HttpSource;
import ai.docling.serve.api.convert.request.source.S3Source;
import ai.docling.serve.api.convert.request.target.S3Target;
import ai.docling.serve.api.convert.response.ConvertDocumentResponse;
import ai.docling.serve.api.health.HealthCheckResponse;
import ai.docling.serve.api.task.request.TaskResultRequest;
import ai.docling.serve.api.task.request.TaskStatusPollRequest;
import ai.docling.serve.api.task.response.TaskStatus;
import ai.docling.serve.api.task.response.TaskStatusPollResponse;
import ai.docling.serve.api.validation.ValidationError;
import ai.docling.serve.api.validation.ValidationErrorContext;
import ai.docling.serve.api.validation.ValidationErrorDetail;
import ai.docling.serve.api.validation.ValidationException;
import ai.docling.serve.client.DoclingServeClient.DoclingServeClientBuilder;
import ai.docling.testcontainers.serve.DoclingServeContainer;
import ai.docling.testcontainers.serve.config.DoclingServeContainerConfig;

abstract class AbstractDoclingServeClientTests {
  private static final Logger LOG = LoggerFactory.getLogger(AbstractDoclingServeClientTests.class);

  protected static final DoclingServeContainer doclingContainer = new DoclingServeContainer(
      DoclingServeContainerConfig.builder()
          .image(DoclingServeContainerConfig.DOCLING_IMAGE)
          .enableUi(true)
          .build()
  );

  // Used to output the container logs on a test failure
  // This could be useful when debugging
  @RegisterExtension
  TestWatcher watcher = new TestWatcher() {
    @Override
    public void testFailed(ExtensionContext context, @Nullable Throwable cause) {
      var message = """
          Test %s.%s failed with message: %s
          Container logs:
          %s
          """.formatted(
          getClass().getName(),
          context.getTestMethod().map(Method::getName).orElse(""),
          Optional.ofNullable(cause).map(Throwable::getMessage).orElse(""),
          doclingContainer.getLogs());

      LOG.error(message);
    }
  };

  @RegisterExtension
  static WireMockExtension wireMockExtension = WireMockExtension.newInstance()
      .options(wireMockConfig().dynamicPort())
      .configureStaticDsl(true)
      .failOnUnmatchedRequests(true)
      .resetOnEachTest(true)
      .build();

  static {
    doclingContainer.start();
  }

  protected abstract WireMockServer getWiremockServer();
  protected abstract DoclingServeApi getDoclingClient(boolean requiresAuth, boolean useWiremock);

  protected DoclingServeApi getDoclingClient(boolean requiresAuth) {
    return getDoclingClient(requiresAuth, false);
  }

  protected DoclingServeApi getDoclingClient() {
    return getDoclingClient(true, false);
  }

  private <T> T readValue(String json, Class<T> valueType) {
    return ((DoclingServeClient) getDoclingClient()).readValue(json, valueType);
  }

  private <T> String writeValueAsString(T value) {
    return ((DoclingServeClient) getDoclingClient()).writeValueAsString(value);
  }

  @Test
  void builderWorks() {
    var clientBuilder = DoclingServeApi.builder()
        .logRequests()
        .logResponses()
        .prettyPrint()
        .baseUrl(doclingContainer.getApiUrl());

    assertThat(clientBuilder)
        .isNotNull()
        .isInstanceOf(DoclingServeClientBuilder.class);

    assertThat(clientBuilder.build())
        .isNotNull()
        .isInstanceOf(DoclingServeClient.class);
  }

  @Test
  void pathPartFromBaseUrl() {
    // From https://github.com/docling-project/docling-java/issues/294
    var wiremockRuntimeInfo = wireMockExtension.getRuntimeInfo();
    wireMockExtension.stubFor(
        get(urlPathEqualTo("/path/health"))
            .withHeader("Accept", equalTo("application/json"))
            .willReturn(okJson("{\"status\": \"ok\"}"))
    );

    var baseUrl = "http://localhost:%d/path".formatted(wiremockRuntimeInfo.getHttpPort());

    var client = DoclingServeApi.builder()
        .logRequests()
        .logResponses()
        .prettyPrint()
        .baseUrl(baseUrl)
        .connectTimeout(Duration.ofSeconds(10))
        .readTimeout(Duration.ofSeconds(45))
        .build();

    assertThat(client.health())
        .isNotNull()
        .extracting(HealthCheckResponse::getStatus)
        .isEqualTo("ok");

    verify(
        1,
        getRequestedFor(urlPathEqualTo("/path/health"))
            .withHeader("Accept", equalTo("application/json"))
    );
  }

  @Nested
  class ClearTests {
    @Test
    void shouldClearConvertersSuccessfully() {
      var response = getDoclingClient().clearConverters(ClearConvertersRequest.builder().build());

      assertThat(response)
          .isNotNull()
          .extracting(ClearResponse::getStatus)
          .isEqualTo("ok");
    }

    @Test
    void shouldClearResultsSuccessfully() {
      var response = getDoclingClient().clearResults(ClearResultsRequest.builder().build());

      assertThat(response)
          .isNotNull()
          .extracting(ClearResponse::getStatus)
          .isEqualTo("ok");
    }
  }

  @Nested
  class TaskTests {
    @Test
    void pollInvalidTaskId() {
      var request = TaskStatusPollRequest.builder()
          .taskId("someInvalidTaskId")
          .build();

      assertThatThrownBy(() -> getDoclingClient().pollTaskStatus(request))
          .isInstanceOf(DoclingServeClientException.class)
          .hasMessage("An error occurred: {\"detail\":\"Task not found.\"}");
    }

    @Test
    void convertUrlTaskResult() throws IOException, InterruptedException {
      var request = TaskResultRequest.builder()
          .taskId(doPollForTaskCompletion().getTaskId())
          .build();

      var result = getDoclingClient().convertTaskResult(request);
      ConvertTests.assertConvertHttpSource(result);
    }

    @Test
    void pollConvertUrlTask() throws IOException, InterruptedException {
      doPollForTaskCompletion();
    }

    private TaskStatusPollResponse doPollForTaskCompletion() throws IOException, InterruptedException {
      var response = submitTask();
      var pollRequest = TaskStatusPollRequest.builder()
          .taskId(response.getTaskId())
          .build();

      var doclingClient = getDoclingClient();
      var taskPollResponse = new AtomicReference<>(doclingClient.pollTaskStatus(pollRequest));

      assertThat(taskPollResponse).isNotNull();
      assertThat(taskPollResponse.get())
          .isNotNull()
          .extracting(
              TaskStatusPollResponse::getTaskId,
              TaskStatusPollResponse::getTaskStatus,
              TaskStatusPollResponse::getTaskType
          )
          .allMatch(Objects::nonNull);

      assertThat(taskPollResponse.get())
          .extracting(
              TaskStatusPollResponse::getTaskId,
              TaskStatusPollResponse::getTaskType
          )
          .containsExactly(
              response.getTaskId(),
              response.getTaskType()
          );

      if (taskPollResponse.get().getTaskStatus() != TaskStatus.SUCCESS) {
        await()
            .atMost(Duration.ofMinutes(1))
            .pollDelay(Duration.ofSeconds(5))
            .pollInterval(Duration.ofSeconds(5))
            .logging(LoggerFactory.getLogger("org.awaitility")::info)
            .until(() -> {
              taskPollResponse.set(doclingClient.pollTaskStatus(pollRequest));
              return taskPollResponse.get().getTaskStatus() == TaskStatus.SUCCESS;
            });
      }

      assertThat(taskPollResponse.get().getTaskStatus()).isEqualTo(TaskStatus.SUCCESS);

      return taskPollResponse.get();
    }

    // @TODO The async api isn't here yet, so we have to do something on our own for now in these tests.
    // Once https://github.com/docling-project/docling-java/issues/127 is implemented then these methods below
    // Can be switched to use that API for making the calls
    private TaskStatusPollResponse submitTask() throws IOException, InterruptedException {
      var request = ConvertDocumentRequest.builder()
          .source(HttpSource.builder().url(URI.create("https://docs.arconia.io/arconia-cli/latest/development/dev/")).build())
          .build();

      var httpRequest = HttpRequest.newBuilder()
          .uri(URI.create("%s/v1/convert/source/async".formatted(doclingContainer.getApiUrl())))
          .header("Content-Type", "application/json")
          .header("Accept", "application/json")
          .POST(new LoggingBodyPublisher<>(request))
          .build();

      logRequest(httpRequest);

      var httpClient = HttpClient.newBuilder()
          .followRedirects(Redirect.NORMAL)
          .version(Version.HTTP_1_1)
          .build();

      var httpResponse = httpClient.send(httpRequest, BodyHandlers.ofString());
      var body = httpResponse.body();

      logResponse(httpResponse, Optional.ofNullable(body));

      var statusCode = httpResponse.statusCode();

      if (statusCode >= 400) {
        // Handle errors
        // The Java HTTPClient doesn't throw exceptions on error codes
        throw new DoclingServeClientException("An error occurred: %s".formatted(body), statusCode, body);
      }

      var response = readValue(body, TaskStatusPollResponse.class);

      assertThat(response)
          .isNotNull()
          .extracting(
              TaskStatusPollResponse::getTaskId,
              TaskStatusPollResponse::getTaskPosition,
              TaskStatusPollResponse::getTaskStatus,
              TaskStatusPollResponse::getTaskType
          )
          .allMatch(Objects::nonNull);

      assertThat(response.getTaskType()).isEqualTo("convert");

      return response;
    }

    private static void logRequest(HttpRequest request) {
      var stringBuilder = new StringBuilder();
      stringBuilder.append("\n→ REQUEST: %s %s\n".formatted(request.method(), request.uri()));
      stringBuilder.append("  HEADERS:\n");

      request.headers().map().forEach((key, values) ->
          stringBuilder.append("  %s: %s\n".formatted(key, String.join(", ", values)))
      );

      LOG.info(stringBuilder.toString());
    }

    private void logResponse(HttpResponse<String> response, Optional<String> responseBody) {
      var stringBuilder = new StringBuilder();
      stringBuilder.append("\n← RESPONSE: %s\n".formatted(response.statusCode()));
      stringBuilder.append("  HEADERS:\n");

      response.headers().map().forEach((key, values) ->
          stringBuilder.append("  %s: %s\n".formatted(key, String.join(", ", values)))
      );

      responseBody
          .map(body -> writeValueAsString(readValue(body, Object.class)))
          .ifPresent(body -> stringBuilder.append("  BODY:\n%s".formatted(body)));
      LOG.info(stringBuilder.toString());
    }

    private class LoggingBodyPublisher<T> implements BodyPublisher {
      private final BodyPublisher delegate;
      private final String stringContent;

      private LoggingBodyPublisher(T content) {
        this.stringContent = writeValueAsString(content);
        this.delegate = BodyPublishers.ofString(this.stringContent);
      }

      @Override
      public long contentLength() {
        return this.delegate.contentLength();
      }

      @Override
      public void subscribe(Subscriber<? super ByteBuffer> subscriber) {
        LOG.info("→ REQUEST BODY: \n{}", this.stringContent);
        this.delegate.subscribe(subscriber);
      }
    }
  }

  @Nested
  class HealthTests {
    @Test
    void shouldSuccessfullyCallHealthEndpoint() {
      HealthCheckResponse response = getDoclingClient(false).health();

      assertThat(response)
          .isNotNull()
          .extracting(HealthCheckResponse::getStatus)
          .isEqualTo("ok");
    }
  }

  @Nested
  class ConvertTests {
    static void assertConvertHttpSource(ConvertDocumentResponse response) {
      assertThat(response).isNotNull();
      assertThat(response.getStatus()).isNotEmpty();
      assertThat(response.getDocument()).isNotNull();
      assertThat(response.getDocument().getFilename()).isNotEmpty();

      if (response.getProcessingTime() != null) {
        assertThat(response.getProcessingTime()).isPositive();
      }

      assertThat(response.getDocument().getMarkdownContent()).isNotEmpty();
    }

    @Test
    void shouldThrowValidationError() {
        var file = Path.of("src", "test", "resources", "story.pdf");

        assertThat(file)
                .exists()
                .isRegularFile();

        var source = HttpSource.builder()
                .url(file.toUri())
                .build();

        var options = ConvertDocumentOptions.builder()
                .toFormat(OutputFormat.MARKDOWN)
                .build();

        var request = ConvertDocumentRequest.builder()
            .source(source)
            .options(options)
            .build();

        assertThatThrownBy(() -> getDoclingClient().convertSource(request))
            .isInstanceOf(ValidationException.class)
            .hasMessageStartingWith("An error occurred while making POST request to ")
            .asInstanceOf(InstanceOfAssertFactories.throwable(ValidationException.class))
            .extracting(ValidationException::getValidationError)
            .isNotNull()
            .extracting(ValidationError::getErrorDetails)
            .asInstanceOf(InstanceOfAssertFactories.list(ValidationErrorDetail.class))
            .singleElement()
            .usingRecursiveComparison()
            .isEqualTo(
              ValidationErrorDetail.builder()
                  .type("url_scheme")
                  .message("URL scheme should be 'http' or 'https'")
                  .locations(List.of("body", "sources", 0, "http", "url"))
                  .input(file.toUri().toString())
                  .context(
                      ValidationErrorContext.builder()
                          .expectedSchemes("'http' or 'https'")
                          .build()
                  ).build()
            );
    }

    @Test
    void shouldConvertS3SourceSuccessfully() {
      // Need to use Wiremock here rather than a "real" backend because Docling Serve requires kubeflow
      // See https://github.com/docling-project/docling-serve/issues/462
      var request = ConvertDocumentRequest.builder()
          .source(
              S3Source.builder()
                  .endpoint("source-s3-endpoint")
                  .bucket("source-bucket")
                  .accessKey("source-access-key")
                  .secretKey("source-secret-key")
                  .verifySsl(false)
                  .build()
          )
          .target(
              S3Target.builder()
                  .endpoint("target-s3-endpoint")
                  .bucket("target-bucket")
                  .accessKey("target-access-key")
                  .secretKey("target-secret-key")
                  .verifySsl(false)
                  .build()
          ).build();

      var wireMockServer = getWiremockServer();

      wireMockServer.stubFor(
          post("/v1/convert/source")
              .withRequestBody(equalToJson(writeValueAsString(request)))
              .withHeader("Content-Type", equalTo("application/json"))
              .withHeader("Accept", equalTo("application/json"))
              .willReturn(okJson("{}"))
      );

      var response = getDoclingClient(false, true).convertSource(request);
      assertThat(response).isNotNull();

      wireMockServer.verify(
          1,
          postRequestedFor(urlPathEqualTo("/v1/convert/source"))
              .withHeader("Content-Type", equalTo("application/json"))
              .withRequestBody(
                  matchingJsonPath("$.sources[0].kind", equalTo("s3"))
                      .and(matchingJsonPath("$.sources[0].endpoint", equalTo("source-s3-endpoint")))
                      .and(matchingJsonPath("$.sources[0].bucket", equalTo("source-bucket")))
                      .and(matchingJsonPath("$.sources[0].access_key", equalTo("source-access-key")))
                      .and(matchingJsonPath("$.sources[0].secret_key", equalTo("source-secret-key")))
                      .and(matchingJsonPath("$.sources[0].verify_ssl", equalTo("false")))
                      .and(matchingJsonPath("$.target.kind", equalTo("s3")))
                      .and(matchingJsonPath("$.target.endpoint", equalTo("target-s3-endpoint")))
                      .and(matchingJsonPath("$.target.bucket", equalTo("target-bucket")))
                      .and(matchingJsonPath("$.target.access_key", equalTo("target-access-key")))
                      .and(matchingJsonPath("$.target.secret_key", equalTo("target-secret-key")))
                      .and(matchingJsonPath("$.target.verify_ssl", equalTo("false")))
              )
      );
    }

    @Test
    void shouldConvertHttpSourceSuccessfully() {
      var request = ConvertDocumentRequest.builder()
          .source(HttpSource.builder().url(URI.create("https://docs.arconia.io/arconia-cli/latest/development/dev/")).build())
          .build();

      var response = getDoclingClient().convertSource(request);
      assertConvertHttpSource(response);
    }

    @Test
    void shouldConvertFileSuccessfully() {
      var response = getDoclingClient().convertFiles(Path.of("src", "test", "resources", "story.pdf"));

      assertThat(response).isNotNull();
      assertThat(response.getStatus()).isNotEmpty();
      assertThat(response.getDocument()).isNotNull();
      assertThat(response.getDocument().getFilename()).isEqualTo("story.pdf");

      if (response.getProcessingTime()!=null) {
        assertThat(response.getProcessingTime()).isPositive();
      }

      assertThat(response.getDocument().getMarkdownContent()).isNotEmpty();
    }

    @Test
    void shouldHandleConversionWithDifferentDocumentOptions() {
      var options = ConvertDocumentOptions.builder()
          .doOcr(true)
          .includeImages(true)
          .tableMode(TableFormerMode.FAST)
          .documentTimeout(Duration.ofMinutes(1))
          .build();

      var request = ConvertDocumentRequest.builder()
          .source(HttpSource.builder().url(URI.create("https://docs.arconia.io/arconia-cli/latest/development/dev/")).build())
          .options(options)
          .build();

      var response = getDoclingClient().convertSource(request);

      assertThat(response).isNotNull();
      assertThat(response.getStatus()).isNotEmpty();
      assertThat(response.getDocument()).isNotNull();
    }

    @Test
    void shouldHandleResponseWithDoclingDocument() {
      ConvertDocumentOptions options = ConvertDocumentOptions.builder()
          .toFormat(OutputFormat.JSON)
          .build();

      ConvertDocumentRequest request = ConvertDocumentRequest.builder()
          .source(HttpSource.builder().url(URI.create("https://docs.arconia.io/arconia-cli/latest/development/dev/")).build())
          .options(options)
          .build();

      ConvertDocumentResponse response = getDoclingClient().convertSource(request);

      assertThat(response).isNotNull();
      assertThat(response.getStatus()).isNotEmpty();
      assertThat(response.getDocument()).isNotNull();

      DoclingDocument doclingDocument = response.getDocument().getJsonContent();
      assertThat(doclingDocument).isNotNull();
      assertThat(doclingDocument.getName()).isNotEmpty();
      assertThat(doclingDocument.getTexts().get(0).getLabel()).isEqualTo(DocItemLabel.TITLE);
    }

    @Test
    void shouldConvertSourceAsync() {
      ConvertDocumentRequest request = ConvertDocumentRequest.builder()
          .source(HttpSource.builder().url(URI.create("https://docs.arconia.io/arconia-cli/latest/development/dev/")).build())
          .build();

      ConvertDocumentResponse response = getDoclingClient().convertSourceAsync(request).toCompletableFuture().join();

      assertThat(response).isNotNull();
      assertThat(response.getStatus()).isNotEmpty();
      assertThat(response.getDocument()).isNotNull();
      assertThat(response.getDocument().getMarkdownContent()).isNotEmpty();
    }

    @Test
    void shouldConvertFileAsync() {
      ConvertDocumentResponse response = getDoclingClient().convertFilesAsync(Path.of("src", "test", "resources", "story.pdf")).toCompletableFuture().join();

      assertThat(response).isNotNull();
      assertThat(response.getStatus()).isNotEmpty();
      assertThat(response.getDocument()).isNotNull();
      assertThat(response.getDocument().getFilename()).isEqualTo("story.pdf");
      assertThat(response.getDocument().getMarkdownContent()).isNotEmpty();
    }

    @Test
    void shouldHandleAsyncConversionWithDifferentDocumentOptions() {
      ConvertDocumentOptions options = ConvertDocumentOptions.builder()
          .doOcr(true)
          .includeImages(true)
          .tableMode(TableFormerMode.FAST)
          .documentTimeout(Duration.ofMinutes(1))
          .build();

      ConvertDocumentRequest request = ConvertDocumentRequest.builder()
          .source(HttpSource.builder().url(URI.create("https://docs.arconia.io/arconia-cli/latest/development/dev/")).build())
          .options(options)
          .build();

      ConvertDocumentResponse response = getDoclingClient().convertSourceAsync(request).toCompletableFuture().join();

      assertThat(response).isNotNull();
      assertThat(response.getStatus()).isNotEmpty();
      assertThat(response.getDocument()).isNotNull();
    }

    @Test
    void shouldChainAsyncOperations() {
      ConvertDocumentRequest request = ConvertDocumentRequest.builder()
          .source(HttpSource.builder().url(URI.create("https://docs.arconia.io/arconia-cli/latest/development/dev/")).build())
          .build();

      // Test chaining with thenApply
      String markdownContent = getDoclingClient().convertSourceAsync(request)
          .thenApply(response -> response.getDocument().getMarkdownContent())
          .toCompletableFuture().join();

      assertThat(markdownContent).isNotEmpty();
    }

    @Test
    void convertFilesNullFiles() {
      assertThatExceptionOfType(IllegalArgumentException.class)
          .isThrownBy(() -> getDoclingClient().convertFiles())
          .withMessage("files cannot be null or empty");
    }

    @Test
    void convertFilesEmptyFiles() {
      assertThatExceptionOfType(IllegalArgumentException.class)
          .isThrownBy(() -> getDoclingClient().convertFiles(new Path[] {}))
          .withMessage("files cannot be null or empty");
    }

    @Test
    void convertNonExistentFile() {
      assertThatExceptionOfType(IllegalArgumentException.class)
          .isThrownBy(() -> getDoclingClient().convertFiles(Path.of("src", "test", "resources", "file1234.pdf")))
          .withMessage("File (src/test/resources/file1234.pdf) does not exist");
    }

    @Test
    void convertFilesNotRegularFile() {
      assertThatExceptionOfType(IllegalArgumentException.class)
          .isThrownBy(() -> getDoclingClient().convertFiles(Path.of("src", "test", "resources")))
          .withMessage("File (src/test/resources) is not a regular file");
    }

    @Test
    void convertFilesAsyncNullFiles() {
      assertThatExceptionOfType(IllegalArgumentException.class)
          .isThrownBy(() -> getDoclingClient().convertFilesAsync())
          .withMessage("files cannot be null or empty");
    }

    @Test
    void convertFilesAsyncEmptyFiles() {
      assertThatExceptionOfType(IllegalArgumentException.class)
          .isThrownBy(() -> getDoclingClient().convertFilesAsync(new Path[] {}))
          .withMessage("files cannot be null or empty");
    }

    @Test
    void convertAsyncNonExistentFile() {
      assertThatExceptionOfType(IllegalArgumentException.class)
          .isThrownBy(() -> getDoclingClient().convertFilesAsync(Path.of("src", "test", "resources", "file1234.pdf")))
          .withMessage("File (src/test/resources/file1234.pdf) does not exist");
    }

    @Test
    void convertAsyncFilesNotRegularFile() {
      assertThatExceptionOfType(IllegalArgumentException.class)
          .isThrownBy(() -> getDoclingClient().convertFilesAsync(Path.of("src", "test", "resources")))
          .withMessage("File (src/test/resources) is not a regular file");
    }
  }

  @Nested
  class ChunkTests {
    @Test
    void chunkHierarchicalNonExistentFile() {
      assertThatExceptionOfType(IllegalArgumentException.class)
          .isThrownBy(() -> getDoclingClient().chunkFilesWithHierarchicalChunker(Path.of("src", "test", "resources", "file1234.pdf")))
          .withMessage("File (src/test/resources/file1234.pdf) does not exist");
    }

    @Test
    void chunkHierarchicalFilesNotRegularFile() {
      assertThatExceptionOfType(IllegalArgumentException.class)
          .isThrownBy(() -> getDoclingClient().chunkFilesWithHierarchicalChunker(Path.of("src", "test", "resources")))
          .withMessage("File (src/test/resources) is not a regular file");
    }

    @Test
    void chunkFilesHierarchicalNullFiles() {
      assertThatExceptionOfType(IllegalArgumentException.class)
          .isThrownBy(() -> getDoclingClient().chunkFilesWithHierarchicalChunker())
          .withMessage("files cannot be null or empty");
    }

    @Test
    void chunkFilesHierarchicalEmptyFiles() {
      assertThatExceptionOfType(IllegalArgumentException.class)
          .isThrownBy(() -> getDoclingClient().chunkFilesWithHierarchicalChunker(new Path[] {}))
          .withMessage("files cannot be null or empty");
    }

    @Test
    void chunkHierarchicalAsyncNonExistentFile() {
      assertThatExceptionOfType(IllegalArgumentException.class)
          .isThrownBy(() -> getDoclingClient().chunkFilesWithHierarchicalChunkerAsync(Path.of("src", "test", "resources", "file1234.pdf")))
          .withMessage("File (src/test/resources/file1234.pdf) does not exist");
    }

    @Test
    void chunkHierarchicalAsyncFilesNotRegularFile() {
      assertThatExceptionOfType(IllegalArgumentException.class)
          .isThrownBy(() -> getDoclingClient().chunkFilesWithHierarchicalChunkerAsync(Path.of("src", "test", "resources")))
          .withMessage("File (src/test/resources) is not a regular file");
    }

    @Test
    void chunkFilesHierarchicalAsyncNullFiles() {
      assertThatExceptionOfType(IllegalArgumentException.class)
          .isThrownBy(() -> getDoclingClient().chunkFilesWithHierarchicalChunkerAsync())
          .withMessage("files cannot be null or empty");
    }

    @Test
    void chunkFilesHierarchicalAsyncEmptyFiles() {
      assertThatExceptionOfType(IllegalArgumentException.class)
          .isThrownBy(() -> getDoclingClient().chunkFilesWithHierarchicalChunkerAsync(new Path[] {}))
          .withMessage("files cannot be null or empty");
    }

    @Test
    void chunkHybridNonExistentFile() {
      assertThatExceptionOfType(IllegalArgumentException.class)
          .isThrownBy(() -> getDoclingClient().chunkFilesWithHybridChunker(Path.of("src", "test", "resources", "file1234.pdf")))
          .withMessage("File (src/test/resources/file1234.pdf) does not exist");
    }

    @Test
    void chunkHybridFilesNotRegularFile() {
      assertThatExceptionOfType(IllegalArgumentException.class)
          .isThrownBy(() -> getDoclingClient().chunkFilesWithHybridChunker(Path.of("src", "test", "resources")))
          .withMessage("File (src/test/resources) is not a regular file");
    }

    @Test
    void chunkHybridAsyncNonExistentFile() {
      assertThatExceptionOfType(IllegalArgumentException.class)
          .isThrownBy(() -> getDoclingClient().chunkFilesWithHybridChunkerAsync(Path.of("src", "test", "resources", "file1234.pdf")))
          .withMessage("File (src/test/resources/file1234.pdf) does not exist");
    }

    @Test
    void chunkHybridAsyncFilesNotRegularFile() {
      assertThatExceptionOfType(IllegalArgumentException.class)
          .isThrownBy(() -> getDoclingClient().chunkFilesWithHybridChunkerAsync(Path.of("src", "test", "resources")))
          .withMessage("File (src/test/resources) is not a regular file");
    }

    @Test
    void chunkFilesHybridNullFiles() {
      assertThatExceptionOfType(IllegalArgumentException.class)
          .isThrownBy(() -> getDoclingClient().chunkFilesWithHybridChunker())
          .withMessage("files cannot be null or empty");
    }

    @Test
    void chunkFilesHybridEmptyFiles() {
      assertThatExceptionOfType(IllegalArgumentException.class)
          .isThrownBy(() -> getDoclingClient().chunkFilesWithHybridChunker(new Path[] {}))
          .withMessage("files cannot be null or empty");
    }

    @Test
    void chunkFilesHybridAsyncNullFiles() {
      assertThatExceptionOfType(IllegalArgumentException.class)
          .isThrownBy(() -> getDoclingClient().chunkFilesWithHybridChunkerAsync())
          .withMessage("files cannot be null or empty");
    }

    @Test
    void chunkFilesHybridAsyncEmptyFiles() {
      assertThatExceptionOfType(IllegalArgumentException.class)
          .isThrownBy(() -> getDoclingClient().chunkFilesWithHybridChunkerAsync(new Path[] {}))
          .withMessage("files cannot be null or empty");
    }

    @Test
    void shouldChainHybridAsyncOperations() {
      var options = ConvertDocumentOptions.builder()
          .toFormat(OutputFormat.JSON)
          .build();

      var request = HybridChunkDocumentRequest.builder()
          .source(HttpSource.builder().url(URI.create("https://docs.arconia.io/arconia-cli/latest/development/dev/")).build())
          .options(options)
          .includeConvertedDoc(true)
          .chunkingOptions(HybridChunkerOptions.builder()
              .includeRawText(true)
              .useMarkdownTables(true)
              .maxTokens(10000)
              .tokenizer("sentence-transformers/all-MiniLM-L6-v2")
              .build())
          .build();

      // Test chaining with thenApply
      var chunks = getDoclingClient().chunkSourceWithHybridChunkerAsync(request)
          .thenApply(ChunkDocumentResponse::getChunks)
          .toCompletableFuture().join();

      assertThat(chunks)
          .isNotEmpty()
          .allMatch(chunk -> !chunk.getText().isEmpty());
    }

    @Test
    void shouldChainHierarchicalAsyncOperations() {
      var options = ConvertDocumentOptions.builder()
          .toFormat(OutputFormat.JSON)
          .build();

      var request = HierarchicalChunkDocumentRequest.builder()
          .source(HttpSource.builder().url(URI.create("https://docs.arconia.io/arconia-cli/latest/development/dev/")).build())
          .options(options)
          .includeConvertedDoc(true)
          .chunkingOptions(HierarchicalChunkerOptions.builder()
              .includeRawText(true)
              .useMarkdownTables(true)
              .build())
          .build();

      // Test chaining with thenApply
      var chunks = getDoclingClient().chunkSourceWithHierarchicalChunkerAsync(request)
          .thenApply(ChunkDocumentResponse::getChunks)
          .toCompletableFuture().join();

      assertThat(chunks)
          .isNotEmpty()
          .allMatch(chunk -> !chunk.getText().isEmpty());
    }

    @Test
    void shouldChunkDocumentWithHierarchicalChunkerAsync() {
      ConvertDocumentOptions options = ConvertDocumentOptions.builder()
          .toFormat(OutputFormat.JSON)
          .build();

      HierarchicalChunkDocumentRequest request = HierarchicalChunkDocumentRequest.builder()
          .source(HttpSource.builder().url(URI.create("https://docs.arconia.io/arconia-cli/latest/development/dev/")).build())
          .options(options)
          .includeConvertedDoc(true)
          .chunkingOptions(HierarchicalChunkerOptions.builder()
              .includeRawText(true)
              .useMarkdownTables(true)
              .build())
          .build();

      ChunkDocumentResponse response = getDoclingClient().chunkSourceWithHierarchicalChunkerAsync(request).toCompletableFuture().join();

      assertThat(response).isNotNull();
      assertThat(response.getChunks()).isNotEmpty();
      assertThat(response.getDocuments()).isNotEmpty();
      assertThat(response.getProcessingTime()).isNotNull();

      List<Chunk> chunks = response.getChunks();
      assertThat(chunks).allMatch(chunk -> !chunk.getText().isEmpty());
    }

    @Test
    void shouldChunkFilesWithHierarchicalChunker() {
      var options = ConvertDocumentOptions.builder()
          .toFormat(OutputFormat.JSON)
          .build();

      var request = HierarchicalChunkDocumentRequest.builder()
          .options(options)
          .includeConvertedDoc(true)
          .chunkingOptions(HierarchicalChunkerOptions.builder()
              .includeRawText(true)
              .useMarkdownTables(true)
              .build())
          .build();

      var response = getDoclingClient().chunkFilesWithHierarchicalChunker(request, Path.of("src", "test", "resources", "story.pdf"));

      assertThat(response).isNotNull();
      assertThat(response.getChunks()).isNotEmpty();
      assertThat(response.getDocuments()).isNotEmpty();
      assertThat(response.getProcessingTime()).isNotNull();

      List<Chunk> chunks = response.getChunks();
      assertThat(chunks).allMatch(chunk -> !chunk.getText().isEmpty());
    }

    @Test
    void shouldChunkFilesWithHierarchicalChunkerAsync() {
      var options = ConvertDocumentOptions.builder()
          .toFormat(OutputFormat.JSON)
          .build();

      var request = HierarchicalChunkDocumentRequest.builder()
          .options(options)
          .includeConvertedDoc(true)
          .chunkingOptions(HierarchicalChunkerOptions.builder()
              .includeRawText(true)
              .useMarkdownTables(true)
              .build())
          .build();

      var response = getDoclingClient().chunkFilesWithHierarchicalChunkerAsync(request, Path.of("src", "test", "resources", "story.pdf")).toCompletableFuture().join();

      assertThat(response).isNotNull();
      assertThat(response.getChunks()).isNotEmpty();
      assertThat(response.getDocuments()).isNotEmpty();
      assertThat(response.getProcessingTime()).isNotNull();

      List<Chunk> chunks = response.getChunks();
      assertThat(chunks).allMatch(chunk -> !chunk.getText().isEmpty());
    }

    @Test
    void shouldChunkDocumentWithHybridChunkerAsync() {
      ConvertDocumentOptions options = ConvertDocumentOptions.builder()
          .toFormat(OutputFormat.JSON)
          .build();

      HybridChunkDocumentRequest request = HybridChunkDocumentRequest.builder()
          .source(HttpSource.builder().url(URI.create("https://docs.arconia.io/arconia-cli/latest/development/dev/")).build())
          .options(options)
          .includeConvertedDoc(true)
          .chunkingOptions(HybridChunkerOptions.builder()
              .includeRawText(true)
              .useMarkdownTables(true)
              .maxTokens(10000)
              .tokenizer("sentence-transformers/all-MiniLM-L6-v2")
              .build())
          .build();

      ChunkDocumentResponse response = getDoclingClient().chunkSourceWithHybridChunkerAsync(request).toCompletableFuture().join();

      assertThat(response).isNotNull();
      assertThat(response.getChunks()).isNotEmpty();
      assertThat(response.getDocuments()).isNotEmpty();
      assertThat(response.getProcessingTime()).isNotNull();

      List<Chunk> chunks = response.getChunks();
      assertThat(chunks).allMatch(chunk -> !chunk.getText().isEmpty());
    }

    @Test
    void shouldThrowValidationErrorHierarchicalChunker() {
        var file = Path.of("src", "test", "resources", "story.pdf");

        assertThat(file)
                .exists()
                .isRegularFile();

        var source = HttpSource.builder()
                .url(file.toUri())
                .build();

        var options = ConvertDocumentOptions.builder()
                .toFormat(OutputFormat.MARKDOWN)
                .build();

        var request = HierarchicalChunkDocumentRequest.builder()
            .source(source)
            .options(options)
            .build();

        assertThatThrownBy(() -> getDoclingClient().chunkSourceWithHierarchicalChunker(request))
            .isInstanceOf(ValidationException.class)
            .hasMessageStartingWith("An error occurred while making POST request to ")
            .asInstanceOf(InstanceOfAssertFactories.throwable(ValidationException.class))
            .extracting(ValidationException::getValidationError)
            .isNotNull()
            .extracting(ValidationError::getErrorDetails)
            .asInstanceOf(InstanceOfAssertFactories.list(ValidationErrorDetail.class))
            .singleElement()
            .usingRecursiveComparison()
            .isEqualTo(
              ValidationErrorDetail.builder()
                  .type("url_scheme")
                  .message("URL scheme should be 'http' or 'https'")
                  .locations(List.of("body", "sources", 0, "http", "url"))
                  .input(file.toUri().toString())
                  .context(
                      ValidationErrorContext.builder()
                          .expectedSchemes("'http' or 'https'")
                          .build()
                  ).build()
            );
    }

    @Test
    void shouldChunkDocumentWithHierarchicalChunker() {
      ConvertDocumentOptions options = ConvertDocumentOptions.builder()
          .toFormat(OutputFormat.JSON)
          .build();

      HierarchicalChunkDocumentRequest request = HierarchicalChunkDocumentRequest.builder()
          .source(HttpSource.builder().url(URI.create("https://docs.arconia.io/arconia-cli/latest/development/dev/")).build())
          .options(options)
          .includeConvertedDoc(true)
          .chunkingOptions(HierarchicalChunkerOptions.builder()
              .includeRawText(true)
              .useMarkdownTables(true)
              .build())
          .build();

      ChunkDocumentResponse response = getDoclingClient().chunkSourceWithHierarchicalChunker(request);

      assertThat(response).isNotNull();
      assertThat(response.getChunks()).isNotEmpty();
      assertThat(response.getDocuments()).isNotEmpty();
      assertThat(response.getProcessingTime()).isNotNull();

      List<Chunk> chunks = response.getChunks();
      assertThat(chunks).allMatch(chunk -> !chunk.getText().isEmpty());
    }

    @Test
    void shouldThrowValidationErrorHybridChunker() {
        var file = Path.of("src", "test", "resources", "story.pdf");

        assertThat(file)
                .exists()
                .isRegularFile();

        var source = HttpSource.builder()
                .url(file.toUri())
                .build();

        var options = ConvertDocumentOptions.builder()
                .toFormat(OutputFormat.MARKDOWN)
                .build();

        var request = HybridChunkDocumentRequest.builder()
            .source(source)
            .options(options)
            .build();

        assertThatThrownBy(() -> getDoclingClient().chunkSourceWithHybridChunker(request))
            .isInstanceOf(ValidationException.class)
            .hasMessageStartingWith("An error occurred while making POST request to ")
            .asInstanceOf(InstanceOfAssertFactories.throwable(ValidationException.class))
            .extracting(ValidationException::getValidationError)
            .isNotNull()
            .extracting(ValidationError::getErrorDetails)
            .asInstanceOf(InstanceOfAssertFactories.list(ValidationErrorDetail.class))
            .singleElement()
            .usingRecursiveComparison()
            .isEqualTo(
              ValidationErrorDetail.builder()
                  .type("url_scheme")
                  .message("URL scheme should be 'http' or 'https'")
                  .locations(List.of("body", "sources", 0, "http", "url"))
                  .input(file.toUri().toString())
                  .context(
                      ValidationErrorContext.builder()
                          .expectedSchemes("'http' or 'https'")
                          .build()
                  ).build()
            );
    }

    @Test
    void shouldChunkDocumentWithHybridChunker() {
      ConvertDocumentOptions options = ConvertDocumentOptions.builder()
          .toFormat(OutputFormat.JSON)
          .build();

      HybridChunkDocumentRequest request = HybridChunkDocumentRequest.builder()
          .source(HttpSource.builder().url(URI.create("https://docs.arconia.io/arconia-cli/latest/development/dev/")).build())
          .options(options)
          .includeConvertedDoc(true)
          .chunkingOptions(HybridChunkerOptions.builder()
              .includeRawText(true)
              .useMarkdownTables(true)
              .maxTokens(10000)
              .tokenizer("sentence-transformers/all-MiniLM-L6-v2")
              .build())
          .build();

      ChunkDocumentResponse response = getDoclingClient().chunkSourceWithHybridChunker(request);

      assertThat(response).isNotNull();
      assertThat(response.getChunks()).isNotEmpty();
      assertThat(response.getDocuments()).isNotEmpty();
      assertThat(response.getProcessingTime()).isNotNull();

      List<Chunk> chunks = response.getChunks();
      assertThat(chunks).allMatch(chunk -> !chunk.getText().isEmpty());
    }

    @Test
    void shouldChunkFileWithHybridChunker() {
      var options = ConvertDocumentOptions.builder()
          .toFormat(OutputFormat.JSON)
          .build();

      var request = HybridChunkDocumentRequest.builder()
          .options(options)
          .includeConvertedDoc(true)
          .chunkingOptions(HybridChunkerOptions.builder()
              .includeRawText(true)
              .useMarkdownTables(true)
              .maxTokens(10000)
              .tokenizer("sentence-transformers/all-MiniLM-L6-v2")
              .build())
          .build();

      var response = getDoclingClient().chunkFilesWithHybridChunker(request, Path.of("src", "test", "resources", "story.pdf"));

      assertThat(response).isNotNull();
      assertThat(response.getChunks()).isNotEmpty();
      assertThat(response.getDocuments()).isNotEmpty();
      assertThat(response.getProcessingTime()).isNotNull();

      List<Chunk> chunks = response.getChunks();
      assertThat(chunks).allMatch(chunk -> !chunk.getText().isEmpty());
    }

    @Test
    void shouldChunkFileWithHybridChunkerAsync() {
      var options = ConvertDocumentOptions.builder()
          .toFormat(OutputFormat.JSON)
          .build();

      var request = HybridChunkDocumentRequest.builder()
          .options(options)
          .includeConvertedDoc(true)
          .chunkingOptions(HybridChunkerOptions.builder()
              .includeRawText(true)
              .useMarkdownTables(true)
              .maxTokens(10000)
              .tokenizer("sentence-transformers/all-MiniLM-L6-v2")
              .build())
          .build();

      var response = getDoclingClient()
          .chunkFilesWithHybridChunkerAsync(request, Path.of("src", "test", "resources", "story.pdf"))
          .toCompletableFuture()
          .join();

      assertThat(response).isNotNull();
      assertThat(response.getChunks()).isNotEmpty();
      assertThat(response.getDocuments()).isNotEmpty();
      assertThat(response.getProcessingTime()).isNotNull();

      List<Chunk> chunks = response.getChunks();
      assertThat(chunks).allMatch(chunk -> !chunk.getText().isEmpty());
    }
  }
}
