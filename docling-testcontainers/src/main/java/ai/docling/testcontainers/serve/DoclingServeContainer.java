package ai.docling.testcontainers.serve;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

import com.github.dockerjava.api.command.InspectContainerResponse;

import ai.docling.testcontainers.serve.config.DoclingServeContainerConfig;

/**
 * A container wrapper class designed to work with the Docling Serve service.
 * The class extends {@link GenericContainer} and provides functionality
 * specific to managing and configuring the Docling container. It allows
 * users to set environment variables, handle port mappings, and configure
 * health checks for the container.
 */
public class DoclingServeContainer extends GenericContainer<DoclingServeContainer> {
  private static final Logger LOG = LoggerFactory.getLogger(DoclingServeContainer.class);

  /**
   * The default container port that docling runs on
   */
  public static final int DEFAULT_DOCLING_PORT = 5001;

  /**
   * The dynamic host name determined from TestContainers
   */
  private final DoclingServeContainerConfig config;

  /**
   * Constructs a new DoclingServeContainer instance with the specified configuration and optional timeout.
   * The container is set up with the necessary environment variables, exposed ports,
   * health check configuration, and an optional timeout for container startup.
   *
   * @param config the configuration details for the Docling container, including image name, environment variables, and other settings
   */
  public DoclingServeContainer(DoclingServeContainerConfig config) {
    super(DockerImageName.parse(config.image()).asCompatibleSubstituteFor(DoclingServeContainerConfig.DOCLING_IMAGE));
    this.config = config;

    // Configure the container
    withExposedPorts(DEFAULT_DOCLING_PORT);
    withEnv(config.containerEnv());
    waitingFor(Wait.forHttp("/health"));

    if (config.enableUi()) {
      withEnv("DOCLING_SERVE_ENABLE_UI", "true");
    }

    withStartupTimeout(config.startupTimeout());

    Optional.ofNullable(config.apiKey())
        .map(String::strip)
        .ifPresent(apiKey -> withEnv("DOCLING_SERVE_API_KEY", apiKey));
  }

  /**
   * Returns the dynamically mapped host port corresponding to the default Docling container port.
   *
   * @return the dynamically mapped port on the host machine for the Docling container
   */
  public int getPort() {
    return getMappedPort(DEFAULT_DOCLING_PORT);
  }

  /**
   * The URL where to access the Docling Serve API.
   */
  public String getApiUrl() {
    return "http://%s:%d".formatted(getHost(), getPort());
  }

  /**
   * The URL where to access the Docling Serve UI, if enabled.
   */
  public Optional<String> getUiUrl() {
    return this.config.enableUi() ?
        Optional.of("%s/ui".formatted(getApiUrl())) :
        Optional.empty();
  }

  @Override
  protected void containerIsStarted(InspectContainerResponse containerInfo) {
    getUiUrl().ifPresent(url -> LOG.info("Docling Serve UI: {}", url));
  }
}
