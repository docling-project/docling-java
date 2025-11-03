package ai.docling.api.serve.convert.request.options;

import java.util.Map;

import org.jspecify.annotations.Nullable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

/**
 * Configuration for local picture description generation.
 * This class contains settings for generating descriptions of pictures using a local model.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@tools.jackson.databind.annotation.JsonDeserialize(builder = PictureDescriptionLocal.Builder.class)
@lombok.extern.jackson.Jacksonized
@lombok.Builder(toBuilder = true)
@lombok.Getter
@lombok.ToString
public class PictureDescriptionLocal {
  @JsonProperty("repo_id")
  @lombok.NonNull
  private String repoId;

  @JsonProperty("prompt")
  @Nullable
  private String prompt;

  @JsonProperty("generation_config")
  @JsonSetter(nulls = Nulls.AS_EMPTY)
  @lombok.Singular("generationConfig")
  private Map<String, Object> generationConfig;

  @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
  public static class Builder { }
}
