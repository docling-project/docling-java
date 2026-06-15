package ai.docling.serve.api.serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import ai.docling.serve.api.convert.response.ConvertDocumentResponse;
import ai.docling.serve.api.convert.response.InBodyConvertDocumentResponse;
import ai.docling.serve.api.convert.response.PreSignedUrlConvertDocumentResponse;
import ai.docling.serve.api.convert.response.PreSignedUrlConvertResponse;
import ai.docling.serve.api.convert.response.ZipArchiveConvertDocumentResponse;

public class Jackson2ConvertDocumentResponseDeserializer extends JsonDeserializer<ConvertDocumentResponse> {

  @Override
  public ConvertDocumentResponse deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    var codec = p.getCodec();
    if (!(codec instanceof ObjectMapper mapper)) {
      throw JsonMappingException.from(p,
          "Expected ObjectMapper codec for ConvertDocumentResponse deserialization");
    }

    JsonNode tree = mapper.readTree(p);

    if (!(tree instanceof ObjectNode node)) {
      throw JsonMappingException.from(p,
          "Expected a JSON object for ConvertDocumentResponse but got " + tree.getNodeType());
    }

    if (node.has("documents")) {
      return mapper.treeToValue(node, PreSignedUrlConvertResponse.class);
    } else if (node.has("document")) {
      return mapper.treeToValue(node, InBodyConvertDocumentResponse.class);
    } else if (node.has("file_name")) {
      return mapper.treeToValue(node, ZipArchiveConvertDocumentResponse.class);
    } else if (node.has("num_converted")) {
      return mapper.treeToValue(node, PreSignedUrlConvertDocumentResponse.class);
    }

    throw JsonMappingException.from(p,
        "Cannot determine ConvertDocumentResponse subtype: none of the expected fields (documents, document, file_name, num_converted) found in JSON");
  }
}
