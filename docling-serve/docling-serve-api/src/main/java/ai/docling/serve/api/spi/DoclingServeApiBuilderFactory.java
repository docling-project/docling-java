package ai.docling.serve.api.spi;

import ai.docling.serve.api.DoclingServeApi;
import ai.docling.serve.api.DoclingServeApi.DoclingApiBuilder;

/**
 * Factory interface for creating builder instances to construct implementations of {@link DoclingServeApi}.
 */
public interface DoclingServeApiBuilderFactory {
  /**
   * Retrieves a builder instance for constructing implementations of {@link DoclingServeApi}.
   * The returned builder allows customization of various configurations and properties
   * before creating an instance of the API.
   *
   * @param <T> the type of the {@link DoclingServeApi} implementation being built
   * @param <B> the type of the concrete builder implementation
   * @return a builder of type {@code B}, which is used to construct instances of {@code T}
   */
  <T extends DoclingServeApi, B extends DoclingApiBuilder<T, B>> B getBuilder();
}
