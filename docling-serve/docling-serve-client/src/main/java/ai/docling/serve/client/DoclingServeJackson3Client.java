package ai.docling.serve.client;

import static ai.docling.serve.api.util.ValidationUtils.ensureNotNull;

import tools.jackson.databind.json.JsonMapper;

/**
 * A client implementation that leverages Jackson 3 for JSON serialization and deserialization.
 *
 * <p>This class extends {@link DoclingServeClient} and provides a concrete implementation for reading
 * and writing JSON data using a {@link JsonMapper} instance.
 */
public class DoclingServeJackson3Client extends DoclingServeClient {
  private final JsonMapper jsonMapper;

  private DoclingServeJackson3Client(Builder builder) {
    super(builder);;
    this.jsonMapper = ensureNotNull(builder.jsonMapperBuilder, "jsonMapperBuilder").build();
  }

  @Override
  public Builder toBuilder() {
    return new Builder(this);
  }

  /**
   * Creates a new {@link Builder} instance for configuring and constructing a {@link DoclingServeJackson3Client}.
   *
   * <p>This method serves as the entry point for creating a new {@link DoclingServeJackson3Client.Builder}
   * to customize and build a {@code DoclingServeJackson3Client}.
   *
   * @return a new {@link Builder} instance
   */
  static Builder builder() {
    return new Builder();
  }

  @Override
  protected <T> T readValue(String json, Class<T> valueType) {
    return this.jsonMapper.readValue(json, valueType);
  }

  @Override
  protected <T> String writeValueAsString(T value) {
    return this.jsonMapper.writeValueAsString(value);
  }

  /**
   * A builder class for configuring and constructing instances of {@link DoclingServeJackson3Client}.
   *
   * <p>This builder extends {@link DoclingServeClientBuilder}, allowing customization of common client
   * parameters such as base URL and HTTP client, while also providing additional functionality
   * specific to JSON mapping via a {@link JsonMapper.Builder}.
   *
   * <p>Instances of this builder can be initialized either as a new builder or based on an
   * existing {@link DoclingServeJackson3Client}.
   *
   * <p>This class is not publicly instantiable; use {@link DoclingServeJackson3Client#builder()}
   * or {@link DoclingServeJackson3Client#toBuilder()} to obtain a builder instance.
   */
  public static final class Builder extends DoclingServeClientBuilder<DoclingServeJackson3Client, Builder> {
    private JsonMapper.Builder jsonMapperBuilder = JsonMapper.builder();

    private Builder() {
    }

    private Builder(DoclingServeJackson3Client doclingClient) {
      super(doclingClient);
      this.jsonMapperBuilder = doclingClient.jsonMapper.rebuild();
    }

    /**
     * Sets the JSON mapper builder to be used for creating the JSON mapper.
     *
     * <p>This allows customization of JSON serialization and deserialization behavior,
     * such as configuring features, modules, or property naming strategies.
     *
     * @param jsonMapperBuilder the {@link JsonMapper.Builder} to use
     * @return this {@link Builder} instance for method chaining
     */
    public Builder jsonParser(JsonMapper.Builder jsonMapperBuilder) {
      this.jsonMapperBuilder = jsonMapperBuilder;
      return this;
    }

    /**
     * Builds and returns a new {@link DoclingServeJackson3Client} instance with the configured properties.
     *
     * <p>This method validates all required parameters and constructs the client.
     *
     * @return a new {@link DoclingServeJackson3Client} instance
     * @throws IllegalArgumentException if any required parameter is null
     */
    @Override
    public DoclingServeJackson3Client build() {
      return new DoclingServeJackson3Client(this);
    }
  }
}
