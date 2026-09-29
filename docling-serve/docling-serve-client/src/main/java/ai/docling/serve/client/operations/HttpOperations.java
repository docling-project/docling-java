package ai.docling.serve.client.operations;

/**
 * Abstract base class for HTTP operations. Provides methods for executing HTTP requests such as GET and POST
 * and deserializing responses into specified types. This class is intended to be extended by subclasses that
 * implement these operations for specific use cases.
 */
public abstract class HttpOperations {

  /**
   * Content-Type header key
   */
  public static final String CONTENT_TYPE_HEADER = "Content-Type";
  /**
   * Content-Type header value for JSON body
   */
  public static final String CONTENT_TYPE_JSON = "application/json";

  /**
   * Content-Type header value for ZIP binary body
   */
  public static final String CONTENT_TYPE_ZIP = "application/zip";

  /**
   * The header name used to specify the API key in HTTP requests.
   * This constant is commonly utilized in authentication mechanisms
   * to include the API key in request headers.
   */
  public static final String API_KEY_HEADER_NAME = "X-Api-Key";

  /**
   * Executes an HTTP GET request using the details specified in the provided {@code RequestContext}.
   *
   * @param <I>            the type of the request payload
   * @param <O>            the type of the response object
   * @param requestContext the context containing details such as the URI, request payload,
   *                       and expected response type of the GET operation
   * @return an instance of the response type {@code O}, which represents the deserialized response data
   */
  protected abstract <I, O> O executeGet(RequestContext<I, O> requestContext);

  /**
   * Executes an HTTP GET request using the details specified in the provided {@code RequestContext}.
   *
   * @param <I>            the type of the request payload
   * @param requestContext the context containing details such as the URI, request payload,
   *                       and expected response type of the GET operation
   * @return an instance of the {@link StreamResponse}, which represents the response.
   */
  protected abstract <I> StreamResponse executeGetWithStreamResponse(RequestContext<I, StreamResponse> requestContext);

  /**
   * Executes an HTTP POST request using the details provided in the {@code RequestContext}.
   * This method is designed to be implemented by subclasses and facilitates sending POST requests
   * with a specified request payload and receiving a deserialized response.
   *
   * @param <I>            the type of the request payload
   * @param <O>            the type of the response object
   * @param requestContext the context containing details such as the URI, request payload, and
   *                       expected response type of the POST operation
   * @return an instance of the response type {@code O}, which represents the deserialized response data
   */
  protected abstract <I, O> O executePost(RequestContext<I, O> requestContext);

  /**
   * Executes an HTTP POST request using the details provided in the {@code RequestContext}.
   * This method is designed to be implemented by subclasses and facilitates sending POST requests
   * with a specified request payload and receiving a stream response.
   *
   * @param <I>            the type of the request payload
   * @param requestContext the context containing details such as the URI, request payload, and
   *                       expected response type of the POST operation
   * @return an instance of the {@link StreamResponse}, which represents the response.
   */
  protected abstract <I> StreamResponse executePostWithStreamResponse(RequestContext<I, StreamResponse> requestContext);

  /**
   * Reads and deserializes the given JSON string into an instance of the specified type.
   *
   * @param json      the JSON string to deserialize; must not be {@code null}
   * @param valueType the {@link Class} of the target type; must not be {@code null}
   * @param <T>       the type of the object to be deserialized
   * @return an instance of {@code T} deserialized from the provided JSON
   * @throws ai.docling.serve.client.JsonReadException if {@code json} is not valid JSON, or does not match {@code valueType}
   */
  protected abstract <T> T readValue(String json, Class<T> valueType);
}
