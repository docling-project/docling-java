package ai.docling.client.tester.service.results;

import java.nio.file.Path;

import ai.docling.client.tester.domain.TagsTestResults;

public interface ResultsHandler {
  void handleResults(TagsTestResults results, Path outputDirectory);
}
