package ai.docling.client.tester.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

import ai.docling.client.tester.service.results.GithubIssueResultsHandler;
import ai.docling.client.tester.service.results.MarkdownFileResultsHandler;

import picocli.CommandLine.ParseResult;

public class GithubResultsHandlerConfig {
  @Produces
  @ApplicationScoped
  GithubIssueResultsHandler githubIssueResultsHandler(Config config, MarkdownFileResultsHandler markdownHandler, ParseResult parseResult) {
    return new GithubIssueResultsHandler(config, markdownHandler, parseResult.matchedOptionValue('g', true));
  }
}
