package ai.docling.serve.api.serialization;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DatabindException;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.node.ObjectNode;

import ai.docling.serve.api.convert.response.ConvertDocumentResponse;
import ai.docling.serve.api.convert.response.InBodyConvertDocumentResponse;
import ai.docling.serve.api.convert.response.PreSignedUrlConvertDocumentResponse;
import ai.docling.serve.api.convert.response.PreSignedUrlConvertResponse;
import ai.docling.serve.api.convert.response.ZipArchiveConvertDocumentResponse;

public class Jackson3ConvertDocumentResponseDeserializer extends ValueDeserializer<ConvertDocumentResponse> {

  @Override
  public ConvertDocumentResponse deserialize(JsonParser p, DeserializationContext ctxt) throws JacksonException {
    JsonNode tree = ctxt.readTree(p);

    if (!(tree instanceof ObjectNode node)) {
      throw DatabindException.from(p,
          "Expected a JSON object for ConvertDocumentResponse but got " + tree.getNodeType());
    }

    if (node.has("documents")) {
      return ctxt.readTreeAsValue(node, PreSignedUrlConvertResponse.class);
    } else if (node.has("document")) {
      return ctxt.readTreeAsValue(node, InBodyConvertDocumentResponse.class);
    } else if (node.has("file_name")) {
      return ctxt.readTreeAsValue(node, ZipArchiveConvertDocumentResponse.class);
    } else if (node.has("num_converted")) {
      return ctxt.readTreeAsValue(node, PreSignedUrlConvertDocumentResponse.class);
    }

    throw DatabindException.from(p,
        "Cannot determine ConvertDocumentResponse subtype: none of the expected fields (documents, document, file_name, num_converted) found in JSON");
  }
}
