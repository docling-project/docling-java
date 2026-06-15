package ai.docling.serve.api.serialization;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import ai.docling.serve.api.validation.ValidationErrorDetail;

public class Jackson2ValidationErrorDetailListDeserializer extends JsonDeserializer<List<ValidationErrorDetail>> {

  @Override
  public List<ValidationErrorDetail> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    if (p.currentToken() == JsonToken.VALUE_STRING) {
      return List.of(ValidationErrorDetail.builder().message(p.getText()).build());
    }

    var type = ctxt.getTypeFactory().constructCollectionType(List.class, ValidationErrorDetail.class);
    return ctxt.readValue(p, type);
  }
}
