package ai.docling.serve.client.operations;

import ai.docling.serve.api.DoclingServeHealthApi;
import ai.docling.serve.api.health.HealthCheckResponse;

/**
 * Base class for health API operations. Provides access to health check functionality
 * of the Docling service.
 */
public final class HealthOperations implements DoclingServeHealthApi {
  private final HttpOperations httpOperations;

  public HealthOperations(HttpOperations httpOperations) {
    this.httpOperations = httpOperations;
  }

  /**
   * Executes a health check for the API and retrieves the health status of the service.
   *
   * @return a {@link HealthCheckResponse} object containing the health status of the API.
   */
  public HealthCheckResponse health() {
    return this.httpOperations.executeGet("/health", HealthCheckResponse.class);
  }
}
