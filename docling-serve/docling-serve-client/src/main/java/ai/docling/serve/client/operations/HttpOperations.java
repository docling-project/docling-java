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
   * Executes an HTTP GET request using the details specified in the provided {@code RequestContext}.
   *
   * @param <I> the type of the request payload
   * @param <O> the type of the response object
   * @param requestContext the context containing details such as the URI, request payload,
   *        and expected response type of the GET operation
   * @return an instance of the response type {@code O}, which represents the deserialized response data
   */
  protected abstract <I, O> O executeGet(RequestContext<I, O> requestContext);

  /**
   * Executes an HTTP POST request using the details provided in the {@code RequestContext}.
   * This method is designed to be implemented by subclasses and facilitates sending POST requests
   * with a specified request payload and receiving a deserialized response.
   *
   * @param <I> the type of the request payload
   * @param <O> the type of the response object
   * @param requestContext the context containing details such as the URI, request payload, and
   *        expected response type of the POST operation
   * @return an instance of the response type {@code O}, which represents the deserialized response data
   */
  protected abstract <I, O> O executePost(RequestContext<I, O> requestContext);
}
