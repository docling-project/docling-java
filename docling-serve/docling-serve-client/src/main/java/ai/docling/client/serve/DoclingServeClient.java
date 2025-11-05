package ai.docling.client.serve;

import static ai.docling.api.serve.util.ValidationUtils.ensureNotBlank;
import static ai.docling.api.serve.util.ValidationUtils.ensureNotNull;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Flow.Subscriber;
import java.util.logging.Level;
import java.util.logging.Logger;

import ai.docling.api.serve.DoclingServeApi;
import ai.docling.api.serve.convert.request.ConvertDocumentRequest;
import ai.docling.api.serve.convert.response.ConvertDocumentResponse;
import ai.docling.api.serve.health.HealthCheckResponse;

/**
 * Abstract class representing a client for interacting with the Docling API.
 *
 * <p>This class handles the foundational functionality needed to perform HTTP
 * requests to the Docling API, with customizable base URL and HTTP client
 * configurations. It provides abstract methods for JSON serialization and
 * deserialization, allowing implementation-specific customization.
 *
 * <p>Concrete subclasses must implement {@link #readValue(String, Class)} and
 * {@link #writeValueAsString(Object)} for serialization and deserialization behavior.
 */
public abstract class DoclingServeClient implements DoclingServeApi {
  private static final Logger LOG = Logger.getLogger(DoclingServeClient.class.getName());
  protected static final URI DEFAULT_BASE_URL = URI.create("http://localhost:5001");

  private final URI baseUrl;
  private final HttpClient httpClient;
  private final boolean logRequests;
  private final boolean logResponses;

  protected DoclingServeClient(DoclingServeClientBuilder builder) {
    this.baseUrl = ensureNotNull(builder.baseUrl, "baseUrl");

    if (Objects.equals(this.baseUrl.getScheme(), "http")) {
      // Docling Serve uses Python FastAPI which causes errors when called from JDK HttpClient.
      // The HttpClient uses HTTP 2 by default and then falls back to HTTP 1.1 if not supported.
      // However, the way FastAPI works results in the fallback not happening, making the call fail.
      builder.httpClientBuilder.version(HttpClient.Version.HTTP_1_1);
    }

    this.httpClient = ensureNotNull(builder.httpClientBuilder, "httpClientBuilder").build();
    this.logRequests = builder.logRequests;
    this.logResponses = builder.logResponses;
  }

  /**
   * Reads and deserializes the given JSON string into an instance of the specified type.
   *
   * @param json      the JSON string to deserialize; must not be {@code null}
   * @param valueType the {@link Class} of the target type; must not be {@code null}
   * @param <T>       the type of the object to be deserialized
   * @return an instance of {@code T} deserialized from the provided JSON
   * @throws RuntimeException if the JSON parsing fails
   */
  protected abstract <T> T readValue(String json, Class<T> valueType);

  /**
   * Serializes the given object into its JSON string representation.
   *
   * @param <T>   the type of the object to serialize
   * @param value the object to serialize; must not be {@code null}
   * @return the JSON string representation of the given object
   * @throws RuntimeException if serialization fails
   */
  protected abstract <T> String writeValueAsString(T value);

  protected void logRequest(HttpRequest request) {
    if (LOG.isLoggable(Level.INFO)) {
      var stringBuilder = new StringBuilder();
      stringBuilder.append("\n→ REQUEST: %s %s\n".formatted(request.method(), request.uri()));
      stringBuilder.append("  HEADERS:\n");

      request.headers().map().forEach((key, values) ->
          stringBuilder.append("  %s: %s\n".formatted(key, String.join(", ", values)))
      );

      LOG.info(stringBuilder.toString());
    }
  }

  protected void logResponse(HttpResponse<String> response, Optional<String> responseBody) {
    if (LOG.isLoggable(Level.INFO)) {
      var stringBuilder = new StringBuilder();
      stringBuilder.append("\n← RESPONSE: %s\n".formatted(response.statusCode()));
      stringBuilder.append("  HEADERS:\n");

      response.headers().map().forEach((key, values) ->
          stringBuilder.append("  %s: %s\n".formatted(key, String.join(", ", values)))
      );

      responseBody.ifPresent(body -> stringBuilder.append("  BODY:\n%s".formatted(body)));
      LOG.info(stringBuilder.toString());
    }
  }

  protected <T> T execute(HttpRequest request, Class<T> expectedValueType) {
    if (this.logRequests) {
      logRequest(request);
    }

    long startTime = System.currentTimeMillis();

    try {
      return httpClient.sendAsync(request, BodyHandlers.ofString())
          .thenApply(response -> getResponse(response, expectedValueType))
          .join();
    }
    finally {
      long duration = System.currentTimeMillis() - startTime;
      LOG.info(() -> "Request took %d ms".formatted(duration));
    }
  }

