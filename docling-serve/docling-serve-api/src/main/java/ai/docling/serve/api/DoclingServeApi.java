package ai.docling.serve.api;

import java.time.Duration;

import org.jspecify.annotations.Nullable;

import ai.docling.serve.api.convert.request.ConvertDocumentRequest;

/**
 * Docling Serve API interface.
 */
public interface DoclingServeApi
    extends DoclingServeHealthApi, DoclingServeConvertApi, DoclingServeChunkApi, DoclingServeClearApi, DoclingServeTaskApi {

  /**
   * Creates and returns a builder instance capable of constructing a duplicate or modified
   * version of the current API instance. The builder provides a customizable way to adjust
   * configuration or properties before constructing a new API instance.
   *
   * @return a {@link DoclingApiBuilder} initialized with the state of the current API instance.
   */
  @SuppressWarnings("unchecked")
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
     * Sets the API key for authenticating requests made by the client being built.
     *
     * The provided API key will be used as a credential to authorize and authenticate
     * API requests. This method updates the configuration of the builder with the specified
     * API key and ensures that the API client includes it in its requests as required for
     * secure access to the API.
     *
     * @param apiKey the API key to be used for authenticating API requests
     * @return {@code this} builder instance for fluent API usage
     */
    B apiKey(@Nullable String apiKey);

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
     * Configures whether the API client should format JSON requests and responses in a "pretty" format.
     * Pretty formatting organizes the response data to improve readability,
     * typically by adding spacing and line breaks.
     *
     * This setting does not affect the functional content of the response but can
     * assist with debugging or human-readable output for development purposes.
     *
     * @param prettyPrint {@code true} to enable pretty-printing of JSON requests and responses;
     *                    {@code false} to use compact formatting.
     * @return {@code this} builder instance for fluent API usage.
     */
    B prettyPrint(boolean prettyPrint);

    /**
     * Configures the API client to format JSON requests and responses in a "pretty" format.
     * Pretty formatting improves readability by including spacing and line breaks.
     *
     * @return {@code this} builder instance for fluent API usage.
     */
    default B prettyPrint() {
      return prettyPrint(true);
    }

    /**
     * Sets the polling interval for async operations.
     *
     * <p>This configures how frequently the client will check the status of async
     * conversion tasks when using {@link DoclingServeApi#convertSourceAsync(ConvertDocumentRequest)} (ConvertDocumentRequest)}.
     *
     * @param asyncPollInterval the polling interval (must not be null or negative)
     * @return this builder instance for method chaining
     * @throws IllegalArgumentException if asyncPollInterval is null or negative
     */
    B asyncPollInterval(Duration asyncPollInterval);

    /**
     * Sets the timeout for async operations.
     *
     * <p>This configures the maximum time to wait for an async conversion task to complete
     * when using {@link DoclingServeApi#convertSourceAsync(ConvertDocumentRequest)} (ConvertDocumentRequest)}.
     *
     * @param asyncTimeout the timeout duration (must not be null or negative)
     * @return this builder instance for method chaining
     * @throws IllegalArgumentException if asyncTimeout is null or negative
     */
    B asyncTimeout(Duration asyncTimeout);

    /**
     * Builds and returns an instance of the specified type, representing the completed configuration
     * of the builder. The returned instance is typically an implementation of the Docling API.
     *
     * @return an instance of type {@code T} representing a configured Docling API client.
     */
    T build();
  }
}
