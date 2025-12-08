package ai.docling.serve.api.chunk.response;

import org.jspecify.annotations.Nullable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import ai.docling.core.DoclingDocument;

/**
 * Content of the original converted document, before getting chunked.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@tools.jackson.databind.annotation.JsonDeserialize(builder = ExportDocumentResponse.Builder.class)
@lombok.extern.jackson.Jacksonized
@lombok.Builder(toBuilder = true)
@lombok.Getter
@lombok.ToString
public class ExportDocumentResponse {

  @JsonProperty("filename")
  private String filename;

  @JsonProperty("md_content")
  @Nullable
  private String markdownContent;

  @JsonProperty("json_content")
  @Nullable
  private DoclingDocument jsonContent;

  @JsonProperty("html_content")
  @Nullable
  private String htmlContent;

  @JsonProperty("text_content")
  @Nullable
  private String textContent;

  @JsonProperty("doctags_content")
  @Nullable
  private String doctagsContent;

  @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
  public static class Builder { }

}
