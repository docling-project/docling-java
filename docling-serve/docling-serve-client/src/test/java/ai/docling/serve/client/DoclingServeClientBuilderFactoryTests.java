package ai.docling.serve.client;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Tests that the deprecated {@link DoclingServeClientBuilderFactory} keeps delegating to {@link DoclingServeClient#builder()}.
 */
@SuppressWarnings({
    "removal",
    "rawtypes",
    "unchecked"
})
class DoclingServeClientBuilderFactoryTests {
  @Test
  void newBuilderDetectsJackson() {
    assertThat(DoclingServeClientBuilderFactory.<DoclingServeClient, DoclingServeClient.DoclingServeClientBuilder>newBuilder())
        .isExactlyInstanceOf(DoclingServeJackson3Client.Builder.class);
  }

  @Test
  void newBuilderUsesTheGivenClassLoader() {
    var classLoader = new ClassHidingClassLoader("tools.jackson.databind.json.JsonMapper");

    assertThat(DoclingServeClientBuilderFactory.<DoclingServeClient, DoclingServeClient.DoclingServeClientBuilder>newBuilder(classLoader))
        .isExactlyInstanceOf(DoclingServeJackson2Client.Builder.class);
  }
}
