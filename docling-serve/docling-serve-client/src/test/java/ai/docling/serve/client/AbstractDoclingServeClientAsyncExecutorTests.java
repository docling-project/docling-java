package ai.docling.serve.client;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.net.URI;
import java.time.Duration;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import com.github.tomakehurst.wiremock.stubbing.Scenario;

import ai.docling.serve.api.DoclingServeApi;
import ai.docling.serve.api.chunk.request.HybridChunkDocumentRequest;
import ai.docling.serve.api.convert.request.ConvertDocumentRequest;
import ai.docling.serve.api.convert.request.source.HttpSource;
import ai.docling.serve.api.convert.response.InBodyConvertDocumentResponse;

/**
 * Tests for the {@code asyncExecutor} option of {@link DoclingServeClient.DoclingServeClientBuilder}.
 */
abstract class AbstractDoclingServeClientAsyncExecutorTests {
  private static final String THREAD_PREFIX = "custom-async-";
  private static final String TASK_ID = "task-1";
  private static final String POLL_PATH = "/v1/status/poll/" + TASK_ID;
  private static final String RESULT_PATH = "/v1/result/" + TASK_ID;
  private static final String POLLED_ONCE = "polled-once";

  // Tasks submitted to the executor for a task that is polled twice: the task submission, the first poll, the
  // delay before the re-poll (CompletableFuture.delayedExecutor submits a task to its base executor once the
  // delay has elapsed), the re-poll and, for a successful task, the result retrieval.
  private static final int FAILED_TASK_SUBMISSIONS = 4;
  private static final int SUCCESSFUL_TASK_SUBMISSIONS = 5;

  private static final String CONVERT_RESULT = """
      {
        "document": {
          "filename": "dev.html",
          "md_content": "# Dev"
        },
        "status": "success",
        "errors": [],
        "processing_time": 0.5,
        "timings": {}
      }
      """;

  private static final String CHUNK_RESULT = """
      {
        "chunks": [],
        "documents": [],
        "processing_time": 0.5
      }
      """;

  private final AtomicInteger executions = new AtomicInteger();
  private final Set<String> threadNames = ConcurrentHashMap.newKeySet();
  private ExecutorService customExecutor;
  private Executor recordingExecutor;

  protected abstract WireMockExtension getWireMock();

  protected abstract DoclingServeClient.DoclingServeClientBuilder<?, ?> newClientBuilder();

  @BeforeEach
  void setUp() {
    var threadCounter = new AtomicInteger();
    this.customExecutor = Executors.newFixedThreadPool(2, runnable -> new Thread(runnable, THREAD_PREFIX + threadCounter.incrementAndGet()));

    // Counts every task submitted to the executor, so that a step bypassing it shows up as a lower count
    this.recordingExecutor = command -> {
      this.executions.incrementAndGet();
      this.customExecutor.execute(() -> {
        this.threadNames.add(Thread.currentThread().getName());
        command.run();
      });
    };
  }

  @AfterEach
  void tearDown() throws InterruptedException {
    this.customExecutor.shutdownNow();
    assertThat(this.customExecutor.awaitTermination(5, TimeUnit.SECONDS)).isTrue();
  }

  @Test
  void convertSourceAsyncRunsEveryStepOnCustomExecutor() throws Exception {
    stubTask("/v1/convert/source/async", "success");
    getWireMock().stubFor(get(urlPathEqualTo(RESULT_PATH)).willReturn(okJson(CONVERT_RESULT)));

    var response = client(this.recordingExecutor)
        .convertSourceAsync(convertRequest())
        .toCompletableFuture()
        .get(10, TimeUnit.SECONDS);

    assertThat(response).isInstanceOf(InBodyConvertDocumentResponse.class);
    assertThat(((InBodyConvertDocumentResponse) response).getDocument().getMarkdownContent()).isEqualTo("# Dev");

    assertThat(this.executions).hasValue(SUCCESSFUL_TASK_SUBMISSIONS);
    assertThat(this.threadNames)
        .isNotEmpty()
        .allSatisfy(threadName -> assertThat(threadName).startsWith(THREAD_PREFIX));
  }

