package ai.docling.serve.api.convert.request.source;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a file-based data source.
 * This class provides functionality to handle Base64-encoded file content and filenames
 * for file sources. Inherits from {@link Source}.
 *
 * <p>JSON serialization/deserialization:
 * <ul>
 *   <li>{@code base64_string} - Represents the Base64-encoded content of the file.</li>
 *   <li>{@code filename} - Represents the name of the uploaded file.</li>
 * </ul>
 *
 * <p>By default, JSON includes non-empty fields only.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@tools.jackson.databind.annotation.JsonDeserialize(builder = FileSource.Builder.class)
@lombok.extern.jackson.Jacksonized
@lombok.Builder(toBuilder = true)
@lombok.Getter
@lombok.ToString
public final class FileSource extends Source {
  @JsonProperty("base64_string")
  @lombok.NonNull
  private String base64String;

  @JsonProperty("filename")
  @lombok.NonNull
  private String filename;

  @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
  public static class Builder { }
}
