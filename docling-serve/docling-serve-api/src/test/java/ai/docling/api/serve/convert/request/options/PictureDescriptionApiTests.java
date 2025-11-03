package ai.docling.api.serve.convert.request.options;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.net.URI;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link PictureDescriptionApi}.
 */
class PictureDescriptionApiTests {

  @Test
  void createApiWithAllFields() {
    URI url = URI.create("https://api.example.com/vision");
    Map<String, Object> headers = Map.of("Authorization", "Bearer token123", "Content-Type", "application/json");
    Map<String, Object> params = Map.of("model", "gpt-4-vision", "max_tokens", 100);
    Duration timeout = Duration.ofSeconds(30);
    String prompt = "Describe this image in detail";
    Integer concurrency = 2;

    PictureDescriptionApi api = PictureDescriptionApi.builder()
        .url(url)
        .headers(headers)
        .params(params)
        .timeout(timeout)
        .concurrency(concurrency)
        .prompt(prompt)
        .build();

    assertThat(api.getUrl()).isEqualTo(url);
    assertThat(api.getHeaders()).isEqualTo(headers);
    assertThat(api.getParams()).isEqualTo(params);
    assertThat(api.getTimeout()).isEqualTo(timeout);
    assertThat(api.getConcurrency()).isEqualTo(concurrency);
    assertThat(api.getPrompt()).isEqualTo(prompt);
  }

  @Test
  void createApiWithOnlyRequiredFields() {
    URI url = URI.create("https://api.example.com/vision");

    PictureDescriptionApi api = PictureDescriptionApi.builder()
        .url(url)
        .build();

    assertThat(api.getUrl()).isEqualTo(url);
    assertThat(api.getHeaders()).isEmpty();
    assertThat(api.getParams()).isEmpty();
    assertThat(api.getTimeout()).isNull();
    assertThat(api.getPrompt()).isNull();
  }

  @Test
  void createApiWithNullUrlThrowsException() {
    assertThatThrownBy(() -> PictureDescriptionApi.builder().build())
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("url is marked non-null but is null");
  }

  @Test
  void pictureDescriptionApiIsImmutable() {
    URI url = URI.create("https://api.example.com/vision");
    Map<String, Object> headers = new HashMap<>(Map.of("Authorization", "Bearer original"));
    Map<String, Object> params = new HashMap<>(Map.of("model", "original-model"));

    PictureDescriptionApi api = PictureDescriptionApi.builder()
        .url(url)
        .headers(headers)
        .params(params)
        .timeout(Duration.ofSeconds(10))
        .concurrency(3)
        .prompt("Original prompt")
        .build();

    assertThat(api.getHeaders()).isEqualTo(headers);
    assertThat(api.getParams()).isEqualTo(params);

    headers.put("X-Custom-Header", "modified");
    params.put("temperature", 0.8);

    assertThat(api.getHeaders()).hasSize(1);
    assertThat(api.getHeaders().get("Authorization")).isEqualTo("Bearer original");
    assertThat(api.getParams()).hasSize(1);
    assertThat(api.getParams().get("model")).isEqualTo("original-model");
  }

}
