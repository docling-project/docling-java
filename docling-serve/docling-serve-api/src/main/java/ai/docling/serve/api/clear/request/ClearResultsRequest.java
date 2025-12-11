package ai.docling.serve.api.clear.request;

import java.time.Duration;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ai.docling.serve.api.auth.AuthenticatedRequest;
import ai.docling.serve.api.auth.Authentication;

/**
 * Represents a request to clear stale data via the Docling Serve Clear API.
 * This class provides a mechanism to specify a threshold duration, after which data
 * is considered stale and eligible for cleanup.
 *
 * The {@code olderThen} field specifies the duration threshold, with a default
 * value of {@link #DEFAULT_OLDER_THAN}, predefined as 1 hour (3600 seconds).
 * This default can be overridden using the builder or by explicitly passing a custom
 * duration at runtime.
 *
 * Instances of this class are immutable and can be created or modified through its builder.
 */
@lombok.Builder(toBuilder = true)
@lombok.Getter
@lombok.ToString
public class ClearResultsRequest implements AuthenticatedRequest {
  /**
   * Represents the default duration used as a threshold for clearing stale results
   * or data in the Docling Serve Clear API. Results older than this duration
   * are considered stale and may be subject to cleanup.
   *
   * The value is predefined as 1 hour (3600 seconds).
   */
  public static final Duration DEFAULT_OLDER_THAN = Duration.ofSeconds(3600);

  @lombok.NonNull
  @lombok.Builder.Default
  private Duration olderThen = DEFAULT_OLDER_THAN;

  @JsonIgnore
  @lombok.NonNull
  @lombok.Builder.Default
  private Authentication authentication = Authentication.builder().build();
}
