package ai.docling.serve.api.serialization;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.temporal.ChronoUnit;

/**
 * Annotation to specify the formatting unit for durations.
 * This annotation can be applied to fields to indicate the {@link ChronoUnit}
 * that governs how durations should be interpreted or formatted.
 * <p>
 *   You would use it like this:
 *   <pre>{@code
 *   @JsonProperty("timeout")
 *   @JsonSerialize(using = Jackson2DurationSerializer.class)
 *   @tools.jackson.databind.annotation.JsonSerialize(using = Jackson3DurationSerializer.class)
 *   @DurationSerializationFormat(ChronoUnit.SECONDS)
 *   @Nullable
 *   private Duration timeout;
 *   }
 *   </pre>
 * @see Jackson2DurationSerializer
 * @see Jackson3DurationSerializer
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DurationSerializationFormat {
  /**
   * Specifies the unit of time to be used for formatting durations.
   *
   * @return the {@link ChronoUnit} representing the unit of time for formatting durations
   */
  ChronoUnit value();
}
