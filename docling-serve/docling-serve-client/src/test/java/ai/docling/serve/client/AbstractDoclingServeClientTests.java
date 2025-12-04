package ai.docling.serve.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Base64;
import java.util.List;

import org.junit.jupiter.api.Test;

import ai.docling.api.core.DoclingDocument;
import ai.docling.api.core.DoclingDocument.DocItemLabel;
import ai.docling.serve.api.DoclingServeApi;
import ai.docling.serve.api.chunk.request.HierarchicalChunkDocumentRequest;
import ai.docling.serve.api.chunk.request.options.HierarchicalChunkerOptions;
import ai.docling.serve.api.chunk.request.HybridChunkDocumentRequest;
import ai.docling.serve.api.chunk.request.options.HybridChunkerOptions;
import ai.docling.serve.api.chunk.response.Chunk;
import ai.docling.serve.api.chunk.response.ChunkDocumentResponse;
import ai.docling.serve.api.convert.request.ConvertDocumentRequest;
import ai.docling.serve.api.convert.request.options.ConvertDocumentOptions;
import ai.docling.serve.api.convert.request.options.OutputFormat;
import ai.docling.serve.api.convert.request.options.TableFormerMode;
import ai.docling.serve.api.convert.request.source.FileSource;
import ai.docling.serve.api.convert.request.source.HttpSource;
import ai.docling.serve.api.convert.response.ConvertDocumentResponse;
import ai.docling.serve.api.health.HealthCheckResponse;
import ai.docling.testcontainers.serve.DoclingServeContainer;
import ai.docling.testcontainers.serve.config.DoclingServeContainerConfig;

abstract class AbstractDoclingServeClientTests {
  protected static final DoclingServeContainer doclingContainer = new DoclingServeContainer(
    DoclingServeContainerConfig.builder()
        .image(DoclingServeContainerConfig.DOCLING_IMAGE)
        .enableUi(true)
        .build()
  );

  static {
    doclingContainer.start();
  }

  protected abstract DoclingServeApi getDoclingClient();

  @Test
  void shouldSuccessfullyCallHealthEndpoint() {
    HealthCheckResponse response = getDoclingClient().health();

    assertThat(response)
        .isNotNull()
        .extracting(HealthCheckResponse::getStatus)
        .isEqualTo("ok");
  }

  @Test
  void shouldConvertHttpSourceSuccessfully() {
    ConvertDocumentRequest request = ConvertDocumentRequest.builder()
        .source(HttpSource.builder().url(URI.create("https://docs.arconia.io/arconia-cli/latest/development/dev/")).build())
        .build();

    ConvertDocumentResponse response = getDoclingClient().convertSource(request);

    assertThat(response).isNotNull();

    assertThat(response.getStatus()).isNotEmpty();
    assertThat(response.getDocument()).isNotNull();
    assertThat(response.getDocument().getFilename()).isNotEmpty();

    if (response.getProcessingTime() != null) {
      assertThat(response.getProcessingTime()).isPositive();
    }

    assertThat(response.getDocument().getMarkdownContent()).isNotEmpty();
  }

  @Test
  void shouldConvertFileSourceSuccessfully() throws IOException {
    var fileResource = readFileFromClasspath("story.pdf");
    ConvertDocumentRequest request = ConvertDocumentRequest.builder()
        .source(FileSource.builder()
            .filename("story.pdf")
            .base64String(Base64.getEncoder().encodeToString(fileResource))
            .build()
        )
        .build();

    ConvertDocumentResponse response = getDoclingClient().convertSource(request);

    assertThat(response).isNotNull();
    assertThat(response.getStatus()).isNotEmpty();
    assertThat(response.getDocument()).isNotNull();
    assertThat(response.getDocument().getFilename()).isEqualTo("story.pdf");

    if (response.getProcessingTime() != null) {
      assertThat(response.getProcessingTime()).isPositive();
    }

    assertThat(response.getDocument().getMarkdownContent()).isNotEmpty();
  }

