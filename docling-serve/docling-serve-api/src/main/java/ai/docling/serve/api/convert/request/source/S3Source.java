package ai.docling.serve.api.convert.request.source;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a data source located in S3.
 * This class provides functionality for accessing objects stored in an S3 bucket.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@tools.jackson.databind.annotation.JsonDeserialize(builder = S3Source.Builder.class)
@lombok.extern.jackson.Jacksonized
@lombok.Builder(toBuilder = true)
@lombok.Getter
@lombok.ToString
public final class S3Source extends Source {
  /**
   * S3 service endpoint, without protocol.
   *
   * @param endpoint S3 service endpoint, without protocol.
   * @return the S3 service endpoint, without protocol.
   */
  @JsonProperty("endpoint")
  @lombok.NonNull
  private String endpoint;

  /**
   * S3 access key
   * @param accessKey S3 access key
   * @return S3 access key
   */
  @JsonProperty("access_key")
  @lombok.NonNull
  private String accessKey;

  /**
   * S3 secret key
   * @param secretKey S3 secret key
   * @return S3 secret key
   */
  @JsonProperty("secret_key")
  @lombok.NonNull
  private String secretKey;

  /**
   * S3 bucket name
   * @param bucket S3 bucket name
   * @return S3 bucket name
   */
  @JsonProperty("bucket")
  @lombok.NonNull
  private String bucket;

  /**
   * Prefix for the object keys on s3
   * @param keyPrefix Prefix for the object keys on s3
   * @return Prefix for the object keys on s3
   */
  @JsonProperty("key_prefix")
  private String keyPrefix;

  /**
   * If enabled, SSL will be used to connect to s3. Defaults to {@code true}.
   *
   * @param verifySsl If enabled, SSL will be used to connect to s3. Defaults to {@code true}.
   * @return If enabled, SSL will be used to connect to s3. Defaults to {@code true}.
   */
  @JsonProperty("verify_ssl")
  @lombok.Builder.Default
  private boolean verifySsl = true;

  /**
   * Maximum number of elements to read from S3. When set, limits the number of objects processed.
   * Useful for batch conversions where the S3 source may contain a large number of objects.
   *
   * @param maxNumElements Maximum number of elements to read from S3.
   * @return Maximum number of elements to read from S3.
   */
  @JsonProperty("max_num_elements")
  @org.jspecify.annotations.Nullable
  private Integer maxNumElements;

  @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
  public static class Builder { }
}
