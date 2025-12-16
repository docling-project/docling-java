package ai.docling.serve.api.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import ai.docling.serve.api.convert.request.source.FileSource;

class FileUtilsTests {
  @Test
  void getBytesNull() {
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> FileUtils.getBytes(null))
        .withMessage("file cannot be null");
  }

  @Test
  void getBytesFileDoesntExist() {
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> FileUtils.getBytes(Path.of("doesnt-exist")))
        .withMessage("File (doesnt-exist) does not exist");
  }

  @Test
  void getBytes() throws IOException {
    assertThat(FileUtils.getBytes(Path.of("src", "test", "resources", "docling-java.pdf")))
        .isNotNull()
        .isNotEmpty();
  }

  @Test
  void convertToBase64Null() {
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> FileUtils.convertToBase64((Path) null))
        .withMessage("file cannot be null");
  }

  @Test
  void convertToBase64FileDoesntExist() {
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> FileUtils.convertToBase64(Path.of("doesnt-exist")))
        .withMessage("File (doesnt-exist) does not exist");
  }

  @Test
  void convertToBase64() throws IOException {
    assertThat(FileUtils.convertToBase64(Path.of("src", "test", "resources", "docling-java.pdf")))
        .isNotNull()
        .isNotEmpty();
  }

  @Test
  void convertToBase64BytesNull() {
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> FileUtils.convertToBase64((byte[]) null))
        .withMessage("bytes cannot be null");
  }

  @Test
  void convertToBase64Bytes() throws IOException {
    assertThat(FileUtils.convertToBase64(Files.readAllBytes(Path.of("src", "test", "resources", "docling-java.pdf"))))
        .isNotNull()
        .isNotEmpty();
  }

  @Test
  void convertFileSourcesNull() {
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> FileUtils.createFileSources(null))
        .withMessage("files cannot be null or empty");
  }

  @Test
  void convertFileSourcesEmpty() {
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> FileUtils.createFileSources())
        .withMessage("files cannot be null or empty");
  }

  @Test
  void convertFileSources() {
    assertThat(FileUtils.createFileSources(Path.of("src", "test", "resources", "docling-java.pdf"), null))
        .allMatch(fileSource -> Utils.isNotNullOrEmpty(fileSource.getBase64String()))
        .singleElement()
        .extracting(FileSource::getFilename)
        .isEqualTo("docling-java.pdf");
  }
}
