package ai.docling.serve.api;

import ai.docling.serve.api.health.HealthCheckResponse;

/**
 * Interface for performing health checks on the Docling service API.
 * This API is designed to verify and report the operational status of the service.
 */
public interface DoclingServeHealthApi {
    /**
   * Executes a health check for the API and retrieves the health status of the service.
   *
   * @return a {@link HealthCheckResponse} object containing the health status of the API.
   */
  HealthCheckResponse health();
}
