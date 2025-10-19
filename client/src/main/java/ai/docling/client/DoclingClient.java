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

/**
 * Default implementation for the Docling API client.
 */
public class DoclingClient implements DoclingApi {

  private static final URI DEFAULT_BASE_URL = URI.create("http://localhost:5001");

  private final URI baseUrl;
  private final HttpClient httpClient;
  private final JsonMapper jsonMapper;

  private DoclingClient(URI baseUrl, HttpClient httpClient, JsonMapper jsonMapper) {
    this.baseUrl = baseUrl;
    this.httpClient = httpClient;
    this.jsonMapper = jsonMapper;
  }

  public static Builder builder() {
    return new Builder();
  }

  @Override
  public HealthCheckResponse health() {
    HttpRequest httpRequest = HttpRequest.newBuilder()
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
    HttpRequest httpRequest = HttpRequest.newBuilder()
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

  public static final class Builder {
    private URI baseUrl = DEFAULT_BASE_URL;
    private HttpClient.Builder httpClientBuilder = HttpClient.newBuilder();
    private JsonMapper.Builder jsonMapperBuilder = JsonMapper.builder();

    private Builder() {}

    public Builder baseUrl(String baseUrl) {
      if (baseUrl == null || baseUrl.isBlank()) {
        throw new IllegalArgumentException("baseUrl cannot be null or empty");
      }
      this.baseUrl = URI.create(baseUrl);
      return this;
    }

    public Builder baseUrl(URI baseUrl) {
      if (baseUrl == null) {
        throw new IllegalArgumentException("baseUrl cannot be null");
      }
      this.baseUrl = baseUrl;
      return this;
    }

    public Builder httpClientBuilder(HttpClient.Builder httpClientBuilder) {
      if (httpClientBuilder == null) {
        throw new IllegalArgumentException("httpClientBuilder cannot be null");
      }
      this.httpClientBuilder = httpClientBuilder;
      return this;
    }

    public Builder jsonParser(JsonMapper.Builder jsonMapperBuilder) {
      if (jsonMapperBuilder == null) {
        throw new IllegalArgumentException("jsonMapperBuilder cannot be null");
      }
      this.jsonMapperBuilder = jsonMapperBuilder;
      return this;
    }

    public DoclingClient build() {
      if (Objects.equals(baseUrl.getScheme(), "http")) {
        // Docling Serve uses Python FastAPI which causes errors when called from JDK HttpClient.
        // The HttpClient uses HTTP 2 by default and then falls back to HTTP 1.1 if not supported.
        // However, the way FastAPI works results in the fallback not happening, making the call fail.
        httpClientBuilder.version(HttpClient.Version.HTTP_1_1);
      }

      return new DoclingClient(baseUrl, httpClientBuilder.build(), jsonMapperBuilder.build());
    }

  }

}
