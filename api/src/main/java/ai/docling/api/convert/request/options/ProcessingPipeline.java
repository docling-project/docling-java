package ai.docling.api.convert.request.options;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Pipelines used by Docling to process documents.
 */
public enum ProcessingPipeline {

    @JsonProperty("asr")
    ASR,
    @JsonProperty("standard")
    STANDARD,
    @JsonProperty("vlm")
    VLM

}
