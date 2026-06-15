package ai.docling.serve.api.convert.request.target;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PresignedUrlTargetTests {

  @Test
  void whenToBuilderInvokedThenEqualInstanceProduced() {
    PresignedUrlTarget target = PresignedUrlTarget.builder().build();

    PresignedUrlTarget roundtripped = target.toBuilder().build();

    assertThat(roundtripped).usingRecursiveComparison().isEqualTo(target);
  }
}
