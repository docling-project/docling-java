package ai.docling.serve.client;

import org.jspecify.annotations.Nullable;

/**
 * Exception thrown when an error occurs during a client request to the Docling Serve API.
 * This exception encapsulates additional details about the HTTP status code and the response
 * body returned by the server, providing more context for the error.
 */
public class DoclingServeClientException extends RuntimeException {
  private final int statusCode;

  @Nullable
  private final String responseBody;

  /**
   * Constructs a new {@code DoclingServeClientException} with an optional cause.
   *
   * @param t the original exception that caused this exception to be thrown. Can be {@code null}.
   */
  public DoclingServeClientException(@Nullable Throwable t) {
    this(t, (t != null) ? t.getMessage() : "An error occurred");
  }

  /**
   * Constructs a new {@code DoclingServeClientException} with the specified detail message.
   *
   * @param t the original exception that caused this exception to be thrown. Can be {@code null}.
   * @param message the detail message, providing information about the exception.
   *                This may include a description of the error condition encountered.
   */
  public DoclingServeClientException(@Nullable Throwable t, String message) {
    super(message, t);
    this.statusCode = -1;
    this.responseBody = null;
  }

  /**
   * Constructs a new {@code DoclingServeClientException} with the specified detail message,
   * HTTP status code, and response body.
   *
   * @param message the detail message, providing context about the exception. This describes
   *                the nature of the error encountered.
   * @param statusCode the HTTP status code returned by the Docling Serve API, indicating
   *                   the outcome or type of error during the client request. A negative
   *                   value indicates no status code is available.
   * @param responseBody the response body returned by the server, containing additional
   *                     details about the error. Can be {@code null} if no response body is provided.
   */
  public DoclingServeClientException(String message, int statusCode, @Nullable String responseBody) {
    super(message);
    this.statusCode = statusCode;
    this.responseBody = responseBody;
  }

  /**
   * Retrieves the HTTP status code associated with this exception.
   *
   * @return the HTTP status code that was returned by the server.
   */
  public int getStatusCode() {
    return this.statusCode;
  }

  /**
   * Retrieves the response body associated with this exception.
   *
   * @return the response body returned by the server as a string.
   */
  public String getResponseBody() {
    return this.responseBody;
  }
}
