package ai.docling.serve.client;

/**
 * Exception thrown when a text cannot be read as the JSON value that was expected, either because it is not
 * valid JSON at all (for example an error page from a gateway or proxy in front of Docling Serve), or because
 * it is JSON of another shape.
 *
 * <p>It is what {@link DoclingServeClient#readValue(String, Class)} reports, whichever JSON library the client
 * uses, so that code shared by every client can tell such a failure from any other one, such as a failure of a
 * custom deserializer, without depending on a JSON library. The failure of the JSON library is its
 * {@linkplain #getCause() cause}.
 *
 * <p>It is not a {@link DoclingServeClientException}, which reports the outcome of an HTTP exchange: reading a
 * text as JSON has nothing to say about a status code or a response body.
 */
public class JsonReadException extends RuntimeException {
  /**
   * Constructs a new {@code JsonReadException} for the failure of a JSON library.
   *
   * @param cause the failure of the JSON library to read the text. The message of this exception is its
   *              {@link Throwable#toString() string representation}, which starts with the name of its class
   */
  public JsonReadException(Throwable cause) {
    super(cause);
  }
}
