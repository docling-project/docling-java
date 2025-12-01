package ai.docling.serve.api.convert.request.source;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Source of the document.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "kind"
)
@JsonSubTypes({
    @Type(value = FileSource.class, name = "file"),
    @Type(value = HttpSource.class, name = "http")
})
@lombok.Getter
@lombok.ToString
public sealed abstract class Source permits FileSource, HttpSource {
  /**
   * Enum representing the type of {@link Source}.
   * <ul>
   *   <li>{@code HTTP} - Represents an HTTP-based source (identified by {@code "http"}).</li>
   *   <li>{@code FILE} - Represents a file-based source (identified by {@code "file"}).</li>
   * </ul>
   * The enum values are serialized and deserialized using their respective JSON property
   * values as defined by the {@link JsonProperty} annotation.
   */
  enum Kind {
    @JsonProperty("http") HTTP,
    @JsonProperty("file") FILE
  }
}
