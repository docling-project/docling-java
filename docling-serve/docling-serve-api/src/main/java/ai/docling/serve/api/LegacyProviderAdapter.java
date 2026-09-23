package ai.docling.serve.api;

import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import ai.docling.serve.api.DoclingServeApi.DoclingApiBuilder;
import ai.docling.serve.api.spi.DoclingServeApiBuilderFactory;
import ai.docling.serve.api.spi.DoclingServeApiProvider;

/**
 * Adapts a deprecated {@link DoclingServeApiBuilderFactory} to the {@link DoclingServeApiProvider} SPI,
 * by replaying the explicitly set options onto the builder the factory returns.
 *
 * <p>The replay map is frozen: it only covers the setters {@link DoclingApiBuilder} had when the old SPI
 * was deprecated. Every option added since is therefore declared {@link Unsupported#FAIL}, so a caller
 * setting it gets an error naming the legacy factory instead of the option being silently dropped.
 */
@SuppressWarnings("removal")
final class LegacyProviderAdapter implements DoclingServeApiProvider {
  private static final Map<ConfigOption<?>, BiConsumer<DoclingApiBuilder<?, ?>, Object>> REPLAY = Map.ofEntries(
      replay(DoclingServeApiConfig.BASE_URL, DoclingApiBuilder::baseUrl), replay(DoclingServeApiConfig.API_KEY, DoclingApiBuilder::apiKey), replay(DoclingServeApiConfig.LOG_REQUESTS, DoclingApiBuilder::logRequests), replay(DoclingServeApiConfig.LOG_RESPONSES, DoclingApiBuilder::logResponses), replay(DoclingServeApiConfig.PRETTY_PRINT, DoclingApiBuilder::prettyPrint), replay(DoclingServeApiConfig.CONNECT_TIMEOUT, DoclingApiBuilder::connectTimeout), replay(DoclingServeApiConfig.READ_TIMEOUT, DoclingApiBuilder::readTimeout), replay(DoclingServeApiConfig.ASYNC_POLL_INTERVAL, DoclingApiBuilder::asyncPollInterval), replay(DoclingServeApiConfig.ASYNC_TIMEOUT, DoclingApiBuilder::asyncTimeout));

  private static final Map<ConfigOption<?>, Unsupported> UNSUPPORTED = DoclingServeApiConfig.ALL_OPTIONS
      .stream()
      .filter(option -> !REPLAY.containsKey(option))
      .collect(Collectors.toUnmodifiableMap(Function.identity(), option -> Unsupported.FAIL));

  private final DoclingServeApiBuilderFactory factory;

  LegacyProviderAdapter(DoclingServeApiBuilderFactory factory) {
    this.factory = factory;
  }

  @Override
  public DoclingServeApi create(DoclingServeApiConfig config) {
    return create(this.factory, config);
  }

  @Override
  public Map<ConfigOption<?>, Unsupported> unsupportedOptions() {
    return UNSUPPORTED;
  }

  @Override
  public String toString() {
    return "%s (deprecated %s)".formatted(this.factory.getClass().getName(), DoclingServeApiBuilderFactory.class.getSimpleName());
  }

  private static <T extends DoclingServeApi, B extends DoclingApiBuilder<T, B>> T create(DoclingServeApiBuilderFactory factory, DoclingServeApiConfig config) {
    B builder = factory.getBuilder();

    config.explicitlySetOptions()
        .forEach(option -> Optional.ofNullable(REPLAY.get(option))
            .ifPresent(setter -> setter.accept(builder, config.get(option))));

    return builder.build();
  }

  private static <T> Map.Entry<ConfigOption<?>, BiConsumer<DoclingApiBuilder<?, ?>, Object>> replay(ConfigOption<T> option, BiConsumer<DoclingApiBuilder<?, ?>, T> setter) {
    return Map.entry(option, (builder, value) -> setter.accept(builder, option.type().cast(value)));
  }
}
