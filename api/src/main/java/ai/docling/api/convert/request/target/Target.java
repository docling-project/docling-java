package ai.docling.api.convert.request.target;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Target of the document conversion.
 */
public sealed interface Target permits InBodyTarget, PutTarget, ZipTarget {

  enum Kind {

    @JsonProperty("inbody") INBODY,
    @JsonProperty("put") PUT,
    @JsonProperty("zip") ZIP

  }

}
