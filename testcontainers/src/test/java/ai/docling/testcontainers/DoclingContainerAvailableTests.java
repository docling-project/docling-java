package ai.docling.testcontainers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import tools.jackson.databind.ObjectMapper;

import ai.docling.testcontainers.config.DoclingContainerConfig;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
class DoclingContainerAvailableTests {
  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
  private record HealthResponse(String status) {}

  @Container
  private final DoclingContainer doclingContainer = new DoclingContainer(
    DoclingContainerConfig.builder()
        .imageName(DoclingContainerConfig.DOCLING_IMAGE)
        .enableUi(true)
        .build(),
      Optional.of(Duration.ofMinutes(2))
  );

  @Test
  void containerAvailable() throws IOException, InterruptedException {
    var healthRequest = HttpRequest.newBuilder(
        URI.create("http://localhost:%d/health".formatted(this.doclingContainer.getPort()))
    )
        .header("Accept", "application/json")
        .timeout(Duration.ofSeconds(10))
        .GET()
        .build();

    var response = HttpClient.newHttpClient()
        .send(healthRequest, jsonBodyHandler(HealthResponse.class))
        .body();

    assertThat(response)
        .isNotNull()
        .usingRecursiveComparison()
        .isEqualTo(new HealthResponse("ok"));
  }

  private static <T> HttpResponse.BodyHandler<T> jsonBodyHandler(Class<T> type) {
    return responseInfo -> HttpResponse.BodySubscribers.mapping(
        HttpResponse.BodySubscribers.ofByteArray(),
        bytes -> {
          try {
            return OBJECT_MAPPER.readValue(bytes, type);
          } catch (Exception e) {
            throw new RuntimeException(e);
          }
        }
    );
  }
}
