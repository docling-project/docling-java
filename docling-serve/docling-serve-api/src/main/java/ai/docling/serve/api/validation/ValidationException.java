package ai.docling.serve.api.validation;

import org.jspecify.annotations.Nullable;

/**
 * Represents an exception that is thrown when validation errors occur.
 *
 * This exception is designed to encapsulate structured validation error details
 * represented by a {@link ValidationError} object. It can provide additional
 * context through an optional message and an optional cause.
 *
 * It extends {@code RuntimeException}, making it an unchecked exception.
 */
public class ValidationException extends RuntimeException {
  @Nullable
  private final ValidationError validationError;

  /**
   * Constructs a new ValidationException with the specified ValidationError.
   *
   * This constructor allows the exception to encapsulate a {@link ValidationError}
   * object for providing detailed information about the validation error that occurred.
   * This instance does not include an additional custom message or cause.
   *
   * @param validationError the {@link ValidationError} containing validation error details,
   *                        or null if no specific validation error details are provided.
   */
  public ValidationException(@Nullable ValidationError validationError) {
    this(validationError, null, null);
  }

  /**
   * Constructs a new ValidationException with the specified ValidationError and cause.
   *
   * This constructor allows the exception to encapsulate a {@link ValidationError}
   * object for providing detailed information about the validation error that occurred,
   * along with an optional cause for contextual information about the exception's origin.
   *
   * @param validationError the {@link ValidationError} containing validation error details,
   *                        or null if no specific validation error details are provided.
   * @param cause           the {@link Throwable} cause of this exception, or null if no
   *                        underlying cause is to be associated with this exception.
   */
  public ValidationException(@Nullable ValidationError validationError, @Nullable Throwable cause) {
    this(validationError, cause, null);
  }

  /**
   * Constructs a new ValidationException with the specified ValidationError and message.
   *
   * This constructor allows the exception to encapsulate a {@link ValidationError}
   * object for providing detailed information about the validation error that occurred,
   * along with an optional custom message describing the error context.
   *
   * @param validationError the {@link ValidationError} containing validation error details,
   *                        or null if no specific validation error details are provided.
   * @param message         the custom message for the exception, or null if no custom message
   *                        is to be associated with this exception.
   */
  public ValidationException(@Nullable ValidationError validationError, @Nullable String message) {
    this(validationError, null, message);
  }

  /**
   * Constructs a new ValidationException with the specified ValidationError, cause, and message.
   *
   * This constructor allows the exception to encapsulate a {@link ValidationError}
   * object for providing detailed information about the validation error that occurred,
   * along with an optional cause and custom message for additional contextual details
   * regarding the exception.
   *
   * @param validationError the {@link ValidationError} containing validation error details,
   *                        or null if no specific validation error details are provided.
   * @param cause           the {@link Throwable} cause of this exception, or null if no
   *                        underlying cause is to be associated with this exception.
   * @param message         the custom message for the exception, or null if no custom message
   *                        is to be associated with this exception.
   */
  public ValidationException(@Nullable ValidationError validationError, @Nullable Throwable cause, @Nullable String message) {
    super(message, cause);
    this.validationError = validationError;
  }

  /**
   * Retrieves the {@link ValidationError} associated with this exception.
   *
   * This method returns the encapsulated {@link ValidationError} that contains
   * detailed information about the validation error. If no specific validation error
   * information is available, this method may return null.
   *
   * @return the {@link ValidationError} associated with this exception, or null if no
   *         validation error details are provided.
   */
  @Nullable
  public ValidationError getValidationError() {
    return this.validationError;
  }
}
