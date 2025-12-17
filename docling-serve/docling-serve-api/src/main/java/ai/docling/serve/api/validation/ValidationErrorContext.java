package ai.docling.serve.api.validation;

import org.jspecify.annotations.Nullable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the context information associated with a validation error.
 *
 * This class provides additional details about the validation error,
 * such as the expected schemes relevant to the validation process.
 *
 * The class leverages Jackson annotations for JSON serialization and
 * deserialization, and Lombok annotations to reduce boilerplate code,
 * including getter methods and builder patterns.
 *
 * The builder pattern is implemented through an inner static `Builder` class
 * for flexible and readable object construction.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@tools.jackson.databind.annotation.JsonDeserialize(builder = ValidationErrorContext.Builder.class)
@lombok.extern.jackson.Jacksonized
@lombok.Builder(toBuilder = true)
@lombok.Getter
@lombok.ToString
public class ValidationErrorContext {
  /**
   * Specifies the expected schemes relevant to a validation process.
   *
   * @param expectedSchemes the expected schemes relevant to a validation process
   * @return the expected schemes relevant to a validation process
   */
  @JsonProperty("expected_schemes")
  @Nullable
  private String expectedSchemes;

  /**
   * Builder class for constructing instances of the enclosing class.
   *
   * This class implements the builder pattern, providing a convenient and flexible
   * approach to construct objects of the enclosing class. It allows for a fluent
   * API style by enabling method chaining during object creation.
   */
  @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
  public static class Builder { }
}
