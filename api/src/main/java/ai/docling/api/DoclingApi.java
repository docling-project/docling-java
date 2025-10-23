package ai.docling.api;

import ai.docling.api.convert.request.ConvertDocumentRequest;
import ai.docling.api.convert.response.ConvertDocumentResponse;
import ai.docling.api.health.HealthCheckResponse;

/**
 * Docling API interface.
 */
public interface DoclingApi {

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
  <T extends DoclingApi, B extends DoclingApiBuilder<T, B>> DoclingApiBuilder<T, B> toBuilder();

  /**
   * A builder interface for constructing implementations of {@link DoclingApi}. This interface
   * supports a fluent API for setting configuration properties before building an instance.
   *
   * @param <T> the type of the {@link DoclingApi} implementation being built.
   * @param <B> the type of the concrete builder implementation.
   */
  interface DoclingApiBuilder<T extends DoclingApi, B extends DoclingApiBuilder<T, B>> {
    /**
     * Builds and returns an instance of the specified type, representing the completed configuration
     * of the builder. The returned instance is typically an implementation of the Docling API.
     *
     * @return an instance of type {@code T} representing a configured Docling API client.
     */
    T build();
  }
}
