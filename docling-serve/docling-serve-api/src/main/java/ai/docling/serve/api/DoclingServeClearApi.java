package ai.docling.serve.api;

import java.time.Duration;

import org.jspecify.annotations.Nullable;

import ai.docling.serve.api.clear.response.ClearResponse;

/**
 * Interface representing the Docling Serve Clear API. This API provides functionality
 * for managing and cleaning up converters and stale data retained by the service.
 * It includes methods for clearing registered converters and stored results based
 * on specified thresholds or default configurations.
 */
public interface DoclingServeClearApi {
  /**
   * Represents the default duration used as a threshold for clearing stale results
   * or data in the Docling Serve Clear API. Results older than this duration
   * are considered stale and may be subject to cleanup.
   *
   * The value is predefined as 1 hour (3600 seconds).
   */
  Duration DEFAULT_OLDER_THAN = Duration.ofSeconds(3600);

  /**
   * Clears all registered converters associated with the API.
   * This method removes any previously configured or cached converters,
   * effectively resetting the converter state to an uninitialized state.
   * After invoking this method, no converters will be available until new ones are added or configured.
   */
  ClearResponse clearConverters();

  /**
   * Clears stored results that are older than the specified duration threshold.
   * This method is used for housekeeping to remove stale or outdated data from the system.
   *
   * @param olderThen the duration threshold; only results older than this duration will be cleared.
   * @return a {@link ClearResponse} object containing the status of the clear operation.
   */
  ClearResponse clearResults(@Nullable Duration olderThen);

  /**
   * Clears stored results that are older than the default duration threshold.
   * This method uses the pre-defined {@code DEFAULT_OLDER_THAN} as the threshold
   * to determine which results are considered stale and should be removed.
   *
   * @return a {@link ClearResponse} object containing the status of the clear operation.
   */
  default ClearResponse clearResults() {
    return clearResults(DEFAULT_OLDER_THAN);
  }
}
