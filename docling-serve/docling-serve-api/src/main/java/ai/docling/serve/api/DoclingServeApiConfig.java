package ai.docling.serve.api;

import java.net.URI;
import java.time.Duration;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.jspecify.annotations.Nullable;

/**
 * An immutable snapshot of the configuration used to create a {@link DoclingServeApi}.
 *
 * <p>Instances are created through {@link DoclingServeApi#builder()} and {@link DoclingServeApiBuilder#config()}, are returned by {@link DoclingServeApi#config()}, and are handed
 * to a
 * {@link ai.docling.serve.api.spi.DoclingServeApiProvider} when {@link DoclingServeApiBuilder#build()} is called. Every option
 * has a typed key exposed as a {@code public static final} {@link ConfigOption} constant on this class,
 * and a matching accessor.
 *
 * <p>The configuration records which options were explicitly set by the caller, as opposed to left
 * at their default value (see {@link #isExplicitlySet(ConfigOption)}). This is what allows a provider
 * to declare options it does not honor without complaining about options the caller never touched.
 *
 * <p>This class is final and owned by the {@code docling-serve-api} module, so new options can be added
 * in future releases without breaking providers.
 */
public final class DoclingServeApiConfig {
  /**
   * The base URL of the Docling Serve API. Defaults to {@code http://localhost:5001}.
   *
   * <p>Providers must honor this option.
   */
  public static final ConfigOption<URI> BASE_URL = ConfigOption.of("baseUrl", URI.class);

  /**
   * The API key used to authenticate requests. Not set by default.
   *
   * <p>Providers must honor this option when it is set.
   */
  public static final ConfigOption<String> API_KEY = ConfigOption.of("apiKey", String.class);

  /**
   * Whether requests are logged. Defaults to {@code false}.
   *
   * <p>Providers may declare this option as unsupported if they have no request logging.
   */
  public static final ConfigOption<Boolean> LOG_REQUESTS = ConfigOption.of("logRequests", Boolean.class);

  /**
   * Whether responses are logged. Defaults to {@code false}.
   *
   * <p>Providers may declare this option as unsupported if they have no response logging.
   */
  public static final ConfigOption<Boolean> LOG_RESPONSES = ConfigOption.of("logResponses", Boolean.class);

  /**
   * Whether JSON requests and responses are pretty-printed. Defaults to {@code false}.
   *
   * <p>Providers may declare this option as unsupported if they cannot control JSON formatting.
   */
  public static final ConfigOption<Boolean> PRETTY_PRINT = ConfigOption.of("prettyPrint", Boolean.class);

  /**
   * The timeout to establish a connection to the Docling Serve API. Defaults to 5 seconds.
   *
   * <p>Providers must honor this option: silently ignoring a timeout surfaces as hangs far from the cause.
   */
  public static final ConfigOption<Duration> CONNECT_TIMEOUT = ConfigOption.of("connectTimeout", Duration.class);

  /**
   * The timeout for receiving a response from the Docling Serve API. Defaults to 30 seconds.
   *
   * <p>Providers must honor this option: silently ignoring a timeout surfaces as hangs far from the cause.
   */
  public static final ConfigOption<Duration> READ_TIMEOUT = ConfigOption.of("readTimeout", Duration.class);

  /**
   * How frequently the status of an async task is polled, for example by
   * {@link DoclingServeApi#convertSourceAsync(ai.docling.serve.api.convert.request.ConvertDocumentRequest)}. Defaults to 2 seconds.
   *
   * <p>Providers may declare this option as unsupported if they do not poll.
   */
  public static final ConfigOption<Duration> ASYNC_POLL_INTERVAL = ConfigOption.of("asyncPollInterval", Duration.class);

  /**
   * The maximum time to wait for an async task to complete, for example in
   * {@link DoclingServeApi#convertSourceAsync(ai.docling.serve.api.convert.request.ConvertDocumentRequest)}. Defaults to 5 minutes.
   *
   * <p>Providers must honor this option.
   */
  public static final ConfigOption<Duration> ASYNC_TIMEOUT = ConfigOption.of("asyncTimeout", Duration.class);

  /**
   * The {@link Executor} used to run async operations. Not set by default.
   *
   * <p>Providers whose concurrency model makes a caller-supplied executor meaningless (for example a
   * reactive implementation) may declare this option as unsupported.
   *
   * @see #asyncExecutor()
   */
  public static final ConfigOption<Executor> ASYNC_EXECUTOR = ConfigOption.of("asyncExecutor", Executor.class);

  /**
   * Every option, in declaration order. Package-private: used by the builder, the legacy provider
   * adapter, and the tests guarding that every option has an accessor and a setter.
   */
  static final List<ConfigOption<?>> ALL_OPTIONS = List.of(
      BASE_URL, API_KEY, LOG_REQUESTS, LOG_RESPONSES, PRETTY_PRINT, CONNECT_TIMEOUT, READ_TIMEOUT, ASYNC_POLL_INTERVAL, ASYNC_TIMEOUT, ASYNC_EXECUTOR);

  private static final URI DEFAULT_BASE_URL = URI.create("http://localhost:5001");
  private static final Duration DEFAULT_CONNECT_TIMEOUT = Duration.ofSeconds(5);
  private static final Duration DEFAULT_READ_TIMEOUT = Duration.ofSeconds(30);
  private static final Duration DEFAULT_ASYNC_POLL_INTERVAL = Duration.ofSeconds(2);
  private static final Duration DEFAULT_ASYNC_TIMEOUT = Duration.ofMinutes(5);

