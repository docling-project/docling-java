# What's New in Docling Java

Docling Java {{ gradle.project_version }} includes important breaking changes, along with new features, enhancements, and bug fixes. This page includes the highlights of the release, but you can also check out the full [release notes](https://github.com/docling-project/docling-java/releases) for more details about each change.

## General

### {{ gradle.project_version }}

* **New `docling-bom` module** — A Maven BOM (`ai.docling:docling-bom`) is now published, allowing consumers to align all Docling Java module versions with a single import.

### 0.5.1

* Include `furniture` field in `DoclingDocument`.
* CycloneDX SBOM artifacts are now published alongside each module.

### 0.1.5

* New `docling-core` module with `DoclingDocument` API mirroring the [docling-core](https://github.com/docling-project/docling-core) Python library's document representation model.

## Docling Serve

### {{ gradle.project_version }}


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
