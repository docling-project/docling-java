package ai.docling.serve.api;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.Test;

import ai.docling.serve.api.DoclingServeApi.DoclingApiBuilder;
import ai.docling.serve.api.spi.DoclingServeApiBuilderFactory;

class DoclingServeApiTests {
  @Test
  void noFactoryFound() {
    assertThatExceptionOfType(IllegalStateException.class)
        .isThrownBy(() -> DoclingServeApi.builder())
        .withMessage("No instance of %s found to build a %s instance. You are probably missing a library on your classpath.", DoclingServeApiBuilderFactory.class.getName(), DoclingApiBuilder.class.getName());
  }
}
