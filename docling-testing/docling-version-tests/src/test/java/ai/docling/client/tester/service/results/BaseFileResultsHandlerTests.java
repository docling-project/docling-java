package ai.docling.client.tester.service.results;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import ai.docling.client.tester.domain.TagsTestResults;

abstract class BaseFileResultsHandlerTests {
  protected static final Path DOCS_PATH = Path.of("src", "test", "resources", "markdownFileResults");
  protected static final Path EXPECTED_RESUTLS = DOCS_PATH.resolve("expected-results.md");
  protected static final JsonMapper JSON_MAPPER = JsonMapper.builder().addModule(new JavaTimeModule()).build();

  protected TagsTestResults getAndAssertSampleResults() throws IOException {
    var results = JSON_MAPPER.readValue(Files.readString(DOCS_PATH.resolve("sampleResults.json")), TagsTestResults.class);

    assertThat(results)
        .isNotNull()
        .extracting(TagsTestResults::hasAtLeastOneFailure)
        .isEqualTo(true);

    return results;
  }
}
