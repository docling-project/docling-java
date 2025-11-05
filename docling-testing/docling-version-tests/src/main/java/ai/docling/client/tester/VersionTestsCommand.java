package ai.docling.client.tester;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import io.quarkus.logging.Log;

import ai.docling.client.tester.client.RegistryClient;
import ai.docling.client.tester.client.RegistryClientFactory;
import ai.docling.client.tester.domain.Tags;
import ai.docling.client.tester.domain.TagsTestRequest;
import ai.docling.client.tester.domain.TagsTestResults;
import ai.docling.client.tester.service.NamedThreadFactory;
import ai.docling.client.tester.service.TagsTester;
import ai.docling.client.tester.service.results.ResultsHandlers;
import ai.docling.testcontainers.serve.config.DoclingServeContainerConfig;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "docling-serve-version-tests", mixinStandardHelpOptions = true)
public class VersionTestsCommand implements Runnable {
  @Option(
      names = { "-p", "--parallelism" },
      required = true,
      defaultValue = "1",
      description = "The number of versions to verify in parallel."
  )
  int parallelism = 1;

  @Option(
      names = { "-i", "--image" },
      required = true,
      description = "The docling-serve image to fetch",
      defaultValue = DoclingServeContainerConfig.DOCLING_ORG + "/" + DoclingServeContainerConfig.DOCLING_IMAGE_NAME
  )
  String image;

  @Option(
      names = { "-r", "--registry" },
      description = "The registry to use",
      defaultValue = "ghcr.io",
      required = true
  )
  String registry;

  @Option(
      names = {"-o", "--output"},
      description = {
          "Output directory",
          "Default: A directory called 'results' in the current directory.",
          "Will be created if it doesn't exist."
      },
      defaultValue = "results",
      required = true)
  Path outputDirectory;

  @Option(
      names = { "-t", "--tags" },
      description = {
          "The tags to test.",
          "If not specified, all versions from <registry>/<image> will be fetched and tested."
      }
  )
  List<String> tagsToTest;

  @Option(
      names = { "-g", "--create-github-issue"},
      description = "Create a GitHub issue if any of the tests fail.",
      negatable = true,
      defaultValue = "true"
  )
  boolean createGithubIssue;

  private final RegistryClientFactory clientFactory;
  private final TagsTester versionTester;
  private final ResultsHandlers resultsHandlers;

  public VersionTestsCommand(RegistryClientFactory clientFactory, TagsTester versionTester, ResultsHandlers resultsHandlers) {
    this.clientFactory = clientFactory;
    this.versionTester = versionTester;
    this.resultsHandlers = resultsHandlers;
  }

  @Override
  public void run() {
    var executor = Executors.newFixedThreadPool(this.parallelism, new NamedThreadFactory("VersionTester"));

    try {
      var request = createTestRequest(executor);
      Log.infof("Going to test tags: %s", request.tags());

      var results = this.versionTester.testTags(request);
      Log.debugf("Results: %s", results);

      var runOutputDir = getAndCreateOutputDirectory(results);
      this.resultsHandlers.handleResults(executor, results, runOutputDir);
    }
    finally {
      executor.shutdown();
    }
  }

  private Path getAndCreateOutputDirectory(TagsTestResults results) {
    var runResultsDir = this.outputDirectory.resolve("results-%s".formatted(results.timestamp()));
    var exists = Files.exists(runResultsDir);

    if (!exists) {
      Log.debugf("Creating output directory: %s", runResultsDir.toAbsolutePath());
      try {
        Files.createDirectories(runResultsDir);
      }
      catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
    else if (exists && !Files.isDirectory(runResultsDir)) {
      throw new IllegalArgumentException("Output directory [%s] already exists and is not a directory".formatted(runResultsDir.toAbsolutePath()));
    }

    return runResultsDir;
  }

  private TagsTestRequest createTestRequest(Executor executor) {
    var requestBuilder = TagsTestRequest.builder()
        .registry(this.registry)
        .image(this.image)
        .executor(executor);

    if ((this.tagsToTest != null) && !this.tagsToTest.isEmpty()) {
      // User passed in the set of tags to test
      requestBuilder.tags(List.copyOf(this.tagsToTest));
    }
    else {
      // Fetch the tags
      Log.infof("Fetching tags for %s/%s", this.registry, this.image);
      var registryClient = this.clientFactory.getRegistryClient(this.registry);
      var tags = getTags(registryClient);
      Log.debugf("[%s] Got tags: %s", this.registry, tags);

      var versionTags = tags.getVersionTags();
      Log.infof("[%s] Got version tags: %s", this.registry, versionTags);
      requestBuilder.tags(versionTags);
    }

    return requestBuilder.build();
  }

  private Tags getTags(RegistryClient registryClient) {
    Log.info("Getting tags");

    var token = registryClient.getTokenForImage(this.image);
    return registryClient.getTags(this.image, token);
  }
}
