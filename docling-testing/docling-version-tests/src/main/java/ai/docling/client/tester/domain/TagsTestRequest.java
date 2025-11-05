package ai.docling.client.tester.domain;

import java.util.List;
import java.util.concurrent.Executor;

public record TagsTestRequest(String registry, String image, Executor executor, List<String> tags) {
  public TagsTestRequest {
    if (executor == null) {
      throw new IllegalArgumentException("executor cannot be null");
    }
  }

  private TagsTestRequest(Builder builder) {
    this(builder.registry, builder.image, builder.executor, builder.tags);
  }

  public Builder toBuilder() {
    return new Builder(this);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private String registry;
    private String image;
    private Executor executor;
    private List<String> tags;

    private Builder() {

    }

    private Builder(TagsTestRequest request) {
      this.registry = request.registry();
      this.image = request.image();
      this.executor = request.executor();
      this.tags = request.tags();
    }

    public Builder registry(String registry) {
      this.registry = registry;
      return this;
    }

    public Builder image(String image) {
      this.image = image;
      return this;
    }

    public Builder tags(List<String> tags) {
      this.tags = tags;
      return this;
    }

    public Builder executor(Executor executor) {
      this.executor = executor;
      return this;
    }

    public TagsTestRequest build() {
      return new TagsTestRequest(this);
    }
  }
}
