package ai.docling.serve.api.serialization;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.core.JsonToken;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;

import java.util.List;

import ai.docling.serve.api.validation.ValidationErrorDetail;

public class Jackson3ValidationErrorDetailListDeserializer extends ValueDeserializer<List<ValidationErrorDetail>> {

  @Override
  public List<ValidationErrorDetail> deserialize(JsonParser p, DeserializationContext ctxt) throws JacksonException {
    if (p.currentToken() == JsonToken.VALUE_STRING) {
      return List.of(ValidationErrorDetail.builder().message(p.getText()).build());
    }

    var type = ctxt.getTypeFactory().constructCollectionType(List.class, ValidationErrorDetail.class);
    return ctxt.readValue(p, type);
  }
}
