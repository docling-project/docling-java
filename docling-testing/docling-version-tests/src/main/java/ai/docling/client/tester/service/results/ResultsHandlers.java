package ai.docling.client.tester.service.results;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executor;

import jakarta.enterprise.context.ApplicationScoped;

import io.quarkus.arc.All;
import io.quarkus.arc.InstanceHandle;

import ai.docling.client.tester.domain.TagsTestResults;
import ai.docling.client.tester.service.WorkParallelizer;

@ApplicationScoped
public class ResultsHandlers {
  private final List<ResultsHandler> handlers;

  public ResultsHandlers(@All List<InstanceHandle<ResultsHandler>> resultsHandlers) {
    this.handlers = Optional.ofNullable(resultsHandlers)
        .orElse(List.of())
        .stream()
        .map(InstanceHandle::get)
        .toList();
  }

  public void handleResults(Executor executor, TagsTestResults results, Path outputDirectory) {
    WorkParallelizer.runInParallelAndWait(
      executor,
      this.handlers,
      handler -> handler.handleResults(results, outputDirectory)
    );
  }
}
