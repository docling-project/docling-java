package ai.docling.serve.api.serialization;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.BeanProperty;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;

public class Jackson3DurationSerializer extends ValueSerializer<Duration> {
  private final ChronoUnit unit;

  public Jackson3DurationSerializer(ChronoUnit unit) {
    this.unit = unit;
  }

  public Jackson3DurationSerializer() {
    this(ChronoUnit.SECONDS);
  }

  @Override
  public void serialize(Duration value, JsonGenerator gen, SerializationContext ctxt) throws JacksonException {
    if (value == null) {
      gen.writeNull();
      return;
    }

    switch (this.unit) {
      case NANOS:
        gen.writeNumber(value.toNanos());
        break;

      case MICROS:
        gen.writeNumber(value.toNanos() / 1000);
        break;

      case MILLIS:
        gen.writeNumber(value.toMillis());
        break;

      case MINUTES:
        gen.writeNumber(value.toMinutes());
        break;

      case HOURS:
        gen.writeNumber(value.toHours());
        break;

      case DAYS:
        gen.writeNumber(value.toDays());
        break;

      case SECONDS:
      default:
        gen.writeNumber(value.getSeconds());
        break;
    }
  }

  @Override
  public ValueSerializer<?> createContextual(SerializationContext ctxt, BeanProperty property) {
    return Optional.ofNullable(property)
        .map(p -> p.getAnnotation(DurationSerializationFormat.class))
        .map(annotation -> new Jackson3DurationSerializer(annotation.value()))
        .orElse(this);
  }
}
