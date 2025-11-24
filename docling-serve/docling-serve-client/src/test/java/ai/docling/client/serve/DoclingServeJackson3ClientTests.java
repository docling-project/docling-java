package ai.docling.client.serve;

import org.junit.jupiter.api.BeforeAll;

import ai.docling.api.serve.DoclingServeApi;

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
