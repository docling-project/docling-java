# What's New in Docling Java

Docling Java {{ gradle.project_version }} provides a number of new features, enhancements, and bug fixes. This page includes the highlights of the release, but you can also check out the full [release notes](https://github.com/docling-project/docling-java/releases) for more details about each new feature and bug fix.

## Docling Serve

### {{ gradle.project_version }}

* Implementation of the Docling Serve clear and task APIs in `docling-serve-api` and `docling-serve-client`.
* Adding `pretty-print` configuration option to `DoclingServeClient` to enable pretty printing of JSON requests and responses.
* Adding the ability to specify an api key for the Docling Serve Testcontainer.
* Adding the ability to specify the api key for the Docling Serve requests.
* Adding async conversion support via `convertSourceAsync(ConvertDocumentRequest)` which returns a `CompletableFuture<ConvertDocumentResponse>`. The method handles all polling internally and completes the future when the conversion is done. Configurable via `asyncPollInterval(Duration)` and `asyncTimeout(Duration)` builder methods.
* Added Javadoc comments for classes in the `ai.docling.serve.api` package.

### 0.3.0

* The classes in the `docling-serve-api` module have been moved from the `ai.docling.api.serve` package to the `ai.docling.serve.api` package.
* The classes in the `docling-serve-client` module have been moved from the `ai.docling.client.serve` package to the `ai.docling.serve.client` package.
* The classes in the `docling-core` module have been moved from the `ai.docling.api.core` package to the `ai.docling.core` package.
* The `DoclingServeApi` provides two new methods: `chunkSourceWithHierarchicalChunker()` for chunking a source document with a hierarchical chunker and `chunkSourceWithHybridChunker()` for chunking a source document with a hybrid chunker. Read more about Docling chunkers in the [Chunking](https://docling-project.github.io/docling/concepts/chunking/) section of the Docling documentation.
* Module info files have been added to all of the modules
