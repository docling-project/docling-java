package ai.docling.serve.api.validation;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

/**
 * Represents a validation error with customizable serialization and deserialization behavior.
 *
 * This class includes Jackson annotations for JSON handling and Lombok annotations for
 * boilerplate code generation, such as getters and builder patterns.
 *
 * It is designed to be used in scenarios where structured validation error
 * details are required to be captured and processed.
 *
 * An inner static Builder class is provided for constructing ValidationError instances
 * using the builder pattern.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@tools.jackson.databind.annotation.JsonDeserialize(builder = ValidationError.Builder.class)
@lombok.extern.jackson.Jacksonized
@lombok.Builder(toBuilder = true)
@lombok.Getter
@lombok.ToString
public class ValidationError {
  /**
   * A collection of validation error details associated with the validation error.
   *
   * @param errorDetails the collection of validation error details
   * @return the collection of validation error details
   */
  @JsonProperty("detail")
  @JsonSetter(nulls = Nulls.AS_EMPTY)
  @lombok.Singular
  private List<ValidationErrorDetail> errorDetails;

  /**
   * Builder class for constructing instances of the enclosing class.
   *
   * This class is used to implement the builder pattern, enabling the creation
   * of objects with a more readable and flexible initialization format. The builder
   * follows the fluent API design by allowing method chaining.
   */
  @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
  public static class Builder { }
}
