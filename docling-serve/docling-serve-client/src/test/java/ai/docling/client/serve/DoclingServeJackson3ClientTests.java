package ai.docling.client.serve;

import org.junit.jupiter.api.BeforeAll;

import ai.docling.api.serve.DoclingServeApi;
import ai.docling.testcontainers.serve.config.DoclingServeContainerConfig;

/**
 * Integration tests for {@link DoclingServeClient}.
 */
class DoclingServeJackson3ClientTests extends AbstractDoclingServeClientTests {
  private static DoclingServeApi doclingClient;

  @BeforeAll
  static void setUp() {
    doclingClient = DoclingServeJackson3Client.builder()
        .logRequests()
        .logResponses()
        .baseUrl("http://localhost:%s".formatted(doclingContainer.getMappedPort(DoclingServeContainerConfig.DEFAULT_DOCLING_SERVE_PORT)))
        .build();
  }

  @Override
  protected DoclingServeApi getDoclingClient() {
    return doclingClient;
  }
}
