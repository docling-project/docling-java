package ai.docling.client.tester.service.results;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectSpy;

import ai.docling.client.tester.domain.TagTestResult;
import ai.docling.client.tester.domain.TagTestResult.Result;
import ai.docling.client.tester.domain.TagsTestRequest;
import ai.docling.client.tester.domain.TagsTestResults;

@QuarkusTest
class GithubIssueResultsHandlerTests extends BaseFileResultsHandlerTests {
  @InjectSpy
  GithubIssueResultsHandler handler;

  @InjectSpy
  MarkdownFileResultsHandler markdownHandler;

  @Test
  void handlesResultsWithFailures(@TempDir Path tempDir) throws IOException {
    var results = getAndAssertSampleResults();

    doNothing()
        .when(this.handler)
        .createGHIssue(eq(results), anyString());

    this.handler.handleResults(results, tempDir);

    verify(this.handler).createGHIssue(eq(results), eq(Files.readString(EXPECTED_RESUTLS)));
    verify(this.markdownHandler).generateMarkdown(results);
    verifyNoMoreInteractions(this.markdownHandler);
  }

  @Test
  void handlesResultsWithoutFailures() throws IOException {
    var allResultsSuccessful = TagsTestResults.from(
            TagsTestRequest.builder()
                .registry("quay.io")
                .image("docling-project/docling-serve")
                .executor(Executors.newSingleThreadExecutor())
                .build()
        )
        .addResult(
            TagTestResult.builder()
                .tag("latest")
                .result(Result.success("Successful!"))
                .build()
        )
        .build();

    assertThat(allResultsSuccessful.hasAtLeastOneFailure()).isFalse();
    this.handler.handleResults(allResultsSuccessful, Path.of(""));

    verifyNoInteractions(this.markdownHandler);
    verify(this.handler, never()).createGHIssue(any(TagsTestResults.class), anyString());
  }
}
