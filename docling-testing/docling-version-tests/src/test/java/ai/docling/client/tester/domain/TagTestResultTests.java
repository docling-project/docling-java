package ai.docling.client.tester.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.Test;

import ai.docling.client.tester.domain.TagTestResult.Result;
import ai.docling.client.tester.domain.TagTestResult.Result.Status;

class TagTestResultTests {

    @Test
    void shouldCreateTagTestResultWithBuilder() {
        // Test TagTestResult.Builder
        var result = TagTestResult.builder()
                .tag("v1.0.0")
                .result(Result.success("Test passed"))
                .serverLogs("Server log output")
                .build();

        assertThat(result.tag()).isEqualTo("v1.0.0");
        assertThat(result.result().status()).isEqualTo(Status.SUCCESS);
        assertThat(result.result().message()).isEqualTo("Test passed");
        assertThat(result.serverLogs()).isEqualTo("Server log output");
    }

    @Test
    void shouldUseTagTestResultToBuilder() {
        // Test TagTestResult.toBuilder()
        var original = TagTestResult.builder()
                .tag("v1.0.0")
                .result(Result.success("Original"))
                .serverLogs("Original logs")
                .build();

        var modified = original.toBuilder()
                .tag("v2.0.0")
                .result(Result.success("Modified"))
                .build();

        assertThat(original.tag()).isEqualTo("v1.0.0");
        assertThat(original.result().message()).isEqualTo("Original");
        assertThat(original.serverLogs()).isEqualTo("Original logs");

        assertThat(modified.tag()).isEqualTo("v2.0.0");
        assertThat(modified.result().message()).isEqualTo("Modified");
        assertThat(modified.serverLogs()).isEqualTo("Original logs");
    }

    @Test
    void shouldCreateSuccessResult() {
        // Test Result.success()
        var result = Result.success("Operation successful");

        assertThat(result.status()).isEqualTo(Status.SUCCESS);
        assertThat(result.message()).isEqualTo("Operation successful");
        assertThat(result.fullStackTrace()).isNull();
    }

    @Test
    void shouldCreateFailureResultWithThrowable() {
        // Test Result.failure(Throwable)
        var exception = new RuntimeException("Test error");
        var result = Result.failure(exception);

        assertThat(result.status()).isEqualTo(Status.FAILURE);
        assertThat(result.message()).isEqualTo("Test error");
        assertThat(result.fullStackTrace()).isNotNull();
        assertThat(result.fullStackTrace()).contains("RuntimeException");
        assertThat(result.fullStackTrace()).contains("Test error");
    }

    @Test
    void shouldCreateFailureResultWithMessageAndThrowable() {
        // Test Result.failure(String, Throwable)
        var exception = new RuntimeException("Original error");
        var result = Result.failure("Custom message", exception);

        assertThat(result.status()).isEqualTo(Status.FAILURE);
        assertThat(result.message()).isEqualTo("Custom message");
        assertThat(result.fullStackTrace()).isNotNull();
        assertThat(result.fullStackTrace()).contains("RuntimeException");
        assertThat(result.fullStackTrace()).contains("Original error");
    }

    @Test
    void shouldHandleNullThrowableInFailure() {
        // Test Result.failure with null throwable (edge case)
        var result = Result.failure("Error message", null);

        assertThat(result.status()).isEqualTo(Status.FAILURE);
        assertThat(result.message()).isEqualTo("Error message");
        assertThat(result.fullStackTrace()).isEmpty();
    }

    @Test
    void shouldCompareTagTestResultsCorrectly() {
        // Test compareTo() method
        var result1 = TagTestResult.builder()
                .tag("v1.0.0")
                .result(Result.success("Success"))
                .build();

        var result2 = TagTestResult.builder()
                .tag("v2.0.0")
                .result(Result.success("Success"))
                .build();

        var result3 = TagTestResult.builder()
                .tag("v1.5.0")
                .result(Result.success("Success"))
                .build();

        // Higher versions should come first (descending order)
        assertThat(result2.compareTo(result1)).isLessThan(0);
        assertThat(result1.compareTo(result2)).isGreaterThan(0);
        assertThat(result3.compareTo(result1)).isLessThan(0);
        assertThat(result3.compareTo(result2)).isGreaterThan(0);
    }

    @Test
    void shouldHandleTagsWithoutVPrefix() {
        // Test version comparison without 'v' prefix
        var result1 = TagTestResult.builder()
                .tag("1.0.0")
                .result(Result.success("Success"))
                .build();

        var result2 = TagTestResult.builder()
                .tag("2.0.0")
                .result(Result.success("Success"))
                .build();

        assertThat(result2.compareTo(result1)).isLessThan(0);
    }

