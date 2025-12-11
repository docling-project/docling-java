package ai.docling.testcontainers.serve.config;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import org.jspecify.annotations.Nullable;

final class DefaultDoclingContainerConfig implements DoclingServeContainerConfig {
  private final String image;
  private final boolean enableUi;
  private final Map<String, String> containerEnv;
  private final Duration startupTimeout;

  @Nullable
  private final String apiKey;

  DefaultDoclingContainerConfig(Builder builder) {
    if ((builder.image == null) || builder.image.strip().isEmpty()) {
      throw new IllegalArgumentException("image must be specified");
    }

    this.enableUi = builder.enableUi;
    this.image = builder.image;
    this.apiKey = builder.apiKey;
    this.startupTimeout = Optional.ofNullable(builder.startupTimeout)
        .orElse(Duration.ofMinutes(1));
    this.containerEnv = Optional.ofNullable(builder.containerEnv)
        .map(Map::copyOf)
        .orElseGet(Map::of);
  }

  @Override
  public String image() {
    return this.image;
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

  @Nullable
  @Override
  public String apiKey() {
    return this.apiKey;
  }
}
