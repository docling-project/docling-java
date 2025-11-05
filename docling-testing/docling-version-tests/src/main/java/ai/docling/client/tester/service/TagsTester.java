package ai.docling.client.tester.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.atIndex;
import static org.assertj.core.api.InstanceOfAssertFactories.MAP;

import java.net.URI;
import java.time.Duration;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;

import io.quarkus.logging.Log;

import ai.docling.api.serve.DoclingServeApi;
import ai.docling.api.serve.convert.request.ConvertDocumentRequest;
import ai.docling.api.serve.convert.request.options.ConvertDocumentOptions;
import ai.docling.api.serve.convert.request.options.OutputFormat;
import ai.docling.api.serve.convert.request.source.HttpSource;
import ai.docling.api.serve.convert.response.ConvertDocumentResponse;
import ai.docling.api.serve.convert.response.DocumentResponse;
import ai.docling.api.serve.health.HealthCheckResponse;
import ai.docling.client.serve.DoclingServeClientBuilderFactory;
import ai.docling.client.tester.domain.TagTestResult;
import ai.docling.client.tester.domain.TagTestResult.Result;
import ai.docling.client.tester.domain.TagsTestRequest;
import ai.docling.client.tester.domain.TagsTestResults;
import ai.docling.testcontainers.serve.DoclingServeContainer;
import ai.docling.testcontainers.serve.config.DoclingServeContainerConfig;

@ApplicationScoped
public class TagsTester {
  public TagsTestResults testTags(TagsTestRequest request) {
    Log.infof("Testing versions for %s/%s", request.registry(), request.image());
    var resultsBuilder = TagsTestResults.from(request);

    WorkParallelizer.transformInParallelAndWait(
            request.executor(),
            request.tags(),
            tag -> testTag(request.registry(), request.image(), tag)
        )
        .forEach(resultsBuilder::addResult);

    return resultsBuilder.build();
  }

  private TagTestResult testTag(String registry, String image, String tag) {
    Log.infof("Testing %s/%s:%s", registry, image, tag);
    var resultBuilder = TagTestResult.builder().tag(tag);
    var containerConfig = DoclingServeContainerConfig.builder()
        .imageName("%s/%s:%s".formatted(registry, image, tag))
        .startupTimeout(Duration.ofMinutes(2))
        .build();

    try (var doclingContainer = new DoclingServeContainer(containerConfig)) {
      try {
        doclingContainer.start();
        doConversion(doclingContainer);
        resultBuilder.result(Result.success("Tag %s is ok".formatted(tag)));
      }
      finally {
        resultBuilder.serverLogs(doclingContainer.getLogs());
      }
    }
    catch (AssertionError | Exception ex) {
      resultBuilder.result(Result.failure(ex));
    }

    return resultBuilder.build();
  }

  private void doConversion(DoclingServeContainer doclingContainer) {
    var doclingClient = DoclingServeClientBuilderFactory.newBuilder()
        .baseUrl("http://localhost:%d".formatted(doclingContainer.getPort()))
        .build();

    checkDoclingHealthy(doclingClient);
    var options = ConvertDocumentOptions.builder()
        .abortOnError(true)
        .includeImages(true)
        .toFormats(List.of(OutputFormat.MARKDOWN, OutputFormat.JSON, OutputFormat.TEXT))
        .build();

    var request = ConvertDocumentRequest.builder()
        .source(HttpSource.builder().url(URI.create("https://docling.ai")).build())
        .options(options)
        .build();

    checkDoclingResponse(doclingClient.convertSource(request));
  }

  private void checkDoclingResponse(ConvertDocumentResponse response) {
    Log.debugf("Response: %s", response);

    assertThat(response)
        .as("Response should not be null")
        .isNotNull();

    assertThat(response.getStatus())
        .as("Response tatus should not be null or empty")
        .isNotEmpty();

    assertThat(response.getErrors())
        .as("Response should not have errors")
        .isNullOrEmpty();

    assertThat(response.getDocument())
        .as("Response should have a valid document")
        .isNotNull()
        .extracting(
            DocumentResponse::getFilename,
            DocumentResponse::getMarkdownContent,
            DocumentResponse::getTextContent,
            DocumentResponse::getJsonContent
        )
        .satisfies(o ->
                assertThat(o)
                    .as("Document should have a filename")
                    .asString()
                    .isNotEmpty(),
            atIndex(0)
        )
        .satisfies(o ->
                assertThat(o)
                    .as("Document should have markdown content")
                    .asString()
                    .isNotEmpty(),
            atIndex(1)
        )
        .satisfies(o ->
                assertThat(o)
                    .as("Document should have text content")
                    .asString()
                    .isNotEmpty(),
            atIndex(2)
        )
        .satisfies(o ->
                assertThat(o)
                    .as("Document should have JSON content")
                    .asInstanceOf(MAP)
                    .isNotEmpty(),
            atIndex(3)
        );
  }

  private void checkDoclingHealthy(DoclingServeApi doclingClient) {
    var health = doclingClient.health();

    Log.debugf("Health: %s", health);

    assertThat(health)
        .isNotNull()
        .extracting(HealthCheckResponse::getStatus)
        .asString()
        .isEqualToIgnoringCase("ok");
  }
}
