package ai.docling.client.tester.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.atIndex;

import java.net.URI;
import java.time.Duration;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.testcontainers.DockerClientFactory;

import io.quarkus.logging.Log;

import ai.docling.api.core.DoclingDocument;
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
            tag -> testTag(request, tag)
        )
        .forEach(resultsBuilder::addResult);

    return resultsBuilder.build();
  }

  private TagTestResult testTag(TagsTestRequest request, String tag) {
    var registry = request.registry();
    var image = request.image();

    Log.infof("Testing %s/%s:%s", registry, image, tag);
    var resultBuilder = TagTestResult.builder().tag(tag);
    var containerConfig = DoclingServeContainerConfig.builder()
        .image("%s/%s:%s".formatted(registry, image, tag))
        .startupTimeout(Duration.ofMinutes(5))
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
    finally {
      if (request.cleanupContainerImages()) {
        // Clean up the image to save on disk space
        DockerClientFactory.instance()
            .client()
            .removeImageCmd(containerConfig.image())
            .withForce(true)
            .exec();
      }
    }

    var result = resultBuilder.build();

    Log.infof("Result [%s] = %s", result.tag(), result.result().status());

    return result;
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
        .as("Response status should not be null or empty")
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
                    .asInstanceOf(InstanceOfAssertFactories.type(DoclingDocument.class))
                    .isNotNull(),
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
