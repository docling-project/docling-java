package ai.docling.serve.api.convert.request.target;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Represents an abstract target for defining where and how the converted document should be delivered.
 *
 * <p>The {@link Target} class is a sealed type that is extended by specific concrete implementations:
 * {@link InBodyTarget}, {@link PutTarget}, and {@link ZipTarget}. These implementations specify different
 * delivery methods, such as including the document in the response body, sending it to a specified URI, or
 * zipping it for inclusion in the response.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "kind"
)
@JsonSubTypes({
    @Type(value = InBodyTarget.class, name = "inbody"),
    @Type(value = PutTarget.class, name = "put"),
    @Type(value = ZipTarget.class, name = "zip")
})
@lombok.Getter
@lombok.ToString
public sealed abstract class Target permits InBodyTarget, PutTarget, ZipTarget {
  enum Kind {
    @JsonProperty("inbody") INBODY,
    @JsonProperty("put") PUT,
    @JsonProperty("zip") ZIP
  }
}
