package ai.docling.api.convert.request.target;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link InBodyTarget}.
 */
class InBodyTargetTests {

  @Test
  void whenValidParametersThenCreateInBodyTarget() {
    InBodyTarget inBodyTarget = InBodyTarget.create();

    assertThat(inBodyTarget.kind()).isEqualTo(Target.Kind.INBODY);
  }

  @Test
  void kindIsAlwaysSetToInBody() {
    InBodyTarget inBodyTarget = new InBodyTarget(Target.Kind.PUT);

    assertThat(inBodyTarget.kind()).isEqualTo(Target.Kind.INBODY);
  }

}
