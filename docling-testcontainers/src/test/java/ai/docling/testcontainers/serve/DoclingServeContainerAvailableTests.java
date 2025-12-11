package ai.docling.testcontainers.serve;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.IOException;
import java.net.http.HttpResponse;

import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import ai.docling.serve.api.auth.Authentication;
import ai.docling.serve.api.clear.request.ClearResultsRequest;
import ai.docling.serve.api.clear.response.ClearResponse;
import ai.docling.serve.api.health.HealthCheckResponse;
import ai.docling.serve.client.DoclingServeClientBuilderFactory;
import ai.docling.serve.client.DoclingServeClientException;
import ai.docling.testcontainers.serve.config.DoclingServeContainerConfig;

import tools.jackson.databind.json.JsonMapper;

@Testcontainers
class DoclingServeContainerAvailableTests {
  private static final JsonMapper JSON_MAPPER = JsonMapper.builder().build();
  private static final String DEFAULT_API_KEY = "default-api-key";

  @Container
  private final DoclingServeContainer doclingContainer = new DoclingServeContainer(
    DoclingServeContainerConfig.builder()
        .image(DoclingServeContainerConfig.DOCLING_IMAGE)
        .enableUi(true)
        .build()
  );

  @Container
  private final DoclingServeContainer noUiDoclingContainer = new DoclingServeContainer(
    DoclingServeContainerConfig.builder()
        .image(DoclingServeContainerConfig.DOCLING_IMAGE)
        .enableUi(false)
        .build()
  );

  @Container
  private final DoclingServeContainer withApiKeyDoclingContainer = new DoclingServeContainer(
      DoclingServeContainerConfig.builder()
          .image(DoclingServeContainerConfig.DOCLING_IMAGE)
          .enableUi(true)
          .apiKey(DEFAULT_API_KEY)
          .build()
  );

  @Test
  void containerNoUI() {
    var client = DoclingServeClientBuilderFactory.newBuilder()
        .baseUrl(this.noUiDoclingContainer.getApiUrl())
        .build();

    assertThat(client.health())
        .isNotNull()
        .extracting(HealthCheckResponse::getStatus)
        .isEqualTo("ok");

    assertThat(this.noUiDoclingContainer.getUiUrl())
        .isNotNull()
        .isEmpty();
  }

  @Test
  void containerAvailable() throws IOException, InterruptedException {
    var client = DoclingServeClientBuilderFactory.newBuilder()
        .baseUrl(this.noUiDoclingContainer.getApiUrl())
        .build();

    assertThat(client.health())
        .isNotNull()
        .extracting(HealthCheckResponse::getStatus)
        .isEqualTo("ok");

    assertThat(this.doclingContainer.getUiUrl())
        .get()
        .isEqualTo("%s/ui".formatted(this.doclingContainer.getApiUrl()));
  }

  @Test
  void containerWithApiKeyClientWithout() {
    var client = DoclingServeClientBuilderFactory.newBuilder()
        .baseUrl(this.withApiKeyDoclingContainer.getApiUrl())
        .logRequests()
        .logResponses()
        .prettyPrint()
        .build();

    assertThatThrownBy(() -> client.clearResults(ClearResultsRequest.builder().build()))
        .hasRootCauseInstanceOf(DoclingServeClientException.class)
        .rootCause()
        .hasMessageContaining("Unauthorized")
        .extracting(t -> ((DoclingServeClientException) t).getStatusCode())
        .isEqualTo(401);
  }

  @Test
  void containerWithoutApiKeyClientWith() {
    var client = DoclingServeClientBuilderFactory.newBuilder()
        .baseUrl(this.doclingContainer.getApiUrl())
        .logRequests()
        .logResponses()
        .prettyPrint()
        .build();

    var auth = Authentication.builder().apiKey(DEFAULT_API_KEY).build();

    assertThat(client.clearResults(ClearResultsRequest.builder().authentication(auth).build()))
        .isNotNull()
        .extracting(ClearResponse::getStatus)
        .isEqualTo("ok");
  }

  @Test
  void containerWithApiKeyClientWith() {
    var client = DoclingServeClientBuilderFactory.newBuilder()
        .baseUrl(this.withApiKeyDoclingContainer.getApiUrl())
        .logRequests()
        .logResponses()
        .prettyPrint()
        .build();

    var auth = Authentication.builder().apiKey(DEFAULT_API_KEY).build();

    assertThat(client.clearResults(ClearResultsRequest.builder().authentication(auth).build()))
        .isNotNull()
        .extracting(ClearResponse::getStatus)
        .isEqualTo("ok");
  }

  private static <T> HttpResponse.BodyHandler<T> jsonBodyHandler(Class<T> type) {
    return responseInfo -> HttpResponse.BodySubscribers.mapping(
        HttpResponse.BodySubscribers.ofByteArray(),
        bytes -> {
          try {
            return JSON_MAPPER.readValue(bytes, type);
          } catch (Exception e) {
            throw new RuntimeException(e);
          }
        }
    );
  }
}
