# What's New in Docling Java

Docling Java {{ gradle.project_version }} includes important breaking changes, along with new features, enhancements, and bug fixes. This page includes the highlights of the release, but you can also check out the full [release notes](https://github.com/docling-project/docling-java/releases) for more details about each change.

## General

### {{ gradle.project_version }}


### 0.5.2

* **New `docling-bom` module** — A Maven BOM (`ai.docling:docling-bom`) is now published, allowing consumers to align all Docling Java module versions with a single import.
* **Codecov configuration fixes** — Fixed module path mappings, ignore rules, and added per-Java-version coverage flags for accurate coverage reporting across all modules.

### 0.5.1

* Include `furniture` field in `DoclingDocument`.
* CycloneDX SBOM artifacts are now published alongside each module.

### 0.1.5

* New `docling-core` module with `DoclingDocument` API mirroring the [docling-core](https://github.com/docling-project/docling-core) Python library's document representation model.

## Docling Serve

### {{ gradle.project_version }}

* **Breaking: new `DoclingServeApiProvider` SPI** — Implementations are now provided through `ai.docling.serve.api.spi.DoclingServeApiProvider`, which receives an immutable `DoclingServeApiConfig`, so new options can be added without breaking implementations. A provider can declare the options it doesn't honor via `unsupportedOptions()`, to have them ignored, logged or rejected when a caller sets them. `DoclingServeApi` has a new abstract `config()` method. `DoclingServeApiBuilderFactory`, `DoclingServeApi.DoclingApiBuilder` and `DoclingServeApi.toBuilder()` are deprecated for removal; legacy factories are still used when no provider is found. `docling-serve-api` now depends on `slf4j-api`. See [Migrating to `DoclingServeApiProvider`](docling-serve/serve-api-provider-migration.md).
* **Breaking: `DoclingServeApi.builder()` returns a `DoclingServeApiBuilder`** — It used to return the builder of the implementation through a generic `<T, B> B` return type. Fluent chains and `var` declarations are unaffected, but code declaring the builder type must be updated. Client-specific settings such as `httpClientBuilder` are available from `DoclingServeClient.builder()`, which detects the version of Jackson the same way, and the JSON mapper from `DoclingServeJackson3Client.builder()` / `DoclingServeJackson2Client.builder()`, which are now public. An API can be copied with `api.config().toBuilder()`.
* **`DoclingServeClientBuilderFactory` is deprecated** — Use `DoclingServeApi.builder()` for the options shared by every implementation, or the new `DoclingServeClient.builder()` for client-specific settings: it returns the same builder as `DoclingServeClientBuilderFactory.newBuilder()`. The factory will be removed in a future release.
* **`toBuilder()` of the reference client keeps the timeouts and the redirect policy** — Copying a `DoclingServeJackson2Client` or `DoclingServeJackson3Client` with `toBuilder()` used to reset `connectTimeout` and `readTimeout` to their defaults, and stop following redirects. The copy now keeps them. Other `HttpClient` settings, such as a proxy or an SSL context, are still not copied.
* **The builders of the reference client validate values when they are set** — Setting an invalid value on the builder of `DoclingServeJackson2Client` or `DoclingServeJackson3Client`, such as `connectTimeout(Duration.ZERO)` or `httpClientBuilder(null)`, now throws an `IllegalArgumentException` from the setter instead of from `build()`. The builders also gain `config(DoclingServeApiConfig)`, which applies a whole configuration at once, and `config()` on the reference client now reports only the options that were explicitly set.
* **Custom `Executor` for async operations** — The async methods (`convertSourceAsync`, `convertSourceBatchAsync`, `convertFilesAsync`, `chunkSourceWith*ChunkerAsync`, ...) used to run on `CompletableFuture`'s default executor (usually `ForkJoinPool.commonPool()`). A new `asyncExecutor(Executor)` builder method lets you run them (task submission, status polling and result retrieval) on your own executor instead, e.g. a virtual-thread executor or one managed by your framework. When not set, the behavior is unchanged. The client never shuts the executor down.

### 0.6.6

* **New `DocumentRequest` sealed base class** — `ConvertDocumentRequest`, `BatchConvertDocumentRequest`, and `ChunkDocumentRequest` now extend a common `DocumentRequest` abstract class in the `ai.docling.serve.api.request` package. This enables polymorphism when working with different request types — for example, accepting a `DocumentRequest` and dispatching to the correct endpoint based on the concrete type via pattern matching.
* **New `ProcessedDocumentResponse` sealed base class** — `ConvertDocumentResponse` and `ChunkDocumentResponse` now extend a common `ProcessedDocumentResponse` abstract class in the `ai.docling.serve.api.response` package. This enables polymorphic handling of document processing responses — for example, using `ProcessedDocumentResponse` as a type bound in generic APIs that work with both conversion and chunking results.
* **`toBuilder()` on the `DocumentRequest` base type** — `DocumentRequest` (and the intermediate `ChunkDocumentRequest`) now expose `toBuilder()`, so a request can be cloned and modified through the base type without first pattern-matching on the concrete subtype. This makes it possible to inject a `source` or `target` once — polymorphically — before dispatching, e.g. `request.toBuilder().source(source).build()`.

### 0.6.1

* **New batch conversion support** — Added `convertSourceBatch()` and `convertSourceBatchAsync()` methods to `DoclingServeConvertApi` for the new `/v1/convert/source/batch` endpoint. Submit multiple HTTP or S3 sources for batch processing with optional webhook callbacks for progress notifications. Requires docling-serve v1.22.0+.
* **New `BatchConvertDocumentRequest`** — Request model for batch conversions, supporting `sources` (HTTP or S3), `target` (PresignedUrlTarget or S3Target), conversion `options`, and optional `callbacks` (webhook specifications).
* **New `CallbackSpec`** — Webhook callback specification for receiving progress notifications during batch processing, with `url`, `headers`, and optional `caCert` fields.
* **New `maxNumElements` field on `S3Source`** — Caps the number of S3 objects processed in a single batch, useful when the source bucket contains many objects.
* **New `PresignedUrlTarget` request target** — Request server-managed presigned-URL delivery by setting `target` to `PresignedUrlTarget`. The docling-serve instance uploads each output artifact to its configured object storage and returns time-limited presigned download URLs in the response. Requires docling-serve v1.22.0+.
* **New `PreSignedUrlConvertResponse` response type** — Returned when using `PresignedUrlTarget`. Contains per-document results in a `documents` list, where each `DocumentArtifactItem` carries the conversion status and a list of `ArtifactRef` entries with presigned download URLs for each output format.
* **New supporting types** — `DocumentArtifactItem`, `ArtifactRef`, `ArtifactType`, `ConversionStatus`, `ProfilingItem`, `ProfilingScope`.
* Added `numPartiallySucceeded` field to `PreSignedUrlConvertDocumentResponse` to align with the current OpenAPI spec.
* Upgraded default Testcontainers image from v1.19.0 to v1.24.0.

### 0.5.0

#### Breaking Changes

* **`ConvertDocumentResponse` is now an abstract class** with three concrete implementations:
    * `InBodyConvertDocumentResponse` - Response content embedded directly in the response body
    * `ZipArchiveConvertDocumentResponse` - Response content packaged and returned as a ZIP archive
    * `PreSignedUrlConvertDocumentResponse` - Response content packaged as a ZIP archive and uploaded to an S3 bucket or a pre-signed URL and statistical data is returned.
* A discriminator method `getResponseType()` is provided to determine the response type, returning one of:
    * `ResponseType.IN_BODY`
    * `ResponseType.ZIP_ARCHIVE`
    * `ResponseType.PRE_SIGNED_URL`
* **Response type determination logic:**
    * `InBodyConvertDocumentResponse` (`ResponseType.IN_BODY`) - Returned when:
        * Target is `InBodyTarget` (default) AND only a single source is provided
    * `ZipArchiveConvertDocumentResponse` (`ResponseType.ZIP_ARCHIVE`) - Returned when:
        * Target is `ZipTarget` OR
        * Multiple sources are provided with default or `InBodyTarget`
    * `PreSignedUrlConvertDocumentResponse` (`ResponseType.PRE_SIGNED_URL`) - Returned when:
        * Target is `S3Target` or `PutTarget`
* **Migration guide:** Use `getResponseType()` to determine the concrete type and cast accordingly, or use pattern matching (Java 16+) or instanceof checks to handle different response types.

### 0.4.7

* Support configuring timeouts in `DoclingServeApi.Builder` via `connectTimeout(Duration)` and `requestTimeout(Duration)`.
* Fix incorrect values in `OcrEngine` enum.

### 0.4.4

* Fix: path component in `baseUrl` is now correctly preserved when building API request URIs.

### 0.4.3

* Add S3-based source and target support with enhanced extensibility.
* Introduce API extension point and enhance builder usage.

### 0.4.2

* Migrate from `java.util.logging` to SLF4J for logging.
* Fix custom `Duration` serializers with `ChronoUnit` support.
* Fix JSON property name from `options` to `convert_options` in chunk document requests.

### 0.4.1

* Handle validation errors returned by Docling Serve (i.e. `422` responses).

### 0.4.0

* Implementation of the Docling Serve clear and task APIs in `docling-serve-api` and `docling-serve-client`.
* Adding `pretty-print` configuration option to `DoclingServeClient` to enable pretty printing of JSON requests and responses.
* Adding the ability to specify an api key for the Docling Serve Testcontainer.
* Adding the ability to specify the api key for the Docling Serve requests.
* Adding async conversion support via `convertSourceAsync(ConvertDocumentRequest)` which returns a `CompletableFuture<ConvertDocumentResponse>`. The method handles all polling internally and completes the future when the conversion is done. Configurable via `asyncPollInterval(Duration)` and `asyncTimeout(Duration)` builder methods.
* Adding async chunking support.
* Adding convenience methods for chunking/converting local files.
* Added Javadoc comments for classes in the `ai.docling.serve.api` package.

### 0.3.0

* The classes in the `docling-serve-api` module have been moved from the `ai.docling.api.serve` package to the `ai.docling.serve.api` package.
* The classes in the `docling-serve-client` module have been moved from the `ai.docling.client.serve` package to the `ai.docling.serve.client` package.
* The classes in the `docling-core` module have been moved from the `ai.docling.api.core` package to the `ai.docling.core` package.
* The `DoclingServeApi` provides two new methods: `chunkSourceWithHierarchicalChunker()` for chunking a source document with a hierarchical chunker and `chunkSourceWithHybridChunker()` for chunking a source document with a hybrid chunker. Read more about Docling chunkers in the [Chunking](https://docling-project.github.io/docling/concepts/chunking/) section of the Docling documentation.
* Module info files have been added to all of the modules
