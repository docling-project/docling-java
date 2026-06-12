package ai.docling.serve.api.convert.request.target;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link PresignedUrlTarget}.
 */
class PresignedUrlTargetTests {

  @Test
  void whenBuiltThenInstanceCreated() {
    PresignedUrlTarget target = PresignedUrlTarget.builder().build();

    assertThat(target).isNotNull();
  }

  @Test
  void whenBuiltThenIsTargetSubtype() {
    PresignedUrlTarget target = PresignedUrlTarget.builder().build();

    assertThat(target).isInstanceOf(Target.class);
  }

  @Test
  void whenToBuilderInvokedThenEqualInstanceProduced() {
    PresignedUrlTarget target = PresignedUrlTarget.builder().build();

    PresignedUrlTarget roundtripped = target.toBuilder().build();

    assertThat(roundtripped).usingRecursiveComparison().isEqualTo(target);
  }
}
