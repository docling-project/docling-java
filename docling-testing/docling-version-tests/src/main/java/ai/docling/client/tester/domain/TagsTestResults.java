package ai.docling.client.tester.domain;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import ai.docling.client.tester.domain.TagTestResult.Result.Status;

public record TagsTestResults(String registry, String image, List<TagTestResult> results, Instant timestamp) {
  public TagsTestResults {
      if (timestamp == null) {
        timestamp = Instant.now();
      }

      results = Optional.ofNullable(results)
          .map(r -> r.stream().sorted().toList())
          .orElseGet(List::of);
  }

  public TagsTestResults(String registry, String image, List<TagTestResult> results) {
    this(registry, image, results, Instant.now());
  }

  public TagsTestResults(Builder builder) {
    this(builder.registry, builder.image, List.copyOf(builder.results), builder.timestamp);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static Builder from(TagsTestRequest request) {
    return new Builder(request);
  }

  public Builder toBuilder() {
    return new Builder(this);
  }

  public boolean hasAtLeastOneFailure() {
    return results.stream()
        .anyMatch(result -> result.result().status() == Status.FAILURE);
  }

  public static class Builder {
    private String registry;
    private String image;
    private final List<TagTestResult> results = new ArrayList<>();
    private Instant timestamp;

    private Builder() {

    }

    private Builder(TagsTestRequest request) {
      this.registry = request.registry();
      this.image = request.image();
    }

    private Builder(TagsTestResults results) {
      this.registry = results.registry();
      this.image = results.image();
      this.results.addAll(results.results());
      this.timestamp = results.timestamp();
    }

    public Builder registry(String registry) {
      this.registry = registry;
      return this;
    }

    public Builder image(String image) {
      this.image = image;
      return this;
    }

    public Builder addResult(TagTestResult result) {
      this.results.add(result);
      return this;
    }

    public Builder setResults(List<TagTestResult> results) {
      clearResults();
      this.results.addAll(results);
      return this;
    }

    public Builder clearResults() {
      this.results.clear();
      return this;
    }

    public TagsTestResults build() {
      return new TagsTestResults(this);
    }
  }
}