  // Only contains explicitly set options, never null values, in declaration order
  private final Map<ConfigOption<?>, Object> values;

  DoclingServeApiConfig(Map<ConfigOption<?>, Object> values) {
    this.values = Collections.unmodifiableMap(
        ALL_OPTIONS.stream()
            .filter(values::containsKey)
            .collect(Collectors.toMap(Function.identity(), values::get, (first, second) -> first, LinkedHashMap::new)));
  }

  /**
   * Creates a new builder initialized with the options explicitly set on this configuration.
   * Options left at their default here are still considered not explicitly set on the returned builder.
   *
   * @return a new builder initialized from this configuration
   */
  public DoclingServeApiBuilder toBuilder() {
    return new DoclingServeApiBuilder(this.values);
  }

  /**
   * The base URL of the Docling Serve API.
   *
   * @return the base URL, or {@code http://localhost:5001} if not set
   * @see #BASE_URL
   */
  public URI baseUrl() {
    return getOrDefault(BASE_URL, DEFAULT_BASE_URL);
  }

  /**
   * The API key used to authenticate requests.
   *
   * @return the API key, or {@code null} if not set
   * @see #API_KEY
   */
  public @Nullable String apiKey() {
    return get(API_KEY);
  }

  /**
   * Whether requests are logged.
   *
   * @return {@code true} if requests are logged, {@code false} by default
   * @see #LOG_REQUESTS
   */
  public boolean logRequests() {
    return getOrDefault(LOG_REQUESTS, false);
  }

  /**
   * Whether responses are logged.
   *
   * @return {@code true} if responses are logged, {@code false} by default
   * @see #LOG_RESPONSES
   */
  public boolean logResponses() {
    return getOrDefault(LOG_RESPONSES, false);
  }

  /**
   * Whether JSON requests and responses are pretty-printed.
   *
   * @return {@code true} if JSON is pretty-printed, {@code false} by default
   * @see #PRETTY_PRINT
   */
  public boolean prettyPrint() {
    return getOrDefault(PRETTY_PRINT, false);
  }

  /**
   * The timeout to establish a connection to the Docling Serve API.
   *
   * @return the connect timeout, 5 seconds by default
   * @see #CONNECT_TIMEOUT
   */
  public Duration connectTimeout() {
    return getOrDefault(CONNECT_TIMEOUT, DEFAULT_CONNECT_TIMEOUT);
  }

  /**
   * The timeout for receiving a response from the Docling Serve API.
   *
   * @return the read timeout, 30 seconds by default
   * @see #READ_TIMEOUT
   */
  public Duration readTimeout() {
    return getOrDefault(READ_TIMEOUT, DEFAULT_READ_TIMEOUT);
  }

  /**
   * How frequently the status of an async task is polled.
   *
   * @return the poll interval, 2 seconds by default
   * @see #ASYNC_POLL_INTERVAL
   */
  public Duration asyncPollInterval() {
    return getOrDefault(ASYNC_POLL_INTERVAL, DEFAULT_ASYNC_POLL_INTERVAL);
  }

  /**
   * The maximum time to wait for an async task to complete.
   *
   * @return the async timeout, 5 minutes by default
   * @see #ASYNC_TIMEOUT
   */
  public Duration asyncTimeout() {
    return getOrDefault(ASYNC_TIMEOUT, DEFAULT_ASYNC_TIMEOUT);
  }

  /**
   * The {@link Executor} used to run async operations.
   *
   * <p>This is deliberately {@code null} rather than defaulted when not set. Implementations must
   * then fall back to the default async executor of {@link java.util.concurrent.CompletableFuture},
   * by using the overloads that take no executor. They must <strong>not</strong> substitute
   * {@link java.util.concurrent.ForkJoinPool#commonPool()}: it is not equivalent, and
   * {@link java.util.concurrent.CompletableFuture#delayedExecutor(long, java.util.concurrent.TimeUnit, Executor)}
   * does not protect against a common pool without workers (for example on a single-CPU container).
   *
   * <p>The lifecycle of the executor is owned by the caller: implementations never shut it down.
   *
   * @return the executor, or {@code null} if not set
   * @see #ASYNC_EXECUTOR
   */
  public @Nullable Executor asyncExecutor() {
    return get(ASYNC_EXECUTOR);
  }

  /**
   * Whether the caller explicitly set the given option, as opposed to leaving it at its default value.
   *
   * @param option the option to check
   * @return {@code true} if the option was explicitly set
   */
  public boolean isExplicitlySet(ConfigOption<?> option) {
    return this.values.containsKey(option);
  }

  /**
   * The options explicitly set by the caller.
   *
   * @return an unmodifiable set of the explicitly set options, in the declaration order of the {@link ConfigOption} constants
   */
  public Set<ConfigOption<?>> explicitlySetOptions() {
    return this.values.keySet();
  }

  /**
   * The value of the given option, if it was explicitly set.
   *
   * <p>Package-private: providers use the typed accessors instead.
   */
  <T> @Nullable T get(ConfigOption<T> option) {
    return option.type().cast(this.values.get(option));
  }

  private <T> T getOrDefault(ConfigOption<T> option, T defaultValue) {
    var value = get(option);
    return (value != null) ? value : defaultValue;
  }

  @Override
  public boolean equals(Object o) {
    return (this == o) || ((o instanceof DoclingServeApiConfig other) && this.values.equals(other.values));
  }

  @Override
  public int hashCode() {
    return this.values.hashCode();
  }

  @Override
  public String toString() {
    return "DoclingServeApiConfig%s".formatted(explicitlySetOptions());
  }
}
