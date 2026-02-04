package ai.docling.testcontainers.serve.config;

import java.time.Duration;
import java.util.Map;

import org.jspecify.annotations.Nullable;

/**
 * A configuration interface for defining properties and settings related
 * to the Docling container. This interface provides methods for retrieving
 * configuration options, such as enabling or disabling DevServices, specifying
 * the container image name, toggling the UI, and setting environment variables
 * for the container.
 */
public interface DoclingServeContainerConfig {
  /**
   * Represents the organizational identifier or namespace for the Docling project.
   * This value is used as a constant identifier throughout the configuration or interactions
   * with the Docling-related containers and components.
   */
  String DOCLING_ORG = "docling-project";

  /**
   * The default name of the container image used for the Docling Service.
   */
  String DOCLING_IMAGE_NAME = "docling-serve";

  /**
   * Represents the version identifier for the docling-serve container image.
   */
  String DOCLING_IMAGE_VERSION = "v1.11.0";

  /**
   * Default image name
   */
  String DOCLING_IMAGE = "ghcr.io/" + DOCLING_ORG + "/" + DOCLING_IMAGE_NAME + ":" + DOCLING_IMAGE_VERSION;

  /**
   * The container image to use.
   * <p>
   *   Default value: {@link #DOCLING_IMAGE}
   * </p>
   */
  String image();

  /**
   * Whether or not to enable the docling UI
   * <p>
   * See https://github.com/docling-project/docling-serve?tab=readme-ov-file#demonstration-ui
   * </p>
   */
  boolean enableUi();

  /**
   * Environment variables that are passed to the container
   */
  Map<String, String> containerEnv();

  /**
   * Specifies the maximum duration to wait for the container to fully start up.
   * <p>
   * This value determines the timeout period for the startup process, ensuring
   * that the container is operational within a defined timeframe.
   * </p>
   * <p>
   *   Default Value: 1 minute
   * </p>
   *
   * @return a {@link Duration} representing the startup timeout for the container.
   */
  Duration startupTimeout();

  /**
   * Retrieves the API key associated with the configuration.
   *
   * @return a {@code String} representing the API key.
   */
  @Nullable
  String apiKey();

  /**
   * Creates a new instance of {@link Builder} initialized with the current configuration values.
   *
   * @return a new {@link Builder} instance with the properties of the current configuration.
   */
  default Builder toBuilder() {
    return new Builder(this);
  }

  /**
   * Creates and returns a new instance of the {@link Builder} class.
   *
   * @return a new {@link Builder} instance for configuring and building a {@link DoclingServeContainerConfig}.
   */
  static Builder builder() {
    return new Builder();
  }

  /**
   * A builder class for constructing instances of {@link DoclingServeContainerConfig}.
   * This class provides methods for setting various configurations and properties
   * needed to create a {@link DoclingServeContainerConfig} instance.
   */
  class Builder {
    /**
     * The name of the container image to be used in the configuration.
     * This variable holds the identifier or tag of the container image
     * that will be utilized when creating or configuring container instances.
     * It is a required field and must be set to a non-null, non-empty value.
     */
    protected String image;

    /**
     * A flag indicating whether the Docling UI should be enabled or disabled.
     * When set to {@code true}, the UI will be enabled; otherwise, it will remain disabled.
     * This field is used during the configuration process within the builder to control
     * the activation state of the UI feature in the container.
     */
    protected boolean enableUi;

    /**
     * A map representing the environment variables to be passed to the container.
     * The keys in the map represent the names of the environment variables,
     * and the corresponding values represent their assigned values.
     *
     * This field is used to configure additional environment-specific settings
     * during the container's initialization and execution.
     *
     * Modifications to this map may be reflected in the container's runtime environment,
     * affecting its behavior as defined by the provided environment variables.
     */
    protected Map<String, String> containerEnv;

    /**
     * Specifies the timeout duration allowed for the container startup process.
     * This value helps determine how long the system should wait for the container
     * to become operational before considering the startup process to have failed.
     * <p>
     * The timeout is represented using the {@link Duration} class, which
     * allows precise specification of time intervals.
     * </p>
     */
    protected Duration startupTimeout;

    /**
     * The API key used for authenticating with external services or systems.
     * This key is typically required to enable secure communication and interaction
     * with third-party APIs or services.
     *
     * This field is protected and may be configured through the {@link Builder} class.
     */
    @Nullable
    protected String apiKey;

    /**
     * Initializes a new instance of the {@link Builder} class.
     * This constructor is protected to restrict direct instantiation outside the package
     * and is primarily used for creating builder instances within the {@link DoclingServeContainerConfig} implementation.
     */
    protected Builder() {
    }

    /**
     * Constructs a {@link Builder} instance initialized with the settings from the given {@link DoclingServeContainerConfig}.
     * This constructor populates the builder fields using the corresponding values from the provided configuration.
     *
     * @param config the {@link DoclingServeContainerConfig} instance whose properties will be used to initialize the builder
     */
    protected Builder(DoclingServeContainerConfig config) {
      this.image = config.image();
      this.enableUi = config.enableUi();
      this.containerEnv = config.containerEnv();
      this.startupTimeout = config.startupTimeout();
      this.apiKey = config.apiKey();
    }

    /**
     * Sets the name of the container image to use for the configuration.
     * This value is required and must not be null or empty.
     *
     * @param image the name of the container image
     * @return the builder instance for method chaining
     * @throws IllegalArgumentException if the provided image is null or empty
     */
    public Builder image(String image) {
      this.image = image;
      return this;
    }

    /**
     * Configures whether the Docling UI is enabled or disabled.
     *
     * @param enableUi a boolean indicating whether to enable the Docling UI
     * @return the builder instance for method chaining
     */
    public Builder enableUi(boolean enableUi) {
      this.enableUi = enableUi;
      return this;
    }

    /**
     * Sets the environment variables to be passed to the container.
     *
     * @param containerEnv a map containing the environment variable names and their corresponding values
     * @return the builder instance for method chaining
     */
    public Builder containerEnv(Map<String, String> containerEnv) {
      this.containerEnv = containerEnv;
      return this;
    }

    /**
     * Adds an environment variable to the container's configuration.
     * This method allows setting a single key-value pair representing
     * an environment variable and its value to be passed to the container.
     *
     * @param key the name of the environment variable
     * @param value the value of the environment variable
     * @return the builder instance for method chaining
     * @throws NullPointerException if the key or value is null
     */
    public Builder containerEnv(String key, String value) {
      this.containerEnv.put(key, value);
      return this;
    }

    /**
     * Sets the maximum duration allowed for the container to start.
     *
     * @param startupTimeout the {@link Duration} to wait for the startup to complete
     * @return the {@link Builder} instance for method chaining
     */
    public Builder startupTimeout(Duration startupTimeout) {
      this.startupTimeout = startupTimeout;
      return this;
    }

    /**
     * Sets the API key to be used for the container configuration.
     *
     * @param apiKey the API key as a string; this value is used to authenticate
     *               or authorize the container's operations
     * @return the builder instance for method chaining
     */
    public Builder apiKey(String apiKey) {
      this.apiKey = apiKey;
      return this;
    }

    /**
     * Builds and returns a new instance of {@link DoclingServeContainerConfig} based on the current builder state.
     *
     * @return a newly constructed {@link DoclingServeContainerConfig} instance containing the configuration
     *         properties and values set within this builder.
     */
    public DoclingServeContainerConfig build() {
      return new DefaultDoclingContainerConfig(this);
    }
  }
}
