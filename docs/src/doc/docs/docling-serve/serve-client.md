# Docling Serve Client

[![docling-serve-client version](https://img.shields.io/badge/docling--serve--client_v{{ gradle.project_version }}-orange)](https://docling-project.github.io/docling-java/{{ gradle.project_version }}/docling-serve/serve-client)

The `docling-serve-client` module is the reference HTTP client for talking to a
[Docling Serve](https://github.com/docling-project/docling-serve) backend.

It implements the framework‑agnostic `DoclingServeApi` interface from
[`docling-serve-api`](serve-api.md) using Java's built‑in `HttpClient` for transport and
Jackson for JSON (auto‑detecting Jackson 2 or 3 at runtime).

If you only need the request/response model (without HTTP), use the API module:
- [`docling-serve-api`](serve-api.md)

## When to use this module

- You want a ready‑to‑use HTTP client for Docling Serve with minimal setup.
- You prefer a small dependency footprint (Java `HttpClient` + Jackson).
- You need a simple builder to tweak base URL, HTTP timeouts, and request/response logging.

## Installation

Add the client dependency to your project.

=== "Gradle"

    ``` kotlin
    dependencies {
      implementation("{{ gradle.project_groupId }}:{{ gradle.serve_client_artifactId }}:{{ gradle.project_version }}")
    }
    ```

=== "Maven"

    ``` xml
    <dependency>
      <groupId>{{ gradle.project_groupId }}</groupId>
      <artifactId>{{ gradle.serve_client_artifactId }}</artifactId>
      <version>{{ gradle.project_version }}</version>
    </dependency>
    ```

This artifact brings in the API types transitively, so you can use `DoclingServeApi`,
`ConvertDocumentRequest`, etc., directly.

???+ warning "Jackson Implementation Required"

    Make sure you also include either a Jackson 2 or Jackson 3 dependency.

## Quick start

Create a client with `DoclingServeApi.builder()`, build a request, and call `convertSource()`:

```java
import java.net.URI;
import ai.docling.serve.api.DoclingServeApi;
import ai.docling.serve.api.convert.request.ConvertDocumentRequest;
import ai.docling.serve.api.convert.request.options.ConvertDocumentOptions;
import ai.docling.serve.api.convert.request.options.OutputFormat;
import ai.docling.serve.api.convert.request.source.HttpSource;
import ai.docling.serve.api.convert.request.target.InBodyTarget;
import ai.docling.serve.api.convert.response.ConvertDocumentResponse;

DoclingServeApi api = DoclingServeApi.builder()
    .baseUrl("http://localhost:8000") // your Docling Serve URL
    .logRequests() // log HTTP requests
    .logResponses() // log HTTP responses
    .prettyPrint() // pretty-print JSON requests/responses
    .build();

ConvertDocumentRequest request = ConvertDocumentRequest.builder()
    .source(HttpSource.builder().url(URI.create("https://arxiv.org/pdf/2408.09869")).build())
    .options(ConvertDocumentOptions.builder()
        .toFormat(OutputFormat.MARKDOWN)
        .includeImages(true)
        .build())
    .target(InBodyTarget.builder().build())
    .build();

ConvertDocumentResponse response = api.convertSource(request);
System.out.println(response.getDocument().getMarkdownContent());
```

## Core concepts and configuration

### Builder factory and Jackson auto‑detection

`DoclingServeApi.builder()` chooses an implementation based on what's on your classpath:

- Jackson 3 present → `DoclingServeJackson3Client`
- Else if Jackson 2 present → `DoclingServeJackson2Client`
- Otherwise → `IllegalStateException`

Advanced: You can customize the JSON mapper via `.toBuilder().jsonParser(...)` on the concrete
client type if you need special Jackson modules or settings.

### Base URL

Set the Docling Serve base URL with either a `String` or `URI`:

```java
DoclingServeApi api = DoclingServeApi.builder()
    .baseUrl("http://localhost:8000")
    .build();
```

Note: When using plain `http://`, the client enforces HTTP/1.1 for compatibility with FastAPI,
which avoids HTTP/2 downgrade mishaps in some environments.

### HTTP client customization (timeouts, proxies, TLS)

You can supply and tune a `java.net.http.HttpClient.Builder`:

```java
import java.net.http.HttpClient;
import java.time.Duration;

DoclingServeApi api = DoclingServeApi.builder()
    .baseUrl("https://serve.example.com")
    .httpClientBuilder(HttpClient.newBuilder()
        .connectTimeout(Duration.ofSeconds(20))
        // .proxy(ProxySelector.of(new InetSocketAddress("proxy", 8080)))
        // .sslContext(customSslContext)
    )
    .build();
```

### Request/response logging

Enable lightweight logging for diagnostics:

```java
DoclingServeApi noisy = DoclingServeClientBuilderFactory.newBuilder()
    .baseUrl("http://localhost:8000")
    .logRequests()
    .logResponses()
    .prettyPrint()
    .build();
```

### Health checks

```java
import ai.docling.serve.api.health.HealthCheckResponse;

HealthCheckResponse health = api.health();
System.out.println("Service status: " + health.getStatus());
```

## Working with sources and targets

All request/response types come from [`docling-serve-api`](serve-api.md). Common patterns:

- HTTP source

  ```java
  import ai.docling.serve.api.convert.request.source.HttpSource;
  var httpSource = HttpSource.builder()
      .url(URI.create("https://example.com/file.pdf"))
      // .header("Authorization", "Bearer ...")
      .build();
  ```

- File upload (Base64)

  ```java
  import java.util.Base64;
  import ai.docling.serve.api.convert.request.source.FileSource;
  byte[] bytes = /* read file */
  var fileSource = FileSource.builder()
      .filename("report.pdf")
      .base64String(Base64.getEncoder().encodeToString(bytes))
      .build();
  ```

- Deliver results in the response body (default)

  ```java
  import ai.docling.serve.api.convert.request.target.InBodyTarget;
  var target = InBodyTarget.builder().build();
  ```

- Upload results to your storage via HTTP PUT

  ```java
  import ai.docling.serve.api.convert.request.target.PutTarget;
  var put = PutTarget.builder()
      .uri(URI.create("https://storage.example.com/out/report.md"))
      // .header("Authorization", "Bearer ...")
      .build();
  ```

## Error handling tips

Transport errors (DNS, TLS, connection reset, timeouts) are thrown as standard Java exceptions
from `HttpClient`. Conversion may also return structured errors in the response body — inspect
`ConvertDocumentResponse#getErrors()` even when content is present:

```java
var result = api.convertSource(request);
if (result.getErrors() != null && !result.getErrors().isEmpty()) {
  result.getErrors().forEach(err ->
      System.err.println("Component=" + err.getComponentType()
          + ", Module=" + err.getModuleName()
          + ", Message=" + err.getErrorMessage()));
}
```

## Version compatibility

The client is tested against all published versions of Docling Serve each week. Below are the latest run results:

{%
    include-markdown "includes/docling-serve/serve-compatibility.md"
    comments=false
    heading-offset=2
%}
