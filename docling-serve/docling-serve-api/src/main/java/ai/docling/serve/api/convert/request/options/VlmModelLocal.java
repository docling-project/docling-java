package ai.docling.serve.api.convert.request.options;

import java.util.Map;

import org.jspecify.annotations.Nullable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

/**
 * Options for running a local vision-language model for the VLM pipeline.
 * The parameters refer to a model hosted on Hugging Face.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@tools.jackson.databind.annotation.JsonDeserialize(builder = VlmModelLocal.Builder.class)
@lombok.extern.jackson.Jacksonized
@lombok.Builder(toBuilder = true)
@lombok.Getter
@lombok.ToString
public class VlmModelLocal {
    @JsonProperty("repo_id")
    @Nullable
    private String repoId;

    @JsonProperty("prompt")
    @Nullable
    private String prompt;

    @JsonProperty("scale")
    @Nullable
    private Integer scale;

    @JsonProperty("response_format")
    @Nullable
    private ResponseFormat responseFormat;

    @JsonProperty("inference_framework")
    @Nullable
    private InferenceFramework inferenceFramework;

    @JsonProperty("transformers_model_type")
    @Nullable
    private TransformersModelType transformersModelType;

    @JsonProperty("extra_generation_config")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular("extraGenerationConfig")
    private Map<String, Object> extraGenerationConfig;

    @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
    public static class Builder { }

    /**
     * Inference framework to use.
     */
    public enum InferenceFramework {

        @JsonProperty("mlx") MLX,
        @JsonProperty("transformers") TRANSFORMERS,
        @JsonProperty("vllm") VLLM

    }

    /**
     * Type of transformers auto-model to use.
     */
    public enum TransformersModelType {

        @JsonProperty("automodel") AUTOMODEL,
        @JsonProperty("automodel-vision2seq") AUTOMODEL_VISION2SEQ,
        @JsonProperty("automodel-causallm") AUTOMODEL_CAUSALLM,
        @JsonProperty("automodel-imagetexttotext") AUTOMODEL_IMAGETEXTTOTEXT

    }

}
