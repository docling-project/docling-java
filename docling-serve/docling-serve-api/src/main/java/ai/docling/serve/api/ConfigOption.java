package ai.docling.serve.api;

import static ai.docling.serve.api.util.ValidationUtils.ensureNotBlank;
import static ai.docling.serve.api.util.ValidationUtils.ensureNotNull;

/**
 * A typed key identifying a single configuration option of a {@link DoclingServeApiConfig}.
 *
 * <p>The set of options is closed: instances can only be created by the {@code ai.docling.serve.api}
 * package, and every available option is exposed as a {@code public static final} constant on
 * {@link DoclingServeApiConfig}. Options are compared by identity.
 *
 * <p>Options are primarily useful to implementors of {@link ai.docling.serve.api.spi.DoclingServeApiProvider}, to declare
 * which options they do not honor via {@link ai.docling.serve.api.spi.DoclingServeApiProvider#unsupportedOptions()}, and to
 * check whether a caller explicitly set an option via {@link DoclingServeApiConfig#isExplicitlySet(ConfigOption)}.
 *
 * @param <T> the type of the option's value
 */
public final class ConfigOption<T> {
  private final String name;
  private final Class<T> type;

  private ConfigOption(String name, Class<T> type) {
    this.name = ensureNotBlank(name, "name");
    this.type = ensureNotNull(type, "type");
  }

  /**
   * Creates a new option. Package-private so that the set of options stays closed.
   *
   * @param name the name of the option, matching the accessor on {@link DoclingServeApiConfig}
   * @param type the type of the option's value
   * @param <T>  the type of the option's value
   * @return a new option
   */
  static <T> ConfigOption<T> of(String name, Class<T> type) {
    return new ConfigOption<>(name, type);
  }

  /**
   * The name of this option. It matches the name of the corresponding accessor on
   * {@link DoclingServeApiConfig} and of the corresponding setter on {@link DoclingServeApiBuilder}.
   *
   * @return the name of this option
   */
  public String name() {
    return this.name;
  }

  /**
   * The type of this option's value.
   *
   * @return the type of this option's value
   */
  public Class<T> type() {
    return this.type;
  }

  @Override
  public String toString() {
    return this.name;
  }
}
