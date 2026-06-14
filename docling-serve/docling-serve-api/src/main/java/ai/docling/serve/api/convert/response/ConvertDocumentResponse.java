package ai.docling.serve.api.convert.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Abstract response returned by the Convert API for a single conversion request.
 *
 * <p>Serialization uses {@link JsonInclude.Include#NON_EMPTY}, so nulls and empty
 * collections/strings are omitted from JSON output.</p>
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonTypeInfo(
    use = JsonTypeInfo.Id.DEDUCTION
)
@JsonSubTypes({
    @JsonSubTypes.Type(InBodyConvertDocumentResponse.class),
    @JsonSubTypes.Type(PreSignedUrlConvertDocumentResponse.class),
    @JsonSubTypes.Type(PreSignedUrlConvertResponse.class),
    @JsonSubTypes.Type(ZipArchiveConvertDocumentResponse.class)
})
public abstract sealed class ConvertDocumentResponse permits InBodyConvertDocumentResponse, PreSignedUrlConvertDocumentResponse,
    PreSignedUrlConvertResponse, ZipArchiveConvertDocumentResponse {
  /**
   * Type of response
   *
   * @return the response type
   */
  @JsonProperty("response_type")
  public abstract ResponseType getResponseType();

}
