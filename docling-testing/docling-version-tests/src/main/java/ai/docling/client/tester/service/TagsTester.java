package ai.docling.client.tester.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.atIndex;
import static org.assertj.core.api.Assertions.fail;

import java.net.URI;
import java.time.Duration;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.testcontainers.DockerClientFactory;

import ai.docling.client.tester.domain.TagTestResult;
import ai.docling.client.tester.domain.TagTestResult.Result;
import ai.docling.client.tester.domain.TagsTestRequest;
import ai.docling.client.tester.domain.TagsTestResults;
import ai.docling.core.DoclingDocument;
import ai.docling.serve.api.DoclingServeApi;
import ai.docling.serve.api.clear.request.ClearConvertersRequest;
import ai.docling.serve.api.clear.request.ClearResultsRequest;
import ai.docling.serve.api.convert.request.ConvertDocumentRequest;
import ai.docling.serve.api.convert.request.options.ConvertDocumentOptions;
import ai.docling.serve.api.convert.request.options.OutputFormat;
import ai.docling.serve.api.convert.request.options.TableFormerMode;
import ai.docling.serve.api.convert.request.source.HttpSource;
import ai.docling.serve.api.convert.response.ConvertDocumentResponse;
import ai.docling.serve.api.convert.response.DocumentResponse;
import ai.docling.serve.api.convert.response.InBodyConvertDocumentResponse;
import ai.docling.serve.api.convert.response.ResponseType;
import ai.docling.serve.api.health.HealthCheckResponse;
import ai.docling.testcontainers.serve.DoclingServeContainer;
import ai.docling.testcontainers.serve.config.DoclingServeContainerConfig;

import io.quarkus.logging.Log;

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
    var doclingClient = DoclingServeApi.builder()
        .baseUrl("http://localhost:%d".formatted(doclingContainer.getPort()))
        .build();

    checkDoclingHealthy(doclingClient);
    
    // Convert with multiple output formats
    testConvertWithMultipleFormats(doclingClient);
    
    // Convert with JSON format and validate DoclingDocument
    testConvertWithJsonFormat(doclingClient);
    
    // Convert with different document options
    testConvertWithDifferentOptions(doclingClient);
    
    // Clear converters
    testClearConverters(doclingClient);
    
    // Clear results
    testClearResults(doclingClient);
  }
  
  private void testConvertWithMultipleFormats(DoclingServeApi doclingClient) {
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
  
  private void testConvertWithJsonFormat(DoclingServeApi doclingClient) {
    var options = ConvertDocumentOptions.builder()
        .toFormat(OutputFormat.JSON)
        .build();

    var request = ConvertDocumentRequest.builder()
        .source(HttpSource.builder().url(URI.create("https://docling.ai")).build())
        .options(options)
        .build();

    var response = doclingClient.convertSource(request);
    
    assertThat(response)
        .as("Response should not be null")
        .isNotNull();
    
    assertThat(response.getResponseType())
        .as("Response type should be IN_BODY")
        .isEqualTo(ResponseType.IN_BODY);
    
    var inBodyResponse = (InBodyConvertDocumentResponse)response;
    
    assertThat(inBodyResponse.getStatus())
        .as("Response status should not be empty")
        .isNotEmpty();
    
    assertThat(inBodyResponse.getDocument())
        .as("Response should have a document")
        .isNotNull();
    
    var doclingDocument = inBodyResponse.getDocument().getJsonContent();
    assertThat(doclingDocument)
        .as("Document should have JSON content")
        .isNotNull();
    
    assertThat(doclingDocument.getName())
        .as("Document name should not be empty")
        .isNotEmpty();
  }
  
  private void testConvertWithDifferentOptions(DoclingServeApi doclingClient) {
    var options = ConvertDocumentOptions.builder()
        .doOcr(true)
        .includeImages(true)
        .tableMode(TableFormerMode.FAST)
        .documentTimeout(Duration.ofMinutes(1))
        .build();

    var request = ConvertDocumentRequest.builder()
        .source(HttpSource.builder().url(URI.create("https://docling.ai")).build())
        .options(options)
        .build();

    var response = doclingClient.convertSource(request);
    
    assertThat(response)
        .as("Response should not be null")
        .isNotNull();
    
    assertThat(response.getResponseType())
        .as("Response type should be IN_BODY")
        .isEqualTo(ResponseType.IN_BODY);
    
    var inBodyResponse = (InBodyConvertDocumentResponse)response;
    
    assertThat(inBodyResponse.getStatus())
        .as("Response status should not be empty")
        .isNotEmpty();
    
    assertThat(inBodyResponse.getDocument())
        .as("Response should have a document")
        .isNotNull();
  }
  
  private void testClearConverters(DoclingServeApi doclingClient) {
    var request = ClearConvertersRequest.builder().build();
    var response = doclingClient.clearConverters(request);
    
    assertThat(response)
        .as("Clear converters response should not be null")
        .isNotNull();
    
    assertThat(response.getStatus())
        .as("Clear converters status should be 'ok'")
        .isEqualTo("ok");
  }
  
  private void testClearResults(DoclingServeApi doclingClient) {
    var request = ClearResultsRequest.builder().build();
    var response = doclingClient.clearResults(request);
    
    assertThat(response)
        .as("Clear results response should not be null")
        .isNotNull();
    
    assertThat(response.getStatus())
        .as("Clear results status should be 'ok'")
        .isEqualTo("ok");
  }

  private void checkDoclingResponse(ConvertDocumentResponse response) {
    switch(response.getResponseType()) {
      case IN_BODY -> {
        var inBodyResponse = (InBodyConvertDocumentResponse)response;
        Log.debugf("Response: %s", inBodyResponse);

        assertThat(inBodyResponse)
            .as("Response should not be null")
            .isNotNull();

        assertThat(inBodyResponse.getStatus())
            .as("Response status should not be null or empty")
            .isNotEmpty();

        assertThat(inBodyResponse.getErrors())
            .as("Response should not have errors")
            .isNullOrEmpty();

        assertThat(inBodyResponse.getDocument())
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
      case PRE_SIGNED_URL, ZIP_ARCHIVE -> fail("Expected InBodyConvertDocumentResponse but got %s", response.getResponseType());
    }
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
