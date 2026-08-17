package ai.docling.serve.api.response;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import ai.docling.serve.api.chunk.response.ChunkDocumentResponse;
import ai.docling.serve.api.convert.response.ConvertDocumentResponse;
import ai.docling.serve.api.convert.response.InBodyConvertDocumentResponse;
import ai.docling.serve.api.convert.response.PreSignedUrlConvertDocumentResponse;
import ai.docling.serve.api.convert.response.PreSignedUrlConvertResponse;
import ai.docling.serve.api.convert.response.ZipArchiveConvertDocumentResponse;

/**
 * Unit tests for the {@link ProcessedDocumentResponse} sealed hierarchy.
 */
class ProcessedDocumentResponseTests {

  @Test
  void allConcreteSubtypesAreDistinguishableViaInstanceOf() {
    List<ProcessedDocumentResponse> responses = List.of(
        InBodyConvertDocumentResponse.builder().build(), PreSignedUrlConvertDocumentResponse.builder().build(), PreSignedUrlConvertResponse.builder()
            .build(), ZipArchiveConvertDocumentResponse.builder().build(), ChunkDocumentResponse.builder().build());

    var results = responses.stream()
        .map(ProcessedDocumentResponseTests::classifyResponse)
        .toList();

    assertThat(results).containsExactly(
        "in-body", "pre-signed-url", "pre-signed-url-response", "zip-archive", "chunk");
  }

  @Test
  void convertDocumentResponseIsAssignableToBase() {
    ProcessedDocumentResponse response = InBodyConvertDocumentResponse.builder().build();

    assertThat(response)
        .isInstanceOf(ProcessedDocumentResponse.class)
        .isInstanceOf(ConvertDocumentResponse.class);
  }

  @Test
  void chunkDocumentResponseIsAssignableToBase() {
    ProcessedDocumentResponse response = ChunkDocumentResponse.builder().build();

    assertThat(response)
        .isInstanceOf(ProcessedDocumentResponse.class)
        .isInstanceOf(ChunkDocumentResponse.class);
  }

  @Test
  void sealedPermitsOnlyExpectedSubtypes() {
    var permitted = ProcessedDocumentResponse.class.getPermittedSubclasses();

    assertThat(permitted)
        .hasSize(2)
        .containsExactlyInAnyOrder(ConvertDocumentResponse.class, ChunkDocumentResponse.class);
  }

  private static String classifyResponse(ProcessedDocumentResponse response) {
    if (response instanceof InBodyConvertDocumentResponse) {
      return "in-body";
    }
    else if (response instanceof PreSignedUrlConvertDocumentResponse) {
      return "pre-signed-url";
    }
    else if (response instanceof PreSignedUrlConvertResponse) {
      return "pre-signed-url-response";
    }
    else if (response instanceof ZipArchiveConvertDocumentResponse) {
      return "zip-archive";
    }
    else if (response instanceof ChunkDocumentResponse) {
      return "chunk";
    }
    throw new IllegalArgumentException("Unknown response type: " + response.getClass());
  }
}
