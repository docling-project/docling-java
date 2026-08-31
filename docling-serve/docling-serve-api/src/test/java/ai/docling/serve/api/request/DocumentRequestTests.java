package ai.docling.serve.api.request;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.net.URI;
import java.util.List;

import org.junit.jupiter.api.Test;

import ai.docling.serve.api.chunk.request.HierarchicalChunkDocumentRequest;
import ai.docling.serve.api.chunk.request.HybridChunkDocumentRequest;
import ai.docling.serve.api.convert.request.BatchConvertDocumentRequest;
import ai.docling.serve.api.convert.request.ConvertDocumentRequest;
import ai.docling.serve.api.convert.request.source.HttpSource;
import ai.docling.serve.api.convert.request.target.InBodyTarget;
import ai.docling.serve.api.convert.request.target.ZipTarget;

/**
 * Unit tests for the {@link DocumentRequest} sealed hierarchy.
 */
class DocumentRequestTests {
  private static final HttpSource HTTP_SOURCE = HttpSource.builder()
      .url(URI.create("http://example.com/doc.pdf"))
      .build();

  @Test
  void sourcesAndTargetAccessibleThroughBaseType() {
    var target = InBodyTarget.builder().build();

    DocumentRequest request = ConvertDocumentRequest.builder()
        .sources(List.of(HTTP_SOURCE))
        .target(target)
        .build();

    assertThat(request.getSources())
        .singleElement()
        .isEqualTo(HTTP_SOURCE);

    assertThat(request.getTarget()).isSameAs(target);
  }

  @Test
  void targetIsNullByDefault() {
    DocumentRequest request = ConvertDocumentRequest.builder()
        .source(HTTP_SOURCE)
        .build();

    assertThat(request.getTarget()).isNull();
  }

  @Test
  void sourcesDefaultToEmptyList() {
    DocumentRequest request = ConvertDocumentRequest.builder().build();

    assertThat(request.getSources()).isEmpty();
  }

  @Test
  void allConcreteSubtypesAreDistinguishableViaInstanceOf() {
    List<DocumentRequest> requests = List.of(
        ConvertDocumentRequest.builder().source(HTTP_SOURCE).build(), BatchConvertDocumentRequest.builder().source(HTTP_SOURCE).target(ZipTarget.builder().build())
            .build(), HierarchicalChunkDocumentRequest.builder().source(HTTP_SOURCE).build(), HybridChunkDocumentRequest.builder().source(HTTP_SOURCE).build()
    );

    var results = requests.stream()
        .map(DocumentRequestTests::classifyRequest)
        .toList();

    assertThat(results).containsExactly("convert", "batch", "hierarchical-chunk", "hybrid-chunk");
  }

  @Test
  void toBuilderPreservesSourcesAndTarget() {
    var target = InBodyTarget.builder().build();

    ConvertDocumentRequest original = ConvertDocumentRequest.builder()
        .source(HTTP_SOURCE)
        .target(target)
        .build();

    ConvertDocumentRequest copy = original.toBuilder().build();

    assertThat(copy.getSources())
        .isEqualTo(original.getSources());

    assertThat(copy.getTarget()).isSameAs(original.getTarget());
  }

  @Test
  void batchConvertGetTargetThrowsWhenTargetIsNull() {
    BatchConvertDocumentRequest request = BatchConvertDocumentRequest.builder()
        .source(HTTP_SOURCE)
        .build();

    assertThatThrownBy(request::getTarget)
        .isInstanceOf(NullPointerException.class);
  }

  @Test
  void toStringIncludesSourcesAndTarget() {
    ConvertDocumentRequest request = ConvertDocumentRequest.builder()
        .source(HTTP_SOURCE)
        .target(InBodyTarget.builder().build())
        .build();

    assertThat(request.toString())
        .contains("sources")
        .contains("target");
  }

  private static String classifyRequest(DocumentRequest request) {
    if (request instanceof ConvertDocumentRequest) {
      return "convert";
    }
    else if (request instanceof BatchConvertDocumentRequest) {
      return "batch";
    }
    else if (request instanceof HierarchicalChunkDocumentRequest) {
      return "hierarchical-chunk";
    }
    else if (request instanceof HybridChunkDocumentRequest) {
      return "hybrid-chunk";
    }
    throw new IllegalArgumentException("Unknown request type: " + request.getClass());
  }
}
