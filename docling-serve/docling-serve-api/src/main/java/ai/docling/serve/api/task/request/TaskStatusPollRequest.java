package ai.docling.serve.api.task.request;

import java.time.Duration;

/**
 * Represents a request to poll the status of a task within the Docling Task API.
 * This class encapsulates the information needed to query the status of an
 * asynchronous task, including task identification and poll wait time.
 *
 * The {@code TaskStatusPollRequest} supports serialization and deserialization
 * using Jackson, ensuring compatibility with JSON-based APIs. It also provides
 * a builder for constructing instances.
 *
 * Key attributes include:
 * - {@code taskId}: A unique identifier for the task whose status is to be polled.
 * - {@code waitTime}: An optional duration indicating the wait interval between
 *   poll attempts. Defaults to {@link #DEFAULT_STATUS_POLL_WAIT_TIME} if not specified.
 *
 * This class is designed to be immutable, with all properties being final and
 * accessible through getter methods. It supports features such as pretty-printing
 * via {@code toString} and JSON-specific annotations for seamless integration
 * with APIs consuming or producing JSON.
 *
 * Thread Safety: The class is thread-safe as it is immutable.
 */
@lombok.Builder(toBuilder = true)
@lombok.Getter
@lombok.ToString
public class TaskStatusPollRequest {
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

  @lombok.Builder.Default
  @lombok.NonNull
  private Duration waitTime = DEFAULT_STATUS_POLL_WAIT_TIME;
}
