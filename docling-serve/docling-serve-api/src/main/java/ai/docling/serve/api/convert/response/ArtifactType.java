package ai.docling.serve.api.convert.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the output format of a converted-document artifact.
 *
 * <ul>
 *   <li>{@code JSON}: Serialized {@code DoclingDocument} JSON.</li>
 *   <li>{@code HTML}: HTML rendering of the document.</li>
 *   <li>{@code MARKDOWN}: Markdown rendering of the document.</li>
 *   <li>{@code TEXT}: Plain-text rendering of the document.</li>
 *   <li>{@code DOCTAGS}: DocTags rendering of the document.</li>
 *   <li>{@code RESOURCE_BUNDLE}: ZIP archive containing extracted images and supporting resources.</li>
 * </ul>
 */
public enum ArtifactType {
  @JsonProperty("json") JSON,
  @JsonProperty("html") HTML,
  @JsonProperty("markdown") MARKDOWN,
  @JsonProperty("text") TEXT,
  @JsonProperty("doctags") DOCTAGS,
  @JsonProperty("resource_bundle") RESOURCE_BUNDLE;
}
