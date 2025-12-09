package ai.docling.serve.api.clear.request;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class ClearResultsRequestTests {
  @Test
  void whenOlderThanIsNullThenThrow() {
    assertThatThrownBy(() -> ClearResultsRequest.builder().olderThan(null).build())
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("olderThan is marked non-null but is null");
  }

  @Test
  void defaultOlderThan() {
    assertThat(ClearResultsRequest.builder().build().getOlderThan())
        .isNotNull()
        .isEqualByComparingTo(ClearResultsRequest.DEFAULT_OLDER_THAN);
  }
}