    @Test
    void shouldGetStatusIcon() {
        // Test Status.getIcon()
        assertThat(Status.SUCCESS.getIcon()).isEqualTo("✅");
        assertThat(Status.FAILURE.getIcon()).isEqualTo("❌");
    }

    @Test
    void shouldCreateTagTestResultWithNullServerLogs() {
        // Test builder with null server logs
        var result = TagTestResult.builder()
                .tag("v1.0.0")
                .result(Result.success("Success"))
                .serverLogs(null)
                .build();

        assertThat(result.serverLogs()).isNull();
    }

    @Test
    void shouldCreateTagTestResultWithMinimalBuilder() {
        // Test builder with only required fields
        var result = TagTestResult.builder()
                .tag("v1.0.0")
                .result(Result.success("Success"))
                .build();

        assertThat(result.tag()).isEqualTo("v1.0.0");
        assertThat(result.result()).isNotNull();
        assertThat(result.serverLogs()).isNull();
    }

    @Test
    void shouldHandleExceptionWithNestedCause() {
        // Test getFullStackTrace with nested exceptions
        var cause = new IllegalStateException("Root cause");
        var exception = new RuntimeException("Wrapper exception", cause);
        var result = Result.failure("Error occurred", exception);

        assertThat(result.status()).isEqualTo(Status.FAILURE);
        assertThat(result.message()).isEqualTo("Error occurred");
        assertThat(result.fullStackTrace()).isNotNull();
        assertThat(result.fullStackTrace()).contains("RuntimeException");
        assertThat(result.fullStackTrace()).contains("Wrapper exception");
        assertThat(result.fullStackTrace()).contains("IllegalStateException");
        assertThat(result.fullStackTrace()).contains("Root cause");
    }

    @Test
    void shouldHandleExceptionWithLongStackTrace() {
        // Test with a real exception that has a full stack trace
        Exception exception;
        try {
            throw new IllegalArgumentException("Test exception with stack trace");
        } catch (IllegalArgumentException e) {
            exception = e;
        }

        var result = Result.failure(exception);

        assertThat(result.status()).isEqualTo(Status.FAILURE);
        assertThat(result.message()).isEqualTo("Test exception with stack trace");
        assertThat(result.fullStackTrace()).isNotNull();
        assertThat(result.fullStackTrace()).contains("IllegalArgumentException");
        assertThat(result.fullStackTrace()).contains("Test exception with stack trace");
        assertThat(result.fullStackTrace()).contains("at ");
    }

    @Test
    void sortsCorrectly() {
        var results = TagsTestResults.builder()
                .registry("ghcr.io")
                .image("docling-project/docling-serve")
                .addResult(
                        TagTestResult.builder()
                                .tag("v1.1.0")
                                .result(Result.success("Yay"))
                                .build())
                .addResult(
                        TagTestResult.builder()
                                .tag("v0.1.1")
                                .result(Result.success("Yay"))
                                .build())
                .addResult(
                        TagTestResult.builder()
                                .tag("v0.1.0")
                                .result(Result.success("Yay"))
                                .build())
                .addResult(
                        TagTestResult.builder()
                                .tag("v1.1.1")
                                .result(Result.success("Yay"))
                                .build())
                .build();

        assertThat(results.results().stream().map(TagTestResult::tag))
                .hasSize(4)
                .containsExactly("v1.1.1", "v1.1.0", "v0.1.1", "v0.1.0");
    }

    @Test
    void shouldCreateWithThreeParameterConstructor() {
        // Test the constructor TagsTestResults(String, String,List<TagTestResult>)
        var result1 = TagTestResult.builder()
                .tag("v1.0.0")
                .result(Result.success("Success"))
                .build();

        var result2 = TagTestResult.builder()
                .tag("v2.0.0")
                .result(Result.failure(new RuntimeException("Failed")))
                .build();

        var results = new TagsTestResults("ghcr.io", "test-image", List.of(result1, result2));

        assertThat(results.registry()).isEqualTo("ghcr.io");
        assertThat(results.image()).isEqualTo("test-image");
        assertThat(results.results()).hasSize(2);
        assertThat(results.timestamp()).isNotNull();
        assertThat(results.timestamp()).isBeforeOrEqualTo(Instant.now());
    }

