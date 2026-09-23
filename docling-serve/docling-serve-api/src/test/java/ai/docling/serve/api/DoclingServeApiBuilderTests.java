package ai.docling.serve.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import ai.docling.serve.api.TestApis.RecordingProvider;
import ai.docling.serve.api.spi.DoclingServeApiProvider.Unsupported;

class DoclingServeApiBuilderTests {
  @Test
  void providerReceivesTheConfiguration() {
    var provider = new RecordingProvider();
    var builder = DoclingServeApi.builder()
        .apiKey("key")
        .readTimeout(Duration.ofSeconds(7));

    var api = builder.build(provider);

    assertThat(provider.created())
        .singleElement()
        .isSameAs(api);
    assertThat(api.config()).isEqualTo(builder.config());
  }

  @Test
  void unsupportedOptionsLeftAtTheirDefaultAreNotEnforced() {
    var provider = new RecordingProvider(Map.of(DoclingServeApiConfig.ASYNC_EXECUTOR, Unsupported.FAIL));

    var api = DoclingServeApi.builder()
        .apiKey("key")
        .build(provider);

    assertThat(provider.created())
        .singleElement()
        .isSameAs(api);
  }

  @Test
  void ignoredOptionsDoNotPreventBuilding() {
    var provider = new RecordingProvider(Map.of(DoclingServeApiConfig.ASYNC_EXECUTOR, Unsupported.IGNORE));

    var api = DoclingServeApi.builder()
        .asyncExecutor(Runnable::run)
        .build(provider);

    assertThat(provider.created())
        .singleElement()
        .isSameAs(api);
    assertThat(api.config().asyncExecutor()).isNotNull();
  }

  @Test
  void warnedOptionsDoNotPreventBuilding() {
    var provider = new RecordingProvider(Map.of(DoclingServeApiConfig.ASYNC_EXECUTOR, Unsupported.WARN));

    var api = DoclingServeApi.builder()
        .asyncExecutor(Runnable::run)
        .build(provider);

    assertThat(provider.created())
        .singleElement()
        .isSameAs(api);
    assertThat(api.config().asyncExecutor()).isNotNull();
  }

  @Test
  void explicitlySetFailOptionsPreventBuildingAndNameEveryOptionInDeclarationOrder() {
    var provider = new RecordingProvider(Map.of(
        DoclingServeApiConfig.ASYNC_EXECUTOR, Unsupported.FAIL, DoclingServeApiConfig.LOG_REQUESTS, Unsupported.FAIL, DoclingServeApiConfig.PRETTY_PRINT, Unsupported.WARN));

    var builder = DoclingServeApi.builder()
        .asyncExecutor(Runnable::run)
        .prettyPrint()
        .logRequests();

    assertThatExceptionOfType(UnsupportedConfigurationException.class)
        .isThrownBy(() -> builder.build(provider))
        .withMessage(
            "The following options were explicitly configured but are not supported by %s: [logRequests, asyncExecutor]", RecordingProvider.class.getName())
        .satisfies(e -> assertThat(e)
            .returns(RecordingProvider.class.getName(), UnsupportedConfigurationException::getProvider)
            .extracting(UnsupportedConfigurationException::getUnsupportedOptions)
            .isEqualTo(List.of(DoclingServeApiConfig.LOG_REQUESTS, DoclingServeApiConfig.ASYNC_EXECUTOR)));

    assertThat(provider.created()).isEmpty();
  }
}
