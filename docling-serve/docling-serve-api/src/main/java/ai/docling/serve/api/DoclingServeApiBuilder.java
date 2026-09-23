package ai.docling.serve.api;

import static ai.docling.serve.api.util.ValidationUtils.ensureNotBlank;
import static ai.docling.serve.api.util.ValidationUtils.ensureNotNull;
import static ai.docling.serve.api.util.ValidationUtils.ensurePositiveDuration;

import java.net.URI;
import java.time.Duration;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ai.docling.serve.api.spi.DoclingServeApiProvider;
import ai.docling.serve.api.spi.DoclingServeApiProvider.Unsupported;

/**
 * A fluent builder for a {@link DoclingServeApiConfig}, and for a {@link DoclingServeApi} created
 * from it by the available {@link DoclingServeApiProvider}.
 *
 * <p>Obtain an instance through {@link DoclingServeApi#builder()} or {@link DoclingServeApiConfig#toBuilder()}.
 * Every setter records its option as explicitly set, which is what {@link #build()} checks against
 * {@link DoclingServeApiProvider#unsupportedOptions()}.
 *
 * <p>Implementation-specific settings (for example a custom JSON mapper or HTTP client) are not
 * available here: use the builder of the concrete implementation directly for those.
 */
public final class DoclingServeApiBuilder {
  private static final Logger LOG = LoggerFactory.getLogger(DoclingServeApiBuilder.class);

  private final Map<ConfigOption<?>, Object> values;

  DoclingServeApiBuilder() {
    this(Map.of());
  }

  DoclingServeApiBuilder(Map<ConfigOption<?>, Object> values) {
    this.values = new LinkedHashMap<>(values);
  }

  /**
   * Sets the base URL of the Docling Serve API.
   *
   * @param baseUrl the base URL, as a {@code String}
   * @return this builder
   * @throws IllegalArgumentException if {@code baseUrl} is null, blank, or not a valid URI
   * @see DoclingServeApiConfig#BASE_URL
   */
  public DoclingServeApiBuilder baseUrl(String baseUrl) {
    return baseUrl(URI.create(ensureNotBlank(baseUrl, "baseUrl")));
  }

  /**
   * Sets the base URL of the Docling Serve API.
   *
   * @param baseUrl the base URL
   * @return this builder
   * @throws IllegalArgumentException if {@code baseUrl} is null
   * @see DoclingServeApiConfig#BASE_URL
   */
  public DoclingServeApiBuilder baseUrl(URI baseUrl) {
    return set(DoclingServeApiConfig.BASE_URL, ensureNotNull(baseUrl, "baseUrl"));
  }

  /**
   * Sets the API key used to authenticate requests.
   *
   * @param apiKey the API key, or {@code null} to unset it
   * @return this builder
   * @see DoclingServeApiConfig#API_KEY
   */
  public DoclingServeApiBuilder apiKey(@Nullable String apiKey) {
    return set(DoclingServeApiConfig.API_KEY, apiKey);
  }

  /**
   * Enables logging of requests.
   *
   * @return this builder
   * @see DoclingServeApiConfig#LOG_REQUESTS
   */
  public DoclingServeApiBuilder logRequests() {
    return logRequests(true);
  }

  /**
   * Sets whether requests are logged.
   *
   * @param logRequests {@code true} to log requests
   * @return this builder
   * @see DoclingServeApiConfig#LOG_REQUESTS
   */
  public DoclingServeApiBuilder logRequests(boolean logRequests) {
    return set(DoclingServeApiConfig.LOG_REQUESTS, logRequests);
  }

  /**
   * Enables logging of responses.
   *
   * @return this builder
   * @see DoclingServeApiConfig#LOG_RESPONSES
   */
  public DoclingServeApiBuilder logResponses() {
    return logResponses(true);
  }

  /**
   * Sets whether responses are logged.
   *
   * @param logResponses {@code true} to log responses
   * @return this builder
   * @see DoclingServeApiConfig#LOG_RESPONSES
   */
  public DoclingServeApiBuilder logResponses(boolean logResponses) {
    return set(DoclingServeApiConfig.LOG_RESPONSES, logResponses);
  }

  /**
   * Enables pretty-printing of JSON requests and responses.
   *
   * @return this builder
   * @see DoclingServeApiConfig#PRETTY_PRINT
   */
  public DoclingServeApiBuilder prettyPrint() {
    return prettyPrint(true);
  }

  /**
   * Sets whether JSON requests and responses are pretty-printed.
   *
   * @param prettyPrint {@code true} to pretty-print JSON
   * @return this builder
   * @see DoclingServeApiConfig#PRETTY_PRINT
   */
  public DoclingServeApiBuilder prettyPrint(boolean prettyPrint) {
    return set(DoclingServeApiConfig.PRETTY_PRINT, prettyPrint);
  }

  /**
   * Sets the timeout to establish a connection to the Docling Serve API.
   *
   * @param connectTimeout the connect timeout
   * @return this builder
   * @throws IllegalArgumentException if {@code connectTimeout} is null, zero or negative
   * @see DoclingServeApiConfig#CONNECT_TIMEOUT
   */
  public DoclingServeApiBuilder connectTimeout(Duration connectTimeout) {
    ensurePositiveDuration(connectTimeout, "connectTimeout");
    return set(DoclingServeApiConfig.CONNECT_TIMEOUT, connectTimeout);
  }

