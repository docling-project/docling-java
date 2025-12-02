package ai.docling.client.tester.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import ai.docling.client.tester.domain.TagTestResult.Result;

class TagTestResultTests {
  @Test
  void sortsCorrectly() {
    var results = TagsTestResults.builder()
        .registry("ghcr.io")
        .image("docling-project/docling-serve")
        .addResult(
            TagTestResult.builder()
                .tag("v1.1.0")
                .result(Result.success("Yay"))
                .build()
        )
        .addResult(
            TagTestResult.builder()
                .tag("v0.1.1")
                .result(Result.success("Yay"))
                .build()
        )
        .addResult(
            TagTestResult.builder()
                .tag("v0.1.0")
                .result(Result.success("Yay"))
                .build()
        )
        .addResult(
            TagTestResult.builder()
                .tag("v1.1.1")
                .result(Result.success("Yay"))
                .build()
        )
        .build();

    assertThat(results.results().stream().map(TagTestResult::tag))
        .hasSize(4)
        .containsExactly("v1.1.1", "v1.1.0", "v0.1.1", "v0.1.0");
  }
}
