package ai.docling.api.serve;

import ai.docling.api.serve.convert.request.ConvertDocumentRequest;
import ai.docling.api.serve.convert.response.ConvertDocumentResponse;
import ai.docling.api.serve.health.HealthCheckResponse;

/**
 * Docling Serve API interface.
 */
public interface DoclingServeApi {

  /**
   * Executes a health check for the API and retrieves the health status of the service.
   *
   * @return a {@link HealthCheckResponse} object containing the health status of the API.
   */
  HealthCheckResponse health();

  /**
   * Converts the provided document source(s) into a processed document based on the specified options.
   *
   * @param request the {@link ConvertDocumentRequest} containing the source(s), conversion options, and optional target.
   * @return a {@link ConvertDocumentResponse} containing the processed document data, processing details, and any errors.
   */
  ConvertDocumentResponse convertSource(ConvertDocumentRequest request);

  /**
   * Creates and returns a builder instance capable of constructing a duplicate or modified
   * version of the current API instance. The builder provides a customizable way to adjust
   * configuration or properties before constructing a new API instance.
   *
   * @return a {@link DoclingApiBuilder} initialized with the state of the current API instance.
   */
  <T extends DoclingServeApi, B extends DoclingApiBuilder<T, B>> DoclingApiBuilder<T, B> toBuilder();

  /**
   * A builder interface for constructing implementations of {@link DoclingServeApi}. This interface
   * supports a fluent API for setting configuration properties before building an instance.
   *
   * @param <T> the type of the {@link DoclingServeApi} implementation being built.
   * @param <B> the type of the concrete builder implementation.
   */
  interface DoclingApiBuilder<T extends DoclingServeApi, B extends DoclingApiBuilder<T, B>> {
    /**
     * Enables logging of requests for the API client being built. This can be useful for
     * debugging or monitoring the behavior of requests made to the API.
     *
     * <p>This method influences the logging behavior of requests initiated by the client
     * without specifying further configuration details.
     *
     * @return {@code this} builder instance for fluent API usage.
     */
    default B logRequests() {
      return logRequests(true);
    }

    /**
     * Configures whether logging of API requests is enabled or not. Logging can help monitor or debug
     * request communications with the API.
     *
     * @param logRequests {@code true} to enable request logging; {@code false} to disable it.
     * @return {@code this} builder instance for fluent API usage.
     */
    B logRequests(boolean logRequests);

    /**
     * Enables logging of responses for the API client being built. This can assist in debugging or
     * monitoring the behavior of responses received from the API.
     *
     * @return {@code this} builder instance for fluent API usage.
     */
    default B logResponses() {
      return logResponses(true);
    }

    /**
     * Configures whether logging of API responses is enabled or not. Logging can help monitor or debug
     * the responses received from the API.
     *
     * @param logResponses {@code true} to enable response logging; {@code false} to disable it.
     * @return {@code this} builder instance for fluent API usage.
     */
    B logResponses(boolean logResponses);

    /**
     * Builds and returns an instance of the specified type, representing the completed configuration
     * of the builder. The returned instance is typically an implementation of the Docling API.
     *
     * @return an instance of type {@code T} representing a configured Docling API client.
     */
    T build();
  }
}