  /**
   * Sets the timeout for receiving a response from the Docling Serve API.
   *
   * @param readTimeout the read timeout
   * @return this builder
   * @throws IllegalArgumentException if {@code readTimeout} is null, zero or negative
   * @see DoclingServeApiConfig#READ_TIMEOUT
   */
  public DoclingServeApiBuilder readTimeout(Duration readTimeout) {
    ensurePositiveDuration(readTimeout, "readTimeout");
    return set(DoclingServeApiConfig.READ_TIMEOUT, readTimeout);
  }

  /**
   * Sets how frequently the status of an async task is polled, for example by
   * {@link DoclingServeApi#convertSourceAsync(ai.docling.serve.api.convert.request.ConvertDocumentRequest)}.
   *
   * @param asyncPollInterval the poll interval
   * @return this builder
   * @throws IllegalArgumentException if {@code asyncPollInterval} is null, zero or negative
   * @see DoclingServeApiConfig#ASYNC_POLL_INTERVAL
   */
  public DoclingServeApiBuilder asyncPollInterval(Duration asyncPollInterval) {
    ensurePositiveDuration(asyncPollInterval, "asyncPollInterval");
    return set(DoclingServeApiConfig.ASYNC_POLL_INTERVAL, asyncPollInterval);
  }

  /**
   * Sets the maximum time to wait for an async task to complete, for example in
   * {@link DoclingServeApi#convertSourceAsync(ai.docling.serve.api.convert.request.ConvertDocumentRequest)}.
   *
   * @param asyncTimeout the async timeout
   * @return this builder
   * @throws IllegalArgumentException if {@code asyncTimeout} is null, zero or negative
   * @see DoclingServeApiConfig#ASYNC_TIMEOUT
   */
  public DoclingServeApiBuilder asyncTimeout(Duration asyncTimeout) {
    ensurePositiveDuration(asyncTimeout, "asyncTimeout");
    return set(DoclingServeApiConfig.ASYNC_TIMEOUT, asyncTimeout);
  }

  /**
   * Sets the {@link Executor} used to run async operations: submitting the task, polling for its
   * status and retrieving its result. If not set, the default async executor of
   * {@link java.util.concurrent.CompletableFuture} is used.
   *
   * <p>The lifecycle of the executor is owned by the caller: it is never shut down. Avoid direct
   * executors such as {@code Runnable::run}, which would run blocking HTTP requests on the calling
   * thread and on the shared scheduler thread of {@link java.util.concurrent.CompletableFuture#delayedExecutor(long, java.util.concurrent.TimeUnit, Executor)}.
   *
   * @param asyncExecutor the executor to use for async operations
   * @return this builder
   * @throws IllegalArgumentException if {@code asyncExecutor} is null
   * @see DoclingServeApiConfig#ASYNC_EXECUTOR
   */
  public DoclingServeApiBuilder asyncExecutor(Executor asyncExecutor) {
    return set(DoclingServeApiConfig.ASYNC_EXECUTOR, ensureNotNull(asyncExecutor, "asyncExecutor"));
  }

  /**
   * Creates an immutable snapshot of the current configuration. Later changes to this builder do not
   * affect the returned configuration.
   *
   * @return the configuration
   */
  public DoclingServeApiConfig config() {
    return new DoclingServeApiConfig(this.values);
  }

  /**
   * Creates a {@link DoclingServeApi} from the current configuration, using the single available
   * {@link DoclingServeApiProvider}.
   *
   * <p>Before the provider is called, every explicitly set option the provider declares in
   * {@link DoclingServeApiProvider#unsupportedOptions()} is enforced: {@link Unsupported#WARN} options
   * are logged, and {@link Unsupported#FAIL} options cause an {@link UnsupportedConfigurationException}.
   *
   * @return a new {@link DoclingServeApi}
   * @throws IllegalStateException             if no provider or more than one provider is available
   * @throws UnsupportedConfigurationException if an explicitly set option is declared {@link Unsupported#FAIL} by the provider
   */
  public DoclingServeApi build() {
    return build(DoclingServeApiProviders.resolve());
  }

  // Package-private so the enforcement can be tested without the ServiceLoader
  DoclingServeApi build(DoclingServeApiProvider provider) {
    var config = config();
    enforce(provider, config);
    return provider.create(config);
  }

  private static void enforce(DoclingServeApiProvider provider, DoclingServeApiConfig config) {
    var unsupported = provider.unsupportedOptions();

    // Iterate in declaration order so that messages are deterministic
    var optionsByAction = DoclingServeApiConfig.ALL_OPTIONS
        .stream()
        .filter(config::isExplicitlySet)
        .collect(
            Collectors.groupingBy(
                option -> unsupported.getOrDefault(option, Unsupported.IGNORE), () -> new EnumMap<Unsupported, List<ConfigOption<?>>>(Unsupported.class), Collectors.toList()));

    var warnings = optionsByAction.getOrDefault(Unsupported.WARN, List.of());
    var failures = optionsByAction.getOrDefault(Unsupported.FAIL, List.of());

    if (!warnings.isEmpty()) {
      LOG.warn(
          "The following options were explicitly configured but are not supported by {} and will be ignored: [{}]", DoclingServeApiProviders.describe(provider), warnings.stream()
              .map(ConfigOption::name)
              .collect(Collectors.joining(", ")));
    }

    if (!failures.isEmpty()) {
      throw new UnsupportedConfigurationException(DoclingServeApiProviders.describe(provider), failures);
    }
  }

  private <T> DoclingServeApiBuilder set(ConfigOption<T> option, @Nullable T value) {
    Optional.ofNullable(value)
        .ifPresentOrElse(v -> this.values.put(option, v), () -> this.values.remove(option));

    return this;
  }
}
