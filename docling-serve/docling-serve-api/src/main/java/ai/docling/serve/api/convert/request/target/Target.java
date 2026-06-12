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
 * {@link InBodyTarget}, {@link PutTarget}, {@link S3Target}, {@link ZipTarget}, and
 * {@link PresignedUrlTarget}. These implementations specify different delivery methods, such as
 * including the document in the response body, sending it to a specified URI, zipping it for
 * inclusion in the response, or returning server-managed presigned URLs that point at the
 * docling-serve operator's object storage.
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
    @Type(value = ZipTarget.class, name = "zip"),
    @Type(value = S3Target.class, name = "s3"),
    @Type(value = PresignedUrlTarget.class, name = "presigned_url")
})
@lombok.Getter
@lombok.ToString
public abstract sealed class Target permits InBodyTarget, PresignedUrlTarget, PutTarget, S3Target, ZipTarget {
  enum Kind {
    @JsonProperty("inbody") INBODY,
    @JsonProperty("put") PUT,
    @JsonProperty("zip") ZIP,
    @JsonProperty("s3") S3,
    @JsonProperty("presigned_url") PRESIGNED_URL
  }
}
