package ai.docling.client.tester.service.results;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import jakarta.enterprise.context.ApplicationScoped;

import io.quarkus.logging.Log;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

import ai.docling.client.tester.domain.TagsTestResults;

@ApplicationScoped
public class MarkdownFileResultsHandler implements ResultsHandler {
  private final Object lock = new Object();
  private volatile String cachedMarkdown;

  @CheckedTemplate(basePath = "results")
  public static class Templates {
    public static native TemplateInstance results(TagsTestResults results);
  }

  @Override
  public void handleResults(TagsTestResults results, Path outputDirectory) {
    Log.debugf("Generating markdown for results [%s] to [%s]", results, outputDirectory);
    var markdown = generateMarkdown(results);
    Log.debugf("Got markdown: %s", markdown);

    writeMarkdown(markdown, outputDirectory);
  }

  protected String generateMarkdown(TagsTestResults results) {
    // double-checked locking to cache result
    // Only need to compute the markdown once
    if (this.cachedMarkdown == null) {
      synchronized (this.lock) {
        if (this.cachedMarkdown == null) {
          this.cachedMarkdown = Templates.results(results).render();
        }
      }
    }

    return this.cachedMarkdown;
  }

  protected void writeMarkdown(String markdown, Path outputDirectory) {
    var file = outputDirectory.resolve("results.md");
    Log.infof("Writing markdown to %s", file.toAbsolutePath());

    try {
      Files.writeString(file, markdown);
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
