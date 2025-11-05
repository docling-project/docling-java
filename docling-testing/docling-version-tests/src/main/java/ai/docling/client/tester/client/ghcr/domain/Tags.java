package ai.docling.client.tester.client.ghcr.domain;

import java.util.Comparator;
import java.util.List;

import org.semver4j.Semver;

public record Tags(String name, List<String> tags) implements ai.docling.client.tester.domain.Tags {
  /**
   * Gets tags that match the pattern {@code v<number>.<number>[optionally .<number>]}
   */
  @Override
  public List<String> getVersionTags() {
    return this.tags.stream()
        .filter(tag -> tag.matches("v\\d+\\.\\d+(\\.\\d+)?"))
        .sorted(Comparator.comparing(v -> new Semver(v.substring(1))))
        .toList();
  }
}
