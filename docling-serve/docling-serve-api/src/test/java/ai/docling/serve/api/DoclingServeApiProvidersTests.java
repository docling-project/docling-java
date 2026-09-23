package ai.docling.serve.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import java.util.List;

import org.junit.jupiter.api.Test;

import ai.docling.serve.api.LegacyProviderAdapterTests.RecordingLegacyFactory;
import ai.docling.serve.api.TestApis.RecordingProvider;
import ai.docling.serve.api.spi.DoclingServeApiBuilderFactory;
import ai.docling.serve.api.spi.DoclingServeApiProvider;

@SuppressWarnings("removal")
class DoclingServeApiProvidersTests {
  @Test
  void singleProviderIsUsed() {
    var provider = new RecordingProvider();

    assertThat(DoclingServeApiProviders.resolve(List.of(provider), List.of())).isSameAs(provider);
  }

  @Test
  void singleLegacyFactoryIsAdapted() {
    var factory = new RecordingLegacyFactory();

    assertThat(DoclingServeApiProviders.resolve(List.of(), List.of(factory)))
        .isInstanceOf(LegacyProviderAdapter.class)
        .hasToString("%s (deprecated DoclingServeApiBuilderFactory)", RecordingLegacyFactory.class.getName());
  }

  @Test
  void providerIsPreferredOverLegacyFactory() {
    var provider = new RecordingProvider();

    assertThat(DoclingServeApiProviders.resolve(List.of(provider), List.of(new RecordingLegacyFactory()))).isSameAs(provider);
  }

  @Test
  void noneFound() {
    assertThatIllegalStateException()
        .isThrownBy(() -> DoclingServeApiProviders.resolve(List.of(), List.of()))
        .withMessage(
            "No instance of %s (or of the deprecated %s) found to build a %s instance. You are probably missing a library on your classpath.", DoclingServeApiProvider.class
                .getName(), DoclingServeApiBuilderFactory.class.getName(), DoclingServeApi.class.getName());
  }

  @Test
  void multipleProvidersFound() {
    assertThatIllegalStateException()
        .isThrownBy(() -> DoclingServeApiProviders.resolve(List.of(new RecordingProvider(), new OtherProvider()), List.of()))
        .withMessage(
            "Multiple instances of %s found to build a %s instance: [%s, %s]", DoclingServeApiProvider.class.getName(), DoclingServeApi.class.getName(), RecordingProvider.class
                .getName(), OtherProvider.class.getName());
  }

  @Test
  void multipleLegacyFactoriesFound() {
    assertThatIllegalStateException()
        .isThrownBy(() -> DoclingServeApiProviders.resolve(List.of(), List.of(new RecordingLegacyFactory(), new RecordingLegacyFactory())))
        .withMessageStartingWith("Multiple instances of %s found to build a %s instance: [", DoclingServeApiBuilderFactory.class.getName(), DoclingServeApi.class.getName())
        .withMessageContaining("%s (deprecated DoclingServeApiBuilderFactory)", RecordingLegacyFactory.class.getName());
  }

  private static final class OtherProvider implements DoclingServeApiProvider {
    @Override
    public DoclingServeApi create(DoclingServeApiConfig config) {
      return new TestApis.StubDoclingServeApi(config);
    }
  }
}
