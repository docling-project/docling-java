package ai.docling.client.serve;

import org.junit.jupiter.api.BeforeAll;

import ai.docling.api.serve.DoclingServeApi;
import ai.docling.testcontainers.serve.DoclingServeContainer;

/**
 * Integration tests for {@link DoclingServeJackson2Client}.
 */
class DoclingServeJackson2ClientTests extends AbstractDoclingServeClientTests {
  private static DoclingServeApi doclingClient;

  @BeforeAll
  static void setUp() {
    doclingClient = DoclingServeJackson2Client.builder()
        .baseUrl("http://localhost:%s".formatted(doclingContainer.getMappedPort(DoclingServeContainer.DEFAULT_DOCLING_PORT)))
        .logRequests()
        .logResponses()
        .build();
  }

  @Override
  protected DoclingServeApi getDoclingClient() {
    return doclingClient;
  }
}