  protected <T> T getResponse(HttpResponse<String> response, Class<T> expectedReturnType) {
    var body = response.body();

    if (this.logResponses) {
      logResponse(response, Optional.ofNullable(body));
    }

    return readValue(body, expectedReturnType);
  }

  @Override
  public HealthCheckResponse health() {
    var httpRequest = HttpRequest.newBuilder()
        .uri(baseUrl.resolve("/health"))
        .header("Accept", "application/json")
        .GET()
        .build();

    return execute(httpRequest, HealthCheckResponse.class);
  }

  @Override
  public ConvertDocumentResponse convertSource(ConvertDocumentRequest request) {
    var httpRequest = HttpRequest.newBuilder()
        .uri(baseUrl.resolve("/v1/convert/source"))
        .header("Accept", "application/json")
        .header("Content-Type", "application/json")
        .POST(new LoggingBodyPublisher<>(request))
        .build();

    return execute(httpRequest, ConvertDocumentResponse.class);
  }

  private class LoggingBodyPublisher<T> implements BodyPublisher {
    private final BodyPublisher delegate;
    private final String stringContent;

    private LoggingBodyPublisher(T content) {
      this.stringContent = writeValueAsString(content);
      this.delegate = BodyPublishers.ofString(this.stringContent);
    }

    @Override
    public long contentLength() {
      return this.delegate.contentLength();
    }

    @Override
    public void subscribe(Subscriber<? super ByteBuffer> subscriber) {
      if (logRequests) {
        LOG.info(() -> "→ REQUEST BODY: %s".formatted(this.stringContent));
      }

      this.delegate.subscribe(subscriber);
    }
  }

  /**
   * Abstract base class for building instances of {@link DoclingServeClient}.
   *
   * <p>This builder class provides methods for configuring shared properties such as the
   * base URL and the HTTP client. Concrete subclasses may extend this builder to
   * add additional configuration options.
   *
   * @param <C> the type of {@link DoclingServeClient} being built
   * @param <B> the type of the builder implementation
   */
  @SuppressWarnings("unchecked")
  public abstract static class DoclingServeClientBuilder<C extends DoclingServeClient, B extends DoclingServeClientBuilder<C, B>> implements DoclingApiBuilder<C, B> {
    private URI baseUrl = DEFAULT_BASE_URL;
    private HttpClient.Builder httpClientBuilder = HttpClient.newBuilder();
    private boolean logRequests = false;
    private boolean logResponses = false;

    /**
     * Protected constructor for use by subclasses of {@link DoclingServeClientBuilder}.
     *
     * <p>Initializes a new instance of the builder with default configuration values.
     * This constructor ensures that the builder cannot be instantiated directly,
     * promoting proper use and extension by subclasses.
     */
    protected DoclingServeClientBuilder() {
    }

    /**
     * Initializes a new {@link DoclingServeClientBuilder} instance using the configuration
     * from the provided {@link DoclingServeClient}.
     *
     * @param doclingClient the {@link DoclingServeClient} whose configuration (e.g., base URL)
     *                      will be used to initialize the builder
     */
    protected DoclingServeClientBuilder(DoclingServeClient doclingClient) {
      this.baseUrl = doclingClient.baseUrl;
      this.httpClientBuilder = HttpClient.newBuilder();
    }

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
    public B baseUrl(String baseUrl) {
      this.baseUrl = URI.create(ensureNotBlank(baseUrl, "baseUrl"));
      return (B) this;
    }

    /**
     * Sets the base URL for the client.
     *
     * <p>This method configures the base URL that will be used for all API requests
     * executed by the client. The provided URL must be non-null.
     *
     * @param baseUrl the base URL to use, as a {@link URI}
     * @return this builder instance for method chaining
     * @throws IllegalArgumentException if {@code baseUrl} is null
     */
    public B baseUrl(URI baseUrl) {
      this.baseUrl = baseUrl;
      return (B) this;
    }

    /**
     * Sets the HTTP client builder to be used for creating the underlying HTTP client.
     *
     * <p>This allows customization of HTTP client properties such as timeouts,
     * proxy settings, SSL context, and other connection parameters.
     *
     * @param httpClientBuilder the {@link HttpClient.Builder} to use
     * @return this {@link DoclingServeJackson3Client.Builder} instance for method chaining
     */
    public B httpClientBuilder(HttpClient.Builder httpClientBuilder) {
      this.httpClientBuilder = httpClientBuilder;
      return (B) this;
    }

    @Override
    public B logRequests(boolean logRequests) {
      this.logRequests = logRequests;
      return (B) this;
    }

    @Override
    public B logResponses(boolean logResponses) {
      this.logResponses = logResponses;
      return (B) this;
    }
  }
}
