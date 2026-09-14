package ai.docling.testing.nativeimage;

import java.net.URI;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import ai.docling.core.DoclingDocument.SectionHeaderItem;
import ai.docling.serve.api.chunk.request.HybridChunkDocumentRequest;
import ai.docling.serve.api.chunk.response.ChunkDocumentResponse;
import ai.docling.serve.api.convert.request.ConvertDocumentRequest;
import ai.docling.serve.api.convert.request.options.ConvertDocumentOptions;
import ai.docling.serve.api.convert.request.options.OutputFormat;
import ai.docling.serve.api.convert.request.source.HttpSource;
import ai.docling.serve.api.convert.request.target.InBodyTarget;
import ai.docling.serve.api.convert.response.ArtifactType;
import ai.docling.serve.api.convert.response.ConvertDocumentResponse;
import ai.docling.serve.api.convert.response.InBodyConvertDocumentResponse;
import ai.docling.serve.api.convert.response.PreSignedUrlConvertResponse;
import ai.docling.serve.api.task.response.TaskStatus;
import ai.docling.serve.api.task.response.TaskStatusPollResponse;
import ai.docling.serve.api.validation.ValidationError;

/** Runs with exactly one Jackson version and no application reflection hints or framework. */
public abstract class SerializationSmokeTest {
  protected abstract String write(Object value) throws Exception;

  protected abstract <T> T read(String json, Class<T> type) throws Exception;

  public void verify() throws Exception {
    verifyConvertRequest();
    verifyDuration();
    verifyPollResponse();
    verifyConvertResponse();
    verifyChunk();
    verifyArtifacts();
    verifyValidationError();
  }

  private void verifyDuration() throws Exception {
    var options = ConvertDocumentOptions.builder()
        .toFormat(OutputFormat.JSON)
        .documentTimeout(Duration.ofSeconds(90))
        .build();
    Map<?, ?> serialized = read(write(options), Map.class);
    equal(List.of("json"), serialized.get("to_formats"));
    equal(90L, ((Number) serialized.get("document_timeout")).longValue());
  }

  private void verifyConvertRequest() throws Exception {
    var request = ConvertDocumentRequest.builder()
        .source(HttpSource.builder().url(URI.create("https://example.com/document.pdf")).build())
        .target(InBodyTarget.builder().build())
        .build();
    String json = write(request);
    Map<?, ?> serialized = read(json, Map.class);
    Map<?, ?> source = (Map<?, ?>) ((List<?>) serialized.get("sources")).get(0);
    equal("https://example.com/document.pdf", source.get("url"));
    equal("http", source.get("kind"));
    equal("inbody", ((Map<?, ?>) serialized.get("target")).get("kind"));
    var restored = read(json, ConvertDocumentRequest.class);
    equal(URI.create("https://example.com/document.pdf"), ((HttpSource) restored.getSources().get(0)).getUrl());
  }

  private void verifyPollResponse() throws Exception {
    var response = read("""
        {"task_id":"convert-1","task_status":"pending","task_position":1,
         "task_meta":{"num_docs":1,"num_processed":0}}
        """, TaskStatusPollResponse.class);
    equal("convert-1", response.getTaskId());
    equal(TaskStatus.PENDING, response.getTaskStatus());
    equal(1L, response.getTaskPosition());
    equal(1L, response.getTaskStatusMetadata().getNumDocs());
    equal("pending", read(write(response), Map.class).get("task_status"));
  }

  private void verifyConvertResponse() throws Exception {
    var response = (InBodyConvertDocumentResponse) read("""
        {"document":{"filename":"document.pdf","json_content":{
          "name":"document.pdf","texts":[{
            "self_ref":"#/texts/0","label":"section_header","text":"Introduction","level":1
          }]}},"status":"success","processing_time":0.25}
        """, ConvertDocumentResponse.class);
    var document = response.getDocument().getJsonContent();
    equal("document.pdf", document.getName());
    var heading = (SectionHeaderItem) document.getTexts().get(0);
    equal("Introduction", heading.getText());
    equal(1, heading.getLevel());
    var restored = (InBodyConvertDocumentResponse) read(write(response), ConvertDocumentResponse.class);
    equal("Introduction", ((SectionHeaderItem) restored.getDocument().getJsonContent().getTexts().get(0)).getText());
  }

  private void verifyChunk() throws Exception {
    var request = HybridChunkDocumentRequest.builder()
        .source(HttpSource.builder().url(URI.create("https://example.com/document.pdf")).build())
        .build();
    var restored = read(write(request), HybridChunkDocumentRequest.class);
    equal(URI.create("https://example.com/document.pdf"), ((HttpSource) restored.getSources().get(0)).getUrl());
    var response = read("""
        {"chunks":[{"filename":"document.pdf","chunk_index":0,"text":"Introduction",
          "headings":["Introduction"],"page_numbers":[1],"doc_items":["#/texts/0"]}],
         "documents":[],"processing_time":0.5}
        """, ChunkDocumentResponse.class);
    equal("Introduction", response.getChunks().get(0).getText());
    equal(List.of(1), response.getChunks().get(0).getPageNumbers());
    equal("Introduction", read(write(response), ChunkDocumentResponse.class).getChunks().get(0).getText());
  }

  private void verifyArtifacts() throws Exception {
    var response = (PreSignedUrlConvertResponse) read("""
        {"documents":[{"source_index":0,"source_uri":"https://example.com/document.pdf",
          "filename":"document.pdf","status":"success",
          "artifacts":[{"artifact_type":"json","mime_type":"application/json","uri":"https://example.com/document.json",
            "url_expires_at":"2026-09-14T12:00:00Z"}]}],"num_converted":1}
        """, ConvertDocumentResponse.class);
    var artifact = response.getDocuments().get(0).getArtifacts().get(0);
    equal(ArtifactType.JSON, artifact.getArtifactType());
    equal(Instant.parse("2026-09-14T12:00:00Z"), artifact.getUrlExpiresAt());
    var restored = (PreSignedUrlConvertResponse) read(write(response), ConvertDocumentResponse.class);
    equal(artifact.getUrlExpiresAt(), restored.getDocuments().get(0).getArtifacts().get(0).getUrlExpiresAt());
  }

  private void verifyValidationError() throws Exception {
    var detailed = read("""
        {"detail":[{"loc":["body","sources",0],"msg":"Field required","type":"missing"}]}
        """, ValidationError.class);
    equal("Field required", detailed.getErrorDetails().get(0).getMessage());
  }

  private static void equal(Object expected, Object actual) {
    if (!Objects.equals(expected, actual)) {
      throw new AssertionError("Expected " + expected + " but got " + actual);
    }
  }
}
