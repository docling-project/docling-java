package ai.docling.serve.client;

/**
 * Abstract base class for HTTP operations. Provides methods for executing HTTP requests such as GET and POST
 * and deserializing responses into specified types. This class is intended to be extended by subclasses that
 * implement these operations for specific use cases.
 */
public abstract class HttpOperations {
  /**
   * Executes an HTTP GET request to the specified URI and deserializes the response into the given type.
   *
   * @param <O> the expected return type for the deserialized response.
   * @param uri the URI to send the GET request to.
   * @param expectedReturnType the class representing the type to which the response should be deserialized.
   * @return an instance of the specified type containing the deserialized response data.
   */
  protected abstract <O> O executeGet(String uri, Class<O> expectedReturnType);

  /**
   * Executes an HTTP POST request to the specified URI with the given request payload and deserializes
   * the response into the specified return type.
   *
   * @param <I> the type of the request payload sent in the POST request.
   * @param <O> the expected return type for the deserialized response.
   * @param uri the URI to send the POST request to.
   * @param request the payload to be sent in the POST request.
   * @param expectedReturnType the class representing the type to which the response should be deserialized.
   * @return an instance of the specified type containing the deserialized response data.
   */
  protected abstract <I, O> O executePost(String uri, I request, Class<O> expectedReturnType);
}