    @Test
    void shouldCreateWithThreeParameterConstructorAndNullResults() {
        // Test the constructor with null results list
        var results = new TagsTestResults("registry", "image", null);

        assertThat(results.registry()).isEqualTo("registry");
        assertThat(results.image()).isEqualTo("image");
        assertThat(results.results()).isEmpty();
        assertThat(results.timestamp()).isNotNull();
    }

    @Test
    void shouldDetectFailures() {
        // Test hasAtLeastOneFailure() method
        var successResult = TagTestResult.builder()
                .tag("v1.0.0")
                .result(Result.success("Success"))
                .build();

        var failureResult = TagTestResult.builder()
                .tag("v2.0.0")
                .result(Result.failure(new RuntimeException("Failed")))
                .build();

        var resultsWithFailure = new TagsTestResults("ghcr.io", "test-image", List.of(successResult, failureResult));
        var resultsWithoutFailure = new TagsTestResults("ghcr.io", "test-image", List.of(successResult));

        assertThat(resultsWithFailure.hasAtLeastOneFailure()).isTrue();
        assertThat(resultsWithoutFailure.hasAtLeastOneFailure()).isFalse();
    }

    @Test
    void shouldHandleEmptyResultsList() {
        // Test with empty results list
        var results = new TagsTestResults("registry", "image", List.of());

        assertThat(results.results()).isEmpty();
        assertThat(results.hasAtLeastOneFailure()).isFalse();
    }

    @Test
    void shouldSortResultsInConstructor() {
        // Test that results are sorted when passed to constructor
        var result1 = TagTestResult.builder()
                .tag("v1.0.0")
                .result(Result.success("Success"))
                .build();

        var result2 = TagTestResult.builder()
                .tag("v2.0.0")
                .result(Result.success("Success"))
                .build();

        var result3 = TagTestResult.builder()
                .tag("v1.5.0")
                .result(Result.success("Success"))
                .build();

        // Pass in unsorted order
        var results = new TagsTestResults("registry", "image", List.of(result1, result3, result2));

        // Should be sorted in descending order
        assertThat(results.results().stream().map(TagTestResult::tag))
                .containsExactly("v2.0.0", "v1.5.0", "v1.0.0");
    }

    @Test
    void shouldCreateFromTagsTestRequest() {
        // Test the from() method
        java.util.concurrent.Executor executor = Runnable::run;
        var request = TagsTestRequest.builder()
                .registry("ghcr.io")
                .image("test-image")
                .executor(executor)
                .build();

        var results = TagsTestResults.from(request).build();

        assertThat(results.registry()).isEqualTo("ghcr.io");
        assertThat(results.image()).isEqualTo("test-image");
        assertThat(results.results()).isEmpty();
    }

    @Test
    void shouldUseToBuilder() {
        // Test toBuilder() method
        var original = TagsTestResults.builder()
                .registry("ghcr.io")
                .image("original-image")
                .addResult(
                        TagTestResult.builder()
                                .tag("v1.0.0")
                                .result(Result.success("Success"))
                                .build())
                .build();

        var modified = original.toBuilder()
                .image("modified-image")
                .addResult(
                        TagTestResult.builder()
                                .tag("v2.0.0")
                                .result(Result.success("Success"))
                                .build())
                .build();

        assertThat(original.image()).isEqualTo("original-image");
        assertThat(original.results()).hasSize(1);

        assertThat(modified.registry()).isEqualTo("ghcr.io");
        assertThat(modified.image()).isEqualTo("modified-image");
        assertThat(modified.results()).hasSize(2);
    }

    @Test
    void shouldSetResults() {
        // Test setResults() method
        var result1 = TagTestResult.builder()
                .tag("v1.0.0")
                .result(Result.success("Success"))
                .build();

        var result2 = TagTestResult.builder()
                .tag("v2.0.0")
                .result(Result.success("Success"))
                .build();

        var results = TagsTestResults.builder()
                .registry("ghcr.io")
                .image("test-image")
                .addResult(result1)
                .setResults(List.of(result2))
                .build();

        assertThat(results.results()).hasSize(1);
        assertThat(results.results().get(0).tag()).isEqualTo("v2.0.0");
    }

    @Test
    void shouldClearResults() {
        // Test clearResults() method
        var result = TagTestResult.builder()
                .tag("v1.0.0")
                .result(Result.success("Success"))
                .build();

        var results = TagsTestResults.builder()
                .registry("ghcr.io")
                .image("test-image")
                .addResult(result)
                .clearResults()
                .build();

        assertThat(results.results()).isEmpty();
    }
}