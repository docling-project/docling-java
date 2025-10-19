package ai.docling.api.convert.request.target;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Target for sending the converted document to a specified URI.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record PutTarget(@JsonProperty("kind") Kind kind, @JsonProperty("url") URI url) implements Target {

  public PutTarget {
    if (url == null) {
      throw new IllegalArgumentException("url cannot be null");
    }
    kind = Kind.PUT;
  }

  public static PutTarget create(URI url) {
    return new PutTarget(Kind.PUT, url);
  }

}
