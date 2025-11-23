package ai.docling.testcontainers.serve;

import java.util.logging.Logger;

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
  private static final Logger LOG = Logger.getLogger(DoclingServeContainer.class.getName());

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
    return "http://" + getHost() + ":" + getPort();
  }

  /**
   * The URL where to access the Docling Serve UI, if enabled.
   */
  public String getUiUrl() {
    return getApiUrl() + "/ui";
  }

  @Override
  protected void containerIsStarted(InspectContainerResponse containerInfo) {
    if (config.enableUi()) {
      LOG.info(() -> "Docling Serve UI: %s".formatted(getUiUrl()));
    }
  }

}
