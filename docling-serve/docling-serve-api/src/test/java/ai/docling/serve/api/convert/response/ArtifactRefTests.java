package ai.docling.serve.api.convert.response;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.net.URI;
import java.time.Instant;

import org.junit.jupiter.api.Test;

class ArtifactRefTests {

  private static final ArtifactRef FULL_REF = ArtifactRef.builder()
      .artifactType(ArtifactType.JSON)
      .mimeType("application/json")
      .uri(URI.create("https://example.com/doc.json"))
      .urlExpiresAt(Instant.parse("2026-06-15T11:22:41Z"))
      .build();

  @Test
  void whenBuiltWithAllFieldsThenGettersReturnSetValues() {
    assertThat(FULL_REF.getArtifactType()).isEqualTo(ArtifactType.JSON);
    assertThat(FULL_REF.getMimeType()).isEqualTo("application/json");
    assertThat(FULL_REF.getUri()).isEqualTo(URI.create("https://example.com/doc.json"));
    assertThat(FULL_REF.getUrlExpiresAt()).isEqualTo(Instant.parse("2026-06-15T11:22:41Z"));
  }

  @Test
  void artifactTypeRequired() {
    assertThatThrownBy(() -> FULL_REF.toBuilder().artifactType(null).build())
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("artifactType");
  }

  @Test
  void mimeTypeRequired() {
    assertThatThrownBy(() -> FULL_REF.toBuilder().mimeType(null).build())
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("mimeType");
  }

  @Test
  void uriRequired() {
    assertThatThrownBy(() -> FULL_REF.toBuilder().uri(null).build())
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("uri");
  }

  @Test
  void urlExpiresAtIsNullable() {
    ArtifactRef ref = FULL_REF.toBuilder().urlExpiresAt(null).build();
    assertThat(ref.getUrlExpiresAt()).isNull();
  }

  @Test
  void toBuilderRoundtripsToEqualInstance() {
    ArtifactRef roundtripped = FULL_REF.toBuilder().build();
    assertThat(roundtripped).usingRecursiveComparison().isEqualTo(FULL_REF);
  }
}
