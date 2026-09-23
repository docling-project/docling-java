package ai.docling.serve.api.spi;

import java.util.Map;

import ai.docling.serve.api.ConfigOption;
import ai.docling.serve.api.DoclingServeApi;
import ai.docling.serve.api.DoclingServeApiConfig;

/**
 * Service provider interface for creating implementations of {@link DoclingServeApi}.
 *
 * <p>Implementations are discovered with {@link java.util.ServiceLoader}, either through a
 * {@code META-INF/services/ai.docling.serve.api.spi.DoclingServeApiProvider} file or a
 * {@code provides} clause in {@code module-info.java}. Exactly one provider must be available when
 * {@link ai.docling.serve.api.DoclingServeApiBuilder#build()} is called.
 *
 * <p>A provider receives an immutable {@link DoclingServeApiConfig} rather than a builder, so new
 * configuration options can be added in future releases without breaking existing providers.
 *
 * <p>Implementations must be public and have a public no-argument constructor.
 */
@FunctionalInterface
public interface DoclingServeApiProvider {
  /**
   * Creates a {@link DoclingServeApi} from the given configuration.
   *
   * <p>By the time this method is called, the options declared in {@link #unsupportedOptions()} have
   * already been enforced: explicitly set options declared as {@link Unsupported#FAIL} have caused
   * {@link ai.docling.serve.api.DoclingServeApiBuilder#build()} to fail, and those declared as {@link Unsupported#WARN}
   * have been logged.
   *
   * <p>The {@link ai.docling.serve.api.DoclingServeApi#config()} of the returned API must report the
   * effective value of every option of the given configuration.
   *
   * @param config the configuration to create the API with
   * @return a new {@link DoclingServeApi}
   */
  DoclingServeApi create(DoclingServeApiConfig config);

  /**
   * The options this provider knowingly does not honor, and what should happen when a caller
   * explicitly sets one of them. Options left at their default value are never reported.
   *
   * <p>Options absent from the returned map are assumed to be honored. This is a declaration, not a
   * verified guarantee: the api module does not check that the options omitted here are actually applied.
   *
   * <p>The default implementation declares no unsupported option.
   *
   * @return the unsupported options, never {@code null}
   */
  default Map<ConfigOption<?>, Unsupported> unsupportedOptions() {
    return Map.of();
  }

  /**
   * What happens when a caller explicitly sets an option a provider does not honor.
   */
  enum Unsupported {
    /**
     * Silently ignore the option.
     */
    IGNORE,

    /**
     * Log a warning naming the option and the provider, then ignore the option.
     */
    WARN,

    /**
     * Fail {@link ai.docling.serve.api.DoclingServeApiBuilder#build()} with an
     * {@link ai.docling.serve.api.UnsupportedConfigurationException}.
     */
    FAIL
  }
}
