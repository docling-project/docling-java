package ai.docling.serve.api.serialization;

import java.io.IOException;
import java.time.Instant;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class Jackson2InstantDeserializer extends JsonDeserializer<Instant> {

  @Override
  public Instant deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    String text = p.getValueAsString();
    return (text != null) ? Instant.parse(text) : null;
  }
}
