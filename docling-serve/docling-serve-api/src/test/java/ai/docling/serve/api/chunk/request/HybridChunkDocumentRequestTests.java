package ai.docling.serve.api.chunk.request;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.InstanceOfAssertFactories.type;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ai.docling.serve.api.chunk.request.options.HybridChunkerOptions;
import ai.docling.serve.api.convert.request.options.ConvertDocumentOptions;
import ai.docling.serve.api.convert.request.source.FileSource;
import ai.docling.serve.api.convert.request.source.HttpSource;
import ai.docling.serve.api.convert.request.target.InBodyTarget;
import ai.docling.serve.api.convert.request.target.ZipTarget;

/**
 * Unit tests for {@link HybridChunkDocumentRequest}.
 */
class HybridChunkDocumentRequestTests {

  @Test
  void whenOptionsIsNullThenThrow() {
    var uri = URI.create("http://example.com");
    var httpSource = HttpSource.builder().url(uri).build();

    assertThatThrownBy(() -> HybridChunkDocumentRequest.builder().options(null).source(httpSource).build())
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("options is marked non-null but is null");
  }

  @Test
  void buildWithHttpSourcesAsList() {
    HybridChunkDocumentRequest request = HybridChunkDocumentRequest.builder()
        .source(HttpSource.builder().url(URI.create("http://example.com")).build())
        .target(InBodyTarget.builder().build())
        .build();
    assertThat(request.getSources()).hasSize(1);
    assertThat(request.getSources().get(0)).isInstanceOf(HttpSource.class);
  }

  @Test
  void buildWithHttpSourcesAsVarargs() {
    HybridChunkDocumentRequest request = HybridChunkDocumentRequest.builder()
        .sources(List.of(
            HttpSource.builder().url(URI.create("http://example.com")).build(),
            HttpSource.builder().url(URI.create("http://example.org")).build()
        ))
        .build();

    assertThat(request.getSources())
        .hasSize(2)
        .allSatisfy(source -> assertThat(source).isInstanceOf(HttpSource.class));
  }

  @Test
  void buildWithFileSourcesAsList() {
    HybridChunkDocumentRequest request = HybridChunkDocumentRequest.builder()
        .sources(List.of(FileSource.builder().filename("file:///path/to/file.txt").base64String("content").build()))
        .build();

    assertThat(request.getSources())
        .hasSize(1)
        .allSatisfy(source -> assertThat(source).isInstanceOf(FileSource.class));
  }

  @Test
  void buildWithFileSourcesAsVarargs() {
    HybridChunkDocumentRequest request = HybridChunkDocumentRequest.builder()
        .sources(List.of(
            FileSource.builder().filename("file1.txt").base64String("base64string1").build(),
            FileSource.builder().filename("file2.txt").base64String("base64string2").build()
        ))
        .target(ZipTarget.builder().build())
        .build();

    assertThat(request.getSources())
        .hasSize(2)
        .allSatisfy(source -> assertThat(source).isInstanceOf(FileSource.class));
  }

  @Test
  void hybridChunkDocumentRequestIsImmutable() {
    List<FileSource> sources = new ArrayList<>(List.of(
        FileSource.builder().filename("test.txt").base64String("dGVzdCBjb250ZW50").build()
    ));

    HybridChunkDocumentRequest request = HybridChunkDocumentRequest.builder()
        .sources(sources)
        .options(ConvertDocumentOptions.builder().doOcr(true).build())
        .target(InBodyTarget.builder().build())
        .build();

    assertThat(request.getSources())
        .usingRecursiveFieldByFieldElementComparator()
        .containsExactlyInAnyOrderElementsOf(sources);

    sources.add(FileSource.builder().filename("changed.txt").base64String("Y2hhbmdlZA==").build());

    assertThat(request.getSources())
        .singleElement()
        .asInstanceOf(type(FileSource.class))
        .extracting(FileSource::getFilename, FileSource::getBase64String)
        .containsExactly("test.txt", "dGVzdCBjb250ZW50");
  }

  @Test
  void buildWithIncludeConvertedDoc() {
    HybridChunkDocumentRequest request = HybridChunkDocumentRequest.builder()
        .source(HttpSource.builder().url(URI.create("http://example.com")).build())
        .includeConvertedDoc(true)
        .build();

    assertThat(request.isIncludeConvertedDoc()).isTrue();
  }

  @Test
  void buildWithChunkingOptions() {
    HybridChunkerOptions chunkingOptions = HybridChunkerOptions.builder()
        .maxTokens(512)
        .tokenizer("bert-base-uncased")
        .useMarkdownTables(true)
        .includeRawText(false)
        .mergePeers(true)
        .build();

    HybridChunkDocumentRequest request = HybridChunkDocumentRequest.builder()
        .source(HttpSource.builder().url(URI.create("http://example.com")).build())
        .chunkingOptions(chunkingOptions)
        .build();

    assertThat(request.getChunkingOptions())
        .isNotNull()
        .satisfies(options -> {
          assertThat(options.getMaxTokens()).isEqualTo(512);
          assertThat(options.getTokenizer()).isEqualTo("bert-base-uncased");
          assertThat(options.isUseMarkdownTables()).isTrue();
          assertThat(options.isIncludeRawText()).isFalse();
          assertThat(options.getMergePeers()).isTrue();
        });
  }

  @Test
  void buildWithAllFields() {
    HybridChunkerOptions chunkingOptions = HybridChunkerOptions.builder()
        .maxTokens(256)
        .build();

    HybridChunkDocumentRequest request = HybridChunkDocumentRequest.builder()
        .source(FileSource.builder().filename("document.pdf").base64String("cGRmY29udGVudA==").build())
        .options(ConvertDocumentOptions.builder().doOcr(false).build())
        .target(ZipTarget.builder().build())
        .includeConvertedDoc(true)
        .chunkingOptions(chunkingOptions)
        .build();

    assertThat(request.getSources()).hasSize(1);
    assertThat(request.getOptions()).isNotNull();
    assertThat(request.getTarget()).isNotNull();
    assertThat(request.isIncludeConvertedDoc()).isTrue();
    assertThat(request.getChunkingOptions()).isNotNull();
  }

  @Test
  void buildWithDefaultOptions() {
    HybridChunkDocumentRequest request = HybridChunkDocumentRequest.builder()
        .source(HttpSource.builder().url(URI.create("http://example.com")).build())
        .build();

    assertThat(request.getOptions()).isNotNull();
    assertThat(request.isIncludeConvertedDoc()).isFalse();
    assertThat(request.getChunkingOptions()).isNotNull();
    assertThat(request.getTarget()).isNull();
  }
}
