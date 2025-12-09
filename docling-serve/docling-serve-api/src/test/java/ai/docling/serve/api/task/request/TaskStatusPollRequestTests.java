package ai.docling.serve.api.task.request;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.Duration;

import org.junit.jupiter.api.Test;

class TaskStatusPollRequestTests {
  @Test
  void whenTaskIdIsNullThenThrow() {
    assertThatThrownBy(() -> TaskStatusPollRequest.builder().waitTime(Duration.ofMinutes(1)).build())
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("taskId is marked non-null but is null");
  }

  @Test
  void whenWaitTimeIsNullThenThrow() {
    assertThatThrownBy(() -> TaskStatusPollRequest.builder().taskId("1234").waitTime(null).build())
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("waitTime is marked non-null but is null");
  }

  @Test
  void defaults() {
    var request = TaskStatusPollRequest.builder().taskId("1234").build();

    assertThat(request.getWaitTime())
        .isNotNull()
        .isEqualByComparingTo(TaskStatusPollRequest.DEFAULT_STATUS_POLL_WAIT_TIME);

    assertThat(request.getTaskId())
        .isEqualTo("1234");
  }
}
