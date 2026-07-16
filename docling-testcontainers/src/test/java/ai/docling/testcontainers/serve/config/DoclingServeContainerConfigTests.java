package ai.docling.testcontainers.serve.config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.Test;

class DoclingServeContainerConfigTests {

  @Test
  void toBuilderPreservesExistingContainerEnv() {
    var original = DoclingServeContainerConfig.builder()
        .image(DoclingServeContainerConfig.DOCLING_IMAGE)
        .containerEnv(Map.of("FIRST", "1"))
        .build();

    var copy = original.toBuilder().build();

    assertThat(copy.containerEnv()).containsExactlyInAnyOrderEntriesOf(Map.of("FIRST", "1"));
  }

  @Test
  void toBuilderAllowsAddingContainerEnvWithoutMutatingOriginal() {
    var original = DoclingServeContainerConfig.builder()
        .image(DoclingServeContainerConfig.DOCLING_IMAGE)
        .containerEnv(Map.of("FIRST", "1"))
        .build();

    var updated = new AtomicReference<DoclingServeContainerConfig>();
    assertThatCode(() -> updated.set(original.toBuilder()
        .containerEnv("SECOND", "2")
        .build()))
        .doesNotThrowAnyException();

    assertThat(updated.get().containerEnv())
        .containsExactlyInAnyOrderEntriesOf(Map.of("FIRST", "1", "SECOND", "2"));

    // The original configuration must remain untouched by the builder copy.
    assertThat(original.containerEnv()).containsExactlyInAnyOrderEntriesOf(Map.of("FIRST", "1"));
  }

  @Test
  void containerEnvMakesDefensiveCopyOfCallerMap() {
    var source = new HashMap<String, String>();
    source.put("FIRST", "1");

    var config = DoclingServeContainerConfig.builder()
        .image(DoclingServeContainerConfig.DOCLING_IMAGE)
        .containerEnv(source)
        .build();

    // Mutating the caller-provided map after the call must not leak into the config.
    source.put("SECOND", "2");
    source.remove("FIRST");

    assertThat(config.containerEnv()).containsExactlyInAnyOrderEntriesOf(Map.of("FIRST", "1"));
  }

  @Test
  void containerEnvKeyValueRejectsNullKeyFailFast() {
    var builder = DoclingServeContainerConfig.builder();

    assertThatNullPointerException().isThrownBy(() -> builder.containerEnv(null, "value"));
  }

  @Test
  void containerEnvKeyValueRejectsNullValueFailFast() {
    var builder = DoclingServeContainerConfig.builder();

    assertThatNullPointerException().isThrownBy(() -> builder.containerEnv("key", null));
  }

  @Test
  void containerEnvNullClearsToEmptyMapWithoutThrowing() {
    var config = new AtomicReference<DoclingServeContainerConfig>();
    assertThatCode(() -> config.set(DoclingServeContainerConfig.builder()
        .image(DoclingServeContainerConfig.DOCLING_IMAGE)
        .containerEnv("FIRST", "1")
        .containerEnv(null)
        .build()))
        .doesNotThrowAnyException();

    assertThat(config.get().containerEnv()).isEmpty();
  }

}
