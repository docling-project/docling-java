package ai.docling.serve.api;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ai.docling.serve.api.spi.DoclingServeApiBuilderFactory;
import ai.docling.serve.api.spi.DoclingServeApiProvider;
import ai.docling.serve.api.spi.ServiceLoaderHelper;

/**
 * Resolves the single {@link DoclingServeApiProvider} to use, falling back to the deprecated
 * {@link DoclingServeApiBuilderFactory} SPI when no provider is available.
 */
@SuppressWarnings("removal")
final class DoclingServeApiProviders {
  private static final Logger LOG = LoggerFactory.getLogger(DoclingServeApiProviders.class);

  private DoclingServeApiProviders() {
  }

  static DoclingServeApiProvider resolve() {
    var providers = List.copyOf(ServiceLoaderHelper.loadFactories(DoclingServeApiProvider.class));
    var legacyFactories = List.copyOf(ServiceLoaderHelper.loadFactories(DoclingServeApiBuilderFactory.class));

    return resolve(providers, legacyFactories);
  }

  // Package-private so the resolution rules can be tested without the ServiceLoader
  static DoclingServeApiProvider resolve(List<DoclingServeApiProvider> providers, List<DoclingServeApiBuilderFactory> legacyFactories) {
    if (!providers.isEmpty() && !legacyFactories.isEmpty()) {
      LOG.warn(
          """
              Ignoring deprecated {} implementation(s) [{}] because {} implementation(s) [{}] are available. \
              {} is deprecated for removal and is no longer used by docling-serve-api: it will be removed in a future release. \
              Migrate these implementations to {}.""", DoclingServeApiBuilderFactory.class.getName(), describeAll(legacyFactories), DoclingServeApiProvider.class
              .getName(), describeAll(providers), DoclingServeApiBuilderFactory.class.getSimpleName(), DoclingServeApiProvider.class.getName());
    }

    return providers.isEmpty() ?
        exactlyOne(legacyFactories.stream().<DoclingServeApiProvider>map(LegacyProviderAdapter::new).toList(), DoclingServeApiBuilderFactory.class) :
        exactlyOne(providers, DoclingServeApiProvider.class);
  }

  /**
   * A human-readable description of a provider, naming the wrapped factory for legacy providers.
   */
  static String describe(Object provider) {
    return (provider instanceof LegacyProviderAdapter legacy) ?
        legacy.toString() :
        provider.getClass().getName();
  }

  private static DoclingServeApiProvider exactlyOne(List<DoclingServeApiProvider> candidates, Class<?> spiType) {
    return switch (candidates.size()) {
      case 0 ->
          throw new IllegalStateException(
              "No instance of %s (or of the deprecated %s) found to build a %s instance. You are probably missing a library on your classpath."
                  .formatted(DoclingServeApiProvider.class.getName(), DoclingServeApiBuilderFactory.class.getName(), DoclingServeApi.class.getName()));
      case 1 ->
          candidates.get(0);
      default ->
          throw new IllegalStateException(
              "Multiple instances of %s found to build a %s instance: [%s]"
                  .formatted(spiType.getName(), DoclingServeApi.class.getName(), describeAll(candidates)));
    };
  }

  private static String describeAll(List<?> providers) {
    return providers.stream()
        .map(DoclingServeApiProviders::describe)
        .collect(Collectors.joining(", "));
  }
}
