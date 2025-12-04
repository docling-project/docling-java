package ai.docling.serve.api.chunk.response;

import java.util.List;

import org.jspecify.annotations.Nullable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

import ai.docling.serve.api.convert.response.ErrorItem;

/**
 * Represents the document being processed by a Docling chunker.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@tools.jackson.databind.annotation.JsonDeserialize(builder = Document.Builder.class)
@lombok.extern.jackson.Jacksonized
@lombok.Builder(toBuilder = true)
@lombok.Getter
@lombok.ToString
public class Document {

  @JsonProperty("kind")
  @Nullable
  private String kind;

  @JsonProperty("content")
  private ExportDocumentResponse content;

  @JsonProperty("status")
  private String status;

  @JsonProperty("errors")
  @JsonSetter(nulls = Nulls.AS_EMPTY)
  @lombok.Singular
  private List<ErrorItem> errors;

  @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
  public static class Builder { }

}
