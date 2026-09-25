package ai.docling.serve.client;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
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
import static org.assertj.core.api.Assertions.tuple;
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
import java.nio.file.Path;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.extension.TestWatcher;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;

import ai.docling.core.DoclingDocument;
import ai.docling.core.DoclingDocument.BaseTextItem;
import ai.docling.core.DoclingDocument.DocItemLabel;
import ai.docling.core.DoclingDocument.FormulaItem;
import ai.docling.core.DoclingDocument.ListItem;
import ai.docling.core.DoclingDocument.TableCell;
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
import ai.docling.serve.api.convert.request.BatchConvertDocumentRequest;
import ai.docling.serve.api.convert.request.CallbackSpec;
import ai.docling.serve.api.convert.request.ConvertDocumentRequest;
import ai.docling.serve.api.convert.request.options.ConvertDocumentOptions;
import ai.docling.serve.api.convert.request.options.ImageRefMode;
import ai.docling.serve.api.convert.request.options.OutputFormat;
import ai.docling.serve.api.convert.request.options.TableFormerMode;
import ai.docling.serve.api.convert.request.source.HttpSource;
import ai.docling.serve.api.convert.request.source.S3Source;
import ai.docling.serve.api.convert.request.target.PresignedUrlTarget;
import ai.docling.serve.api.convert.request.target.PutTarget;
import ai.docling.serve.api.convert.request.target.S3Target;
import ai.docling.serve.api.convert.request.target.ZipTarget;
import ai.docling.serve.api.convert.response.ArtifactType;
import ai.docling.serve.api.convert.response.ConversionStatus;
import ai.docling.serve.api.convert.response.ConvertDocumentResponse;
import ai.docling.serve.api.convert.response.InBodyConvertDocumentResponse;
import ai.docling.serve.api.convert.response.PreSignedUrlConvertDocumentResponse;
import ai.docling.serve.api.convert.response.PreSignedUrlConvertResponse;
import ai.docling.serve.api.convert.response.ResponseType;
import ai.docling.serve.api.convert.response.ZipArchiveConvertDocumentResponse;
import ai.docling.serve.api.health.HealthCheckResponse;
import ai.docling.serve.api.task.request.TaskResultRequest;
import ai.docling.serve.api.task.request.TaskStatusPollRequest;
import ai.docling.serve.api.task.response.TaskStatus;
import ai.docling.serve.api.task.response.TaskStatusPollResponse;
import ai.docling.serve.api.util.FileUtils;
import ai.docling.serve.api.validation.ValidationError;
import ai.docling.serve.api.validation.ValidationErrorDetail;
import ai.docling.serve.api.validation.ValidationException;
import ai.docling.testcontainers.serve.DoclingServeContainer;
import ai.docling.testcontainers.serve.config.DoclingServeContainerConfig;

abstract class AbstractDoclingServeClientTests {
  private static final Logger LOG = LoggerFactory.getLogger(AbstractDoclingServeClientTests.class);

