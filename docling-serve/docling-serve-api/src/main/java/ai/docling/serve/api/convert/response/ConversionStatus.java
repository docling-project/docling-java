package ai.docling.serve.api.convert.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the possible conversion outcomes for a document.
 *
 * <ul>
 *   <li>{@code PENDING}: Indicates that the conversion has not yet started.</li>
 *   <li>{@code STARTED}: Indicates that the conversion is currently in progress.</li>
 *   <li>{@code SUCCESS}: Indicates that all pages of the document were converted.</li>
 *   <li>{@code PARTIAL_SUCCESS}: Indicates that some pages were converted and others failed.</li>
 *   <li>{@code FAILURE}: Indicates that the document could not be converted.</li>
 *   <li>{@code SKIPPED}: Indicates that the document was rejected at admission.</li>
 * </ul>
 */
public enum ConversionStatus {
  @JsonProperty("pending") PENDING,
  @JsonProperty("started") STARTED,
  @JsonProperty("success") SUCCESS,
  @JsonProperty("partial_success") PARTIAL_SUCCESS,
  @JsonProperty("failure") FAILURE,
  @JsonProperty("skipped") SKIPPED;
}
