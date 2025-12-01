package ai.docling.serve.client;

import ai.docling.serve.client.DoclingServeClient.DoclingServeClientBuilder;

/**
 * A factory class for creating instances of {@link DoclingServeClientBuilder}.
 *
 * <p>This factory determines which version of Jackson (2 or 3) is available
 * on the application's classpath and provides a corresponding builder for
 * creating {@link DoclingServeClient} implementations.
 *
 * <p>If neither Jackson 2 nor Jackson 3 is present on the classpath, an
 * {@link IllegalStateException} is thrown.
 *
 * <p>The factory uses a type-safe generic method to support custom subclasses of
 * {@link DoclingServeClient} and {@link DoclingServeClientBuilder}.
 */
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
   * <p>The method uses generics to support custom implementations of
   * {@link DoclingServeClient} and {@link DoclingServeClientBuilder}.
   *
   * @param <C> the type of {@link DoclingServeClient} to be created by the builder
   * @param <B> the type of {@link DoclingServeClientBuilder} to be returned
   * @param classLoader the {@link ClassLoader} used to check for Jackson's presence
   * @return a compatible {@link DoclingServeClientBuilder} instance
   * @throws IllegalStateException if neither Jackson 2 nor Jackson 3 is available on the classpath
   */
  @SuppressWarnings("unchecked")
  public static <C extends DoclingServeClient, B extends DoclingServeClientBuilder<C, B>> B newBuilder(ClassLoader classLoader) {
    if (JacksonVersion.JACKSON_3.isOnClasspath(classLoader)) {
      return (B) DoclingServeJackson3Client.builder();
    }
    else if (JacksonVersion.JACKSON_2.isOnClasspath(classLoader)) {
      return (B) DoclingServeJackson2Client.builder();
    }

    throw new IllegalStateException("Neither Jackson 2 or 3 is on the classpath");
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
   * <p>This method utilizes generics to support custom implementations of
   * {@link DoclingServeClient} and {@link DoclingServeClientBuilder}.
   *
   * @param <C> the type of {@link DoclingServeClient} to be created by the builder
   * @param <B> the type of {@link DoclingServeClientBuilder} to be returned
   * @return a compatible {@link DoclingServeClientBuilder} instance
   * @throws IllegalStateException if neither Jackson 2 nor Jackson 3 is available on the classpath
   */
  public static <C extends DoclingServeClient, B extends DoclingServeClientBuilder<C, B>> B newBuilder() {
    return newBuilder(Thread.currentThread().getContextClassLoader());
  }

  private enum JacksonVersion {
    JACKSON_2("com.fasterxml.jackson.databind.json.JsonMapper"),
    JACKSON_3("tools.jackson.databind.json.JsonMapper");

    private final String jacksonClassName;

    JacksonVersion(String jacksonClassName) {
      this.jacksonClassName = jacksonClassName;
    }

    private boolean isOnClasspath() {
      return isOnClasspath(Thread.currentThread().getContextClassLoader());
    }

    private boolean isOnClasspath(ClassLoader classLoader) {
      try {
        Class.forName(this.jacksonClassName, false, classLoader);
        return true;
      } catch (ClassNotFoundException e) {
        return false;
      }
    }
  }
}
