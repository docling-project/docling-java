package ai.docling.serve.client;

import static ai.docling.serve.api.util.ValidationUtils.ensureNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;
import java.util.concurrent.Flow.Subscriber;
import java.util.stream.Collectors;

import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ai.docling.serve.api.DoclingServeApi;
import ai.docling.serve.api.DoclingServeApiBuilder;
import ai.docling.serve.api.DoclingServeApiConfig;
import ai.docling.serve.api.chunk.request.HierarchicalChunkDocumentRequest;
import ai.docling.serve.api.chunk.request.HybridChunkDocumentRequest;
import ai.docling.serve.api.chunk.response.ChunkDocumentResponse;
import ai.docling.serve.api.clear.request.ClearConvertersRequest;
import ai.docling.serve.api.clear.request.ClearResultsRequest;
import ai.docling.serve.api.clear.response.ClearResponse;
import ai.docling.serve.api.convert.request.BatchConvertDocumentRequest;
import ai.docling.serve.api.convert.request.ConvertDocumentRequest;
import ai.docling.serve.api.convert.response.ConvertDocumentResponse;
import ai.docling.serve.api.health.HealthCheckResponse;
import ai.docling.serve.api.task.request.TaskResultRequest;
import ai.docling.serve.api.task.request.TaskStatusPollRequest;
import ai.docling.serve.api.task.response.TaskStatusPollResponse;
import ai.docling.serve.api.util.Utils;
import ai.docling.serve.api.validation.ValidationError;
import ai.docling.serve.api.validation.ValidationErrorDetail;
import ai.docling.serve.api.validation.ValidationException;
import ai.docling.serve.client.operations.ChunkOperations;
import ai.docling.serve.client.operations.ClearOperations;
import ai.docling.serve.client.operations.ConvertOperations;
import ai.docling.serve.client.operations.HealthOperations;
import ai.docling.serve.client.operations.HttpOperations;
import ai.docling.serve.client.operations.RequestContext;
import ai.docling.serve.client.operations.StreamResponse;
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
 * for each API interface ({@link ai.docling.serve.api.DoclingServeHealthApi}, {@link ai.docling.serve.api.DoclingServeConvertApi},
 * {@link ai.docling.serve.api.DoclingServeChunkApi}, {@link ai.docling.serve.api.DoclingServeClearApi}, {@link ai.docling.serve.api.DoclingServeTaskApi}).
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
  private final DoclingServeApiConfig config;

  private final HealthOperations healthOps;
  private final ConvertOperations convertOps;
  private final ChunkOperations chunkOps;
  private final ClearOperations clearOps;
  private final TaskOperations taskOps;

  /**
   * Creates a builder for the client matching the version of Jackson on the classpath: a
   * {@link DoclingServeJackson3Client} if Jackson 3 is available, otherwise a {@link DoclingServeJackson2Client}.
   *
   * <p>Use this builder for client-specific settings that don't depend on Jackson, such as
   * {@link DoclingServeClientBuilder#httpClientBuilder(HttpClient.Builder)}. For the options shared by every
   * implementation, prefer {@link DoclingServeApi#builder()}. To customize the JSON mapper, which depends on the
   * version of Jackson, use {@link DoclingServeJackson3Client#builder()} or {@link DoclingServeJackson2Client#builder()}.
   *
   * @return a new builder for the detected client
   * @throws IllegalStateException if neither Jackson 2 nor Jackson 3 is on the classpath
   */
  public static DoclingServeClientBuilder<?, ?> builder() {
    return builderFor(Thread.currentThread().getContextClassLoader());
  }

  // Package-private so the detection can be tested with a class loader hiding Jackson
  static DoclingServeClientBuilder<?, ?> builderFor(ClassLoader classLoader) {
    if (JacksonVersion.JACKSON_3.isOnClasspath(classLoader)) {
      return DoclingServeJackson3Client.builder();
    }
    else if (JacksonVersion.JACKSON_2.isOnClasspath(classLoader)) {
      return DoclingServeJackson2Client.builder();
    }

    throw new IllegalStateException("""
        Neither Jackson 2 nor Jackson 3 is on the classpath. You must add one of the following dependencies:

        For Jackson 2:
          Maven:  com.fasterxml.jackson.core:jackson-databind
          Gradle: implementation("com.fasterxml.jackson.core:jackson-databind:<version>")

        For Jackson 3:
          Maven:  tools.jackson.core:jackson-databind
          Gradle: implementation("tools.jackson.core:jackson-databind:<version>")
        """);
  }

  protected DoclingServeClient(DoclingServeClientBuilder builder) {
    this.config = builder.settings.config();

    var base = this.config.baseUrl();

    if (Objects.equals(base.getScheme(), "http")) {
      // Docling Serve uses Python FastAPI which causes errors when called from JDK HttpClient.
      // The HttpClient uses HTTP 2 by default and then falls back to HTTP 1.1 if not supported.
      // However, the way FastAPI works results in the fallback not happening, making the call fail.
      builder.httpClientBuilder.version(HttpClient.Version.HTTP_1_1);
    }

    this.baseUrl = !base.getPath().endsWith("/") ?
        URI.create(base + "/") :
        base;

    this.httpClient = builder.httpClientBuilder
        .connectTimeout(this.config.connectTimeout())
        .build();

    // Initialize operations handlers
    this.healthOps = new HealthOperations(this);
    this.taskOps = new TaskOperations(this);
    this.convertOps = new ConvertOperations(this, this.taskOps, this.config.asyncPollInterval(), this.config.asyncTimeout(), this.config.asyncExecutor());
    this.chunkOps = new ChunkOperations(this, this.taskOps, this.config.asyncPollInterval(), this.config.asyncTimeout(), this.config.asyncExecutor());
    this.clearOps = new ClearOperations(this);
  }

  /**
   * {@inheritDoc}
   *
   * <p>This is the configuration the client was built with, as set on its builder: options left at their
   * default value are not recorded as explicitly set.
   *
   * <p>Client-specific settings, such as the HTTP client or the JSON mapper, are not part of the
   * configuration: use the {@code toBuilder()} method of the concrete client to keep them.
   */
  @Override
  public DoclingServeApiConfig config() {
    return this.config;
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
    return this.config.prettyPrint();
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
        entry.getKey(), entry.getValue().stream()
            .map(value -> isSensitiveHeader(entry.getKey()) ? "*".repeat(value.length()) : value)
            .toList()
    );
  }

  protected void logResponse(HttpResponse<String> response, Optional<String> responseBody) {
    if (LOG.isInfoEnabled()) {
      var stringBuilder = new StringBuilder();
      stringBuilder.append("\n← RESPONSE: %s\n".formatted(response.statusCode()));
      stringBuilder.append("  HEADERS:\n");

      response.headers().map().forEach((key, values) -> stringBuilder.append("  %s: %s\n".formatted(key, String.join(", ", values)))
      );

      responseBody
          .map(body -> this.config.prettyPrint() ? writeValueAsString(readValue(body, Object.class)) : body)
          .ifPresent(body -> stringBuilder.append("  BODY:\n%s".formatted(body)));
      LOG.info(stringBuilder.toString());
    }
  }

  protected <T> T execute(HttpRequest request, Class<T> expectedValueType) {
    if (this.config.logRequests()) {
      logRequest(request);
    }

    long startTime = System.currentTimeMillis();

    try {
      HttpResponse<?> response = null;
      if (StreamResponse.class.equals(expectedValueType)) {
        response = this.httpClient.send(request, BodyHandlers.ofInputStream());
      }
      else {
        response = this.httpClient.send(request, BodyHandlers.ofString());
      }
      return getResponse(request, response, expectedValueType);
    }
    catch (IOException | InterruptedException e) {
      throw new DoclingServeClientException(e);
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
  protected <I> StreamResponse executePostWithStreamResponse(RequestContext<I, StreamResponse> requestContext) {
    var httpRequest = createRequestBuilder(requestContext)
        .header("Accept", "application/octet-stream")
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

  @Override
  protected <I> StreamResponse executeGetWithStreamResponse(RequestContext<I, StreamResponse> requestContext) {
    var httpRequest = createRequestBuilder(requestContext)
        .header("Accept", "application/octet-stream")
        .GET()
        .build();
    return execute(httpRequest, requestContext.getResponseType());
  }

  protected <I, O> HttpRequest.Builder createRequestBuilder(RequestContext<I, O> requestContext) {
    var requestBuilder = HttpRequest.newBuilder()
        .uri(this.baseUrl.resolve(resolvePath(requestContext.getUri())))
        .header("Accept", "application/json")
        .timeout(this.config.readTimeout());

    var apiKey = this.config.apiKey();

    if (Utils.isNotNullOrBlank(apiKey)) {
      requestBuilder.header(API_KEY_HEADER_NAME, apiKey);
    }

    return requestBuilder;
  }

  private String resolvePath(String path) {
    return Optional.ofNullable(path)
        .filter(p -> p.startsWith("/") && (p.length() > 1))
        .map(p -> p.substring(1))
        .orElse(path);
  }

  protected <T> T getResponse(HttpRequest request, HttpResponse<?> response, Class<T> expectedReturnType) {
    var body = response.body();

    // if expectedReturnType is StreamResponse.class, avoid logging potential binary data
    if (this.config.logResponses() && !(StreamResponse.class.equals(expectedReturnType))) {
      logResponse((HttpResponse<String>) response, Optional.ofNullable(body.toString()));
    }

    var statusCode = response.statusCode();

    if (statusCode >= 400) {
      if (StreamResponse.class.equals(expectedReturnType)) {
        // typical 4XX  & 5XX responses are usually accompanied by JSON response bodies
        // hence, reading the stream here.
        try (InputStream is = (InputStream) body) {
          body = new String(is.readAllBytes(), StandardCharsets.UTF_8);
        }
        catch (IOException e) {
          throw new DoclingServeClientException(e);
        }
      }

      if (statusCode == 422) {
        var validationError = readValue(body.toString(), ValidationError.class);
        var errorText = validationError.getErrorDetails()
            .stream()
            .map(ValidationErrorDetail::getMessage)
            .filter(Objects::nonNull)
            .collect(Collectors.joining("\n"));

        throw new ValidationException(
            validationError, "An error occurred while making %s request to %s:\n%s".formatted(request.method(), request.uri(), errorText)
        );
      }
      else {
        throw new DoclingServeClientException("An error occurred: %s".formatted(body.toString()), statusCode, body.toString());
      }
    }

    if (StreamResponse.class.equals(expectedReturnType)) {
      return (T) StreamResponse
          .builder()
          .headers(headerName -> response.headers().firstValue(headerName))
          .body((InputStream) body)
          .build();
    }
    else {
      return readValue(body.toString(), expectedReturnType);
    }
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

  @Override
  public TaskStatusPollResponse convertSourceBatch(BatchConvertDocumentRequest request) {
    return this.convertOps.convertSourceBatch(request);
  }

  @Override
  public CompletionStage<ConvertDocumentResponse> convertSourceBatchAsync(BatchConvertDocumentRequest request) {
    return this.convertOps.convertSourceBatchAsync(request);
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
      if (config.logRequests()) {
        LOG.info("\n→ REQUEST BODY: \n{}", this.stringContent);
      }

      this.delegate.subscribe(subscriber);
    }
  }

  /**
   * Abstract base class for building instances of {@link DoclingServeClient}.
   *
   * <p>The options shared by every implementation are collected into a {@link DoclingServeApiConfig}, which the
   * built client reports from {@link DoclingServeClient#config()}. This builder adds the client-specific settings,
   * such as the {@link HttpClient}, and concrete subclasses add their own, such as the JSON mapper.
   *
   * <p>Values are validated when they are set.
   *
   * @param <C> the type of {@link DoclingServeClient} being built
   * @param <B> the type of the builder implementation
   */
  @SuppressWarnings({
      "unchecked",
      "removal"
  })
  public abstract static class DoclingServeClientBuilder<C extends DoclingServeClient, B extends DoclingServeClientBuilder<C, B>> implements DoclingApiBuilder<C, B> {
    private DoclingServeApiBuilder settings;
    private HttpClient.Builder httpClientBuilder;

    /**
     * Protected constructor for use by subclasses of {@link DoclingServeClientBuilder}.
     *
     * <p>Initializes a new instance of the builder with default configuration values.
     */
    protected DoclingServeClientBuilder() {
      this.settings = DoclingServeApi.builder();
      this.httpClientBuilder = HttpClient.newBuilder().followRedirects(Redirect.NORMAL);
    }

    /**
     * Initializes a new {@link DoclingServeClientBuilder} instance using the configuration
     * of the provided {@link DoclingServeClient}.
     *
     * <p>Settings of the underlying {@link HttpClient} are not kept, apart from its redirect policy:
     * a proxy, an SSL context or an authenticator must be configured again with
     * {@link #httpClientBuilder(HttpClient.Builder)}.
     *
     * @param doclingClient the {@link DoclingServeClient} whose configuration will be used to initialize the builder
     */
    protected DoclingServeClientBuilder(DoclingServeClient doclingClient) {
      this.settings = doclingClient.config.toBuilder();
      this.httpClientBuilder = HttpClient.newBuilder()
          .followRedirects(doclingClient.httpClient.followRedirects());
    }

    /**
     * Replaces every option shared by all implementations with the given configuration. Options that
     * aren't explicitly set in it fall back to their default value. Client-specific settings are kept.
     *
     * @param config the configuration to apply
     * @return this builder instance for method chaining
     * @throws IllegalArgumentException if {@code config} is null
     */
    public B config(DoclingServeApiConfig config) {
      this.settings = ensureNotNull(config, "config").toBuilder();
      return (B) this;
    }

    /**
     * Sets the base URL for the client.
     *
     * @param baseUrl the base URL to use, as a {@link URI}
     * @return this builder instance for method chaining
     * @throws IllegalArgumentException if {@code baseUrl} is null
     * @see DoclingServeApiConfig#BASE_URL
     */
    @Override
    public B baseUrl(URI baseUrl) {
      this.settings.baseUrl(baseUrl);
      return (B) this;
    }

    /**
     * Sets the HTTP client builder to be used for creating the underlying HTTP client.
     *
     * <p>This allows customization of HTTP client properties such as proxy settings, SSL context,
     * and other connection parameters. The connect timeout is always set from {@link #connectTimeout(Duration)}.
     *
     * @param httpClientBuilder the {@link HttpClient.Builder} to use
     * @return this builder instance for method chaining
     * @throws IllegalArgumentException if {@code httpClientBuilder} is null
     */
    public B httpClientBuilder(HttpClient.Builder httpClientBuilder) {
      this.httpClientBuilder = ensureNotNull(httpClientBuilder, "httpClientBuilder");
      return (B) this;
    }

    /**
     * Sets the API key used to authenticate requests.
     *
     * @param apiKey the API key, or {@code null} to unset it
     * @return this builder instance for method chaining
     * @see DoclingServeApiConfig#API_KEY
     */
    @Override
    public B apiKey(@Nullable String apiKey) {
      this.settings.apiKey(apiKey);
      return (B) this;
    }

    /**
     * Sets whether requests are logged.
     *
     * @param logRequests {@code true} to log requests
     * @return this builder instance for method chaining
     * @see DoclingServeApiConfig#LOG_REQUESTS
     */
    @Override
    public B logRequests(boolean logRequests) {
      this.settings.logRequests(logRequests);
      return (B) this;
    }

    /**
     * Sets whether responses are logged.
     *
     * @param logResponses {@code true} to log responses
     * @return this builder instance for method chaining
     * @see DoclingServeApiConfig#LOG_RESPONSES
     */
    @Override
    public B logResponses(boolean logResponses) {
      this.settings.logResponses(logResponses);
      return (B) this;
    }

    /**
     * Sets whether JSON requests and responses are pretty-printed.
     *
     * @param prettyPrint {@code true} to pretty-print JSON
     * @return this builder instance for method chaining
     * @see DoclingServeApiConfig#PRETTY_PRINT
     */
    @Override
    public B prettyPrint(boolean prettyPrint) {
      this.settings.prettyPrint(prettyPrint);
      return (B) this;
    }

    /**
     * Sets the timeout to establish a connection to the Docling Serve API.
     *
     * @param connectTimeout the connect timeout
     * @return this builder instance for method chaining
     * @throws IllegalArgumentException if {@code connectTimeout} is null, zero or negative
     * @see DoclingServeApiConfig#CONNECT_TIMEOUT
     */
    @Override
    public B connectTimeout(Duration connectTimeout) {
      this.settings.connectTimeout(connectTimeout);
      return (B) this;
    }

    /**
     * Sets the timeout for receiving a response from the Docling Serve API.
     *
     * @param readTimeout the read timeout
     * @return this builder instance for method chaining
     * @throws IllegalArgumentException if {@code readTimeout} is null, zero or negative
     * @see DoclingServeApiConfig#READ_TIMEOUT
     */
    @Override
    public B readTimeout(Duration readTimeout) {
      this.settings.readTimeout(readTimeout);
      return (B) this;
    }

    /**
     * Sets how frequently the status of an async task is polled.
     *
     * @param asyncPollInterval the poll interval
     * @return this builder instance for method chaining
     * @throws IllegalArgumentException if {@code asyncPollInterval} is null, zero or negative
     * @see DoclingServeApiConfig#ASYNC_POLL_INTERVAL
     */
    @Override
    public B asyncPollInterval(Duration asyncPollInterval) {
      this.settings.asyncPollInterval(asyncPollInterval);
      return (B) this;
    }

    /**
     * Sets the maximum time to wait for an async task to complete.
     *
     * @param asyncTimeout the async timeout
     * @return this builder instance for method chaining
     * @throws IllegalArgumentException if {@code asyncTimeout} is null, zero or negative
     * @see DoclingServeApiConfig#ASYNC_TIMEOUT
     */
    @Override
    public B asyncTimeout(Duration asyncTimeout) {
      this.settings.asyncTimeout(asyncTimeout);
      return (B) this;
    }

    /**
     * Sets the {@link Executor} used to run async operations.
     *
     * <p>If not set, async operations run on the default async executor of
     * {@link java.util.concurrent.CompletableFuture}. The executor is never shut down by the client.
     *
     * @param asyncExecutor the executor to use for async operations
     * @return this builder instance for method chaining
     * @throws IllegalArgumentException if {@code asyncExecutor} is null
     * @see DoclingServeApiConfig#ASYNC_EXECUTOR
     */
    public B asyncExecutor(Executor asyncExecutor) {
      this.settings.asyncExecutor(asyncExecutor);
      return (B) this;
    }
  }

  private enum JacksonVersion {
    JACKSON_2("com.fasterxml.jackson.databind.json.JsonMapper"),
    JACKSON_3("tools.jackson.databind.json.JsonMapper");

    private final String jacksonClassName;

    JacksonVersion(String jacksonClassName) {
      this.jacksonClassName = jacksonClassName;
    }

    private boolean isOnClasspath(ClassLoader classLoader) {
      try {
        Class.forName(this.jacksonClassName, false, classLoader);
        return true;
      }
      catch (ClassNotFoundException e) {
        return false;
      }
    }
  }
}
