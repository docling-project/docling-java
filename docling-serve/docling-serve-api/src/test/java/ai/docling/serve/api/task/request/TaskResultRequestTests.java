package ai.docling.serve.api.task.request;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class TaskResultRequestTests {
  @Test
  void whenTaskIdIsNullThenThrow() {
    assertThatThrownBy(() -> TaskResultRequest.builder().build())
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("taskId is marked non-null but is null");
  }
}
