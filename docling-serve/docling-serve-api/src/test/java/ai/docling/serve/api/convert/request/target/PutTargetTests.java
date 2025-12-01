package ai.docling.serve.api.convert.request.target;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.net.URI;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link PutTarget}.
 */
class PutTargetTests {

  @Test
  void whenUriIsNullThenThrow() {
    assertThatThrownBy(() -> PutTarget.builder().build())
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("url is marked non-null but is nul");
  }

  @Test
  void whenValidParametersThenCreatePutTarget() {
    URI uri = URI.create("https://example.com/upload");

    PutTarget putTarget = PutTarget.builder().url(uri).build();

    assertThat(putTarget.getUrl()).isEqualTo(uri);
  }
}
