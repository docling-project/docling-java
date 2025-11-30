# Welcome to the Docling Java Project!

This is the repository for Docling Java, a Java API for using [Docling](https://github.com/docling-project).

[![Docs](https://img.shields.io/badge/docs-live-brightgreen)](https://docling-project.github.io/docling-java/)
[![docling-serve-api version](https://img.shields.io/maven-central/v/ai.docling/docling-serve-api?label=docling-serve-api
)](https://docling-project.github.io/docling-java/dev/docling-serve/serve-api/)
[![docling-serve-client version](https://img.shields.io/maven-central/v/ai.docling/docling-serve-client?label=docling-serve-client)](https://docling-project.github.io/docling-java/dev/docling-serve/serve-client/)
[![docling-testcontainers version](https://img.shields.io/maven-central/v/ai.docling/docling-testcontainers?label=docling-testcontainers)](https://docling-project.github.io/docling-java/dev/testcontainers/)
[![License MIT](https://img.shields.io/github/license/docling-project/docling-java)](https://opensource.org/licenses/MIT)
[![Discord](https://img.shields.io/discord/1399788921306746971?color=6A7EC2&logo=discord&logoColor=ffffff)](https://docling.ai/discord)
[![OpenSSF Best Practices](https://www.bestpractices.dev/projects/11397/badge)](https://www.bestpractices.dev/projects/11397)

[Docling](https://github.com/docling-project) simplifies document processing, parsing diverse formats, including advanced PDF understanding, and providing seamless integrations with the Generative AI ecosystem.

## Features

* 🗂️ Parsing of [multiple document formats][supported_formats] incl. PDF, DOCX, PPTX, XLSX, HTML, WAV, MP3, VTT, images (PNG, TIFF, JPEG, ...), and more
* 📑 Advanced PDF understanding incl. page layout, reading order, table structure, code, formulas, image classification, and more
* 🧬 Unified, expressive [DoclingDocument][docling_document] representation format
* ↪️ Various [export formats][supported_formats] and options, including Markdown, HTML, [DocTags](https://arxiv.org/abs/2503.11576) and lossless JSON
* 🔒 Local execution capabilities for sensitive data and air-gapped environments
* 🤖 Plug-and-play [integrations][integrations] including [LangChain4j](https://docs.langchain4j.dev/)
* 🔍 Extensive OCR support for scanned PDFs and images
* 👓 Support of several Visual Language Models ([GraniteDocling](https://huggingface.co/ibm-granite/granite-docling-258M))
* 🎙️ Audio support with Automatic Speech Recognition (ASR) models

## Documentation

[See the documentation](https://docling-project.github.io/docling-java/) for complete information on the [various artifacts](#artifacts) that are provided by this project.

## Artifacts

This project provides the following artifacts:

- [`docling-core`](docling-core): Java API for working with the data types used by Docling for document representation (see [Docling Core](https://github.com/docling-project/docling-core)).
- [`docling-serve-api`](docling-serve/docling-serve-api): Java API for interacting with a [Docling Serve](https://github.com/docling-project/docling-serve) backend. It's framework‑agnostic.
* [`docling-serve-client`](docling-serve/docling-serve-client): A reference implementation of the [`docling-serve-api`](docling-serve/docling-serve-api) using Java's [`HttpClient`](https://openjdk.org/groups/net/httpclient/intro.html) and [Jackson](https://github.com/FasterXML/jackson) to connect to a [Docling Serve](https://github.com/docling-project/docling-serve) endpoint.
* [`docling-testing`](docling-testing): Utilities for testing Docling integration.
* [`docling-testcontainers`](docling-testcontainers): A [Testcontainers module](https://testcontainers.com/) for running Docling in a Docker container.

## Getting started

Use `DoclingServeApi.convertSource()` to convert individual documents. For example:

```java
import ai.docling.api.serve.DoclingServeApi;
import ai.docling.api.serve.convert.request.ConvertDocumentRequest;
import ai.docling.api.serve.convert.request.source.HttpSource;
import ai.docling.api.serve.convert.response.ConvertDocumentResponse;
import ai.docling.client.serve.DoclingServeClientBuilderFactory;

DoclingServeApi doclingServeApi = DoclingServeClientBuilderFactory.newBuilder()
    .baseUrl("<location of docling serve instance>")
    .build();

ConvertDocumentRequest request = ConvertDocumentRequest.builder()
    .source(
        HttpSource.builder()
            .url(URI.create("https://arxiv.org/pdf/2408.09869"))
            .build()
    )
    .build();

ConvertDocumentResponse response = doclingServeApi.convertSource(request);
System.out.println(response.getDocument().getMarkdownContent());
```

More [usage information](https://docling-project.github.io/docling-java) is available in the docs.

## Get help and support

Please feel free to connect with us using the [discussion section](https://github.com/docling-project/docling-java/discussions).

## Contributing

Please read [Contributing to Docling Java](CONTRIBUTING.md) for details.

## License

The Docling codebase is under MIT license.
For individual model usage, please refer to the model licenses found in the original packages.

### IBM ❤️ Open Source AI

The project was started by the AI for knowledge team at IBM Research Zurich.

[supported_formats]: https://docling-project.github.io/docling/usage/supported_formats/
[docling_document]: https://docling-project.github.io/docling/concepts/docling_document/
[integrations]: https://docling-project.github.io/docling/integrations/

## Contributors ✨

Thanks goes to these wonderful people ([emoji key](https://allcontributors.org/docs/en/emoji-key)):

<!-- ALL-CONTRIBUTORS-LIST:START - Do not remove or modify this section -->
<!-- prettier-ignore-start -->
<!-- markdownlint-disable -->
<table>
  <tbody>
    <tr>
      <td align="center" valign="top" width="14.28%"><a href="https://developers.redhat.com/author/eric-deandrea"><img src="https://avatars.githubusercontent.com/u/363447?v=4?s=100" width="100px;" alt="Eric Deandrea"/><br /><sub><b>Eric Deandrea</b></sub></a><br /><a href="#code-edeandrea" title="Code">💻</a> <a href="#content-edeandrea" title="Content">🖋</a> <a href="#doc-edeandrea" title="Documentation">📖</a> <a href="#ideas-edeandrea" title="Ideas, Planning, & Feedback">🤔</a> <a href="#infra-edeandrea" title="Infrastructure (Hosting, Build-Tools, etc)">🚇</a> <a href="#maintenance-edeandrea" title="Maintenance">🚧</a> <a href="#projectManagement-edeandrea" title="Project Management">📆</a> <a href="#test-edeandrea" title="Tests">⚠️</a></td>
      <td align="center" valign="top" width="14.28%"><a href="http://thomasvitale.com"><img src="https://avatars.githubusercontent.com/u/8523418?v=4?s=100" width="100px;" alt="Thomas Vitale"/><br /><sub><b>Thomas Vitale</b></sub></a><br /><a href="#code-ThomasVitale" title="Code">💻</a> <a href="#content-ThomasVitale" title="Content">🖋</a> <a href="#doc-ThomasVitale" title="Documentation">📖</a> <a href="#ideas-ThomasVitale" title="Ideas, Planning, & Feedback">🤔</a> <a href="#infra-ThomasVitale" title="Infrastructure (Hosting, Build-Tools, etc)">🚇</a> <a href="#maintenance-ThomasVitale" title="Maintenance">🚧</a> <a href="#projectManagement-ThomasVitale" title="Project Management">📆</a> <a href="#test-ThomasVitale" title="Tests">⚠️</a> <a href="#review-ThomasVitale" title="Reviewed Pull Requests">👀</a></td>
      <td align="center" valign="top" width="14.28%"><a href="http://www.lordofthejars.com"><img src="https://avatars.githubusercontent.com/u/1517153?v=4?s=100" width="100px;" alt="Alex Soto"/><br /><sub><b>Alex Soto</b></sub></a><br /><a href="#ideas-lordofthejars" title="Ideas, Planning, & Feedback">🤔</a> <a href="#projectManagement-lordofthejars" title="Project Management">📆</a></td>
      <td align="center" valign="top" width="14.28%"><a href="http://ibm.biz/michele-dolfi"><img src="https://avatars.githubusercontent.com/u/97102151?v=4?s=100" width="100px;" alt="Michele Dolfi"/><br /><sub><b>Michele Dolfi</b></sub></a><br /><a href="#design-dolfim-ibm" title="Design">🎨</a> <a href="#ideas-dolfim-ibm" title="Ideas, Planning, & Feedback">🤔</a> <a href="#infra-dolfim-ibm" title="Infrastructure (Hosting, Build-Tools, etc)">🚇</a> <a href="#question-dolfim-ibm" title="Answering Questions">💬</a></td>
      <td align="center" valign="top" width="14.28%"><a href="http://ibm.biz/cesar-berrospi"><img src="https://avatars.githubusercontent.com/u/75900930?v=4?s=100" width="100px;" alt="Cesar Berrospi Ramis"/><br /><sub><b>Cesar Berrospi Ramis</b></sub></a><br /><a href="#ideas-ceberam" title="Ideas, Planning, & Feedback">🤔</a></td>
    </tr>
  </tbody>
</table>

<!-- markdownlint-restore -->
<!-- prettier-ignore-end -->

<!-- ALL-CONTRIBUTORS-LIST:END -->

This project follows the [all-contributors](https://github.com/all-contributors/all-contributors) specification. Contributions of any kind are welcome!

### IBM ❤️ Open Source AI
