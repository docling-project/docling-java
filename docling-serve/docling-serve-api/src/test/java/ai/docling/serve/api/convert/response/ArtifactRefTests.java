package ai.docling.serve.api.convert.response;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.time.Instant;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link ArtifactRef}.
 */
class ArtifactRefTests {

  @Test
  void whenBuiltWithAllFieldsThenGettersReturnSetValues() {
    URI uri = URI.create("https://example.com/doc.json");
    Instant expiresAt = Instant.parse("2026-06-15T11:22:41Z");

    ArtifactRef ref = ArtifactRef.builder()
        .artifactType(ArtifactType.JSON)
        .mimeType("application/json")
        .uri(uri)
        .urlExpiresAt(expiresAt)
        .build();

    assertThat(ref.getArtifactType()).isEqualTo(ArtifactType.JSON);
    assertThat(ref.getMimeType()).isEqualTo("application/json");
    assertThat(ref.getUri()).isEqualTo(uri);
    assertThat(ref.getUrlExpiresAt()).isEqualTo(expiresAt);
  }

  @Test
  void whenBuiltWithNullFieldsThenGettersReturnNull() {
    ArtifactRef ref = ArtifactRef.builder().build();

    assertThat(ref.getArtifactType()).isNull();
    assertThat(ref.getMimeType()).isNull();
    assertThat(ref.getUri()).isNull();
    assertThat(ref.getUrlExpiresAt()).isNull();
  }

  @Test
  void toBuilderRoundtripsToEqualInstance() {
    ArtifactRef original = ArtifactRef.builder()
        .artifactType(ArtifactType.MARKDOWN)
        .mimeType("text/markdown")
        .uri(URI.create("https://example.com/doc.md"))
        .build();

    ArtifactRef roundtripped = original.toBuilder().build();

    assertThat(roundtripped).usingRecursiveComparison().isEqualTo(original);
  }
}
