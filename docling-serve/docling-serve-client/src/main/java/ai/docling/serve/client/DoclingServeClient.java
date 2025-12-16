package ai.docling.serve.client;

import static ai.docling.serve.api.util.ValidationUtils.ensureNotBlank;
import static ai.docling.serve.api.util.ValidationUtils.ensureNotNull;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.ByteBuffer;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Flow.Subscriber;

import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ai.docling.serve.api.DoclingServeApi;
import ai.docling.serve.api.DoclingServeChunkApi;
import ai.docling.serve.api.DoclingServeClearApi;
import ai.docling.serve.api.DoclingServeConvertApi;
import ai.docling.serve.api.DoclingServeHealthApi;
import ai.docling.serve.api.DoclingServeTaskApi;
import ai.docling.serve.api.chunk.request.HierarchicalChunkDocumentRequest;
import ai.docling.serve.api.chunk.request.HybridChunkDocumentRequest;
import ai.docling.serve.api.chunk.response.ChunkDocumentResponse;
import ai.docling.serve.api.clear.request.ClearConvertersRequest;
import ai.docling.serve.api.clear.request.ClearResultsRequest;
import ai.docling.serve.api.clear.response.ClearResponse;
import ai.docling.serve.api.convert.request.ConvertDocumentRequest;
import ai.docling.serve.api.convert.response.ConvertDocumentResponse;
import ai.docling.serve.api.health.HealthCheckResponse;
import ai.docling.serve.api.task.request.TaskResultRequest;
import ai.docling.serve.api.task.request.TaskStatusPollRequest;
import ai.docling.serve.api.task.response.TaskStatusPollResponse;
import ai.docling.serve.api.util.Utils;
import ai.docling.serve.client.operations.ChunkOperations;
import ai.docling.serve.client.operations.ClearOperations;
import ai.docling.serve.client.operations.ConvertOperations;
import ai.docling.serve.client.operations.HealthOperations;
import ai.docling.serve.client.operations.HttpOperations;
import ai.docling.serve.client.operations.RequestContext;
import ai.docling.serve.client.operations.TaskOperations;

/**
 * Abstract class representing a client for interacting with the Docling API.
 *
 * <p>This class handles the foundational functionality needed to perform HTTP
 * requests to the Docling API, with customizable base URL and HTTP client
 * configurations. It provides abstract methods for JSON serialization and
 * deserialization, allowing implementation-specific customization.
 *
 * <p>The client is structured hierarchically, with separate nested implementations
 * for each API interface ({@link DoclingServeHealthApi}, {@link DoclingServeConvertApi},
 * {@link DoclingServeChunkApi}, {@link DoclingServeClearApi}, {@link DoclingServeTaskApi}).
 * These implementations share common HTTP execution logic and configuration.
 *
 * <p>Concrete subclasses must implement {@link #readValue(String, Class)} and
 * {@link #writeValueAsString(Object)} for serialization and deserialization behavior.
 */
public abstract class DoclingServeClient extends HttpOperations implements DoclingServeApi {
  private static final Logger LOG = LoggerFactory.getLogger(DoclingServeClient.class);
  protected static final URI DEFAULT_BASE_URL = URI.create("http://localhost:5001");

  private final URI baseUrl;
  private final HttpClient httpClient;
  private final boolean logRequests;
  private final boolean logResponses;
  private final boolean prettyPrintJson;
  private final @Nullable String apiKey;
  private final Duration asyncPollInterval;
  private final Duration asyncTimeout;

  private final HealthOperations healthOps;
  private final ConvertOperations convertOps;
  private final ChunkOperations chunkOps;
  private final ClearOperations clearOps;
  private final TaskOperations taskOps;

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
    this.prettyPrintJson = builder.prettyPrintJson;
    this.apiKey = builder.apiKey;
    this.asyncPollInterval = builder.asyncPollInterval;
    this.asyncTimeout = builder.asyncTimeout;

