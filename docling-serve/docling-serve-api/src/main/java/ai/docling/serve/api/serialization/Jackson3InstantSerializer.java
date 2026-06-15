package ai.docling.serve.api.serialization;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;

import java.time.Instant;

public class Jackson3InstantSerializer extends ValueSerializer<Instant> {

  @Override
  public void serialize(Instant value, JsonGenerator gen, SerializationContext ctxt) throws JacksonException {
    if (value == null) {
      gen.writeNull();
      return;
    }

    gen.writeString(value.toString());
  }
}
