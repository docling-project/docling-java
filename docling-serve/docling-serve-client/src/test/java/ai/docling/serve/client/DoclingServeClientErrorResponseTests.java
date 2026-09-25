package ai.docling.serve.client;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;

import ai.docling.serve.api.DoclingServeApi;
import ai.docling.serve.api.validation.ValidationException;

class DoclingServeClientErrorResponseTests {
  @RegisterExtension
  static WireMockExtension wireMock = WireMockExtension.newInstance()
      .options(wireMockConfig().dynamicPort())
      .build();

  private DoclingServeApi client() {
    return DoclingServeApi.builder()
        .baseUrl("http://localhost:%d".formatted(wireMock.getRuntimeInfo().getHttpPort()))
        .build();
  }

  @Test
  void unprocessableEntityWithValidationBodyThrowsValidationException() {
    wireMock.stubFor(get(urlPathEqualTo("/health"))
        .willReturn(aResponse()
            .withStatus(422)
            .withHeader("Content-Type", "application/json")
            .withBody("{\"detail\": [{\"type\": \"missing\", \"loc\": [\"body\"], \"msg\": \"Field required\"}]}")));

    assertThatThrownBy(() -> client().health())
        .isInstanceOf(ValidationException.class)
        .hasMessageContaining("Field required");
  }

  @Test
  void unprocessableEntityWithNonValidationBodyKeepsStatusAndBody() {
    wireMock.stubFor(get(urlPathEqualTo("/health"))
        .willReturn(aResponse()
            .withStatus(422)
            .withHeader("Content-Type", "text/html")
            .withBody("<html>Unprocessable by gateway</html>")));

    assertThatThrownBy(() -> client().health())
        .isInstanceOfSatisfying(DoclingServeClientException.class, e -> {
          assertThat(e.getStatusCode()).isEqualTo(422);
          assertThat(e.getResponseBody()).isEqualTo("<html>Unprocessable by gateway</html>");
        });
  }
}
