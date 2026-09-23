package ai.docling.serve.api;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.net.URI;
import java.time.Duration;

import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.Test;

import ai.docling.serve.api.DoclingServeApi.DoclingApiBuilder;
import ai.docling.serve.api.spi.DoclingServeApiBuilderFactory;

class DoclingServeApiTests {
  @Test
  void noFactoryFound() {
    assertThatExceptionOfType(IllegalStateException.class)
        .isThrownBy(() -> DoclingServeApi.builder())
        .withMessage("No instance of %s found to build a %s instance. You are probably missing a library on your classpath.", DoclingServeApiBuilderFactory.class
            .getName(), DoclingApiBuilder.class.getName());
  }

  @Test
  void asyncExecutorIsUnsupportedByDefault() {
    assertThatExceptionOfType(UnsupportedOperationException.class)
        .isThrownBy(() -> new MinimalBuilder().asyncExecutor(Runnable::run));
  }

  // A builder implementing only the abstract methods of the interface, e.g. one provided through the SPI.
  // This class compiling is what guarantees that new DoclingApiBuilder methods don't break such builders.
  private static final class MinimalBuilder implements DoclingApiBuilder<DoclingServeApi, MinimalBuilder> {
    @Override
    public MinimalBuilder baseUrl(URI baseUrl) {
      return this;
    }

    @Override
    public MinimalBuilder apiKey(@Nullable String apiKey) {
      return this;
    }

    @Override
    public MinimalBuilder logRequests(boolean logRequests) {
      return this;
    }

    @Override
    public MinimalBuilder logResponses(boolean logResponses) {
      return this;
    }

    @Override
    public MinimalBuilder prettyPrint(boolean prettyPrint) {
      return this;
    }

    @Override
    public MinimalBuilder connectTimeout(Duration connectTimeout) {
      return this;
    }

    @Override
    public MinimalBuilder readTimeout(Duration readTimeout) {
      return this;
    }

    @Override
    public MinimalBuilder asyncPollInterval(Duration asyncPollInterval) {
      return this;
    }

    @Override
    public MinimalBuilder asyncTimeout(Duration asyncTimeout) {
      return this;
    }

    @Override
    public DoclingServeApi build() {
      throw new UnsupportedOperationException();
    }
  }
}