  // docling-serve names extracted images after a hash of their content, which changes whenever the
  // rendered image bytes do (e.g. across docling-serve versions), so ZIP entry names are compared
  // with the hash replaced by a placeholder.
  private static final String IMAGE_HASH_PLACEHOLDER = "<hash>";
  private static final Pattern IMAGE_ARTIFACT_HASH = Pattern.compile("(?<=^artifacts/image_\\d{6}_)[0-9a-f]{64}(?=\\.png$)");
  private static final String FIRST_IMAGE_ARTIFACT_ENTRY = "artifacts/image_000000_%s.png".formatted(IMAGE_HASH_PLACEHOLDER);

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
          getClass().getName(), context.getTestMethod().map(Method::getName).orElse(""), Optional.ofNullable(cause).map(Throwable::getMessage).orElse(""), doclingContainer
              .getLogs());

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

  /**
   * A client that logs and pretty-prints responses, and whose JSON mapper throws an {@link IllegalStateException}
   * with the message {@code boom} when it deserializes an {@link Object}, which is what logging a response does
   * to pretty-print it. It is a failure that is not caused by the text being something other than JSON.
   *
   * <p>The failure is registered for {@link Object} only because Jackson does not consult a deserializer
   * registered for a type that has a builder, such as {@link HealthCheckResponse}, over the builder.
   */
  protected abstract DoclingServeApi getDoclingClientWithFailingDeserializer();

  /**
   * A client, built without {@code prettyPrint()}, whose JSON mapper throws {@code IllegalStateException} with
   * the message {@code boom} specifically when deserializing a {@link ValidationError}, through a mixin rather
   * than a deserializer registered for the type directly, since {@link ValidationError} has a builder (see
   * {@link #getDoclingClientWithFailingDeserializer()}).
   *
   * <p>Used to confirm that a failure of {@code parseValidationError} that is not a {@link JsonReadException},
   * such as a broken custom deserializer, is not swallowed and re-reported as a generic 422.
   */
  protected abstract DoclingServeApi getDoclingClientWithFailingValidationErrorDeserializer();

  protected DoclingServeApi getDoclingClient(boolean requiresAuth) {
    return getDoclingClient(requiresAuth, false);
  }

  protected DoclingServeApi getDoclingClient() {
    return getDoclingClient(true, false);
  }

  private <T> T readValue(String json, Class<T> valueType) {
    return ((DoclingServeClient) getDoclingClient()).readValue(json, valueType);
  }

  private <T> T readValue(DoclingServeApi client, String json, Class<T> valueType) {
    return ((DoclingServeClient) client).readValue(json, valueType);
  }

  private <T> String writeValueAsString(T value) {
    return ((DoclingServeClient) getDoclingClient()).writeValueAsString(value);
  }

  @Test
  void builderWorks() {
    var client = DoclingServeApi.builder()
        .logRequests()
        .logResponses()
        .prettyPrint()
        .baseUrl(doclingContainer.getApiUrl())
        .build();

    assertThat(client)
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
        1, getRequestedFor(urlPathEqualTo("/path/health"))
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

    @Test
    void shouldThrowExceptionWhenClearResultsRequestIsNull() {
      assertThatThrownBy(() -> getDoclingClient().clearResults(null))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessageContaining("request");
    }

    @Test
    void shouldClearResultsWithDuration() {
      // Setup WireMock stub
      getWiremockServer().stubFor(
          get(urlPathEqualTo("/v1/clear/results"))
              .withQueryParam("older_then", equalTo("86400"))  // 24 hours = 86400 seconds
              .willReturn(okJson("{\"status\": \"ok\"}"))
      );

      var request = ClearResultsRequest.builder()
          .olderThen(Duration.ofHours(24))
          .build();

      var response = getDoclingClient(false, true).clearResults(request);

      // Verify response
      assertThat(response)
          .isNotNull()
          .extracting(ClearResponse::getStatus)
          .isEqualTo("ok");

      // Verify the request was made with correct query parameter
      getWiremockServer().verify(getRequestedFor(urlPathEqualTo("/v1/clear/results"))
          .withQueryParam("older_then", equalTo("86400")));
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
      ConvertTests.assertConvertSingleHttpSourceWithDefaultTarget(result);
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
              TaskStatusPollResponse::getTaskId, TaskStatusPollResponse::getTaskStatus, TaskStatusPollResponse::getTaskType
          )
          .allMatch(Objects::nonNull);

      assertThat(taskPollResponse.get())
          .extracting(
              TaskStatusPollResponse::getTaskId, TaskStatusPollResponse::getTaskType
          )
          .containsExactly(
              response.getTaskId(), response.getTaskType()
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
              TaskStatusPollResponse::getTaskId, TaskStatusPollResponse::getTaskPosition, TaskStatusPollResponse::getTaskStatus, TaskStatusPollResponse::getTaskType
          )
          .allMatch(Objects::nonNull);

      assertThat(response.getTaskType()).isEqualTo("convert");

      return response;
    }

    private static void logRequest(HttpRequest request) {
      var stringBuilder = new StringBuilder();
      stringBuilder.append("\n→ REQUEST: %s %s\n".formatted(request.method(), request.uri()));
      stringBuilder.append("  HEADERS:\n");

      request.headers().map().forEach((key, values) -> stringBuilder.append("  %s: %s\n".formatted(key, String.join(", ", values)))
      );

      LOG.info(stringBuilder.toString());
    }

    private void logResponse(HttpResponse<String> response, Optional<String> responseBody) {
      var stringBuilder = new StringBuilder();
      stringBuilder.append("\n← RESPONSE: %s\n".formatted(response.statusCode()));
      stringBuilder.append("  HEADERS:\n");

      response.headers().map().forEach((key, values) -> stringBuilder.append("  %s: %s\n".formatted(key, String.join(", ", values)))
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

  // The clients used by these tests log responses and pretty-print them, which is when a response that is not
  // JSON, such as an error page from a gateway in front of docling-serve, used to fail while it was being logged
  @Nested
  class NonJsonResponseTests {
    @AfterEach
    void resetStubs() {
      getWiremockServer().resetAll();
    }

    @Test
    void htmlErrorBodyKeepsStatusAndBody() {
      stubHealth(502, "text/html", "<html>Bad Gateway</html>");

      assertThatThrownBy(() -> getDoclingClient(false, true).health())
          .asInstanceOf(InstanceOfAssertFactories.type(DoclingServeClientException.class))
          .returns(502, DoclingServeClientException::getStatusCode)
          .returns("<html>Bad Gateway</html>", DoclingServeClientException::getResponseBody);
    }

    @Test
    void plainTextErrorBodyKeepsStatusAndBody() {
      stubHealth(500, "text/plain", "Internal Server Error");

      assertThatThrownBy(() -> getDoclingClient(false, true).health())
          .asInstanceOf(InstanceOfAssertFactories.type(DoclingServeClientException.class))
          .returns(500, DoclingServeClientException::getStatusCode)
          .returns("Internal Server Error", DoclingServeClientException::getResponseBody);
    }

    @Test
    void jsonErrorBodyKeepsStatusAndBody() {
      stubHealth(500, "application/json", "{\"detail\":\"Something went wrong\"}");

      assertThatThrownBy(() -> getDoclingClient(false, true).health())
          .asInstanceOf(InstanceOfAssertFactories.type(DoclingServeClientException.class))
          .returns(500, DoclingServeClientException::getStatusCode)
          .returns("{\"detail\":\"Something went wrong\"}", DoclingServeClientException::getResponseBody);
    }

    @Test
    void readValueReadsJson() {
      assertThat(readValue(getDoclingClient(), "{\"status\": \"ok\"}", HealthCheckResponse.class))
          .extracting(HealthCheckResponse::getStatus)
          .isEqualTo("ok");
    }

    @Test
    void readValueOfTextThatIsNotJsonThrowsJsonReadException() {
      assertThatThrownBy(() -> readValue(getDoclingClient(), "<html>Bad Gateway</html>", Object.class))
          .isExactlyInstanceOf(JsonReadException.class)
          .isNotInstanceOf(DoclingServeClientException.class)
          .hasCauseInstanceOf(Exception.class);
    }

    @Test
    void readValueOfJsonOfAnotherShapeThrowsJsonReadException() {
      assertThatThrownBy(() -> readValue(getDoclingClient(), "[1, 2]", HealthCheckResponse.class))
          .isExactlyInstanceOf(JsonReadException.class)
          .hasCauseInstanceOf(Exception.class);
    }

    @Test
    void failureThatIsNotAParseFailureIsNotHiddenWhileLoggingAResponse() {
      stubHealth(200, "application/json", "{\"status\": \"ok\"}");

      assertThatThrownBy(() -> getDoclingClientWithFailingDeserializer().health())
          .isExactlyInstanceOf(IllegalStateException.class)
          .hasMessage("boom");
    }

    @Test
    void readValueLetsAFailureThatIsNotAParseFailurePropagate() {
      assertThatThrownBy(() -> readValue(getDoclingClientWithFailingDeserializer(), "{\"status\": \"ok\"}", Object.class))
          .isExactlyInstanceOf(IllegalStateException.class)
          .hasMessage("boom");
    }


    private void stubHealth(int status, String contentType, String body) {
      getWiremockServer().stubFor(
          get(urlPathEqualTo("/health"))
              .willReturn(
                  aResponse()
                      .withStatus(status)
                      .withHeader("Content-Type", contentType)
                      .withBody(body)
              )
      );
    }
  }

  // A 422 response is only a validation error when its body carries validation details. ValidationError is read
  // leniently, so any JSON object is read into one, which is empty when the body has no details
  @Nested
  class UnprocessableEntityResponseTests {
    @AfterEach
    void resetStubs() {
      getWiremockServer().resetAll();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "{\"error\":\"gateway says no\"}",
        "{}",
        "{\"detail\":[]}"
    })
    void jsonBodyWithoutValidationDetailsKeepsStatusAndBody(String body) {
      stubHealth("application/json", body);

      assertThatThrownBy(() -> getDoclingClient(false, true).health())
          .isNotInstanceOf(ValidationException.class)
          .asInstanceOf(InstanceOfAssertFactories.type(DoclingServeClientException.class))
          .returns(422, DoclingServeClientException::getStatusCode)
          .returns(body, DoclingServeClientException::getResponseBody);
    }

    @Test
    void jsonBodyWithValidationDetailsIsAValidationException() {
      stubHealth("application/json", "{\"detail\":[{\"type\":\"missing\",\"loc\":[\"body\",\"sources\"],\"msg\":\"Field required\"}]}");

      assertThatThrownBy(() -> getDoclingClient(false, true).health())
          .isNotInstanceOf(DoclingServeClientException.class)
          .asInstanceOf(InstanceOfAssertFactories.throwable(ValidationException.class))
          .hasMessageEndingWith(":\nField required")
          .extracting(ValidationException::getValidationError)
          .extracting(ValidationError::getErrorDetails)
          .asInstanceOf(InstanceOfAssertFactories.list(ValidationErrorDetail.class))
          .singleElement()
          .extracting(ValidationErrorDetail::getMessage)
          .isEqualTo("Field required");
    }

    // A 422 from something other than docling-serve, e.g. a gateway, may not carry a JSON body at all
    @Test
    void nonJsonBodyKeepsStatusAndBody() {
      stubHealth("text/html", "<html>Unprocessable by gateway</html>");

      assertThatThrownBy(() -> getDoclingClient(false, true).health())
          .asInstanceOf(InstanceOfAssertFactories.type(DoclingServeClientException.class))
          .returns(422, DoclingServeClientException::getStatusCode)
          .returns("<html>Unprocessable by gateway</html>", DoclingServeClientException::getResponseBody);
    }

    // Guards the narrowed catch in parseValidationError: only a JsonReadException falls back to a generic
    // 422, any other failure while reading the body as a ValidationError must propagate
    @Test
    void failureThatIsNotAParseFailureIsNotHiddenAsAGeneric422() {
      stubHealth("application/json", "{\"detail\":[{\"type\":\"missing\",\"loc\":[\"body\"],\"msg\":\"Field required\"}]}");

      assertThatThrownBy(() -> getDoclingClientWithFailingValidationErrorDeserializer().health())
          .isExactlyInstanceOf(IllegalStateException.class)
          .hasMessage("boom");
    }

    private void stubHealth(String contentType, String body) {
      getWiremockServer().stubFor(
          get(urlPathEqualTo("/health"))
              .willReturn(
                  aResponse()
                      .withStatus(422)
                      .withHeader("Content-Type", contentType)
                      .withBody(body)
              )
      );
    }
  }

  // docling-serve sends "text": "" for a formula converted without formula enrichment and for a list item that only
  // introduces a nested list. The text and orig fields are required strings in the DoclingDocument schema, so a document
  // written back to JSON must keep them
  @Nested
  class EmptyRequiredStringTests {
    private static final String TASK_ID = "empty-strings-task";
    private static final String FORMULA_ORIG = "Then came the frost . . . . . . . and the snow";

    private static final String CONVERT_RESPONSE = """
        {
          "document": {
            "filename": "misprocessed.pdf",
            "json_content": {
              "schema_name": "DoclingDocument",
              "version": "1.8.0",
              "name": "misprocessed",
              "texts": [
                {
                  "self_ref": "#/texts/0",
                  "parent": {"$ref": "#/body"},
                  "children": [],
                  "content_layer": "body",
                  "label": "formula",
                  "prov": [],
                  "orig": "%s",
                  "text": ""
                },
                {
                  "self_ref": "#/texts/1",
                  "parent": {"$ref": "#/groups/0"},
                  "children": [{"$ref": "#/groups/1"}],
                  "content_layer": "body",
                  "label": "list_item",
                  "prov": [],
                  "orig": "",
                  "text": "",
                  "enumerated": false,
                  "marker": "-"
                }
              ],
              "tables": [
                {
                  "self_ref": "#/tables/0",
                  "content_layer": "body",
                  "label": "table",
                  "data": {
                    "num_rows": 1,
                    "num_cols": 2,
                    "table_cells": [
                      {
                        "text": "",
                        "start_row_offset_idx": 0,
                        "end_row_offset_idx": 1,
                        "start_col_offset_idx": 0,
                        "end_col_offset_idx": 1
                      },
                      {
                        "text": "",
                        "start_row_offset_idx": 0,
                        "end_row_offset_idx": 1,
                        "start_col_offset_idx": 1,
                        "end_col_offset_idx": 2,
                        "ref": {"$ref": "#/groups/2"}
                      }
                    ]
                  }
                }
              ]
            }
          },
          "status": "success",
          "errors": [],
          "processing_time": 0.5,
          "timings": {}
        }
        """.formatted(FORMULA_ORIG);

    @AfterEach
    void resetStubs() {
      getWiremockServer().resetAll();
    }

    @Test
    void convertSourceKeepsEmptyRequiredStrings() {
      getWiremockServer().stubFor(
          post(urlPathEqualTo("/v1/convert/source"))
              .willReturn(okJson(CONVERT_RESPONSE))
      );

      var response = getDoclingClient(false, true)
          .convertSource(jsonConvertRequest());

      assertKeepsEmptyRequiredStrings(response);
    }

    @Test
    void convertSourceAsyncKeepsEmptyRequiredStrings() throws Exception {
      var wireMockServer = getWiremockServer();

      wireMockServer.stubFor(
          post(urlPathEqualTo("/v1/convert/source/async"))
              .willReturn(okJson(taskStatus("pending")))
      );

      // Reporting success on the first poll means the client does not wait for its poll interval
      wireMockServer.stubFor(
          get(urlPathEqualTo("/v1/status/poll/%s".formatted(TASK_ID)))
              .willReturn(okJson(taskStatus("success")))
      );

      wireMockServer.stubFor(
          get(urlPathEqualTo("/v1/result/%s".formatted(TASK_ID)))
              .willReturn(okJson(CONVERT_RESPONSE))
      );

      var response = getDoclingClient(false, true)
          .convertSourceAsync(jsonConvertRequest())
          .toCompletableFuture()
          .get(10, TimeUnit.SECONDS);

      assertKeepsEmptyRequiredStrings(response);
    }

    private void assertKeepsEmptyRequiredStrings(ConvertDocumentResponse response) {
      var document = assertThat(response)
          .asInstanceOf(InstanceOfAssertFactories.type(InBodyConvertDocumentResponse.class))
          .extracting(inBodyResponse -> inBodyResponse.getDocument().getJsonContent())
          .isNotNull()
          .actual();

      var cells = document.getTables()
          .get(0)
          .getData()
          .getTableCells();

      // Reading keeps the empty strings: only writing the document back used to drop them
      assertThat(document.getTexts())
          .hasExactlyElementsOfTypes(FormulaItem.class, ListItem.class)
          .extracting(BaseTextItem::getText, BaseTextItem::getOrig)
          .containsExactly(tuple("", FORMULA_ORIG), tuple("", ""));

      assertThat(cells)
          .extracting(TableCell::getText)
          .containsExactly("", "");

      var writtenDocument = writeAndRead(document);

      assertThat(writtenDocument)
          .extractingByKey("texts", InstanceOfAssertFactories.list(Map.class))
          .allSatisfy(text -> assertThat(text).doesNotContainKeys("prov", "comments", "source"))
          .extracting(text -> text.get("text"), text -> text.get("orig"))
          .containsExactly(tuple("", FORMULA_ORIG), tuple("", ""));

      assertThat(writtenDocument)
          .extractingByKey("tables", InstanceOfAssertFactories.list(Map.class))
          .singleElement(InstanceOfAssertFactories.MAP)
          .extractingByKey("data", InstanceOfAssertFactories.MAP)
          .extractingByKey("table_cells", InstanceOfAssertFactories.list(Map.class))
          .extracting(cell -> cell.get("text"), cell -> cell.containsKey("ref"))
          .containsExactly(tuple("", false), tuple("", true));
    }

    // Writes the document with the client's own JSON mapper, as an application storing it would, and reads it back
    @SuppressWarnings("unchecked")
    private Map<String, Object> writeAndRead(DoclingDocument document) {
      return readValue(writeValueAsString(document), Map.class);
    }

    private ConvertDocumentRequest jsonConvertRequest() {
      return ConvertDocumentRequest.builder()
          .source(HttpSource.builder()
              .url(URI.create("https://github.com/user-attachments/files/29854351/misprocessed.pdf"))
              .build())
          .options(ConvertDocumentOptions.builder()
              .toFormat(OutputFormat.JSON)
              .build())
          .build();
    }

    private String taskStatus(String status) {
      return """
          {
            "task_id": "%s",
            "task_type": "convert",
            "task_status": "%s",
            "task_position": 0,
            "task_meta": null
          }
          """.formatted(TASK_ID, status);
    }
  }

  @Nested
  class ConvertTests {
    static void assertConvertSingleHttpSourceWithDefaultTarget(ConvertDocumentResponse response) {
      assertThat(response).isNotNull();
      assertThat(response.getResponseType().equals(ResponseType.IN_BODY)).isTrue();
      var inBodyResponse = (InBodyConvertDocumentResponse) response;
      assertThat(inBodyResponse.getStatus()).isNotEmpty();
      assertThat(inBodyResponse.getDocument()).isNotNull();
      assertThat(inBodyResponse.getDocument().getFilename()).isNotEmpty();

      if (inBodyResponse.getProcessingTime() != null) {
        assertThat(inBodyResponse.getProcessingTime()).isPositive();
      }

      assertThat(inBodyResponse.getDocument().getMarkdownContent()).isNotEmpty();
    }

    static void assertZipArchiveEntries(InputStream inputStream, Set<String> expectedEntries) {
      Set<String> actualEntries = new TreeSet<>();
      try (ZipInputStream zipInputStream = new ZipInputStream(inputStream)) {
        ZipEntry entry;
        while ((entry = zipInputStream.getNextEntry()) != null) {
          var normalizedName = IMAGE_ARTIFACT_HASH
              .matcher(entry.getName())
              .replaceFirst(IMAGE_HASH_PLACEHOLDER);
          actualEntries.add(normalizedName);
          LOG.info("Found entry in ZIP: {} (size: {} bytes)", entry.getName(), entry.getSize());
          zipInputStream.closeEntry();
        }
      }
      catch (IOException e) {
        throw new RuntimeException(e);
      }
      assertThat(actualEntries).containsExactlyInAnyOrderElementsOf(expectedEntries);
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
                  .build()
//                  .input(file.toUri().toString())
//                  .context(
//                      ValidationErrorContext.builder()
//                          .expectedSchemes("'http' or 'https'")
//                          .build()
//                  ).build()
          );
    }

    @Test
    void shouldConvertSourceWithPutTargetSuccessfully() {
      var request = ConvertDocumentRequest.builder()
          .source(
              HttpSource
                  .builder()
                  .url(URI.create("https://docs.arconia.io/arconia-cli/latest/development/dev/"))
                  .build()
          )
          .target(
              PutTarget.builder().url(URI.create("https://github.com/docling-project/docling-java/save")).build()
          ).build();

      var wireMockServer = getWiremockServer();

      wireMockServer.stubFor(
          post("/v1/convert/source")
              .withRequestBody(equalToJson(writeValueAsString(request)))
              .withHeader("Content-Type", equalTo("application/json"))
              .withHeader("Accept", equalTo("application/json"))
              .willReturn(okJson("""
                     {
                       "processing_time": 7,
                       "num_converted": 10,
                       "num_succeeded": 5,
                       "num_failed": 5
                     }
                  """))
      );

      var response = getDoclingClient(false, true).convertSource(request);
      assertThat(response).isNotNull();
      assertThat(response.getResponseType().equals(ResponseType.PRE_SIGNED_URL)).isTrue();
      var preSignedUrlResponse = (PreSignedUrlConvertDocumentResponse) response;

      assertThat(preSignedUrlResponse.getNumConverted()).isEqualTo(10);
      assertThat(preSignedUrlResponse.getNumSucceeded()).isEqualTo(5);
      assertThat(preSignedUrlResponse.getNumFailed()).isEqualTo(5);
      assertThat(preSignedUrlResponse.getProcessingTime()).isPositive().isEqualTo(7);

      wireMockServer.verify(
          1, postRequestedFor(urlPathEqualTo("/v1/convert/source"))
              .withHeader("Content-Type", equalTo("application/json"))
              .withRequestBody(
                  matchingJsonPath("$.sources[0].kind", equalTo("http"))
                      .and(matchingJsonPath("$.sources[0].url", equalTo("https://docs.arconia.io/arconia-cli/latest/development/dev/")))
                      .and(matchingJsonPath("$.target.kind", equalTo("put")))
                      .and(matchingJsonPath("$.target.url", equalTo("https://github.com/docling-project/docling-java/save")))
              )
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
              .willReturn(okJson("""
                     {
                       "processing_time": 7,
                       "num_converted": 10,
                       "num_succeeded": 5,
                       "num_failed": 5
                     }
                  """))
      );

      var response = getDoclingClient(false, true).convertSource(request);
      assertThat(response).isNotNull();

      wireMockServer.verify(
          1, postRequestedFor(urlPathEqualTo("/v1/convert/source"))
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
    void shouldConvertSourceWithPresignedUrlTargetSuccessfully() {
      var request = ConvertDocumentRequest.builder()
          .source(
              HttpSource
                  .builder()
                  .url(URI.create("https://arxiv.org/pdf/2408.09869"))
                  .build()
          )
          .target(
              PresignedUrlTarget.builder().build()
          ).build();

      var wireMockServer = getWiremockServer();

      wireMockServer.stubFor(
          post("/v1/convert/source")
              .withRequestBody(equalToJson(writeValueAsString(request)))
              .withHeader("Content-Type", equalTo("application/json"))
              .withHeader("Accept", equalTo("application/json"))
              .willReturn(okJson("""
                     {
                       "processing_time": 4.13,
                       "num_converted": 1,
                       "num_succeeded": 1,
                       "num_partially_succeeded": 0,
                       "num_failed": 0,
                       "documents": [
                         {
                           "source_index": 0,
                           "source_uri": "https://arxiv.org/pdf/2408.09869",
                           "filename": "2408.09869",
                           "status": "success",
                           "errors": [],
                           "timings": {},
                           "artifacts": [
                             {
                               "artifact_type": "markdown",
                               "mime_type": "text/markdown",
                               "uri": "https://storage.example.com/2408.09869.md",
                               "url_expires_at": "2026-06-15T12:00:00Z"
                             }
                           ]
                         }
                       ]
                     }
                  """))
      );

      var response = getDoclingClient(false, true).convertSource(request);
      assertThat(response).isNotNull();
      assertThat(response.getResponseType()).isEqualTo(ResponseType.PRE_SIGNED_URL_RESPONSE);
      assertThat(response).isInstanceOf(PreSignedUrlConvertResponse.class);

      var presignedResponse = (PreSignedUrlConvertResponse) response;
      assertThat(presignedResponse.getProcessingTime()).isEqualTo(4.13);
      assertThat(presignedResponse.getNumConverted()).isEqualTo(1);
      assertThat(presignedResponse.getNumSucceeded()).isEqualTo(1);
      assertThat(presignedResponse.getNumPartiallySucceeded()).isZero();
      assertThat(presignedResponse.getNumFailed()).isZero();

      assertThat(presignedResponse.getDocuments()).hasSize(1);
      var doc = presignedResponse.getDocuments().get(0);
      assertThat(doc.getSourceIndex()).isZero();
      assertThat(doc.getSourceUri()).isEqualTo("https://arxiv.org/pdf/2408.09869");
      assertThat(doc.getFilename()).isEqualTo("2408.09869");
      assertThat(doc.getStatus()).isEqualTo(ConversionStatus.SUCCESS);
      assertThat(doc.getErrors()).isEmpty();
      assertThat(doc.getArtifacts()).hasSize(1);

      var artifact = doc.getArtifacts().get(0);
      assertThat(artifact.getArtifactType()).isEqualTo(ArtifactType.MARKDOWN);
      assertThat(artifact.getMimeType()).isEqualTo("text/markdown");
      assertThat(artifact.getUri()).isEqualTo(URI.create("https://storage.example.com/2408.09869.md"));
      assertThat(artifact.getUrlExpiresAt()).isNotNull();

      wireMockServer.verify(
          postRequestedFor(urlPathEqualTo("/v1/convert/source"))
              .withHeader("Content-Type", equalTo("application/json"))
              .withRequestBody(
                  matchingJsonPath("$.sources[0].kind", equalTo("http"))
                      .and(matchingJsonPath("$.sources[0].url", equalTo("https://arxiv.org/pdf/2408.09869")))
                      .and(matchingJsonPath("$.target.kind", equalTo("presigned_url")))
              )
      );
    }

    @Test
    void shouldConvertSourceWithPresignedUrlTargetAndMultipleDocuments() {
      var request = ConvertDocumentRequest.builder()
          .source(HttpSource.builder().url(URI.create("https://arxiv.org/pdf/2408.09869")).build())
          .source(HttpSource.builder().url(URI.create("https://arxiv.org/pdf/2501.17887")).build())
          .target(PresignedUrlTarget.builder().build())
          .build();

      var wireMockServer = getWiremockServer();

      wireMockServer.stubFor(
          post("/v1/convert/source")
              .withHeader("Content-Type", equalTo("application/json"))
              .withHeader("Accept", equalTo("application/json"))
              .willReturn(okJson("""
                     {
                       "processing_time": 8.27,
                       "num_converted": 2,
                       "num_succeeded": 2,
                       "num_partially_succeeded": 0,
                       "num_failed": 0,
                       "documents": [
                         {
                           "source_index": 0,
                           "source_uri": "https://arxiv.org/pdf/2408.09869",
                           "filename": "2408.09869",
                           "status": "success",
                           "artifacts": [
                             {
                               "artifact_type": "markdown",
                               "mime_type": "text/markdown",
                               "uri": "https://storage.example.com/2408.09869.md"
                             }
                           ]
                         },
                         {
                           "source_index": 1,
                           "source_uri": "https://arxiv.org/pdf/2501.17887",
                           "filename": "2501.17887",
                           "status": "success",
                           "artifacts": [
                             {
                               "artifact_type": "markdown",
                               "mime_type": "text/markdown",
                               "uri": "https://storage.example.com/2501.17887.md"
                             }
                           ]
                         }
                       ]
                     }
                  """))
      );

      var response = getDoclingClient(false, true).convertSource(request);
      assertThat(response).isNotNull();
      assertThat(response.getResponseType()).isEqualTo(ResponseType.PRE_SIGNED_URL_RESPONSE);

      var presignedResponse = (PreSignedUrlConvertResponse) response;
      assertThat(presignedResponse.getNumConverted()).isEqualTo(2);
      assertThat(presignedResponse.getNumSucceeded()).isEqualTo(2);
      assertThat(presignedResponse.getDocuments()).hasSize(2);
      assertThat(presignedResponse.getDocuments().get(0).getFilename()).isEqualTo("2408.09869");
      assertThat(presignedResponse.getDocuments().get(1).getFilename()).isEqualTo("2501.17887");
      assertThat(presignedResponse.getDocuments().get(1).getSourceIndex()).isEqualTo(1);

      wireMockServer.verify(
          postRequestedFor(urlPathEqualTo("/v1/convert/source"))
              .withRequestBody(
                  matchingJsonPath("$.target.kind", equalTo("presigned_url"))
                      .and(matchingJsonPath("$.sources[0].kind", equalTo("http")))
                      .and(matchingJsonPath("$.sources[1].kind", equalTo("http")))
              )
      );
    }

    @Test
    void shouldConvertSingleHttpSourceWithDefaultTargetSuccessfully() {
      var request = ConvertDocumentRequest.builder()
          .source(HttpSource.builder().url(URI.create("https://docs.arconia.io/arconia-cli/latest/development/dev/")).build())
          .build();

      var response = getDoclingClient().convertSource(request);
      assertConvertSingleHttpSourceWithDefaultTarget(response);
    }

    @Test
    void shouldConvertFileSuccessfully() {
      var response = getDoclingClient().convertFiles(Path.of("src", "test", "resources", "story.pdf"));
      assertThat(ResponseType.IN_BODY.equals(response.getResponseType())).isTrue();
      assertThat(response).isNotNull();

      var inBodyResponse = (InBodyConvertDocumentResponse) response;

      assertThat(inBodyResponse.getStatus()).isNotEmpty();
      assertThat(inBodyResponse.getDocument()).isNotNull();
      assertThat(inBodyResponse.getDocument().getFilename()).isEqualTo("story.pdf");

      if (inBodyResponse.getProcessingTime() != null) {
        assertThat(inBodyResponse.getProcessingTime()).isPositive();
      }

      assertThat(inBodyResponse.getDocument().getMarkdownContent()).isNotEmpty();
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

      assertThat(ResponseType.IN_BODY.equals(response.getResponseType())).isTrue();
      assertThat(response).isNotNull();

      var inBodyResponse = (InBodyConvertDocumentResponse) response;

      assertThat(inBodyResponse.getStatus()).isNotEmpty();
      assertThat(inBodyResponse.getDocument()).isNotNull();
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

      assertThat(ResponseType.IN_BODY.equals(response.getResponseType())).isTrue();
      assertThat(response).isNotNull();

      var inBodyResponse = (InBodyConvertDocumentResponse) response;

      assertThat(inBodyResponse.getStatus()).isNotEmpty();
      assertThat(inBodyResponse.getDocument()).isNotNull();

      DoclingDocument doclingDocument = inBodyResponse.getDocument().getJsonContent();
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

      assertThat(ResponseType.IN_BODY.equals(response.getResponseType())).isTrue();
      assertThat(response).isInstanceOf(InBodyConvertDocumentResponse.class);
      assertThat(response).isNotNull();

      var inBodyResponse = (InBodyConvertDocumentResponse) response;

      assertThat(inBodyResponse.getStatus()).isNotEmpty();
      assertThat(inBodyResponse.getDocument()).isNotNull();
      assertThat(inBodyResponse.getDocument().getMarkdownContent()).isNotEmpty();
    }

    @Test
    void shouldConvertFileAsync() {
      ConvertDocumentResponse response = getDoclingClient().convertFilesAsync(Path.of("src", "test", "resources", "story.pdf")).toCompletableFuture().join();

      assertThat(ResponseType.IN_BODY.equals(response.getResponseType())).isTrue();
      assertThat(response).isNotNull();

      var inBodyResponse = (InBodyConvertDocumentResponse) response;

      assertThat(inBodyResponse.getStatus()).isNotEmpty();
      assertThat(inBodyResponse.getDocument()).isNotNull();
      assertThat(inBodyResponse.getDocument().getFilename()).isEqualTo("story.pdf");
      assertThat(inBodyResponse.getDocument().getMarkdownContent()).isNotEmpty();
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

      assertThat(ResponseType.IN_BODY.equals(response.getResponseType())).isTrue();
      assertThat(response).isNotNull();

      var inBodyResponse = (InBodyConvertDocumentResponse) response;


      assertThat(inBodyResponse).isNotNull();
      assertThat(inBodyResponse.getStatus()).isNotEmpty();
      assertThat(inBodyResponse.getDocument()).isNotNull();
    }

    @Test
    void shouldChainAsyncOperations() {
      ConvertDocumentRequest request = ConvertDocumentRequest.builder()
          .source(HttpSource.builder().url(URI.create("https://docs.arconia.io/arconia-cli/latest/development/dev/")).build())
          .build();

      // Test chaining with thenApply
      String markdownContent = getDoclingClient().convertSourceAsync(request)
          .thenApply(response -> ((InBodyConvertDocumentResponse) response).getDocument().getMarkdownContent())
          .toCompletableFuture().join();

      assertThat(markdownContent).isNotEmpty();
    }

    @Test
    void shouldConvertSingleFileSourceWithZipTargetAsync() {
      Path[] files = new Path[]{
          Path.of("src", "test", "resources", "2408.09869.pdf")
      };

      var requestBuilder = ConvertDocumentRequest
          .builder()
          .target(ZipTarget.builder().build());

      FileUtils.createFileSources(files)
          .forEach(requestBuilder::source);

      var request = requestBuilder.build();

      var response = getDoclingClient()
          .convertSourceAsync(request).toCompletableFuture().join();

      assertThat(response).isNotNull();
      assertThat(response.getResponseType().equals(ResponseType.ZIP_ARCHIVE)).isTrue();
      assertThat(response).isInstanceOf(ZipArchiveConvertDocumentResponse.class);
      assertThat(((ZipArchiveConvertDocumentResponse) response).getFileName()).isEqualTo("converted_docs.zip");
      assertThat(((ZipArchiveConvertDocumentResponse) response).getInputStream()).isNotNull();
      assertZipArchiveEntries(((ZipArchiveConvertDocumentResponse) response).getInputStream(), Set.of("2408.09869.md"));
    }

    @Test
    void shouldConvertSingleFileSourceWithZipTargetAndReferencedImageExportModeAsync() {
      Path[] files = new Path[]{
          Path.of("src", "test", "resources", "2408.09869.pdf")
      };

      var requestBuilder = ConvertDocumentRequest
          .builder()
          .target(ZipTarget.builder().build())
          .options(ConvertDocumentOptions.builder().imageExportMode(ImageRefMode.REFERENCED).build());

      FileUtils.createFileSources(files)
          .forEach(requestBuilder::source);

      var request = requestBuilder.build();

      var response = getDoclingClient()
          .convertSourceAsync(request).toCompletableFuture().join();

      assertThat(response).isNotNull();
      assertThat(response.getResponseType().equals(ResponseType.ZIP_ARCHIVE)).isTrue();
      assertThat(response).isInstanceOf(ZipArchiveConvertDocumentResponse.class);
      assertThat(((ZipArchiveConvertDocumentResponse) response).getFileName()).isEqualTo("converted_docs.zip");
      assertThat(((ZipArchiveConvertDocumentResponse) response).getInputStream()).isNotNull();
      assertZipArchiveEntries(((ZipArchiveConvertDocumentResponse) response).getInputStream(), Set
          .of("2408.09869.md", "artifacts/", FIRST_IMAGE_ARTIFACT_ENTRY));
    }

    @Test
    void shouldConvertMultipleFileSourcesAsync() {
      Path[] files = new Path[]{
          Path.of("src", "test", "resources", "2408.09869.pdf"),
          Path.of("src", "test", "resources", "story.pdf")
      };

      var requestBuilder = ConvertDocumentRequest
          .builder();

      FileUtils.createFileSources(files)
          .forEach(requestBuilder::source);

      var request = requestBuilder.build();

      var response = getDoclingClient()
          .convertSourceAsync(request).toCompletableFuture().join();

      assertThat(response).isNotNull();
      assertThat(response.getResponseType().equals(ResponseType.ZIP_ARCHIVE)).isTrue();
      assertThat(response).isInstanceOf(ZipArchiveConvertDocumentResponse.class);
      assertThat(((ZipArchiveConvertDocumentResponse) response).getFileName()).isEqualTo("converted_docs.zip");
      assertThat(((ZipArchiveConvertDocumentResponse) response).getInputStream()).isNotNull();
      assertZipArchiveEntries(((ZipArchiveConvertDocumentResponse) response).getInputStream(), Set.of("2408.09869.md", "story.md"));
    }

    @Test
    void shouldConvertMultipleFileSourcesWithReferencedImageExportModeAsync() {
      Path[] files = new Path[]{
          Path.of("src", "test", "resources", "2408.09869.pdf"),
          Path.of("src", "test", "resources", "story.pdf")
      };

      var requestBuilder = ConvertDocumentRequest
          .builder()
          .options(ConvertDocumentOptions.builder().imageExportMode(ImageRefMode.REFERENCED).build());

      FileUtils.createFileSources(files)
          .forEach(requestBuilder::source);

      var request = requestBuilder.build();

      var response = getDoclingClient()
          .convertSourceAsync(request).toCompletableFuture().join();

      assertThat(response).isNotNull();
      assertThat(response.getResponseType().equals(ResponseType.ZIP_ARCHIVE)).isTrue();
      assertThat(response).isInstanceOf(ZipArchiveConvertDocumentResponse.class);
      assertThat(((ZipArchiveConvertDocumentResponse) response).getFileName()).isEqualTo("converted_docs.zip");
      assertThat(((ZipArchiveConvertDocumentResponse) response).getInputStream()).isNotNull();
      assertZipArchiveEntries(((ZipArchiveConvertDocumentResponse) response).getInputStream(), Set
          .of("2408.09869.md", "story.md", "artifacts/", FIRST_IMAGE_ARTIFACT_ENTRY));
    }

    @Test
    void shouldConvertMultipleFileSourcesWithInBodyTargetAsync() {
      Path[] files = new Path[]{
          Path.of("src", "test", "resources", "2408.09869.pdf"),
          Path.of("src", "test", "resources", "story.pdf")
      };

      var requestBuilder = ConvertDocumentRequest
          .builder()
          .target(ZipTarget.builder().build());

      FileUtils.createFileSources(files)
          .forEach(requestBuilder::source);

      var request = requestBuilder.build();

      var response = getDoclingClient()
          .convertSourceAsync(request).toCompletableFuture().join();

      assertThat(response).isNotNull();
      assertThat(response.getResponseType().equals(ResponseType.ZIP_ARCHIVE)).isTrue();
      assertThat(response).isInstanceOf(ZipArchiveConvertDocumentResponse.class);
      assertThat(((ZipArchiveConvertDocumentResponse) response).getFileName()).isEqualTo("converted_docs.zip");
      assertThat(((ZipArchiveConvertDocumentResponse) response).getInputStream()).isNotNull();
      assertZipArchiveEntries(((ZipArchiveConvertDocumentResponse) response).getInputStream(), Set.of("2408.09869.md", "story.md"));
    }

    @Test
    void shouldConvertMultipleFileSourcesWithInBodyTargetAndReferencedImageExportModeAsync() {
      Path[] files = new Path[]{
          Path.of("src", "test", "resources", "2408.09869.pdf"),
          Path.of("src", "test", "resources", "story.pdf")
      };

      var requestBuilder = ConvertDocumentRequest
          .builder()
          .target(ZipTarget.builder().build())
          .options(ConvertDocumentOptions.builder().imageExportMode(ImageRefMode.REFERENCED).build());

      FileUtils.createFileSources(files)
          .forEach(requestBuilder::source);

      var request = requestBuilder.build();

      var response = getDoclingClient()
          .convertSourceAsync(request).toCompletableFuture().join();

      assertThat(response).isNotNull();
      assertThat(response.getResponseType().equals(ResponseType.ZIP_ARCHIVE)).isTrue();
      assertThat(response).isInstanceOf(ZipArchiveConvertDocumentResponse.class);
      assertThat(((ZipArchiveConvertDocumentResponse) response).getFileName()).isEqualTo("converted_docs.zip");
      assertThat(((ZipArchiveConvertDocumentResponse) response).getInputStream()).isNotNull();
      assertZipArchiveEntries(((ZipArchiveConvertDocumentResponse) response).getInputStream(), Set
          .of("2408.09869.md", "story.md", "artifacts/", FIRST_IMAGE_ARTIFACT_ENTRY));
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
          .isThrownBy(() -> getDoclingClient().convertFiles(new Path[]{}))
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
          .isThrownBy(() -> getDoclingClient().convertFilesAsync(new Path[]{}))
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

    @Test
    void shouldConvertSourceBatchWithPresignedUrlTarget() {
      var request = BatchConvertDocumentRequest.builder()
          .source(
              HttpSource.builder()
                  .url(URI.create("https://arxiv.org/pdf/2408.09869"))
                  .build()
          )
          .source(
              HttpSource.builder()
                  .url(URI.create("https://arxiv.org/pdf/2501.17887"))
                  .build()
          )
          .target(PresignedUrlTarget.builder().build())
          .build();

      var wireMockServer = getWiremockServer();

      wireMockServer.stubFor(
          post("/v1/convert/source/batch")
              .withRequestBody(equalToJson(writeValueAsString(request)))
              .withHeader("Content-Type", equalTo("application/json"))
              .withHeader("Accept", equalTo("application/json"))
              .willReturn(okJson("""
                     {
                       "task_id": "batch-task-123",
                       "task_type": "convert",
                       "task_status": "pending",
                       "task_position": 1,
                       "task_meta": null
                     }
                  """))
      );

      var response = getDoclingClient(false, true).convertSourceBatch(request);
      assertThat(response).isNotNull();
      assertThat(response.getTaskId()).isEqualTo("batch-task-123");
      assertThat(response.getTaskStatus()).isEqualTo(TaskStatus.PENDING);

      wireMockServer.verify(
          1, postRequestedFor(urlPathEqualTo("/v1/convert/source/batch"))
              .withHeader("Content-Type", equalTo("application/json"))
              .withRequestBody(
                  matchingJsonPath("$.sources[0].kind", equalTo("http"))
                      .and(matchingJsonPath("$.sources[0].url", equalTo("https://arxiv.org/pdf/2408.09869")))
                      .and(matchingJsonPath("$.sources[1].kind", equalTo("http")))
                      .and(matchingJsonPath("$.sources[1].url", equalTo("https://arxiv.org/pdf/2501.17887")))
                      .and(matchingJsonPath("$.target.kind", equalTo("presigned_url")))
              )
      );
    }

    @Test
    void shouldConvertSourceBatchAsyncWithPresignedUrlTarget() {
      var request = BatchConvertDocumentRequest.builder()
          .source(
              HttpSource.builder()
                  .url(URI.create("https://arxiv.org/pdf/2408.09869"))
                  .build()
          )
          .target(PresignedUrlTarget.builder().build())
          .build();

      var wireMockServer = getWiremockServer();

      wireMockServer.stubFor(
          post("/v1/convert/source/batch")
              .withHeader("Content-Type", equalTo("application/json"))
              .withHeader("Accept", equalTo("application/json"))
              .willReturn(okJson("""
                     {
                       "task_id": "batch-async-task-001",
                       "task_type": "convert",
                       "task_status": "pending",
                       "task_position": 1,
                       "task_meta": null
                     }
                  """))
      );

      wireMockServer.stubFor(
          get(urlPathEqualTo("/v1/status/poll/batch-async-task-001"))
              .willReturn(okJson("""
                     {
                       "task_id": "batch-async-task-001",
                       "task_type": "convert",
                       "task_status": "success",
                       "task_position": 0,
                       "task_meta": null
                     }
                  """))
      );

      wireMockServer.stubFor(
          get(urlPathEqualTo("/v1/result/batch-async-task-001"))
              .willReturn(okJson("""
                     {
                       "processing_time": 42.5,
                       "num_converted": 1,
                       "num_succeeded": 1,
                       "num_partially_succeeded": 0,
                       "num_failed": 0,
                       "documents": [
                         {
                           "source_index": 0,
                           "source_uri": "https://arxiv.org/pdf/2408.09869",
                           "filename": "2408.09869",
                           "status": "success",
                           "errors": [],
                           "timings": {},
                           "artifacts": [
                             {
                               "artifact_type": "markdown",
                               "mime_type": "text/markdown",
                               "uri": "https://storage.example.com/2408.09869.md",
                               "url_expires_at": "2026-06-15T12:00:00Z"
                             }
                           ]
                         }
                       ]
                     }
                  """))
      );

      var response = getDoclingClient(false, true)
          .convertSourceBatchAsync(request).toCompletableFuture().join();

      assertThat(response).isNotNull();
      assertThat(response.getResponseType()).isEqualTo(ResponseType.PRE_SIGNED_URL_RESPONSE);
      assertThat(response).isInstanceOf(PreSignedUrlConvertResponse.class);

      var presignedResponse = (PreSignedUrlConvertResponse) response;
      assertThat(presignedResponse.getProcessingTime()).isEqualTo(42.5);
      assertThat(presignedResponse.getNumConverted()).isEqualTo(1);
      assertThat(presignedResponse.getNumSucceeded()).isEqualTo(1);
      assertThat(presignedResponse.getDocuments()).hasSize(1);

      var doc = presignedResponse.getDocuments().get(0);
      assertThat(doc.getSourceUri()).isEqualTo("https://arxiv.org/pdf/2408.09869");
      assertThat(doc.getStatus()).isEqualTo(ConversionStatus.SUCCESS);
      assertThat(doc.getArtifacts()).hasSize(1);
      assertThat(doc.getArtifacts().get(0).getArtifactType()).isEqualTo(ArtifactType.MARKDOWN);
      assertThat(doc.getArtifacts().get(0).getUri()).isEqualTo(URI.create("https://storage.example.com/2408.09869.md"));

    }

    @Test
    void shouldConvertSourceBatchWithS3SourceAndS3Target() {
      var request = BatchConvertDocumentRequest.builder()
          .source(
              S3Source.builder()
                  .endpoint("source-s3-endpoint")
                  .bucket("source-bucket")
                  .accessKey("source-access-key")
                  .secretKey("source-secret-key")
                  .keyPrefix("incoming/")
                  .maxNumElements(500)
                  .verifySsl(false)
                  .build()
          )
          .target(
              S3Target.builder()
                  .endpoint("target-s3-endpoint")
                  .bucket("target-bucket")
                  .accessKey("target-access-key")
                  .secretKey("target-secret-key")
                  .keyPrefix("converted/")
                  .verifySsl(false)
                  .build()
          ).build();

      var wireMockServer = getWiremockServer();

      wireMockServer.stubFor(
          post("/v1/convert/source/batch")
              .withRequestBody(equalToJson(writeValueAsString(request)))
              .withHeader("Content-Type", equalTo("application/json"))
              .withHeader("Accept", equalTo("application/json"))
              .willReturn(okJson("""
                     {
                       "task_id": "batch-s3-task-456",
                       "task_type": "convert",
                       "task_status": "pending",
                       "task_position": 2,
                       "task_meta": null
                     }
                  """))
      );

      var response = getDoclingClient(false, true).convertSourceBatch(request);
      assertThat(response).isNotNull();
      assertThat(response.getTaskId()).isEqualTo("batch-s3-task-456");
      assertThat(response.getTaskStatus()).isEqualTo(TaskStatus.PENDING);

      wireMockServer.verify(
          1, postRequestedFor(urlPathEqualTo("/v1/convert/source/batch"))
              .withHeader("Content-Type", equalTo("application/json"))
              .withRequestBody(
                  matchingJsonPath("$.sources[0].kind", equalTo("s3"))
                      .and(matchingJsonPath("$.sources[0].endpoint", equalTo("source-s3-endpoint")))
                      .and(matchingJsonPath("$.sources[0].bucket", equalTo("source-bucket")))
                      .and(matchingJsonPath("$.sources[0].key_prefix", equalTo("incoming/")))
                      .and(matchingJsonPath("$.sources[0].max_num_elements", equalTo("500")))
                      .and(matchingJsonPath("$.target.kind", equalTo("s3")))
                      .and(matchingJsonPath("$.target.endpoint", equalTo("target-s3-endpoint")))
                      .and(matchingJsonPath("$.target.bucket", equalTo("target-bucket")))
                      .and(matchingJsonPath("$.target.key_prefix", equalTo("converted/")))
              )
      );
    }

    @Test
    void shouldConvertSourceBatchWithCallbacks() {
      var request = BatchConvertDocumentRequest.builder()
          .source(
              HttpSource.builder()
                  .url(URI.create("https://arxiv.org/pdf/2408.09869"))
                  .build()
          )
          .target(PresignedUrlTarget.builder().build())
          .callback(
              CallbackSpec.builder()
                  .url(URI.create("https://my-app.example.com/docling/progress"))
                  .header("Authorization", "Bearer token123")
                  .build()
          )
          .build();

      var wireMockServer = getWiremockServer();

      wireMockServer.stubFor(
          post("/v1/convert/source/batch")
              .withRequestBody(equalToJson(writeValueAsString(request)))
              .withHeader("Content-Type", equalTo("application/json"))
              .withHeader("Accept", equalTo("application/json"))
              .willReturn(okJson("""
                     {
                       "task_id": "batch-callback-789",
                       "task_type": "convert",
                       "task_status": "pending",
                       "task_position": 1,
                       "task_meta": null
                     }
                  """))
      );

      var response = getDoclingClient(false, true).convertSourceBatch(request);
      assertThat(response).isNotNull();
      assertThat(response.getTaskId()).isEqualTo("batch-callback-789");

      wireMockServer.verify(
          1, postRequestedFor(urlPathEqualTo("/v1/convert/source/batch"))
              .withRequestBody(
                  matchingJsonPath("$.callbacks[0].url", equalTo("https://my-app.example.com/docling/progress"))
                      .and(matchingJsonPath("$.callbacks[0].headers.Authorization", equalTo("Bearer token123")))
              )
      );
    }

    @Test
    void convertSourceBatchNullRequest() {
      assertThatExceptionOfType(IllegalArgumentException.class)
          .isThrownBy(() -> getDoclingClient().convertSourceBatch(null))
          .withMessage("request cannot be null");
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
          .isThrownBy(() -> getDoclingClient().chunkFilesWithHierarchicalChunker(new Path[]{}))
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
          .isThrownBy(() -> getDoclingClient().chunkFilesWithHierarchicalChunkerAsync(new Path[]{}))
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
          .isThrownBy(() -> getDoclingClient().chunkFilesWithHybridChunker(new Path[]{}))
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
          .isThrownBy(() -> getDoclingClient().chunkFilesWithHybridChunkerAsync(new Path[]{}))
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
                  .build()
//                  .input(file.toUri().toString())
//                  .context(
//                      ValidationErrorContext.builder()
//                          .expectedSchemes("'http' or 'https'")
//                          .build()
//                  ).build()
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
                  .build()
//                  .input(file.toUri().toString())
//                  .context(
//                      ValidationErrorContext.builder()
//                          .expectedSchemes("'http' or 'https'")
//                          .build()
//                  ).build()
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
