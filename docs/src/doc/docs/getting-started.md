# Getting Started

Use the [`DoclingServeApi`](docling-serve/serve-api.md) to convert a document by pointing at a running [Docling Serve](https://github.com/docling-project/docling-serve) instance.

```java
import ai.docling.api.serve.DoclingServeApi;
import ai.docling.api.serve.convert.request.ConvertDocumentRequest;
import ai.docling.api.serve.convert.request.source.HttpSource;
import ai.docling.api.serve.convert.response.ConvertDocumentResponse;
import ai.docling.client.serve.DoclingServeClientBuilderFactory;

DoclingServeApi doclingServeApi = DoclingServeClientBuilderFactory.newBuilder()
    .baseUrl("<location of docling serve instance>")
    .build();

ConvertDocumentRequest request = new ConvertDocumentRequest()
    .withSources(List.of(new HttpSource().withUrl(URI.create("https://arxiv.org/pdf/2408.09869"))))
    .build();

ConvertDocumentResponse response = doclingApi.convertSource(request);
System.out.println(response.getDocument().getMarkdownContent());
```

For more examples and options, explore the modules listed above and the repository README.

## Links

- Source repository: https://github.com/docling-project/docling-java
- Docling (core project): https://github.com/docling-project
- Supported formats: https://docling-project.github.io/docling/usage/supported_formats/
- DoclingDocument concept: https://docling-project.github.io/docling/concepts/docling_document/
- Community Discord: https://docling.ai/discord
