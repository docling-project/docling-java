package ai.docling.api.serve.convert.request.source;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link FileSource}.
 */
class FileSourceTests {

  @Test
  void whenBase64StringIsNullThenThrow() {
    assertThatThrownBy(() -> FileSource.builder().filename("test.txt").build())
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("base64String is marked non-null but is nul");
  }

  @Test
  void whenFilenameIsNullThenThrow() {
    assertThatThrownBy(() -> FileSource.builder().base64String("dGVzdCBjb250ZW50").build())
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("filename is marked non-null but is nul");
  }

  @Test
  void whenValidParametersThenCreateFileSource() {
    String base64String = "dGVzdCBjb250ZW50";
    String filename = "test.txt";

    FileSource fileSource = FileSource.builder().base64String(base64String).filename(filename).build();

    assertThat(fileSource.getBase64String()).isEqualTo(base64String);
    assertThat(fileSource.getFilename()).isEqualTo(filename);
  }

  @Test
  void builderCreatesFileSource() {
    String filename = "presentation.pptx";
    String base64String = "dGVzdCBjb250ZW50";

    FileSource fileSource = FileSource.builder()
        .filename(filename)
        .base64String(base64String)
        .build();

    assertThat(fileSource.getBase64String()).isEqualTo(base64String);
    assertThat(fileSource.getFilename()).isEqualTo(filename);
  }

}
