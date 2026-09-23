package ai.docling.serve.client;

import ai.docling.serve.api.DoclingServeApi;
import ai.docling.serve.api.DoclingServeApiConfig;
import ai.docling.serve.api.spi.DoclingServeApiProvider;

/**
 * The {@link DoclingServeApiProvider} of the {@code docling-serve-client} module.
 *
 * <p>It creates a {@link DoclingServeJackson3Client} or a {@link DoclingServeJackson2Client}, depending on
 * which version of Jackson is on the classpath (Jackson 3 is preferred). It honors every option of
 * {@link DoclingServeApiConfig}.
 *
 * <p>For client-specific settings, such as the HTTP client, use {@link DoclingServeClient#builder()} instead of {@link DoclingServeApi#builder()}. To customize the JSON mapper,
 * use {@link DoclingServeJackson3Client#builder()} or {@link DoclingServeJackson2Client#builder()}.
 */
public final class DoclingServeClientProvider implements DoclingServeApiProvider {
  /**
   * {@inheritDoc}
   *
   * @throws IllegalStateException if neither Jackson 2 nor Jackson 3 is on the classpath
   */
  @Override
  public DoclingServeApi create(DoclingServeApiConfig config) {
    return DoclingServeClient.builder()
        .config(config)
        .build();
  }
}
