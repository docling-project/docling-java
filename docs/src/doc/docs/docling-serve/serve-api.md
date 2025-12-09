# Docling Serve API

[![docling-serve-api version](https://img.shields.io/badge/docling--serve--api_v{{ gradle.project_version }}-orange)](https://docling-project.github.io/docling-java/{{ gradle.project_version }}/docling-serve/serve-api)

The `docling-serve-api` module defines the core, framework-agnostic Java API used to communicate
with a [Docling Serve](https://github.com/docling-project/docling-serve) backend. It provides the request/response model and the main `DoclingServeApi`
interface. You can use any implementation of this interface to talk to a running
[Docling Serve](https://github.com/docling-project/docling-serve) instance.

The base Java version is 17. This module has no other required dependencies, although it is compatible with both Jackson [2.x](https://github.com/FasterXML/jackson) and [3.x](https://github.com/FasterXML/jackson/blob/main/jackson3/MIGRATING_TO_JACKSON_3.md).

If you need a ready-to-use HTTP implementation, see the reference client:
- Docling Serve Client: [`docling-serve-client`](serve-client.md)

## When to use this module

- You want a stable, typed Java model for Docling Serve requests/responses without committing to a
  specific HTTP stack.
- You plan to create your own client implementation (e.g., different HTTP lib, reactive runtime) but
  still rely on the shared API model.
- You want to write code that is portable across different client implementations.

## Installation

Add the API dependency to your project.

=== "Gradle"

    ``` kotlin
    dependencies {
      implementation("{{ gradle.project_groupId }}:{{ gradle.serve_api_artifactId }}:{{ gradle.project_version }}")
    }
    ```

=== "Maven"

    ``` xml
    <dependency>
      <groupId>{{ gradle.project_groupId }}</groupId>
      <artifactId>{{ gradle.serve_api_artifactId }}</artifactId>
      <version>{{ gradle.project_version }}</version>
    </dependency>
    ```

Note: The API module does not perform network I/O by itself. To call a live service, combine it with
an implementation such as [`docling-serve-client`](serve-client.md).

## Quick start

Below is a minimal example using the reference client to create an implementation of `DoclingServeApi`,
build a conversion request, and retrieve Markdown output. The request/response types all come from
`docling-serve-api`.

```java
import java.net.URI;
import ai.docling.serve.api.DoclingServeApi;
import ai.docling.serve.api.convert.request.ConvertDocumentRequest;
import ai.docling.serve.api.convert.request.options.ConvertDocumentOptions;
import ai.docling.serve.api.convert.request.options.OutputFormat;
import ai.docling.serve.api.convert.request.source.HttpSource;
import ai.docling.serve.api.convert.request.target.InBodyTarget;
import ai.docling.serve.api.convert.response.ConvertDocumentResponse;
import ai.docling.serve.client.DoclingServeClientBuilderFactory; // from docling-serve-client

DoclingServeApi api = DoclingServeClientBuilderFactory
    .newBuilder()
    .baseUrl("http://localhost:8000") // your Docling Serve URL
    .build();

ConvertDocumentRequest request = ConvertDocumentRequest.builder()
    .source(HttpSource.builder().url(URI.create("https://arxiv.org/pdf/2408.09869"))
        .build())
    .options(ConvertDocumentOptions.builder()
        .toFormat(OutputFormat.MARKDOWN) // request Markdown output
        .includeImages(true)
        .build())
    .target(InBodyTarget.builder().build()) // get results in the HTTP response body
    .build();

ConvertDocumentResponse response = api.convertSource(request);
System.out.println(response.getDocument().getMarkdownContent());
```

## Core concepts

### The `DoclingServeApi` interface

Defined in `ai.docling.serve.api.DoclingServeApi`, this interface exposes two primary operations:

- `health()` → returns a `HealthCheckResponse` with service status.
- `convertSource(request)` → submits one or more sources plus options and an optional target,
  returning `ConvertDocumentResponse`.

Any HTTP or non-HTTP implementation can implement this interface. The reference implementation is
provided by the `docling-serve-client` module.

### Requests: `ConvertDocumentRequest`

Create a request via the builder:

```java
ConvertDocumentRequest request = ConvertDocumentRequest.builder()
    .source(/* one of the supported sources */)
    .options(/* conversion options */)
    .target(/* optional delivery target */)
    .build();
```

Supported sources (`ai.docling.serve.api.convert.request.source`):
- `HttpSource` — fetch content from a URL (optional custom headers)
- `FileSource` — embed content as Base64 with a filename

Targets (`ai.docling.serve.api.convert.request.target`):
- `InBodyTarget` — receive results directly in the API response body (default use case)
- `PutTarget` — the service uploads converted content via HTTP PUT to a specified URI
- `ZipTarget` — receive a zipped result

Options (`ai.docling.serve.api.convert.request.options.ConvertDocumentOptions`) let you control:
- Input/output formats (e.g., `fromFormats`, `toFormats`)
- OCR (e.g., `doOcr`, `forceOcr`, `ocrEngine`, `ocrLang`)
- PDF processing (e.g., `pdfBackend`)
- Tables (e.g., `tableMode`, `tableCellMatching`)
- Pipelines and page ranges (e.g., `pipeline`, `pageRange`)
- Timeouts and error behavior (e.g., `documentTimeout`, `abortOnError`)
- Enrichments (code/formula/picture), image handling, scaling, page break placeholder
- VLM pipeline hints

Explore the `options` package for the full list of knobs you can turn.

### Responses: `ConvertDocumentResponse` and `DocumentResponse`

- `ConvertDocumentResponse` contains the converted `document` (if any), `errors`, processing `status`,
  total `processing_time`, and detailed `timings` map.
- `DocumentResponse` holds the actual content fields you requested, such as `md_content` (Markdown),
  `html_content`, `text_content`, and a `json_content` map. It also includes the `filename` and
  `doctags_content` when relevant.

## Health checks

You can ping the service to check readiness and basic status:

```java
import ai.docling.serve.api.health.HealthCheckResponse;

HealthCheckResponse health = api.health();
System.out.println("Service status: " + health.getStatus());
```

## Error handling

Conversion may succeed partially (e.g., some pages) while returning warnings or errors. Always inspect
`ConvertDocumentResponse#getErrors()` and consider `status`:

```java
ConvertDocumentResponse response = api.convertSource(request);

if (response.getErrors() != null && !response.getErrors().isEmpty()) {
  response.getErrors().forEach(err ->
      System.err.println("Component: " + err.getComponentType() + 
          ", Module: " + err.getModuleName() + 
          ", Message: " + err.getErrorMessage()));
}

if (response.getDocument() != null && response.getDocument().getMarkdownContent() != null) {
  // Use the output
}
```

The exact transport-level exceptions (e.g., timeouts, connectivity) depend on the client implementation
you use. The reference client throws standard Java exceptions for HTTP and I/O failures.

## Logging and builders

`DoclingServeApi` exposes a `toBuilder()` method so implementations can be duplicated and tweaked. Most
client builders, including the reference client, also expose `logRequests()` and `logResponses()` for
simple diagnostics:

```java
DoclingServeApi newApi = api.toBuilder()
    .logRequests()
    .logResponses()
    .build();
```

## Version compatibility

The API is tested against all published versions of Docling Serve each week. Below are the latest run results:

{%
    include-markdown "includes/docling-serve/serve-compatibility.md"
    comments=false
    heading-offset=2
%}
