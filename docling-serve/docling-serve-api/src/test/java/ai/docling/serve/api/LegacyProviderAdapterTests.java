package ai.docling.serve.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.net.URI;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;

import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.Test;

import ai.docling.serve.api.DoclingServeApi.DoclingApiBuilder;
import ai.docling.serve.api.spi.DoclingServeApiBuilderFactory;
import ai.docling.serve.api.spi.DoclingServeApiProvider.Unsupported;

@SuppressWarnings("removal")
class LegacyProviderAdapterTests {
  @Test
  void onlyExplicitlySetOptionsAreReplayed() {
    var factory = new RecordingLegacyFactory();
    var builder = DoclingServeApi.builder()
        .baseUrl("http://example.com")
        .apiKey("key")
        .logRequests()
        .readTimeout(Duration.ofSeconds(7));

    var api = builder.build(new LegacyProviderAdapter(factory));

    assertThat(api.config()).isEqualTo(builder.config());
    assertThat(factory.builder.calls).containsExactly(
        Map.entry("baseUrl", URI.create("http://example.com")), Map.entry("apiKey", "key"), Map.entry("logRequests", true), Map.entry("readTimeout", Duration.ofSeconds(7)));
  }

  @Test
  void everyLegacyOptionIsReplayed() {
    var factory = new RecordingLegacyFactory();

    DoclingServeApi.builder()
        .baseUrl("http://example.com")
        .apiKey("key")
        .logRequests()
        .logResponses()
        .prettyPrint()
        .connectTimeout(Duration.ofSeconds(1))
        .readTimeout(Duration.ofSeconds(2))
        .asyncPollInterval(Duration.ofSeconds(3))
        .asyncTimeout(Duration.ofSeconds(4))
        .build(new LegacyProviderAdapter(factory));

    assertThat(factory.builder.calls.keySet())
        .containsExactly("baseUrl", "apiKey", "logRequests", "logResponses", "prettyPrint", "connectTimeout", "readTimeout", "asyncPollInterval", "asyncTimeout");
  }

  @Test
  void optionsAddedAfterTheDeprecationAreUnsupported() {
    assertThat(new LegacyProviderAdapter(new RecordingLegacyFactory()).unsupportedOptions())
        .containsExactly(Map.entry(DoclingServeApiConfig.ASYNC_EXECUTOR, Unsupported.FAIL));
  }

  @Test
  void settingAnOptionAddedAfterTheDeprecationFailsNamingTheLegacyFactory() {
    var factory = new RecordingLegacyFactory();
    var builder = DoclingServeApi.builder().asyncExecutor(Runnable::run);

    assertThatExceptionOfType(UnsupportedConfigurationException.class)
        .isThrownBy(() -> builder.build(new LegacyProviderAdapter(factory)))
        .withMessage(
            "The following options were explicitly configured but are not supported by %s (deprecated DoclingServeApiBuilderFactory): [asyncExecutor]", RecordingLegacyFactory.class
                .getName());

    assertThat(factory.builder.calls).isEmpty();
  }

  static final class RecordingLegacyFactory implements DoclingServeApiBuilderFactory {
    final RecordingLegacyBuilder builder = new RecordingLegacyBuilder();

    @Override
    @SuppressWarnings("unchecked")
    public <T extends DoclingServeApi, B extends DoclingApiBuilder<T, B>> B getBuilder() {
      return (B) this.builder;
    }
  }

  // Implements only the abstract methods of DoclingApiBuilder, like a third-party builder would:
  // this class compiling is what guarantees the deprecated interface did not gain abstract methods.
  // It builds a stub reporting the settings it received, as a legacy implementation recompiled
  // against this version would.
  static final class RecordingLegacyBuilder implements DoclingApiBuilder<DoclingServeApi, RecordingLegacyBuilder> {
    final Map<String, Object> calls = new LinkedHashMap<>();
    private final DoclingServeApiBuilder settings = DoclingServeApi.builder();

    @Override
    public RecordingLegacyBuilder baseUrl(URI baseUrl) {
      this.settings.baseUrl(baseUrl);
      return record("baseUrl", baseUrl);
    }

    @Override
    public RecordingLegacyBuilder apiKey(@Nullable String apiKey) {
      this.settings.apiKey(apiKey);
      return record("apiKey", apiKey);
    }

    @Override
    public RecordingLegacyBuilder logRequests(boolean logRequests) {
      this.settings.logRequests(logRequests);
      return record("logRequests", logRequests);
    }

    @Override
    public RecordingLegacyBuilder logResponses(boolean logResponses) {
      this.settings.logResponses(logResponses);
      return record("logResponses", logResponses);
    }

    @Override
    public RecordingLegacyBuilder prettyPrint(boolean prettyPrint) {
      this.settings.prettyPrint(prettyPrint);
      return record("prettyPrint", prettyPrint);
    }

    @Override
    public RecordingLegacyBuilder connectTimeout(Duration connectTimeout) {
      this.settings.connectTimeout(connectTimeout);
      return record("connectTimeout", connectTimeout);
    }

    @Override
    public RecordingLegacyBuilder readTimeout(Duration readTimeout) {
      this.settings.readTimeout(readTimeout);
      return record("readTimeout", readTimeout);
    }

    @Override
    public RecordingLegacyBuilder asyncPollInterval(Duration asyncPollInterval) {
      this.settings.asyncPollInterval(asyncPollInterval);
      return record("asyncPollInterval", asyncPollInterval);
    }

    @Override
    public RecordingLegacyBuilder asyncTimeout(Duration asyncTimeout) {
      this.settings.asyncTimeout(asyncTimeout);
      return record("asyncTimeout", asyncTimeout);
    }

    @Override
    public DoclingServeApi build() {
      return new TestApis.StubDoclingServeApi(this.settings.config());
    }

    private RecordingLegacyBuilder record(String name, @Nullable Object value) {
      this.calls.put(name, value);
      return this;
    }
  }
}
