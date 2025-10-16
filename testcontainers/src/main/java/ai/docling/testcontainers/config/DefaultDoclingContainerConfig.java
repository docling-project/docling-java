package ai.docling.testcontainers.config;

import java.util.Map;
import java.util.Optional;

final class DefaultDoclingContainerConfig implements DoclingContainerConfig {
  private final String imageName;
  private final boolean enableUi;
  private final Map<String, String> containerEnv;

  DefaultDoclingContainerConfig(Builder builder) {
    if ((builder.imageName == null) || builder.imageName.strip().isEmpty()) {
      throw new IllegalArgumentException("imageName must be specified");
    }

    this.enableUi = builder.enableUi;
    this.imageName = builder.imageName;
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
    return this.containerEnv;
  }
}
