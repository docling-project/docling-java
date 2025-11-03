package ai.docling.api.serve.convert.request.source;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link HttpSource}.
 */
class HttpSourceTests {

  @Test
  void whenUrlIsNullThenThrow() {
    assertThatThrownBy(() -> HttpSource.builder().build())
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("url is marked non-null but is nul");
  }

  @Test
  void whenValidParametersThenCreateHttpSource() {
    URI url = URI.create("https://example.com/document.pdf");
    Map<String, Object> headers = Map.of("Authorization", "Bearer token123");

    HttpSource httpSource = HttpSource.builder().url(url).headers(headers).build();

    assertThat(httpSource.getUrl()).isEqualTo(url);
    assertThat(httpSource.getHeaders()).isEqualTo(headers);
  }

  @Test
  void whenRequiredParametersThenCreateHttpSource() {
    URI url = URI.create("https://example.com/document.pdf");

    HttpSource httpSource = HttpSource.builder().url(url).build();

    assertThat(httpSource.getUrl()).isEqualTo(url);
    assertThat(httpSource.getHeaders()).isNotNull().isEmpty();
  }

  @Test
  void builderCreatesHttpSource() {
    URI url = URI.create("https://example.com/presentation.pptx");
    Map<String, Object> headers = Map.of("User-Agent", "test-agent");

    HttpSource httpSource = HttpSource.builder()
        .url(url)
        .headers(headers)
        .build();

    assertThat(httpSource.getUrl()).isEqualTo(url);
    assertThat(httpSource.getHeaders()).isEqualTo(headers);
  }

  @Test
  void httpSourceIsImmutable() {
    Map<String, Object> headers = new HashMap<>(Map.of("Authorization", "Bearer token123"));

    HttpSource httpSource = HttpSource.builder()
        .url(URI.create("https://example.com/test.pdf"))
        .headers(headers)
        .build();

    assertThat(httpSource.getHeaders()).isEqualTo(headers);

    headers.put("newStuff", "awesome");

    assertThat(httpSource.getHeaders()).size().isEqualTo(1);
    assertThat(httpSource.getHeaders()).containsEntry("Authorization", "Bearer token123");
    assertThat(httpSource.getHeaders()).doesNotContainKey("newStuff");
  }

}
