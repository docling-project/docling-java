package ai.docling.client.tester.config;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;

@ConfigMapping(prefix = "docling-version-tester")
public interface Config {
  @WithDefault("false")
  boolean logRequests();

  @WithDefault("false")
  boolean logResponses();

  GitHub github();

  interface GitHub {
    IssueCreation issueCreation();

    interface IssueCreation {
      @WithDefault("docling-project")
      String issueOrg();

      @WithDefault("docling-java")
      String issueRepo();

      default String repoPath() {
        return "%s/%s".formatted(issueOrg(), issueRepo());
      }
    }
  }
}
