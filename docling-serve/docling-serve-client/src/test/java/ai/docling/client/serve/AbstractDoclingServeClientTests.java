package ai.docling.client.serve;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Base64;

import org.junit.jupiter.api.Test;

import ai.docling.api.serve.DoclingServeApi;
import ai.docling.api.serve.convert.request.ConvertDocumentRequest;
import ai.docling.api.serve.convert.request.options.ConvertDocumentOptions;
import ai.docling.api.serve.convert.request.options.TableFormerMode;
import ai.docling.api.serve.convert.request.source.FileSource;
import ai.docling.api.serve.convert.request.source.HttpSource;
import ai.docling.api.serve.convert.response.ConvertDocumentResponse;
import ai.docling.api.serve.health.HealthCheckResponse;
import ai.docling.testcontainers.serve.DoclingServeContainer;
import ai.docling.testcontainers.serve.config.DoclingServeContainerConfig;

abstract class AbstractDoclingServeClientTests {
  protected static final DoclingServeContainer doclingContainer = new DoclingServeContainer(
    DoclingServeContainerConfig.builder()
        .imageName(DoclingServeContainerConfig.DOCLING_IMAGE)
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

  private static byte[] readFileFromClasspath(String filePath) throws IOException {
    try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath)) {
      if (inputStream == null) {
        throw new IOException("File not found in classpath: " + filePath);
      }
      return inputStream.readAllBytes();
    }
  }
}
