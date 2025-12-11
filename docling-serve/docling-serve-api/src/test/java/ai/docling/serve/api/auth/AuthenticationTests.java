package ai.docling.serve.api.auth;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class AuthenticationTests {
  @Test
  void nothingSpecified() {
    assertThat(Authentication.builder().build())
        .isNotNull()
        .extracting(Authentication::getApiKey)
        .isNull();
  }

  @Test
  void keySpecified() {
    assertThat(Authentication.builder().apiKey("key").build())
        .isNotNull()
        .extracting(Authentication::getApiKey)
        .isEqualTo("key");
  }
}
