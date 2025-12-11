package ai.docling.serve.api.clear.request;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import ai.docling.serve.api.auth.Authentication;

class ClearConvertersRequestTests {
  @Test
  void noAuthSpecified() {
    assertThat(ClearConvertersRequest.builder().build())
        .isNotNull()
        .extracting(ClearConvertersRequest::getAuthentication)
        .isNotNull()
        .extracting(Authentication::getApiKey)
        .isNull();
  }

  @Test
  void authSpecified() {
    assertThat(ClearConvertersRequest.builder().authentication(Authentication.builder().apiKey("key").build()).build())
        .isNotNull()
        .extracting(ClearConvertersRequest::getAuthentication)
        .isNotNull()
        .extracting(Authentication::getApiKey)
        .isEqualTo("key");
  }
}
