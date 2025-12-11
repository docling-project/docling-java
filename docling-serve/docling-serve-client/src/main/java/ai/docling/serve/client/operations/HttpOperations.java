package ai.docling.serve.client.operations;

/**
 * Abstract base class for HTTP operations. Provides methods for executing HTTP requests such as GET and POST
 * and deserializing responses into specified types. This class is intended to be extended by subclasses that
 * implement these operations for specific use cases.
 */
public abstract class HttpOperations {
  /**
   * The header name used to specify the API key in HTTP requests.
   * This constant is commonly utilized in authentication mechanisms
   * to include the API key in request headers.
   */
  public static final String API_KEY_HEADER_NAME = "X-Api-Key";

  /**
   * Executes an HTTP GET request to the specified URI and deserializes the response into the given type.
   *
   * @param <I> Parameter type for the request
   * @param <O> the expected return type for the deserialized response.
   * @param uri the URI to send the GET request to.
   * @param request parameters for the request
   * @param expectedReturnType the class representing the type to which the response should be deserialized.
   * @return an instance of the specified type containing the deserialized response data.
   */
  protected abstract <I, O> O executeGet(String uri, I request, Class<O> expectedReturnType);

  /**
   * Executes an HTTP GET request to the specified URI and deserializes
   * the response into the given type.
   *
   * @param <O> the expected return type for the deserialized response.
   * @param uri the URI to send the GET request to. Must not be null.
   * @param expectedReturnType the class representing the type to which
   *        the response should be deserialized. Must not be null.
   * @return an instance of the specified type containing the deserialized
   *         response data.
   */
  protected <O> O executeGet(String uri, Class<O> expectedReturnType) {
    return executeGet(uri, null, expectedReturnType);
  }

  /**
   * Executes an HTTP POST request to the specified URI with the given request payload and deserializes
   * the response into the specified return type.
   *
   * @param <I> Parameter type for the request
   * @param <O> the expected return type for the deserialized response.
   * @param uri the URI to send the POST request to.
   * @param request parameters for the request
   * @param expectedReturnType the class representing the type to which the response should be deserialized.
   * @return an instance of the specified type containing the deserialized response data.
   */
  protected abstract <I, O> O executePost(String uri, I request, Class<O> expectedReturnType);
}
