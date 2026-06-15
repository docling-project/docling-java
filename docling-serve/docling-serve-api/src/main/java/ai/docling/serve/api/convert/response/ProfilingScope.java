package ai.docling.serve.api.convert.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the scope of a profiling measurement.
 *
 * <ul>
 *   <li>{@code PAGE}: Indicates that the measurement is recorded per page.</li>
 *   <li>{@code DOCUMENT}: Indicates that the measurement is recorded per document.</li>
 * </ul>
 */
public enum ProfilingScope {
  @JsonProperty("page") PAGE,
  @JsonProperty("document") DOCUMENT;
}
