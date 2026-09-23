package ai.docling.serve.api;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.Test;

import ai.docling.serve.api.spi.DoclingServeApiBuilderFactory;
import ai.docling.serve.api.spi.DoclingServeApiProvider;

@SuppressWarnings("removal")
class DoclingServeApiTests {
  @Test
  void noProviderFound() {
    assertThatExceptionOfType(IllegalStateException.class)
        .isThrownBy(() -> DoclingServeApi.builder().build())
        .withMessage(
            "No instance of %s (or of the deprecated %s) found to build a %s instance. You are probably missing a library on your classpath.", DoclingServeApiProvider.class
                .getName(), DoclingServeApiBuilderFactory.class.getName(), DoclingServeApi.class.getName());
  }
}
