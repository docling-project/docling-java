package ai.docling.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Objects;

import tools.jackson.databind.json.JsonMapper;

import ai.docling.api.DoclingApi;
import ai.docling.api.convert.request.ConvertDocumentRequest;
import ai.docling.api.convert.response.ConvertDocumentResponse;
import ai.docling.api.health.HealthCheckResponse;

import static ai.docling.api.util.ValidationUtils.*;

/**
 * Default implementation for the Docling API client.
 */
public class DoclingClient implements DoclingApi {
  private static final URI DEFAULT_BASE_URL = URI.create("http://localhost:5001");

  private final URI baseUrl;
  private final HttpClient httpClient;
  private final JsonMapper jsonMapper;

  private DoclingClient(Builder builder) {
    this.baseUrl = ensureNotNull(builder.baseUrl, "baseUrl");

    if (Objects.equals(this.baseUrl.getScheme(), "http")) {
      // Docling Serve uses Python FastAPI which causes errors when called from JDK HttpClient.
      // The HttpClient uses HTTP 2 by default and then falls back to HTTP 1.1 if not supported.
      // However, the way FastAPI works results in the fallback not happening, making the call fail.
      builder.httpClientBuilder.version(HttpClient.Version.HTTP_1_1);
    }

    this.httpClient = ensureNotNull(builder.httpClientBuilder, "httpClientBuilder").build();
    this.jsonMapper = ensureNotNull(builder.jsonMapperBuilder, "jsonMapperBuilder").build();
  }

  @Override
  public HealthCheckResponse health() {
    var httpRequest = HttpRequest.newBuilder()
        .uri(baseUrl.resolve("/health"))
        .header("Accept", "application/json")
        .GET()
        .build();

    return httpClient.sendAsync(httpRequest, BodyHandlers.ofString())
        .thenApply(HttpResponse::body)
        .thenApply(json -> jsonMapper.readValue(json, HealthCheckResponse.class))
        .join();
  }

  @Override
  public ConvertDocumentResponse convertSource(ConvertDocumentRequest request) {
    var httpRequest = HttpRequest.newBuilder()
        .uri(baseUrl.resolve("/v1/convert/source"))
        .header("Accept", "application/json")
        .header("Content-Type", "application/json")
        .POST(HttpRequest.BodyPublishers.ofString(jsonMapper.writeValueAsString(request)))
        .build();

    return httpClient.sendAsync(httpRequest, BodyHandlers.ofString())
        .thenApply(HttpResponse::body)
        .thenApply(json -> jsonMapper.readValue(json, ConvertDocumentResponse.class))
        .join();
  }

  @Override
  public Builder toBuilder() {
    return new Builder(this);
  }

  /**
   * Creates and returns a new instance of {@link Builder} for constructing a {@link DoclingClient}.
   * The builder allows customization of configuration such as base URL, HTTP client, and JSON mapper.
   *
   * @return a new {@link Builder} instance for creating a {@link DoclingClient}.
   */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * A builder class for creating instances of {@link DoclingClient}. This builder supports a fluent
   * API for configuring properties such as the base URL, HTTP client, and JSON mapper.
   *
   * <p>The {@link Builder} provides customization options through the available methods and ensures
   * proper validation of inputs during configuration.
   *
   * <p>An instance of {@code Builder} can be obtained via {@link DoclingClient#builder()} or
   * {@link DoclingClient#toBuilder()}.
   *
   * <p>This class is an implementation of {@link DoclingApiBuilder}, allowing it to construct
   * {@code DoclingClient} instances as part of the API construction process.
   */
  public static final class Builder implements DoclingApiBuilder<DoclingClient, Builder> {
    private URI baseUrl = DEFAULT_BASE_URL;
    private HttpClient.Builder httpClientBuilder = HttpClient.newBuilder();
    private JsonMapper.Builder jsonMapperBuilder = JsonMapper.builder();

    private Builder() {
    }

    private Builder(DoclingClient doclingClient) {
      this.baseUrl = doclingClient.baseUrl;
      this.httpClientBuilder = doclingClient.httpClient.newBuilder();
      this.jsonMapperBuilder = doclingClient.jsonMapper.rebuild();
    }

    /**
     * Sets the base URL for the Docling API service.
     * 
     * <p>The URL string will be parsed and converted to a {@link URI}.
     *
     * @param baseUrl the base URL as a string (must not be blank)
     * @return this {@link Builder} instance for method chaining
     * @throws IllegalArgumentException if baseUrl is null or blank
     */
    public Builder baseUrl(String baseUrl) {
      this.baseUrl = URI.create(ensureNotBlank(baseUrl, "baseUrl"));
      return this;
    }

    /**
     * Sets the base URL for the Docling API service.
     *
     * @param baseUrl the base URL as a {@link URI}
     * @return this {@link Builder} instance for method chaining
     */
    public Builder baseUrl(URI baseUrl) {
      this.baseUrl = baseUrl;
      return this;
    }

    /**
     * Sets the HTTP client builder to be used for creating the underlying HTTP client.
     * 
     * <p>This allows customization of HTTP client properties such as timeouts,
     * proxy settings, SSL context, and other connection parameters.
     *
     * @param httpClientBuilder the {@link HttpClient.Builder} to use
     * @return this {@link Builder} instance for method chaining
     */
    public Builder httpClientBuilder(HttpClient.Builder httpClientBuilder) {
      this.httpClientBuilder = httpClientBuilder;
      return this;
    }

    /**
     * Sets the JSON mapper builder to be used for creating the JSON mapper.
     * 
     * <p>This allows customization of JSON serialization and deserialization behavior,
     * such as configuring features, modules, or property naming strategies.
     *
     * @param jsonMapperBuilder the {@link JsonMapper.Builder} to use
     * @return this {@link Builder} instance for method chaining
     */
    public Builder jsonParser(JsonMapper.Builder jsonMapperBuilder) {
      this.jsonMapperBuilder = jsonMapperBuilder;
      return this;
    }

    /**
     * Builds and returns a new {@link DoclingClient} instance with the configured properties.
     *
     * <p>This method validates all required parameters and constructs the client.
     *
     * @return a new {@link DoclingClient} instance
     * @throws IllegalArgumentException if any required parameter is null
     */
    @Override
    public DoclingClient build() {
      return new DoclingClient(this);
    }
  }
}
