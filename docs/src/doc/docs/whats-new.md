# What's New in Docling Java {{ gradle.project_version }}

Docling Java {{ gradle.project_version }} provides a number of new features, enhancements, and bug fixes. This page includes the highlights of the release, but you can also check out the full [release notes](https://github.com/docling-project/docling-java/releases) for more details about each new feature and bug fix.

## Docling Serve

* The classes in the `docling-serve-api` module have been moved from the `ai.docling.api.serve` package to the `ai.docling.serve.api` package.
* The classes in the `docling-serve-client` module have been moved from the `ai.docling.client.serve` package to the `ai.docling.serve.client` package.
* The `DoclingServeApi` provides two new methods: `chunkSourceWithHierarchicalChunker()` for chunking a source document with a hierarchical chunker and `chunkSourceWithHybridChunker()` for chunking a source document with a hybrid chunker. Read more about Docling chunkers in the [Chunking](https://docling-project.github.io/docling/concepts/chunking/) section of the Docling documentation.
