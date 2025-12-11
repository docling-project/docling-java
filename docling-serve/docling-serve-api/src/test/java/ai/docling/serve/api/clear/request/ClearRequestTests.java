package ai.docling.serve.api.clear.request;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;

class ClearRequestTests {
  @Test
  void nullOlderThen() {
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> ClearRequest.builder().olderThen(null).build())
        .withMessage("olderThen is marked non-null but is null");
  }

  @Test
  void defaultOlderThen() {
    assertThat(ClearRequest.builder().build())
        .isNotNull()
        .extracting(ClearRequest::getOlderThen)
        .asInstanceOf(InstanceOfAssertFactories.DURATION)
        .isEqualByComparingTo(ClearRequest.DEFAULT_OLDER_THAN);
  }
}
