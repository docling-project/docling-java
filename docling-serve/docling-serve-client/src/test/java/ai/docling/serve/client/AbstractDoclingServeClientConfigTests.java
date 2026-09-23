package ai.docling.serve.client;

import static ai.docling.serve.api.DoclingServeApiConfig.API_KEY;
import static ai.docling.serve.api.DoclingServeApiConfig.ASYNC_EXECUTOR;
import static ai.docling.serve.api.DoclingServeApiConfig.ASYNC_POLL_INTERVAL;
import static ai.docling.serve.api.DoclingServeApiConfig.ASYNC_TIMEOUT;
import static ai.docling.serve.api.DoclingServeApiConfig.BASE_URL;
import static ai.docling.serve.api.DoclingServeApiConfig.CONNECT_TIMEOUT;
import static ai.docling.serve.api.DoclingServeApiConfig.LOG_REQUESTS;
import static ai.docling.serve.api.DoclingServeApiConfig.LOG_RESPONSES;
import static ai.docling.serve.api.DoclingServeApiConfig.PRETTY_PRINT;
import static ai.docling.serve.api.DoclingServeApiConfig.READ_TIMEOUT;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.temporaryRedirect;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.time.Duration;
import java.util.concurrent.Executor;

import org.junit.jupiter.api.Test;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;

import ai.docling.serve.api.DoclingServeApi;
import ai.docling.serve.api.DoclingServeApiConfig;
import ai.docling.serve.api.health.HealthCheckResponse;

/**
 * Tests for {@link DoclingServeClient#config()}, and for the settings kept by the {@code toBuilder()}
 * method of the client.
 */
abstract class AbstractDoclingServeClientConfigTests {
  // These tests never run async operations
  private static final Executor EXECUTOR = command -> {
    throw new UnsupportedOperationException("Not expected to run");
  };

  protected abstract WireMockExtension getWireMock();

  protected abstract DoclingServeClient.DoclingServeClientBuilder<?, ?> newClientBuilder();

  @Test
  void configReportsEverySetting() {
    var config = fullyConfiguredClient().config();

    assertThat(config)
        .returns(URI.create(getWireMock().baseUrl()), DoclingServeApiConfig::baseUrl)
        .returns("key", DoclingServeApiConfig::apiKey)
        .returns(true, DoclingServeApiConfig::logRequests)
        .returns(true, DoclingServeApiConfig::logResponses)
        .returns(true, DoclingServeApiConfig::prettyPrint)
        .returns(Duration.ofSeconds(7), DoclingServeApiConfig::connectTimeout)
        .returns(Duration.ofSeconds(11), DoclingServeApiConfig::readTimeout)
        .returns(Duration.ofSeconds(3), DoclingServeApiConfig::asyncPollInterval)
        .returns(Duration.ofSeconds(13), DoclingServeApiConfig::asyncTimeout)
        .returns(EXECUTOR, DoclingServeApiConfig::asyncExecutor);

    assertThat(config.explicitlySetOptions())
        .containsExactly(BASE_URL, API_KEY, LOG_REQUESTS, LOG_RESPONSES, PRETTY_PRINT, CONNECT_TIMEOUT, READ_TIMEOUT, ASYNC_POLL_INTERVAL, ASYNC_TIMEOUT, ASYNC_EXECUTOR);
  }

  @Test
  void configOfAnUnconfiguredClientReportsTheDefaults() {
    var config = newClientBuilder()
        .build()
        .config();

    assertThat(config)
        .returns(URI.create("http://localhost:5001"), DoclingServeApiConfig::baseUrl)
        .returns(null, DoclingServeApiConfig::apiKey)
        .returns(false, DoclingServeApiConfig::logRequests)
        .returns(false, DoclingServeApiConfig::logResponses)
        .returns(false, DoclingServeApiConfig::prettyPrint)
        .returns(Duration.ofSeconds(5), DoclingServeApiConfig::connectTimeout)
        .returns(Duration.ofSeconds(30), DoclingServeApiConfig::readTimeout)
        .returns(Duration.ofSeconds(2), DoclingServeApiConfig::asyncPollInterval)
        .returns(Duration.ofMinutes(5), DoclingServeApiConfig::asyncTimeout)
        .returns(null, DoclingServeApiConfig::asyncExecutor);

    assertThat(config.explicitlySetOptions()).isEmpty();
  }

  @Test
  void configRoundTripsThroughTheProvider() {
    var config = fullyConfiguredClient().config();

    assertThat(config.toBuilder().build().config()).isEqualTo(config);
  }

  @Test
  void clientBuiltThroughTheProviderReportsOnlyTheOptionsThatWereSet() {
    var client = DoclingServeApi.builder()
        .baseUrl(getWireMock().baseUrl())
        .apiKey("key")
        .build();

    assertThat(client.config().explicitlySetOptions()).containsExactly(BASE_URL, API_KEY);
  }

  @Test
  void configReplacesTheSharedOptions() {
    var config = DoclingServeApi.builder()
        .apiKey("key")
        .config();

    var client = newClientBuilder()
        .logRequests()
        .config(config)
        .build();

    assertThat(client.config()).isEqualTo(config);
  }

  @Test
  @SuppressWarnings("removal")
  void toBuilderKeepsEverySetting() {
    var client = fullyConfiguredClient();

    assertThat(client.toBuilder().build().config()).isEqualTo(client.config());
  }

  @Test
  @SuppressWarnings("removal")
  void toBuilderKeepsFollowingRedirects() {
    getWireMock().stubFor(get(urlPathEqualTo("/health")).willReturn(temporaryRedirect("/moved/health")));
    getWireMock().stubFor(get(urlPathEqualTo("/moved/health")).willReturn(okJson("{\"status\": \"ok\"}")));

    var client = newClientBuilder()
        .baseUrl(getWireMock().baseUrl())
        .build();

    // The original client follows the redirect, so a copy failing to do so can only be the copy's fault
    assertThat(client.health())
        .extracting(HealthCheckResponse::getStatus)
        .isEqualTo("ok");

    assertThat(client.toBuilder().build().health())
        .extracting(HealthCheckResponse::getStatus)
        .isEqualTo("ok");
  }

  private DoclingServeClient fullyConfiguredClient() {
    return newClientBuilder()
        .baseUrl(getWireMock().baseUrl())
        .apiKey("key")
        .logRequests()
        .logResponses()
        .prettyPrint()
        .connectTimeout(Duration.ofSeconds(7))
        .readTimeout(Duration.ofSeconds(11))
        .asyncPollInterval(Duration.ofSeconds(3))
        .asyncTimeout(Duration.ofSeconds(13))
        .asyncExecutor(EXECUTOR)
        .build();
  }
}
