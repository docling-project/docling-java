package ai.docling.client.serve;

import org.junit.jupiter.api.BeforeAll;

import ai.docling.api.serve.DoclingServeApi;
import ai.docling.testcontainers.serve.DoclingServeContainer;

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
        .baseUrl("http://localhost:%s".formatted(doclingContainer.getMappedPort(DoclingServeContainer.DEFAULT_DOCLING_PORT)))
        .build();
  }

  @Override
  protected DoclingServeApi getDoclingClient() {
    return doclingClient;
  }
}
