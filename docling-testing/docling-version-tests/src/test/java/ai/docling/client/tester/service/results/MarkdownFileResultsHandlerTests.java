package ai.docling.client.tester.service.results;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import io.quarkus.test.junit.QuarkusTest;

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
        .hasContent(Files.readString(EXPECTED_RESUTLS));
  }
}
