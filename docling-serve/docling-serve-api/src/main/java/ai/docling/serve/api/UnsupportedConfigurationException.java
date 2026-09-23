package ai.docling.serve.api;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Thrown by {@link DoclingServeApiBuilder#build()} when the caller explicitly set options that the
 * resolved {@link ai.docling.serve.api.spi.DoclingServeApiProvider} declares as {@link ai.docling.serve.api.spi.DoclingServeApiProvider.Unsupported#FAIL}.
 */
public class UnsupportedConfigurationException extends IllegalArgumentException {
  private final String provider;
  private final List<ConfigOption<?>> unsupportedOptions;

  /**
   * Creates a new exception.
   *
   * @param provider           a description of the provider that does not support the options
   * @param unsupportedOptions the explicitly set options the provider does not support
   */
  public UnsupportedConfigurationException(String provider, List<ConfigOption<?>> unsupportedOptions) {
    super("The following options were explicitly configured but are not supported by %s: [%s]".formatted(
        provider, unsupportedOptions.stream()
            .map(ConfigOption::name)
            .collect(Collectors.joining(", "))));

    this.provider = provider;
    this.unsupportedOptions = List.copyOf(unsupportedOptions);
  }

  /**
   * A description of the provider that does not support the options.
   *
   * @return the provider description
   */
  public String getProvider() {
    return this.provider;
  }

  /**
   * The explicitly set options the provider does not support.
   *
   * @return an unmodifiable list of the unsupported options
   */
  public List<ConfigOption<?>> getUnsupportedOptions() {
    return this.unsupportedOptions;
  }
}
