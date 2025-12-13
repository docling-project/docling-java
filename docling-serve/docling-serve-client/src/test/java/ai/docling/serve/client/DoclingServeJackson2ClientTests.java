package ai.docling.serve.client;

import org.junit.jupiter.api.BeforeAll;

import ai.docling.serve.api.DoclingServeApi;

/**
 * Integration tests for {@link DoclingServeJackson2Client}.
 */
class DoclingServeJackson2ClientTests extends AbstractDoclingServeClientTests {
  private static DoclingServeApi doclingClient;
  private static DoclingServeApi authDoclingClient;

  @BeforeAll
  static void setUp() {
    var builder = DoclingServeJackson2Client.builder()
        .logRequests()
        .logResponses()
        .prettyPrint()
        .baseUrl(doclingContainer.getApiUrl());

    doclingClient = builder.build();
    authDoclingClient = builder.apiKey("key").build();
  }

  @Override
  protected DoclingServeApi getDoclingClient(boolean requiresAuth) {
    return requiresAuth ? authDoclingClient : doclingClient;
  }
}
