package ai.docling.serve.api;

import static ai.docling.serve.api.util.ValidationUtils.ensureNotBlank;

import java.net.URI;
import java.time.Duration;
import java.util.stream.Collectors;

import org.jspecify.annotations.Nullable;

import ai.docling.serve.api.convert.request.ConvertDocumentRequest;
import ai.docling.serve.api.spi.DoclingServeApiBuilderFactory;
import ai.docling.serve.api.spi.ServiceLoaderHelper;

/**
 * Docling Serve API interface.
 */
public interface DoclingServeApi
    extends DoclingServeHealthApi, DoclingServeConvertApi, DoclingServeChunkApi, DoclingServeClearApi, DoclingServeTaskApi {

  /**
   * Creates and returns a builder instance capable of constructing implementations of {@link DoclingServeApi}.
   * The method ensures that exactly one factory capable of building a builder instance is available
   * via the {@link DoclingServeApiBuilderFactory} interface.
   *
   * If no factories are found, or if multiple factories are found, an {@link IllegalStateException} is thrown.
   *
   * @param <T> the type of the {@link DoclingServeApi} implementation being built
   * @param <B> the type of the builder implementation for the {@link DoclingServeApi}
   * @return a builder instance of type {@code B} constructed using the available factory
   * @throws IllegalStateException if no factories or more than one factory are found
   */
  static <T extends DoclingServeApi, B extends DoclingApiBuilder<T, B>> B builder() {
    var factories = ServiceLoaderHelper.loadFactories(DoclingServeApiBuilderFactory.class);

    if (factories.isEmpty()) {
      // No factory found
      throw new IllegalStateException("No instance of %s found to build a %s instance. You are probably missing a library on your classpath.".formatted(DoclingServeApiBuilderFactory.class.getName(), DoclingApiBuilder.class.getName()));
    }

    if (factories.size() > 1) {
      // Multiple factories found
      throw new IllegalStateException("Multiple instances of %s found to build a %s instance: [%s]".formatted(DoclingServeApiBuilderFactory.class.getName(), DoclingApiBuilder.class.getName(), factories.stream().map(f -> f.getClass().getName()).collect(Collectors.joining(", "))));
    }

    // Only 1 factory (what we want)
    return factories.iterator().next().getBuilder();
  }

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
     * Sets the base URL for the client.
     *
     * <p>This method configures the base URL that will be used for all API requests
     * executed by the client. The provided URL must be non-null and not blank.
     *
     * @param baseUrl the base URL to use, as a {@code String}
     * @return this builder instance for method chaining
     * @throws IllegalArgumentException if {@code baseUrl} is null, blank, or invalid
     */
    default B baseUrl(String baseUrl) {
      return baseUrl(URI.create(ensureNotBlank(baseUrl, "baseUrl")));
    }

    /**
     * Sets the base URL for the client.
     *
     * <p>This method configures the base URL that will be used for all API requests
     * executed by the client. The provided URL must be non-null.
     *
     * @param baseUrl the base URL to use, as a {@link URI}
     * @return this builder instance for method chaining
     */
    B baseUrl(URI baseUrl);

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
     * Timeout to establish a connection to the Docling Serve API.
     */
    B connectTimeout(Duration connectTimeout);

    /**
     * Timeout for receiving a response from the Docling Serve API.
     */
    B readTimeout(Duration readTimeout);

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
