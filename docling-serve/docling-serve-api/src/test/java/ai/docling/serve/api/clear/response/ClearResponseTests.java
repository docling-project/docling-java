package ai.docling.serve.api.clear.response;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ClearResponseTests {
  @Test
  void whenValidParametersThenCreateClearResponse() {
    String status = "healthy";

    ClearResponse response = ClearResponse.builder()
        .status(status)
        .build();

    assertThat(response.getStatus()).isEqualTo(status);
  }

  @Test
  void whenStatusIsNullThenCreateClearResponse() {
    ClearResponse response = ClearResponse.builder().build();

    assertThat(response.getStatus()).isNull();
  }

  @Test
  void whenEmptyStatusThenCreateClearResponse() {
    String status = "";

    ClearResponse response = ClearResponse.builder()
        .status(status)
        .build();

    assertThat(response.getStatus()).isEqualTo(status);
  }
}
