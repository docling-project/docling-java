package ai.docling.serve.api.health;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link HealthCheckResponse}.
 */
class HealthCheckResponseTests {

  @Test
  void whenValidParametersThenCreateHealthCheckResponse() {
    String status = "healthy";

    HealthCheckResponse response = HealthCheckResponse.builder()
        .status(status)
        .build();

    assertThat(response.getStatus()).isEqualTo(status);
  }

  @Test
  void whenStatusIsNullThenCreateHealthCheckResponse() {
    HealthCheckResponse response = HealthCheckResponse.builder().build();

    assertThat(response.getStatus()).isNull();
  }

  @Test
  void whenEmptyStatusThenCreateHealthCheckResponse() {
    String status = "";

    HealthCheckResponse response = HealthCheckResponse.builder()
        .status(status)
        .build();

    assertThat(response.getStatus()).isEqualTo(status);
  }

}
