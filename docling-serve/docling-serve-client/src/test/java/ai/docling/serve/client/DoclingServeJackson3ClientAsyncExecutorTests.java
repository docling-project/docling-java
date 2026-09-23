package ai.docling.serve.client;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

import org.junit.jupiter.api.extension.RegisterExtension;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;

/**
 * Async executor tests for {@link DoclingServeJackson3Client}.
 */
class DoclingServeJackson3ClientAsyncExecutorTests extends AbstractDoclingServeClientAsyncExecutorTests {
  @RegisterExtension
  static WireMockExtension wireMock = WireMockExtension.newInstance()
      .options(wireMockConfig().dynamicPort())
      .build();

  @Override
  protected WireMockExtension getWireMock() {
    return wireMock;
  }

  @Override
  protected DoclingServeClient.DoclingServeClientBuilder<?, ?> newClientBuilder() {
    return DoclingServeJackson3Client.builder();
  }
}
