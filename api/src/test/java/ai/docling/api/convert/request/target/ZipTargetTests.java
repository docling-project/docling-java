package ai.docling.api.convert.request.target;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link ZipTarget}.
 */
class ZipTargetTests {

  @Test
  void whenValidParametersThenCreateZipTarget() {
    ZipTarget zipTarget = ZipTarget.create();

    assertThat(zipTarget.kind()).isEqualTo(Target.Kind.ZIP);
  }

  @Test
  void kindIsAlwaysSetToZip() {
    ZipTarget zipTarget = new ZipTarget(Target.Kind.PUT);

    assertThat(zipTarget.kind()).isEqualTo(Target.Kind.ZIP);
  }

}
