package ai.docling.client.tester.service.results;

import java.io.IOException;
import java.nio.file.Path;

import org.kohsuke.github.GitHubBuilder;

import io.quarkus.logging.Log;

import ai.docling.client.tester.config.Config;
import ai.docling.client.tester.domain.TagsTestResults;

public class GithubIssueResultsHandler implements ResultsHandler {
  private final Config config;
  private final MarkdownFileResultsHandler markdownHandler;
  private final boolean createGithubIssueFromConfig;

  public GithubIssueResultsHandler(Config config, MarkdownFileResultsHandler markdownHandler, boolean createGithubIssueFromConfig) {
    this.config = config;
    this.markdownHandler = markdownHandler;
    this.createGithubIssueFromConfig = createGithubIssueFromConfig;
  }

  @Override
  public void handleResults(TagsTestResults results, Path outputDirectory) {
    // Only do anything if the user configured it to
    if (this.createGithubIssueFromConfig) {
      // We only want to create a GH issue if there is at least one result with a failure
      if (results.hasAtLeastOneFailure()) {
        Log.info("At least one test failure detected. Creating GitHub issue.");
        var markdown = this.markdownHandler.generateMarkdown(results);

        try {
          createGHIssue(results, markdown);
        }
        catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
      else {
        Log.info("No test failures detected. Skipping GitHub issue creation.");
      }
    }
    else {
      Log.info("GitHub issue creation is disabled. Skipping.");
    }
  }

  protected void createGHIssue(TagsTestResults results, String markdown) throws IOException {
    var github = new GitHubBuilder().withOAuthToken(System.getenv("GITHUB_TOKEN")).build();
    var repo = github.getRepository(this.config.github().issueCreation().repoPath());
    var body = """
        At least one failure against a Docling Serve version was detected.
        
        %s
        """.formatted(markdown);

    var issue = repo.createIssue("[Docling Serve Testing] - Error testing version(s) against Docling Serve")
        .label("automation")
        .label("area:docling-serve")
        .body(body)
        .create();

    Log.infof("Created GitHub issue: %s", issue.getHtmlUrl());
  }
}
