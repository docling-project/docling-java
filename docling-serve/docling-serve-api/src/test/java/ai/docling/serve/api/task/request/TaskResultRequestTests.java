package ai.docling.serve.api.task.request;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.Test;

class TaskResultRequestTests {
  @Test
  void nullTaskId() {
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> TaskResultRequest.builder().taskId(null).build())
        .withMessage("taskId is marked non-null but is null");
  }

  @Test
  void noTaskId() {
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> TaskResultRequest.builder().build())
        .withMessage("taskId is marked non-null but is null");
  }
}
