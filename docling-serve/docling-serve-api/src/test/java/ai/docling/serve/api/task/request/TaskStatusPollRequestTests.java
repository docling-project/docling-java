package ai.docling.serve.api.task.request;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;

import ai.docling.serve.api.auth.Authentication;

class TaskStatusPollRequestTests {
  @Test
  void nullTaskId() {
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> TaskStatusPollRequest.builder().taskId(null).build())
        .withMessage("taskId is marked non-null but is null");
  }

  @Test
  void noTaskId() {
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> TaskStatusPollRequest.builder().build())
        .withMessage("taskId is marked non-null but is null");
  }

  @Test
  void nullWaitTime() {
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> TaskStatusPollRequest.builder().waitTime(null).build())
        .withMessage("waitTime is marked non-null but is null");
  }

  @Test
  void defaultWaitTime() {
    var request = TaskStatusPollRequest.builder().taskId("1").build();
    assertThat(request)
        .isNotNull()
        .extracting(TaskStatusPollRequest::getWaitTime)
        .asInstanceOf(InstanceOfAssertFactories.DURATION)
        .isEqualByComparingTo(TaskStatusPollRequest.DEFAULT_STATUS_POLL_WAIT_TIME);

    assertThat(request.getAuthentication())
        .isNotNull()
        .extracting(Authentication::getApiKey)
        .isNull();
  }

  @Test
  void buildWithoutAuth() {
    var request = TaskStatusPollRequest.builder().taskId("1").build();

    assertThat(request.getTaskId()).isEqualTo("1");
    assertThat(request.getAuthentication())
        .isNotNull()
        .extracting(Authentication::getApiKey)
        .isNull();
  }

  @Test
  void buildWithAuth() {
    var request = TaskStatusPollRequest.builder()
        .taskId("1")
        .authentication(Authentication.builder().apiKey("key").build())
        .build();

    assertThat(request.getAuthentication())
        .isNotNull()
        .extracting(Authentication::getApiKey)
        .isEqualTo("key");
  }
}
