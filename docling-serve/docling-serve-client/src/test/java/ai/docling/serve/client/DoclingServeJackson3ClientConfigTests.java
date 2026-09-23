package ai.docling.serve.client;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

import org.junit.jupiter.api.extension.RegisterExtension;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;

/**
 * Config tests for {@link DoclingServeJackson3Client}.
 */
class DoclingServeJackson3ClientConfigTests extends AbstractDoclingServeClientConfigTests {
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
