package ai.docling.api.convert.request.source;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Source of the document.
 */
public sealed interface Source permits FileSource, HttpSource {

  enum Kind {

    @JsonProperty("http") HTTP,
    @JsonProperty("file") FILE

  }

}
