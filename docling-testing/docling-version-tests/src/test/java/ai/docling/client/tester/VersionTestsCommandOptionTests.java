package ai.docling.client.tester;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Singleton;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import io.quarkus.test.junit.main.Launch;
import io.quarkus.test.junit.main.LaunchResult;
import io.quarkus.test.junit.main.QuarkusMainTest;

import ai.docling.client.tester.VersionTestsCommandOptionTests.NoActualTestingProfile;
import ai.docling.client.tester.domain.TagsTestRequest;
import ai.docling.client.tester.domain.TagsTestResults;
import ai.docling.client.tester.service.TagsTester;

@QuarkusMainTest
@TestProfile(NoActualTestingProfile.class)
class VersionTestsCommandOptionTests {
  @Test
  @Launch({ "-t", "v1.0.0", "--tags", "v1.0.1", "-o", "build/results" })
  void providedTags(LaunchResult result) {
    assertThat(result.getOutput())
        .isNotNull()
        .isNotEmpty()
        .contains("Going to test tags: [v1.0.0, v1.0.1]");
  }

  @Test
  @Launch({ "-o", "build/results" })
  void noTagsProvided(LaunchResult result) {
    assertThat(result.getOutput())
        .isNotNull()
        .isNotEmpty()
        .contains("Going to test tags: [v0.6.0, v0.7.0, v0.8.0, v0.9.0, v0.10.0, v0.10.1, v0.11.0, v0.12.0, v0.13.0, v0.14.0, v0.15.0, v0.16.0, v0.16.1, v1.0.0, v1.0.1, v1.1.0, v1.2.0, v1.2.1, v1.2.2, v1.3.0, v1.3.1, v1.4.0, v1.4.1, v1.5.0, v1.5.1, v1.6.0, v1.7.0, v1.7.1, v1.7.2]")
        .contains("GitHub issue creation is disabled. Skipping.");
  }

  @Test
  @Launch({ "-o", "build/results", "-e", "^v0.*" })
  void noTagsProvidedWithExclusionFilter(LaunchResult result) {
    assertThat(result.getOutput())
        .isNotNull()
        .isNotEmpty()
        .contains("Going to test tags: [v1.0.0, v1.0.1, v1.1.0, v1.2.0, v1.2.1, v1.2.2, v1.3.0, v1.3.1, v1.4.0, v1.4.1, v1.5.0, v1.5.1, v1.6.0, v1.7.0, v1.7.1, v1.7.2]")
        .contains("GitHub issue creation is disabled. Skipping.");
  }

  @Test
  @Launch({ "-o", "build/results" })
  void dontCreateGithubIssue(LaunchResult result) {
    assertThat(result.getOutput())
        .isNotNull()
        .isNotEmpty()
        .contains("GitHub issue creation is disabled. Skipping.");
  }

  public static class NoActualTestingProfile implements QuarkusTestProfile {
    @Override
    public Set<Class<?>> getEnabledAlternatives() {
      return Set.of(NoopTagsTester.class);
    }
  }

  @Singleton
  @Alternative
  public static class NoopTagsTester extends TagsTester {
    @Override
    public TagsTestResults testTags(TagsTestRequest request) {
      return TagsTestResults.from(request).build();
    }
  }
}