    // Initialize operations handlers
    this.healthOps = new HealthOperations(this);
    this.taskOps = new TaskOperations(this);
    this.convertOps = new ConvertOperations(this, this.taskOps, this.asyncPollInterval, this.asyncTimeout);
    this.chunkOps = new ChunkOperations(this, this.taskOps, this.asyncPollInterval, this.asyncTimeout);
    this.clearOps = new ClearOperations(this);
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

  protected boolean prettyPrintJson() {
    return this.prettyPrintJson;
  }

  protected void logRequest(HttpRequest request) {
    if (LOG.isInfoEnabled()) {
      var stringBuilder = new StringBuilder();
      stringBuilder.append("\n→ REQUEST: %s %s\n".formatted(request.method(), request.uri()));
      stringBuilder.append("  HEADERS:\n");

      // Need to mask sensitive headers
      request.headers()
          .map()
          .entrySet()
          .stream()
          .map(this::maskSensitiveHeaderValues)
          .forEach(entry -> stringBuilder.append("  %s: %s\n".formatted(entry.getKey(), String.join(", ", entry.getValue())))
      );

      LOG.info(stringBuilder.toString());
    }
  }

  private boolean isSensitiveHeader(String headerName) {
    return API_KEY_HEADER_NAME.equalsIgnoreCase(headerName);
  }

  private Map.Entry<String, List<String>> maskSensitiveHeaderValues(Map.Entry<String, List<String>> entry) {
    return Map.entry(
        entry.getKey(),
        entry.getValue().stream()
            .map(value -> isSensitiveHeader(entry.getKey()) ? "*".repeat(value.length()) : value)
            .toList()
    );
  }

  protected void logResponse(HttpResponse<String> response, Optional<String> responseBody) {
    if (LOG.isInfoEnabled()) {
      var stringBuilder = new StringBuilder();
      stringBuilder.append("\n← RESPONSE: %s\n".formatted(response.statusCode()));
      stringBuilder.append("  HEADERS:\n");

      response.headers().map().forEach((key, values) ->
          stringBuilder.append("  %s: %s\n".formatted(key, String.join(", ", values)))
      );

      responseBody
          .map(body -> this.prettyPrintJson ? writeValueAsString(readValue(body, Object.class)) : body)
          .ifPresent(body -> stringBuilder.append("  BODY:\n%s".formatted(body)));
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
      LOG.info("Request [{} {}] took {}ms", request.method(), request.uri(), duration);
    }
  }

  @Override
  protected <I, O> O executePost(RequestContext<I, O> requestContext) {
    var httpRequest = createRequestBuilder(requestContext)
        .header("Content-Type", "application/json")
        .POST(new LoggingBodyPublisher<>(requestContext.getRequest()))
        .build();

    return execute(httpRequest, requestContext.getResponseType());
  }

  @Override
  protected <I, O> O executeGet(RequestContext<I, O> requestContext) {
    var httpRequest = createRequestBuilder(requestContext)
        .GET()
        .build();

    return execute(httpRequest, requestContext.getResponseType());
  }

  protected <I, O> HttpRequest.Builder createRequestBuilder(RequestContext<I, O> requestContext) {
    var requestBuilder = HttpRequest.newBuilder()
        .uri(this.baseUrl.resolve(requestContext.getUri()))
        .header("Accept", "application/json");

      if (Utils.isNotNullOrBlank(this.apiKey)) {
        requestBuilder.header(API_KEY_HEADER_NAME, this.apiKey);
      }

    return requestBuilder;
  }

  protected <T> T getResponse(HttpResponse<String> response, Class<T> expectedReturnType) {
    var body = response.body();

    if (this.logResponses) {
      logResponse(response, Optional.ofNullable(body));
    }

    var statusCode = response.statusCode();

    if (statusCode >= 400) {
      // Handle errors
      // The Java HTTPClient doesn't throw exceptions on error codes
      throw new DoclingServeClientException("An error occurred: %s".formatted(body), statusCode, body);
    }

    return readValue(body, expectedReturnType);
  }

  @Override
  public HealthCheckResponse health() {
    return this.healthOps.health();
  }

  @Override
  public ConvertDocumentResponse convertSource(ConvertDocumentRequest request) {
    return this.convertOps.convertSource(request);
  }

