package ai.docling.serve.api.convert.response;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.net.URI;
import java.util.List;

import org.junit.jupiter.api.Test;

class DocumentArtifactItemTests {

  @Test
  void whenBuiltWithAllFieldsThenGettersReturnSetValues() {
    ArtifactRef jsonRef = ArtifactRef.builder()
        .artifactType(ArtifactType.JSON)
        .mimeType("application/json")
        .uri(URI.create("https://example.com/doc.json"))
        .build();
    ArtifactRef mdRef = ArtifactRef.builder()
        .artifactType(ArtifactType.MARKDOWN)
        .mimeType("text/markdown")
        .uri(URI.create("https://example.com/doc.md"))
        .build();
    ErrorItem error = ErrorItem.builder()
        .componentType("pipeline")
        .errorMessage("page 3 malformed")
        .moduleName("StandardPdfPipeline")
        .build();

    DocumentArtifactItem item = DocumentArtifactItem.builder()
        .sourceIndex(0)
        .sourceUri("https://example.com/example.pdf")
        .filename("example.pdf")
        .status(ConversionStatus.PARTIAL_SUCCESS)
        .error(error)
        .artifact(jsonRef)
        .artifact(mdRef)
        .build();

    assertThat(item.getSourceIndex()).isZero();
    assertThat(item.getSourceUri()).isEqualTo("https://example.com/example.pdf");
    assertThat(item.getFilename()).isEqualTo("example.pdf");
    assertThat(item.getStatus()).isEqualTo(ConversionStatus.PARTIAL_SUCCESS);
    assertThat(item.getErrors()).containsExactly(error);
    assertThat(item.getArtifacts()).containsExactly(jsonRef, mdRef);
    assertThat(item.getTimings()).isNotNull().isEmpty();
  }

  @Test
  void whenBuiltWithNullCollectionsThenGettersReturnEmpty() {
    DocumentArtifactItem item = DocumentArtifactItem.builder()
        .sourceIndex(0)
        .sourceUri("https://example.com/example.pdf")
        .filename("example.pdf")
        .status(ConversionStatus.SUCCESS)
        .build();

    assertThat(item.getErrors()).isNotNull().isEmpty();
    assertThat(item.getTimings()).isNotNull().isEmpty();
    assertThat(item.getArtifacts()).isNotNull().isEmpty();
  }

  @Test
  void sourceUriRequired() {
    assertThatThrownBy(() -> DocumentArtifactItem.builder()
        .filename("example.pdf")
        .status(ConversionStatus.SUCCESS)
        .sourceUri(null)
        .build())
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("sourceUri");
  }

  @Test
  void filenameRequired() {
    assertThatThrownBy(() -> DocumentArtifactItem.builder()
        .sourceUri("https://example.com/example.pdf")
        .status(ConversionStatus.SUCCESS)
        .filename(null)
        .build())
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("filename");
  }

  @Test
  void statusRequired() {
    assertThatThrownBy(() -> DocumentArtifactItem.builder()
        .sourceUri("https://example.com/example.pdf")
        .filename("example.pdf")
        .status(null)
        .build())
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("status");
  }

  @Test
  void errorsListIsImmutableAfterBuild() {
    ErrorItem first = ErrorItem.builder().errorMessage("first").build();

    DocumentArtifactItem item = DocumentArtifactItem.builder()
        .sourceUri("https://example.com/example.pdf")
        .filename("example.pdf")
        .status(ConversionStatus.FAILURE)
        .errors(List.of(first))
        .build();

    assertThat(item.getErrors()).containsExactly(first);
    assertThat(item.getErrors()).isUnmodifiable();
  }
}
