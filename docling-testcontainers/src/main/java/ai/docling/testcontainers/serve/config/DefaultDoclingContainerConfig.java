package ai.docling.testcontainers.serve.config;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

final class DefaultDoclingContainerConfig implements DoclingServeContainerConfig {
  private final String imageName;
  private final boolean enableUi;
  private final Map<String, String> containerEnv;
  private final Duration startupTimeout;

  DefaultDoclingContainerConfig(Builder builder) {
    if ((builder.imageName == null) || builder.imageName.strip().isEmpty()) {
      throw new IllegalArgumentException("imageName must be specified");
    }

    this.enableUi = builder.enableUi;
    this.imageName = builder.imageName;
    this.startupTimeout = Optional.ofNullable(builder.startupTimeout)
        .orElse(Duration.ofMinutes(1));
    this.containerEnv = Optional.ofNullable(builder.containerEnv)
        .map(Map::copyOf)
        .orElseGet(Map::of);
  }

  @Override
  public String imageName() {
    return this.imageName;
  }

  @Override
  public boolean enableUi() {
    return this.enableUi;
  }

  @Override
  public Map<String, String> containerEnv() {
    return Collections.unmodifiableMap(this.containerEnv);
  }

  @Override
  public Duration startupTimeout() {
    return this.startupTimeout;
  }
}
