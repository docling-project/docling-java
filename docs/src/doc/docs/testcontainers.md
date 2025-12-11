# Testcontainers

[![docling-testcontainers version](https://img.shields.io/badge/docling--testcontainers_v{{ gradle.project_version }}-orange)](https://docling-project.github.io/docling-java/{{ gradle.project_version }}/testcontainers)

The `docling-testcontainers` module provides a ready-to-use [Testcontainers](https://testcontainers.com/) integration for running a [Docling Serve](https://github.com/docling-project/docling-serve) instance in your tests. It wraps the official container image and exposes a simple Java API so you can spin up Docling as part of your JUnit test lifecycle and exercise client code against a real server.

If you need to talk to a running server from your application code, pair this module with the reference HTTP client:
- [`docling-serve-client`](docling-serve/serve-client.md)

## When to use this module

- You want end-to-end tests that run against a real Docling Serve in Docker.
- You need a portable, reliable way to boot Docling Serve on CI without managing it yourself.
- You want a convenient Java API to tweak the container image, environment variables, startup timeout, and optional UI.

## Installation

Add the Testcontainers module to your test dependencies.

=== "Gradle"

    ``` kotlin
    dependencies {
        testImplementation("{{ gradle.project_groupId }}:{{ gradle.testcontainers_artifactId }}:{{ gradle.project_version }}")
    }
    ```

=== "Maven"

    ``` xml
    <dependencies>
      <dependency>
        <groupId>{{ gradle.project_groupId }}</groupId>
        <artifactId>{{ gradle.testcontainers_artifactId }}</artifactId>
        <version>{{ gradle.project_version }}</version>
        <scope>test</scope>
      </dependency>
    </dependencies>
    ```

## Quick start

Spin up Docling Serve with JUnit 5 and run a simple health check using the reference client.

```java
import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import ai.docling.serve.api.DoclingServeApi;
import ai.docling.serve.api.health.HealthCheckResponse;
import ai.docling.serve.client.DoclingServeClientBuilderFactory;
import ai.docling.testcontainers.serve.DoclingServeContainer;
import ai.docling.testcontainers.serve.config.DoclingServeContainerConfig;

@Testcontainers
class DoclingContainerSmokeTest {
  @Container
  private final DoclingServeContainer docling = new DoclingServeContainer(
      DoclingServeContainerConfig.builder()
          .image(DoclingServeContainerConfig.DOCLING_IMAGE)
          .enableUi(true)
          .build()
  );

  @Test
  void health_is_ok() {
    String base = "http://" + docling.getHost() + ":" + docling.getPort();

    DoclingServeApi api = DoclingServeClientBuilderFactory
        .newBuilder()
        .baseUrl(base)
        .build();

    HealthCheckResponse health = api.health();
    assertThat(health.getStatus()).isNotBlank();
  }
}
```

???+ note

    - `@Container` manages container lifecycle for you. Use a `static` field to reuse the container across tests in the class.
    - `getHost()` and `getPort()` are provided by Testcontainers/`GenericContainer`; `getPort()` maps the container’s internal port to a random free host port.

## Using it with the reference client for conversions

Once the container is up, you can call the Convert endpoint through `docling-serve-client`:

```java
import java.net.URI;
import ai.docling.serve.api.convert.request.ConvertDocumentRequest;
import ai.docling.serve.api.convert.request.options.ConvertDocumentOptions;
import ai.docling.serve.api.convert.request.options.OutputFormat;
import ai.docling.serve.api.convert.request.source.HttpSource;
import ai.docling.serve.api.convert.request.target.InBodyTarget;
import ai.docling.serve.api.convert.response.ConvertDocumentResponse;

String baseUrl = "http://" + docling.getHost() + ":" + docling.getPort();
DoclingServeApi api = DoclingServeClientBuilderFactory.newBuilder().baseUrl(baseUrl).build();

ConvertDocumentRequest request = ConvertDocumentRequest.builder()
    .source(HttpSource.builder().url(URI.create("https://arxiv.org/pdf/2408.09869")).build())
    .options(ConvertDocumentOptions.builder()
        .toFormat(OutputFormat.MARKDOWN)
        .includeImages(true)
        .build())
    .target(InBodyTarget.builder().build())
    .build();

ConvertDocumentResponse response = api.convertSource(request);
// Assert on response.getDocument().getMarkdownContent(), errors, timings, etc.
```

## Configuration

The container is configured via `DoclingServeContainerConfig`. Use its fluent builder to customize:

- Container image (default points to the official GHCR image)
- Whether to enable the demonstration UI
- Additional environment variables
- Startup timeout

```java
import java.time.Duration;
import java.util.Map;
import ai.docling.testcontainers.serve.config.DoclingServeContainerConfig;
import ai.docling.testcontainers.serve.DoclingServeContainer;

DoclingServeContainerConfig config = DoclingServeContainerConfig.builder()
    // Override the image if you need a different tag or registry
    .image("ghcr.io/docling-project/docling-serve:v1.9.0")
    // Enable the optional web UI served by Docling Serve
    .enableUi(true)
    // Pass environment variables to fine‑tune behavior
    .containerEnv(Map.of(
        // Example: configure OCR languages or pipeline hints if supported by the server
        "DOCLING_OCR_LANG", "eng,deu"
    ))
    // Increase startup timeout if your CI is slow to pull images
    .startupTimeout(Duration.ofMinutes(2))
    // Set an optional api key for that consumers must specify
    .apiKey("my-secret-api-key")
    .build();

DoclingServeContainer container = new DoclingServeContainer(config);
```

### Defaults

- Default internal port: `5001` (exposed and mapped automatically)
- Default image: `ghcr.io/docling-project/docling-serve:<version>`
- Default startup timeout: 1 minute

## Tips and caveats

- A container runtime (Docker/Podman) must be available to the test process (locally or on CI). For GitHub Actions, consider the official Testcontainers setup docs.
- Prefer `static` containers for faster test suites; or use reusability where applicable per Testcontainers guidelines.
- If you run multiple containers, consider a shared `@Testcontainers` test base to avoid repeated pulls.
- For plain `http://` base URLs, the reference client forces HTTP/1.1 to interoperate smoothly with FastAPI.
- Clean up resources: JUnit/Testcontainers manages lifecycle automatically for annotated containers. If you manage containers manually, remember to call `start()`/`stop()`.

## Related modules

- API types and request model: [`docling-serve-api`](docling-serve/serve-api.md)
- Reference HTTP client: [`docling-serve-client`](docling-serve/serve-client.md)
