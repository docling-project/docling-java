package ai.docling.client.tester.service.results;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Path;

import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import io.quarkus.test.junit.QuarkusTest;

import ai.docling.client.tester.domain.TagTestResult;

@QuarkusTest
class MarkdownFileResultsHandlerTests extends BaseFileResultsHandlerTests {
  @Inject
  MarkdownFileResultsHandler handler;

  @Test
  void handlesResults(@TempDir Path tempDir) throws IOException {
    var results = getAndAssertSampleResults();

    this.handler.handleResults(results, tempDir);
    var outputFile = tempDir.resolve("results.md");

    assertThat(EXPECTED_RESUTLS).exists();
    assertThat(outputFile)
        .exists()
        .content()
        .contains(results.registry())
        .contains(results.image())
        .contains(results.timestamp().toString())
        .contains(results.results().stream().map(TagTestResult::serverLogs).toList())
        .contains(results.results().stream().map(r -> r.result().status().toString()).toList());
  }
}
