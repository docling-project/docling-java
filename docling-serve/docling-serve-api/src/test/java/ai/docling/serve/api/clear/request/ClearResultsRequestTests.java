package ai.docling.serve.api.clear.request;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;

import ai.docling.serve.api.auth.Authentication;

class ClearResultsRequestTests {
  @Test
  void nullOlderThen() {
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> ClearResultsRequest.builder().olderThen(null).build())
        .withMessage("olderThen is marked non-null but is null");
  }

  @Test
  void defaultOlderThen() {
    var request = ClearResultsRequest.builder().build();

    assertThat(request)
        .isNotNull()
        .extracting(ClearResultsRequest::getOlderThen)
        .asInstanceOf(InstanceOfAssertFactories.DURATION)
        .isEqualByComparingTo(ClearResultsRequest.DEFAULT_OLDER_THAN);

    assertThat(request.getAuthentication())
        .isNotNull()
        .extracting(Authentication::getApiKey)
        .isNull();
  }

  @Test
  void buildWithAuth() {
    var request = ClearResultsRequest.builder()
        .authentication(Authentication.builder().apiKey("key").build())
        .build();

    assertThat(request.getAuthentication())
        .isNotNull()
        .extracting(Authentication::getApiKey)
        .isEqualTo("key");
  }
}
