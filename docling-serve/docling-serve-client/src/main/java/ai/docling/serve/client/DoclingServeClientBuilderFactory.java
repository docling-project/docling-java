package ai.docling.serve.client;

import ai.docling.serve.client.DoclingServeClient.DoclingServeClientBuilder;

/**
 * A factory class for creating instances of {@link DoclingServeClientBuilder}, matching the version of Jackson
 * (2 or 3) on the classpath.
 *
 * @deprecated Use {@link ai.docling.serve.api.DoclingServeApi#builder()} for the options shared by every
 *             implementation, or {@link DoclingServeClient#builder()} for client-specific settings such as the
 *             HTTP client: it detects the version of Jackson the same way. To customize the JSON mapper, use
 *             {@link DoclingServeJackson3Client#builder()} or {@link DoclingServeJackson2Client#builder()}. This
 *             class will be removed in a future release.
 */
@Deprecated(since = "0.7.0", forRemoval = true)
public final class DoclingServeClientBuilderFactory {
  private DoclingServeClientBuilderFactory() {
  }

  /**
   * Creates and returns a new instance of a {@link DoclingServeClientBuilder} compatible
   * with the Jackson version present on the provided classloader's classpath.
   *
   * <p>If Jackson 3 is detected, returns a builder for {@code DoclingServeJackson3Client}.
   * If Jackson 2 is detected, returns a builder for {@code DoclingServeJackson2Client}.
   * If neither version of Jackson is found on the classpath, an
   * {@link IllegalStateException} is thrown.
   *
   * @param <C>         the type of {@link DoclingServeClient} to be created by the builder
   * @param <B>         the type of {@link DoclingServeClientBuilder} to be returned
   * @param classLoader the {@link ClassLoader} used to check for Jackson's presence
   * @return a compatible {@link DoclingServeClientBuilder} instance
   * @throws IllegalStateException if neither Jackson 2 nor Jackson 3 is available on the classpath
   * @deprecated Use {@link DoclingServeClient#builder()} instead.
   */
  @Deprecated(since = "0.7.0", forRemoval = true)
  @SuppressWarnings("unchecked")
  public static <C extends DoclingServeClient, B extends DoclingServeClientBuilder<C, B>> B newBuilder(ClassLoader classLoader) {
    return (B) DoclingServeClient.builderFor(classLoader);
  }

  /**
   * Creates and returns a new instance of a {@link DoclingServeClientBuilder} that is
   * compatible with the version of Jackson available on the application's classpath.
   *
   * <p>If Jackson 3 is found on the classpath, it returns a builder for
   * {@code DoclingServeJackson3Client}. If Jackson 2 is found, it returns a builder
   * for {@code DoclingServeJackson2Client}. If neither are found, an
   * {@link IllegalStateException} is thrown.
   *
   * @param <C> the type of {@link DoclingServeClient} to be created by the builder
   * @param <B> the type of {@link DoclingServeClientBuilder} to be returned
   * @return a compatible {@link DoclingServeClientBuilder} instance
   * @throws IllegalStateException if neither Jackson 2 nor Jackson 3 is available on the classpath
   * @deprecated Use {@link DoclingServeClient#builder()} instead.
   */
  @Deprecated(since = "0.7.0", forRemoval = true)
  public static <C extends DoclingServeClient, B extends DoclingServeClientBuilder<C, B>> B newBuilder() {
    return newBuilder(Thread.currentThread().getContextClassLoader());
  }
}
