package ai.docling.serve.api.serialization;

import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

public class Jackson2DurationSerializer extends JsonSerializer<Duration> implements ContextualSerializer {
  private final ChronoUnit unit;

  public Jackson2DurationSerializer() {
    this(ChronoUnit.SECONDS);
  }

  public Jackson2DurationSerializer(ChronoUnit unit) {
    this.unit = unit;
  }

  @Override
  public void serialize(Duration value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
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
  public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) {
    return Optional.ofNullable(property)
        .map(p -> p.getAnnotation(DurationSerializationFormat.class))
        .map(annotation -> new Jackson2DurationSerializer(annotation.value()))
        .orElse(this);
  }
}
