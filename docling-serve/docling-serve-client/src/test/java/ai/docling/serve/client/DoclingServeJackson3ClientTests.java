package ai.docling.serve.client;

import org.junit.jupiter.api.BeforeAll;

import ai.docling.serve.api.DoclingServeApi;

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
        .baseUrl(doclingContainer.getApiUrl())
        .build();
  }

  @Override
  protected DoclingServeApi getDoclingClient() {
    return doclingClient;
  }
}