  @Override
  public ChunkDocumentResponse chunkSourceWithHierarchicalChunker(HierarchicalChunkDocumentRequest request) {
    return this.chunkOps.chunkSourceWithHierarchicalChunker(request);
  }

  @Override
  public ChunkDocumentResponse chunkSourceWithHybridChunker(HybridChunkDocumentRequest request) {
    return this.chunkOps.chunkSourceWithHybridChunker(request);
  }

  @Override
  public CompletionStage<ChunkDocumentResponse> chunkSourceWithHierarchicalChunkerAsync(HierarchicalChunkDocumentRequest request) {
    return this.chunkOps.chunkSourceWithHierarchicalChunkerAsync(request);
  }

  @Override
  public CompletionStage<ChunkDocumentResponse> chunkSourceWithHybridChunkerAsync(HybridChunkDocumentRequest request) {
    return this.chunkOps.chunkSourceWithHybridChunkerAsync(request);
  }

  @Override
  public TaskStatusPollResponse pollTaskStatus(TaskStatusPollRequest request) {
    return this.taskOps.pollTaskStatus(request);
  }

  @Override
  public ConvertDocumentResponse convertTaskResult(TaskResultRequest request) {
    return this.taskOps.convertTaskResult(request);
  }

  @Override
  public ChunkDocumentResponse chunkTaskResult(TaskResultRequest request) {
    return this.taskOps.chunkTaskResult(request);
  }

  @Override
  public ClearResponse clearConverters(ClearConvertersRequest request) {
    return this.clearOps.clearConverters(request);
  }

  @Override
  public ClearResponse clearResults(ClearResultsRequest request) {
    return this.clearOps.clearResults(request);
  }

  @Override
  public CompletionStage<ConvertDocumentResponse> convertSourceAsync(ConvertDocumentRequest request) {
    return this.convertOps.convertSourceAsync(request);
  }

  private class LoggingBodyPublisher<T> implements BodyPublisher {
    private final BodyPublisher delegate;
    private final String stringContent;

    private LoggingBodyPublisher(@Nullable T content) {
      if (content == null) {
        this.stringContent = "";
        this.delegate = BodyPublishers.noBody();
      }
      else {
        this.stringContent = writeValueAsString(content);
        this.delegate = BodyPublishers.ofString(this.stringContent);
      }
    }

    @Override
    public long contentLength() {
      return this.delegate.contentLength();
    }

    @Override
    public void subscribe(Subscriber<? super ByteBuffer> subscriber) {
      if (logRequests) {
        LOG.info("→ REQUEST BODY: \n{}", this.stringContent);
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
    private HttpClient.Builder httpClientBuilder = HttpClient.newBuilder().followRedirects(Redirect.NORMAL);
    private boolean logRequests = false;
    private boolean logResponses = false;
    private boolean prettyPrintJson = false;
    private @Nullable String apiKey;
    private Duration asyncPollInterval = Duration.ofSeconds(2);
    private Duration asyncTimeout = Duration.ofMinutes(5);

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
      this.apiKey = doclingClient.apiKey;
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
    public B apiKey(@Nullable String apiKey) {
      this.apiKey = apiKey;
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

    @Override
    public B prettyPrint(boolean prettyPrint) {
      this.prettyPrintJson = prettyPrint;
      return (B) this;
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
    @Override
    public B asyncPollInterval(Duration asyncPollInterval) {
      if (asyncPollInterval == null || asyncPollInterval.isNegative() || asyncPollInterval.isZero()) {
        throw new IllegalArgumentException("asyncPollInterval must be a positive duration");
      }

      this.asyncPollInterval = asyncPollInterval;
      return (B) this;
    }

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
    @Override
    public B asyncTimeout(Duration asyncTimeout) {
      if (asyncTimeout == null || asyncTimeout.isNegative() || asyncTimeout.isZero()) {
        throw new IllegalArgumentException("asyncTimeout must be a positive duration");
      }
      this.asyncTimeout = asyncTimeout;
      return (B) this;
    }
  }
}
