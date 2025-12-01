package ai.docling.serve.api.convert.response;

import org.jspecify.annotations.Nullable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import ai.docling.api.core.DoclingDocument;

/**
 * A simple mutable POJO representing the converted document returned by the
 * Docling Convert API. The properties map 1:1 to the JSON payload using the
 * {@link JsonProperty} names declared on each field.
 *
 * <p>Serialization is configured with {@link JsonInclude.Include#NON_EMPTY},
 * meaning nulls, empty strings, and empty collections/maps are omitted from the
 * serialized JSON.</p>
 *
 * <p>Notes on mutability and maps:</p>
 * <ul>
 *   <li>{@code jsonContent} defaults to an empty map and is never exposed as
 *   {@code null}. The getter returns an unmodifiable view.</li>
 *   <li>When setting {@code jsonContent} with a non-null map, the reference is
 *   preserved; subsequent external modifications to the provided map are
 *   observable via {@link #getJsonContent()}.</li>
 * </ul>
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@tools.jackson.databind.annotation.JsonDeserialize(builder = DocumentResponse.Builder.class)
@lombok.extern.jackson.Jacksonized
@lombok.Builder(toBuilder = true)
@lombok.Getter
@lombok.ToString
public class DocumentResponse {
  @JsonProperty("doctags_content")
  @Nullable
  private String doctagsContent;

  @JsonProperty("filename")
  private String filename;

  @JsonProperty("html_content")
  @Nullable
  private String htmlContent;

  @JsonProperty("json_content")
  @Nullable
  private DoclingDocument jsonContent;

  @JsonProperty("md_content")
  @Nullable
  private String markdownContent;

  @JsonProperty("text_content")
  @Nullable
  private String textContent;

  @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
  public static class Builder { }
}
