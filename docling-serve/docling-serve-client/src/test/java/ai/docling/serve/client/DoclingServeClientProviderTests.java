package ai.docling.serve.client;

import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;

import ai.docling.serve.api.DoclingServeApi;
import ai.docling.serve.api.convert.request.ConvertDocumentRequest;
import ai.docling.serve.api.convert.request.source.HttpSource;
import ai.docling.serve.api.health.HealthCheckResponse;
import ai.docling.serve.client.operations.HttpOperations;

/**
 * Tests that {@link DoclingServeClientProvider}, discovered through {@link DoclingServeApi#builder()},
 * applies the configuration to the client it creates.
 */
class DoclingServeClientProviderTests {
  @RegisterExtension
  static WireMockExtension wireMock = WireMockExtension.newInstance()
      .options(wireMockConfig().dynamicPort())
      .build();

  @Test
  void providerIsDiscoveredAndPrefersJackson3() {
    var client = DoclingServeApi.builder()
        .baseUrl(wireMock.baseUrl())
        .build();

    assertThat(client).isExactlyInstanceOf(DoclingServeJackson3Client.class);
  }

  @Test
  void providerDeclaresNoUnsupportedOption() {
    assertThat(new DoclingServeClientProvider().unsupportedOptions()).isEmpty();
  }

  @Test
  void baseUrlAndApiKeyAreApplied() {
    wireMock.stubFor(
        get(urlPathEqualTo("/path/health"))
            .withHeader(HttpOperations.API_KEY_HEADER_NAME, equalTo("key"))
            .willReturn(okJson("{\"status\": \"ok\"}")));

    var client = DoclingServeApi.builder()
        .baseUrl(wireMock.baseUrl() + "/path")
        .apiKey("key")
        .build();

    assertThat(client.health())
        .extracting(HealthCheckResponse::getStatus)
        .isEqualTo("ok");

    wireMock.verify(1, getRequestedFor(urlPathEqualTo("/path/health")).withHeader(HttpOperations.API_KEY_HEADER_NAME, equalTo("key")));
  }

  @Test
  void asyncExecutorIsApplied() throws Exception {
    wireMock.stubFor(post(urlPathEqualTo("/v1/convert/source/async")).willReturn(okJson(taskStatus("success"))));
    wireMock.stubFor(get(urlPathEqualTo("/v1/status/poll/task-1")).willReturn(okJson(taskStatus("success"))));
    wireMock.stubFor(get(urlPathEqualTo("/v1/result/task-1")).willReturn(okJson("""
        {
          "document": { "filename": "dev.html", "md_content": "# Dev" },
          "status": "success",
          "errors": [],
          "processing_time": 0.5,
          "timings": {}
        }
        """)));

    var executions = new AtomicInteger();

    var client = DoclingServeApi.builder()
        .baseUrl(wireMock.baseUrl())
        .asyncPollInterval(Duration.ofMillis(50))
        .asyncExecutor(command -> {
          executions.incrementAndGet();
          new Thread(command).start();
        })
        .build();

    client.convertSourceAsync(convertRequest())
        .toCompletableFuture()
        .get(10, TimeUnit.SECONDS);

    // If the provider dropped the executor, the client would use the CompletableFuture default and this would be 0
    assertThat(executions).hasPositiveValue();
  }

  private static ConvertDocumentRequest convertRequest() {
    return ConvertDocumentRequest.builder()
        .source(HttpSource.builder().url(URI.create("https://docs.arconia.io/arconia-cli/latest/development/dev/")).build())
        .build();
  }

  private static String taskStatus(String status) {
    return """
        {
          "task_id": "task-1",
          "task_status": "%s"
        }
        """.formatted(status);
  }
}
