package ai.docling.serve.client.operations;

import org.jspecify.annotations.Nullable;

import ai.docling.serve.api.util.ValidationUtils;

public class RequestContext<I, O> {
  private final String uri;
  private final I request;
  private final Class<O> responseType;

  private RequestContext(Builder<I, O> builder) {
    this.uri = ValidationUtils.ensureNotBlank(builder.uri, "uri");
    this.request = builder.request;
    this.responseType = ValidationUtils.ensureNotNull(builder.responseType, "responseType");
  }

  /**
   * Returns the URI for the request.
   *
   * @return the URI string
   */
  public String getUri() {
    return uri;
  }

  /**
   * Returns the request object.
   *
   * @return the request object of type I
   */
  @Nullable
  public I getRequest() {
    return request;
  }

  /**
   * Returns the response type class.
   *
   * @return the Class object representing the response type O
   */
  public Class<O> getResponseType() {
    return responseType;
  }

  /**
   * Creates a new Builder instance for constructing a RequestContext.
   *
   * @param <I> the request type
   * @param <O> the response type
   * @return a new Builder instance
   */
  public static <I, O> Builder<I, O> builder() {
    return new Builder<>();
  }

  /**
   * Creates a new Builder instance initialized with the values from this RequestContext.
   *
   * @return a new Builder instance with values copied from this RequestContext
   */
  public Builder<I, O> toBuilder() {
    return new Builder<>(this);
  }

  public static class Builder<I, O> {
    private String uri;
    private @Nullable I request;
    private Class<O> responseType;

    /**
     * Creates a new empty Builder instance.
     */
    public Builder() {
    }

    /**
     * Creates a new Builder instance initialized with values from the given RequestContext.
     *
     * @param context the RequestContext to copy values from
     */
    public Builder(RequestContext<I, O> context) {
      this.uri = context.uri;
      this.request = context.request;
      this.responseType = context.responseType;
    }

    /**
     * Sets the URI for the request.
     *
     * @param uri the URI string
     * @return this Builder instance for method chaining
     */
    public Builder<I, O> uri(String uri) {
      this.uri = uri;
      return this;
    }

    /**
     * Sets the request object.
     *
     * @param request the request object of type I
     * @return this Builder instance for method chaining
     */
    public Builder<I, O> request(@Nullable I request) {
      this.request = request;
      return this;
    }

    /**
     * Sets the response type class.
     *
     * @param responseType the Class object representing the response type O
     * @return this Builder instance for method chaining
     */
    public Builder<I, O> responseType(Class<O> responseType) {
      this.responseType = responseType;
      return this;
    }

    /**
     * Builds and returns a new RequestContext instance with the configured values.
     *
     * @return a new RequestContext instance
     */
    public RequestContext<I, O> build() {
      return new RequestContext<>(this);
    }
  }
}
