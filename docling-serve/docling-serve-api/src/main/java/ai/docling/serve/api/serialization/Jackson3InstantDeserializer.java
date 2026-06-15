package ai.docling.serve.api.serialization;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;

import java.time.Instant;

public class Jackson3InstantDeserializer extends ValueDeserializer<Instant> {

  @Override
  public Instant deserialize(JsonParser p, DeserializationContext ctxt) throws JacksonException {
    String text = p.getValueAsString();
    return (text != null) ? Instant.parse(text) : null;
  }
}
