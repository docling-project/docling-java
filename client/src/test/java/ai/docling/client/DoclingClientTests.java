package ai.docling.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.time.Duration;
import java.util.Base64;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import ai.docling.api.convert.request.ConvertDocumentRequest;
import ai.docling.api.convert.request.options.ConvertDocumentOptions;
import ai.docling.api.convert.request.options.TableFormerMode;
import ai.docling.api.convert.response.ConvertDocumentResponse;
import ai.docling.api.health.HealthCheckResponse;
import ai.docling.testcontainers.DoclingContainer;
import ai.docling.testcontainers.config.DoclingContainerConfig;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration tests for {@link DoclingClient}.
 */
@Testcontainers
class DoclingClientTests {

  @Container
  private static final DoclingContainer doclingContainer = new DoclingContainer(
    DoclingContainerConfig.builder()
        .imageName(Images.DOCLING)
        .enableUi(true)
        .build(),
      Optional.of(Duration.ofMinutes(2))
  );

  private static DoclingClient doclingClient;

  @BeforeAll
  static void setUp() {
    doclingClient = DoclingClient.builder()
        .baseUrl("http://localhost:%s".formatted(doclingContainer.getMappedPort(Images.DOCLING_DEFAULT_PORT)))
        .build();
  }

  @Test
  void shouldSuccessfullyCallHealthEndpoint() {
    HealthCheckResponse response = doclingClient.health();

    assertThat(response).isNotNull();
    assertThat(response.status()).isEqualTo("ok");
  }

  @Test
  void shouldConvertHttpSourceSuccessfully() {
    ConvertDocumentRequest request = ConvertDocumentRequest.builder()
        .addHttpSources(URI.create("https://docs.arconia.io/arconia-cli/latest/development/dev/"))
        .build();

    ConvertDocumentResponse response = doclingClient.convertSource(request);

    assertThat(response).isNotNull();

    assertThat(response.status()).isNotEmpty();
    assertThat(response.document()).isNotNull();
    assertThat(response.document().filename()).isNotEmpty();

    if (response.processingTime() != null) {
      assertThat(response.processingTime()).isPositive();
    }

    assertThat(response.document().markdownContent()).isNotEmpty();
  }

  @Test
  void shouldConvertFileSourceSuccessfully() throws IOException {
    var fileResource = readFileFromClasspath("story.pdf");
    ConvertDocumentRequest request = ConvertDocumentRequest.builder()
        .addFileSources("story.pdf", Base64.getEncoder().encodeToString(fileResource))
        .build();

    ConvertDocumentResponse response = doclingClient.convertSource(request);

    assertThat(response).isNotNull();
    assertThat(response.status()).isNotEmpty();
    assertThat(response.document()).isNotNull();
    assertThat(response.document().filename()).isEqualTo("story.pdf");

    if (response.processingTime() != null) {
      assertThat(response.processingTime()).isPositive();
    }

    assertThat(response.document().markdownContent()).isNotEmpty();
  }

  @Test
  void shouldHandleConversionWithDifferentDocumentOptions() {
    ConvertDocumentOptions options = ConvertDocumentOptions.builder()
        .doOcr(true)
        .includeImages(true)
        .tableMode(TableFormerMode.FAST)
        .build();

    ConvertDocumentRequest request = ConvertDocumentRequest.builder()
        .addHttpSources(URI.create("https://docs.arconia.io/arconia-cli/latest/development/dev/"))
        .options(options)
        .build();

    ConvertDocumentResponse response = doclingClient.convertSource(request);

    assertThat(response).isNotNull();
    assertThat(response.status()).isNotEmpty();
    assertThat(response.document()).isNotNull();
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
