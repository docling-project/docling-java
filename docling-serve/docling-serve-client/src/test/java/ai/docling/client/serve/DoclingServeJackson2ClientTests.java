package ai.docling.client.serve;

import org.junit.jupiter.api.BeforeAll;

import ai.docling.api.serve.DoclingServeApi;

/**
 * Integration tests for {@link DoclingServeJackson2Client}.
 */
class DoclingServeJackson2ClientTests extends AbstractDoclingServeClientTests {
  private static DoclingServeApi doclingClient;

  @BeforeAll
  static void setUp() {
    doclingClient = DoclingServeJackson2Client.builder()
        .baseUrl(doclingContainer.getApiUrl())
        .logRequests()
        .logResponses()
        .build();
  }

  @Override
  protected DoclingServeApi getDoclingClient() {
    return doclingClient;
  }
}
