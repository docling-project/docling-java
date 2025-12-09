package ai.docling.serve.client;

/**
 * Exception thrown when an error occurs during a client request to the Docling Serve API.
 * This exception encapsulates additional details about the HTTP status code and the response
 * body returned by the server, providing more context for the error.
 */
public class DoclingServeClientException extends RuntimeException {
  private final int statusCode;
  private final String responseBody;

  public DoclingServeClientException(String message, int statusCode, String responseBody) {
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
