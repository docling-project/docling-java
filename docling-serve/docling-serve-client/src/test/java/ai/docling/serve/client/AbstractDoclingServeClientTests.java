package ai.docling.serve.client;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.awaitility.Awaitility.await;

import java.io.IOException;
import java.io.InputStream;
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
import java.time.Duration;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.atomic.AtomicReference;

import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.extension.TestWatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ai.docling.core.DoclingDocument;
import ai.docling.core.DoclingDocument.DocItemLabel;
import ai.docling.serve.api.DoclingServeApi;
import ai.docling.serve.api.chunk.request.HierarchicalChunkDocumentRequest;
import ai.docling.serve.api.chunk.request.HybridChunkDocumentRequest;
import ai.docling.serve.api.chunk.request.options.HierarchicalChunkerOptions;
import ai.docling.serve.api.chunk.request.options.HybridChunkerOptions;
import ai.docling.serve.api.chunk.response.Chunk;
import ai.docling.serve.api.chunk.response.ChunkDocumentResponse;
import ai.docling.serve.api.clear.request.ClearRequest;
import ai.docling.serve.api.clear.response.ClearResponse;
import ai.docling.serve.api.convert.request.ConvertDocumentRequest;
import ai.docling.serve.api.convert.request.options.ConvertDocumentOptions;
import ai.docling.serve.api.convert.request.options.OutputFormat;
import ai.docling.serve.api.convert.request.options.TableFormerMode;
import ai.docling.serve.api.convert.request.source.FileSource;
import ai.docling.serve.api.convert.request.source.HttpSource;
import ai.docling.serve.api.convert.response.ConvertDocumentResponse;
import ai.docling.serve.api.health.HealthCheckResponse;
import ai.docling.serve.api.task.request.TaskResultRequest;
import ai.docling.serve.api.task.request.TaskStatusPollRequest;
import ai.docling.serve.api.task.response.TaskStatus;
import ai.docling.serve.api.task.response.TaskStatusPollResponse;
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

  static {
    doclingContainer.start();
  }

  protected abstract DoclingServeApi getDoclingClient();

  private <T> T readValue(String json, Class<T> valueType) {
    return ((DoclingServeClient) getDoclingClient()).readValue(json, valueType);
  }

  private <T> String writeValueAsString(T value) {
    return ((DoclingServeClient) getDoclingClient()).writeValueAsString(value);
  }

  @Nested
  class ClearTests {
    @Test
    void shouldClearConvertersSuccessfully() {
      var response = getDoclingClient().clearConverters();

      assertThat(response)
          .isNotNull()
          .extracting(ClearResponse::getStatus)
          .isEqualTo("ok");
    }

    @Test
    void shouldClearResultsSuccessfully() {
      var response = getDoclingClient().clearResults(ClearRequest.builder().build());

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
          .hasRootCauseInstanceOf(DoclingServeClientException.class)
          .hasRootCauseMessage("An error occurred: {\"detail\":\"Task not found.\"}");
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
      HealthCheckResponse response = getDoclingClient().health();

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
    void shouldConvertHttpSourceSuccessfully() {
      ConvertDocumentRequest request = ConvertDocumentRequest.builder()
          .source(HttpSource.builder().url(URI.create("https://docs.arconia.io/arconia-cli/latest/development/dev/")).build())
          .build();

      ConvertDocumentResponse response = getDoclingClient().convertSource(request);
      assertConvertHttpSource(response);
    }

    @Test
    void shouldConvertFileSourceSuccessfully() throws IOException {
      var fileResource = readFileFromClasspath("story.pdf");
      ConvertDocumentRequest request = ConvertDocumentRequest.builder()
          .source(FileSource.builder()
              .filename("story.pdf")
              .base64String(Base64.getEncoder().encodeToString(fileResource))
              .build()
          )

          .build();

      ConvertDocumentResponse response = getDoclingClient().convertSource(request);

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

      ConvertDocumentResponse response = getDoclingClient().convertSource(request);

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
  }

  @Nested
  class ChunkTests {
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
  }

  private static byte[] readFileFromClasspath(String filePath) throws IOException {
    try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath)) {
      if (inputStream == null) {
        throw new IOException("File not found in classpath: " + filePath);
      }
      return inputStream.readAllBytes();
    }
  }
}