  @Test
  void chunkSourceAsyncRunsEveryStepOnCustomExecutor() throws Exception {
    stubTask("/v1/chunk/hybrid/source/async", "success");
    getWireMock().stubFor(get(urlPathEqualTo(RESULT_PATH)).willReturn(okJson(CHUNK_RESULT)));

    var request = HybridChunkDocumentRequest.builder()
        .source(HttpSource.builder().url(URI.create("https://docs.arconia.io/arconia-cli/latest/development/dev/")).build())
        .build();

    var response = client(this.recordingExecutor)
        .chunkSourceWithHybridChunkerAsync(request)
        .toCompletableFuture()
        .get(10, TimeUnit.SECONDS);

    assertThat(response.getProcessingTime()).isEqualTo(0.5);

    assertThat(this.executions).hasValue(SUCCESSFUL_TASK_SUBMISSIONS);
    assertThat(this.threadNames)
        .isNotEmpty()
        .allSatisfy(threadName -> assertThat(threadName).startsWith(THREAD_PREFIX));
  }

  @Test
  void failedTaskRunsEveryPollOnCustomExecutor() {
    stubTask("/v1/convert/source/async", "failure");

    var future = client(this.recordingExecutor)
        .convertSourceAsync(convertRequest())
        .toCompletableFuture();

    assertThatThrownBy(() -> future.get(10, TimeUnit.SECONDS))
        .hasRootCauseMessage("Async conversion failed for task %s: Task failed".formatted(TASK_ID));

    assertThat(this.executions).hasValue(FAILED_TASK_SUBMISSIONS);
    getWireMock().verify(0, getRequestedFor(urlPathEqualTo(RESULT_PATH)));
  }

  @Test
  void asyncOperationsWorkWithoutCustomExecutor() throws Exception {
    stubTask("/v1/convert/source/async", "success");
    getWireMock().stubFor(get(urlPathEqualTo(RESULT_PATH)).willReturn(okJson(CONVERT_RESULT)));

    var response = client(null)
        .convertSourceAsync(convertRequest())
        .toCompletableFuture()
        .get(10, TimeUnit.SECONDS);

    assertThat(((InBodyConvertDocumentResponse) response).getDocument().getMarkdownContent()).isEqualTo("# Dev");
    getWireMock().verify(2, getRequestedFor(urlPathEqualTo(POLL_PATH)));
    getWireMock().verify(1, getRequestedFor(urlPathEqualTo(RESULT_PATH)));
    assertThat(this.executions).hasValue(0);
  }

  @Test
  void toBuilderKeepsAsyncExecutor() throws Exception {
    stubTask("/v1/convert/source/async", "success");
    getWireMock().stubFor(get(urlPathEqualTo(RESULT_PATH)).willReturn(okJson(CONVERT_RESULT)));

    DoclingServeApi client = client(this.recordingExecutor).toBuilder().build();

    client.convertSourceAsync(convertRequest())
        .toCompletableFuture()
        .get(10, TimeUnit.SECONDS);

    assertThat(this.executions).hasValue(SUCCESSFUL_TASK_SUBMISSIONS);
  }

  @Test
  void nullAsyncExecutorIsRejected() {
    assertThatThrownBy(() -> newClientBuilder().asyncExecutor(null))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("asyncExecutor");
  }

  private DoclingServeApi client(@Nullable Executor asyncExecutor) {
    var builder = newClientBuilder()
        .baseUrl(getWireMock().baseUrl())
        .asyncPollInterval(Duration.ofMillis(50))
        .asyncTimeout(Duration.ofSeconds(10));

    if (asyncExecutor != null) {
      builder.asyncExecutor(asyncExecutor);
    }

    return builder.build();
  }

  // The first poll reports the task as still running, which makes the client schedule a delayed re-poll
  private void stubTask(String submitPath, String finalStatus) {
    var wireMock = getWireMock();

    wireMock.stubFor(
        post(urlPathEqualTo(submitPath))
            .willReturn(okJson(taskStatus("pending")))
    );

    wireMock.stubFor(
        get(urlPathEqualTo(POLL_PATH))
            .inScenario("polling")
            .whenScenarioStateIs(Scenario.STARTED)
            .willReturn(okJson(taskStatus("started")))
            .willSetStateTo(POLLED_ONCE)
    );

    wireMock.stubFor(
        get(urlPathEqualTo(POLL_PATH))
            .inScenario("polling")
            .whenScenarioStateIs(POLLED_ONCE)
            .willReturn(okJson(taskStatus(finalStatus)))
    );
  }

  private static ConvertDocumentRequest convertRequest() {
    return ConvertDocumentRequest.builder()
        .source(HttpSource.builder().url(URI.create("https://docs.arconia.io/arconia-cli/latest/development/dev/")).build())
        .build();
  }

  private static String taskStatus(String status) {
    return """
        {
          "task_id": "%s",
          "task_status": "%s"
        }
        """.formatted(TASK_ID, status);
  }
}
