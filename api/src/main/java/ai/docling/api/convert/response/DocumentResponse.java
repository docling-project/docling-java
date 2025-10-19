package ai.docling.api.convert.response;

import java.util.HashMap;
import java.util.Map;

import org.jspecify.annotations.Nullable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record DocumentResponse(

    @JsonProperty("doctags_content") @Nullable String doctagsContent,

    @JsonProperty("filename") String filename,

    @JsonProperty("html_content") @Nullable String htmlContent,

    @JsonProperty("json_content") @Nullable Map<String, Object> jsonContent,

    @JsonProperty("md_content") @Nullable String markdownContent,

    @JsonProperty("text_content") @Nullable String textContent

) {

  public DocumentResponse {
    if (jsonContent != null) {
      jsonContent = new HashMap<>(jsonContent);
    }
  }

}
