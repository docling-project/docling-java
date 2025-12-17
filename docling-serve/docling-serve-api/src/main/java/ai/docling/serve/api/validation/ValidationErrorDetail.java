package ai.docling.serve.api.validation;

import java.util.List;

import org.jspecify.annotations.Nullable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

/**
 * Represents detailed information about a validation error.
 *
 * This class provides structured data associated with a validation error,
 * including the locations of the error, an error message, the type of
 * validation error, and the input causing the error.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@tools.jackson.databind.annotation.JsonDeserialize(builder = ValidationErrorDetail.Builder.class)
@lombok.extern.jackson.Jacksonized
@lombok.Builder(toBuilder = true)
@lombok.Getter
@lombok.ToString
public class ValidationErrorDetail {
  /**
   * Represents the specific locations associated with a validation error.
   *
   * @param loc the specific locations associated with a validation error
   * @return the specific locations associated with a validation error
   */
  @JsonProperty("loc")
  @JsonSetter(nulls = Nulls.AS_EMPTY)
  @lombok.Singular
  private List<Object> locations;

  /**
   * Represents an optional validation error message.
   *
   * @param message the optional validation error message
   * @return the optional validation error message
   */
  @JsonProperty("msg")
  @Nullable
  private String message;

  /**
   * Represents the type of validation error.
   *
   * @param type the type of validation error
   * @return the type of validation error
   */
  @JsonProperty("type")
  @Nullable
  private String type;

  /**
   * Represents the input causing the validation error.
   *
   * @param input the input causing the validation error
   * @return the input causing the validation error
   */
  @JsonProperty("input")
  @Nullable
  private String input;

  /**
   * Represents the context information associated with a validation error.
   *
   * @param context the context information associated with a validation error
   * @return the context information associated with a validation error
   */
  @JsonProperty("ctx")
  @lombok.NonNull
  @lombok.Builder.Default
  private ValidationErrorContext context = ValidationErrorContext.builder().build();

  /**
   * Builder class for constructing instances of the enclosing class.
   *
   * This class is used to implement the builder pattern, providing a flexible
   * and readable mechanism for initializing instances of the enclosing class.
   */
  @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
  public static class Builder { }
}