  @Test
  void shouldHandleConversionWithDifferentDocumentOptions() {
    ConvertDocumentOptions options = ConvertDocumentOptions.builder()
        .doOcr(true)
        .includeImages(true)
        .tableMode(TableFormerMode.FAST)
        .build();

    ConvertDocumentRequest request = ConvertDocumentRequest.builder()
        .source(HttpSource.builder().url(URI.create("https://docs.arconia.io/arconia-cli/latest/development/dev/")).build())
        .options(options)
        .build();

    ConvertDocumentResponse response = getDoclingClient().convertSource(request);

    assertThat(response).isNotNull();
    assertThat(response.getStatus()).isNotEmpty();
    assertThat(response.getDocument()).isNotNull();
  }

  @Test
  void shouldHandleResponseWithDoclingDocument() {
    ConvertDocumentOptions options = ConvertDocumentOptions.builder()
        .toFormat(OutputFormat.JSON)
        .build();

    ConvertDocumentRequest request = ConvertDocumentRequest.builder()
        .source(HttpSource.builder().url(URI.create("https://docs.arconia.io/arconia-cli/latest/development/dev/")).build())
        .options(options)
        .build();

    ConvertDocumentResponse response = getDoclingClient().convertSource(request);

    assertThat(response).isNotNull();
    assertThat(response.getStatus()).isNotEmpty();
    assertThat(response.getDocument()).isNotNull();

    DoclingDocument doclingDocument = response.getDocument().getJsonContent();
    assertThat(doclingDocument).isNotNull();
    assertThat(doclingDocument.getName()).isNotEmpty();
    assertThat(doclingDocument.getTexts().get(0).getLabel()).isEqualTo(DocItemLabel.TITLE);
  }

  @Test
  void shouldChunkDocumentWithHierarchicalChunker() {
    ConvertDocumentOptions options = ConvertDocumentOptions.builder()
        .toFormat(OutputFormat.JSON)
        .build();

    HierarchicalChunkDocumentRequest request = HierarchicalChunkDocumentRequest.builder()
        .source(HttpSource.builder().url(URI.create("https://docs.arconia.io/arconia-cli/latest/development/dev/")).build())
        .options(options)
        .includeConvertedDoc(true)
        .chunkingOptions(HierarchicalChunkerOptions.builder()
            .includeRawText(true)
            .useMarkdownTables(true)
            .build())
        .build();

    ChunkDocumentResponse response = getDoclingClient().chunkSourceWithHierarchicalChunker(request);

    assertThat(response).isNotNull();
    assertThat(response.getChunks()).isNotEmpty();
    assertThat(response.getDocuments()).isNotEmpty();
    assertThat(response.getProcessingTime()).isNotNull();

    List<Chunk> chunks = response.getChunks();
    assertThat(chunks).allMatch(chunk -> !chunk.getText().isEmpty());
  }

  @Test
  void shouldChunkDocumentWithHybridChunker() {
    ConvertDocumentOptions options = ConvertDocumentOptions.builder()
        .toFormat(OutputFormat.JSON)
        .build();

    HybridChunkDocumentRequest request = HybridChunkDocumentRequest.builder()
        .source(HttpSource.builder().url(URI.create("https://docs.arconia.io/arconia-cli/latest/development/dev/")).build())
        .options(options)
        .includeConvertedDoc(true)
        .chunkingOptions(HybridChunkerOptions.builder()
            .includeRawText(true)
            .useMarkdownTables(true)
            .maxTokens(10000)
            .tokenizer("sentence-transformers/all-MiniLM-L6-v2")
            .build())
        .build();

    ChunkDocumentResponse response = getDoclingClient().chunkSourceWithHybridChunker(request);

    assertThat(response).isNotNull();
    assertThat(response.getChunks()).isNotEmpty();
    assertThat(response.getDocuments()).isNotEmpty();
    assertThat(response.getProcessingTime()).isNotNull();

    List<Chunk> chunks = response.getChunks();
    assertThat(chunks).allMatch(chunk -> !chunk.getText().isEmpty());
  }

  private static byte[] readFileFromClasspath(String filePath) throws IOException {
    try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath)) {
      if (inputStream == null) {
        throw new IOException("File not found in classpath: " + filePath);
      }
      return inputStream.readAllBytes();
    }
  }
}
