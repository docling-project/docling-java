package ai.docling.serve.api.task.request;

import java.time.Duration;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ai.docling.serve.api.auth.AuthenticatedRequest;
import ai.docling.serve.api.auth.Authentication;

/**
 * Represents a request for polling the status of a task.
 *
 * This class encapsulates the information required to poll the status of a specific task.
 * It includes the unique task identifier and an optional wait time between consecutive
 * polling attempts. Instances of this class are immutable and can be built using the
 * provided builder methods.
 *
 * Attributes:
 * - `DEFAULT_STATUS_POLL_WAIT_TIME`: Specifies the default value for the wait time
 *   between polling attempts. By default, this is set to {@link Duration#ZERO}, indicating
 *   no delay between consecutive polling attempts.
 * - `taskId`: The unique identifier of the task whose status is being polled. This field is
 *   mandatory and must not be null.
 * - `waitTime`: The duration to wait between consecutive polling attempts. If not explicitly
 *   set, it defaults to {@link #DEFAULT_STATUS_POLL_WAIT_TIME}.
 */
@lombok.Builder(toBuilder = true)
@lombok.Getter
@lombok.ToString
public class TaskStatusPollRequest implements AuthenticatedRequest {
  /**
   * The default wait time between status polling attempts for a task.
   * <p>
   * This value is used when no explicit wait time is specified in a
   * {@code TaskStatusPollRequest} instance. It is set to {@link Duration#ZERO},
   * meaning there is no delay by default between consecutive polling attempts.
   * </p>
   */
  public static final Duration DEFAULT_STATUS_POLL_WAIT_TIME = Duration.ZERO;

  @lombok.NonNull
  private String taskId;

  @lombok.NonNull
  @lombok.Builder.Default
  private Duration waitTime = DEFAULT_STATUS_POLL_WAIT_TIME;

  @JsonIgnore
  @lombok.NonNull
  @lombok.Builder.Default
  private Authentication authentication = Authentication.builder().build();
}
