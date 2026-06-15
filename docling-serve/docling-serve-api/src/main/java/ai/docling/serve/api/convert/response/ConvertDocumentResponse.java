package ai.docling.serve.api.convert.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import ai.docling.serve.api.serialization.Jackson2ConvertDocumentResponseDeserializer;
import ai.docling.serve.api.serialization.Jackson3ConvertDocumentResponseDeserializer;

/**
 * Abstract response returned by the Convert API for a single conversion request.
 *
 * <p>Deserialization uses explicit custom deserializers that dispatch to the correct
 * concrete subtype based on distinguishing JSON fields.</p>
 *
 * <p>Serialization uses {@link JsonInclude.Include#NON_EMPTY}, so nulls and empty
 * collections/strings are omitted from JSON output.</p>
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@com.fasterxml.jackson.databind.annotation.JsonDeserialize(using = Jackson2ConvertDocumentResponseDeserializer.class)
@tools.jackson.databind.annotation.JsonDeserialize(using = Jackson3ConvertDocumentResponseDeserializer.class)
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
