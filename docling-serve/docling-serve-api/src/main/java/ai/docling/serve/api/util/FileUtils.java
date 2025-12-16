package ai.docling.serve.api.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;
import java.util.stream.Stream;

import ai.docling.serve.api.convert.request.source.FileSource;

/**
 * Utility class that provides file-related operations such as reading files
 * and encoding file contents to Base64.
 */
public final class FileUtils {
  private FileUtils() {}

  /**
   * Reads the contents of the specified file and returns them as a byte array.
   *
   * @param file the path to the file to be read; must not be null, must exist, must be a regular file, and must be readable
   * @return a byte array containing the contents of the file
   * @throws IOException if an I/O error occurs while reading the file
   * @throws IllegalArgumentException if the file does not exist, is not a regular file, or is not readable
   */
  public static byte[] getBytes(Path file) throws IOException {
    ValidationUtils.ensureNotNull(file, "file");

    if (!Files.exists(file)) {
      throw new IllegalArgumentException("File (%s) does not exist".formatted(file));
    }

    if (!Files.isRegularFile(file)) {
      throw new IllegalArgumentException("File (%s) is not a regular file".formatted(file));
    }

    if (!Files.isReadable(file)) {
      throw new IllegalArgumentException("File (%s) is not readable".formatted(file));
    }

    return Files.readAllBytes(file);
  }

  /**
   * Converts the contents of the specified file to a Base64-encoded string.
   *
   * @param file the path to the file to be encoded; must not be null, must exist,
   *             must be a regular file, and must be readable
   * @return a Base64-encoded string representing the contents of the file
   * @throws IOException if an I/O error occurs while reading the file
   * @throws IllegalArgumentException if the file does not exist, is not a regular file, or is not readable
   */
  public static String convertToBase64(Path file) throws IOException {
    return convertToBase64(getBytes(file));
  }

  /**
   * Converts the given byte array to a Base64-encoded string.
   *
   * @param bytes the byte array to be encoded; must not be null
   * @return a Base64-encoded string representing the input byte array; if the input is an empty array, an empty string is returned
   * @throws IllegalArgumentException if the input byte array is null
   */
  public static String convertToBase64(byte[] bytes) {
    ValidationUtils.ensureNotNull(bytes, "bytes");

    return (bytes.length == 0) ? "" : Base64.getEncoder().encodeToString(bytes);
  }

  /**
   * Creates a {@link Stream} of {@link FileSource} instances based on the provided file paths.
   * Each {@link FileSource} will contain the filename and the Base64-encoded content
   * of the corresponding file.
   *
   * @param files an array of {@link Path} objects representing the file paths to be processed;
   *              must not be null or empty, and each element must not be null
   * @return a {@link Stream} of {@link FileSource} instances, one for each valid file path
   * @throws RuntimeException if an I/O error occurs while reading any of the files
   * @throws IllegalArgumentException if the input array is null or empty
   */
  public static Stream<FileSource> createFileSources(Path... files) {
    ValidationUtils.ensureNotEmpty(files, "files");

    return Arrays.stream(files)
        .filter(Objects::nonNull)
        .map(file -> {
              try {
                return FileSource.builder()
                    .filename(file.getFileName().toString())
                    .base64String(convertToBase64(file))
                    .build();
              }
              catch (IOException e) {
                throw new RuntimeException(e);
              }
            }
        );
  }
}
