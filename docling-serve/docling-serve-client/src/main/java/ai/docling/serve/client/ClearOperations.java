package ai.docling.serve.client;

import java.time.Duration;
import java.util.Optional;

import org.jspecify.annotations.Nullable;

import ai.docling.serve.api.DoclingServeClearApi;
import ai.docling.serve.api.clear.response.ClearResponse;

/**
 * Base class for clear API operations. Provides functionality for managing and cleaning up
 * converters and stale data retained by the service.
 */
final class ClearOperations implements DoclingServeClearApi {
  private final HttpOperations httpOperations;

  ClearOperations(HttpOperations httpOperations) {
    this.httpOperations = httpOperations;
  }

  /**
   * Clears all registered converters associated with the API.
   */
  public ClearResponse clearConverters() {
    return this.httpOperations.executeGet("/v1/clear/converters", ClearResponse.class);
  }

  /**
   * Clears the results stored by the service that are older than the specified duration.
   *
   * @param olderThen the {@link Duration} indicating the age threshold. Results older than
   *                  this duration will be cleared.
   * @return a {@link ClearResponse} containing information about the outcome of the clear operation.
   */
  public ClearResponse clearResults(@Nullable Duration olderThen) {
    var olderThenSeconds = Optional.ofNullable(olderThen)
        .orElse(DEFAULT_OLDER_THAN)
        .toSeconds();

    return this.httpOperations.executeGet("/v1/clear/results?older_then=%d".formatted(olderThenSeconds), ClearResponse.class);
  }
}
