package ai.docling.serve.api.clear.request;

import java.time.Duration;

/**
 * Represents a request to clear stale results or data in the Docling Serve Clear API.
 * This class stores the parameters required for defining the threshold duration
 * beyond which results are considered stale and subject to cleanup.
 *
 * The main parameter, {@code olderThan}, indicates the threshold duration,
 * while a default duration of 1 hour (3600 seconds) is provided through {@code DEFAULT_OLDER_THAN}.
 *
 * This class is designed to be immutable and uses the Builder pattern
 * to facilitate the creation of instances with customized configurations.
 *
 * Annotations are used to support JSON serialization and the deserialization process.
 */
@lombok.Builder(toBuilder = true)
@lombok.Getter
@lombok.ToString
public class ClearResultsRequest {
  /**
   * Represents the default duration used as a threshold for clearing stale results
   * or data in the Docling Serve Clear API. Results older than this duration
   * are considered stale and may be subject to cleanup.
   *
   * The value is predefined as 1 hour (3600 seconds).
   */
  public static final Duration DEFAULT_OLDER_THAN = Duration.ofSeconds(3600);

  @lombok.Builder.Default
  @lombok.NonNull
  private Duration olderThan = DEFAULT_OLDER_THAN;
}
