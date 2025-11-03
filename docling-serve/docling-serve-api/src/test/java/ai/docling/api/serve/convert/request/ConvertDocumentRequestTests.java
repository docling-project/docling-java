package ai.docling.api.serve.convert.request;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.InstanceOfAssertFactories.type;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ai.docling.api.serve.convert.request.options.ConvertDocumentOptions;
import ai.docling.api.serve.convert.request.source.FileSource;
import ai.docling.api.serve.convert.request.source.HttpSource;
import ai.docling.api.serve.convert.request.target.InBodyTarget;
import ai.docling.api.serve.convert.request.target.ZipTarget;

/**
 * Unit tests for {@link ConvertDocumentRequest}.
 */
class ConvertDocumentRequestTests {
  @Test
  void whenOptionsIsNullThenThrow() {
    var uri = URI.create("http://example.com");
    var httpSource = HttpSource.builder().url(uri).build();

    assertThatThrownBy(() -> ConvertDocumentRequest.builder().options(null).source(httpSource).build())
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("options is marked non-null but is null");
  }

  @Test
  void buildWithHttpSourcesAsList() {
    ConvertDocumentRequest request = ConvertDocumentRequest.builder()
        .source(HttpSource.builder().url(URI.create("http://example.com")).build())
        .target(InBodyTarget.builder().build())
        .build();
    assertThat(request.getSources()).hasSize(1);
    assertThat(request.getSources().get(0)).isInstanceOf(HttpSource.class);
  }

  @Test
  void buildWithHttpSourcesAsVarargs() {
    ConvertDocumentRequest request = ConvertDocumentRequest.builder()
        .sources(List.of(HttpSource.builder().url(URI.create("http://example.com")).build(), HttpSource.builder().url(URI.create("http://example.org")).build()))
        .build();

    assertThat(request.getSources())
        .hasSize(2)
        .allSatisfy(source -> assertThat(source).isInstanceOf(HttpSource.class));
  }

  @Test
  void buildWithFileSourcesAsList() {
    ConvertDocumentRequest request = ConvertDocumentRequest.builder()
        .sources(List.of(FileSource.builder().filename("file:///path/to/file.txt").base64String("content").build()))
        .build();

    assertThat(request.getSources())
        .hasSize(1)
        .allSatisfy(source -> assertThat(source).isInstanceOf(FileSource.class));
  }

  @Test
  void buildWithFileSourcesAsVarargs() {
    ConvertDocumentRequest request = ConvertDocumentRequest.builder()
        .sources(List.of(
            FileSource.builder().filename("file1.txt").base64String("base64string1").build(),
            FileSource.builder().filename("file2.txt").base64String("base64string2").build()
            )
        )
        .target(ZipTarget.builder().build())
        .build();

    assertThat(request.getSources())
        .hasSize(2)
        .allSatisfy(source -> assertThat(source).isInstanceOf(FileSource.class));
  }

  @Test
  void convertDocumentRequestIsImmutable() {
    List<FileSource> sources = new ArrayList<>(List.of(FileSource.builder().filename("test.txt").base64String("dGVzdCBjb250ZW50").build()));

    ConvertDocumentRequest request = ConvertDocumentRequest.builder()
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
}
