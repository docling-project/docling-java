package ai.docling.client.tester.service.results;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Set;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import ai.docling.client.tester.domain.TagTestResult;
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

    return filterOutTestClassFromStackTraces(results);
  }

  protected TagsTestResults filterOutTestClassFromStackTraces(TagsTestResults results) {
    var filteredResults = results.results().stream()
        .map(result -> filterResultStackTrace(result))
        .toList();

    return results.toBuilder()
        .setResults(filteredResults)
        .build();
  }

  private TagTestResult filterResultStackTrace(TagTestResult result) {
    if (result.result().error() == null) {
      return result;
    }

    Throwable filteredError = cloneAndFilterThrowable(result.result().error());

    return result.toBuilder()
        .result(new TagTestResult.Result(
            result.result().status(),
            result.result().message(),
            filteredError
        ))
        .build();
  }

  private Throwable cloneAndFilterThrowable(Throwable original) {
    var classNamesToRemove = Set.of(BaseFileResultsHandlerTests.class.getName(), getClass().getName());

    // Filter the stack trace
    var filteredStackTrace = Arrays.stream(original.getStackTrace())
        .filter(element -> !classNamesToRemove.contains(element.getClassName()))
        .toArray(StackTraceElement[]::new);

    original.setStackTrace(filteredStackTrace);

    // Recursively filter cause
    if (original.getCause() != null) {
      cloneAndFilterThrowable(original.getCause());
    }

    // Recursively filter suppressed exceptions
    Arrays.stream(original.getSuppressed())
        .forEach(this::cloneAndFilterThrowable);

    return original;
  }
}
