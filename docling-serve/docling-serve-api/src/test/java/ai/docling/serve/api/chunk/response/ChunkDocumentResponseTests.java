package ai.docling.serve.api.chunk.response;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link ChunkDocumentResponse}.
 */
class ChunkDocumentResponseTests {

  @Test
  void buildWithDefaultValues() {
    ChunkDocumentResponse response = ChunkDocumentResponse.builder().build();

    assertThat(response.getChunks()).isEmpty();
    assertThat(response.getDocuments()).isEmpty();
    assertThat(response.getProcessingTime()).isNull();
  }

  @Test
  void buildWithChunks() {
    Chunk chunk1 = Chunk.builder()
        .filename("doc1.pdf")
        .chunkIndex(0)
        .text("First chunk")
        .numTokens(10)
        .build();

    Chunk chunk2 = Chunk.builder()
        .filename("doc1.pdf")
        .chunkIndex(1)
        .text("Second chunk")
        .numTokens(15)
        .build();

    ChunkDocumentResponse response = ChunkDocumentResponse.builder()
        .chunk(chunk1)
        .chunk(chunk2)
        .build();

    assertThat(response.getChunks()).containsExactly(chunk1, chunk2);
    assertThat(response.getDocuments()).isEmpty();
    assertThat(response.getProcessingTime()).isNull();
  }

  @Test
  void buildWithDocuments() {
    ExportDocumentResponse exportDoc = ExportDocumentResponse.builder().build();
    
    Document doc1 = Document.builder()
        .kind("markdown")
        .content(exportDoc)
        .status("success")
        .build();

    Document doc2 = Document.builder()
        .kind("json")
        .content(exportDoc)
        .status("success")
        .build();

    ChunkDocumentResponse response = ChunkDocumentResponse.builder()
        .document(doc1)
        .document(doc2)
        .build();

    assertThat(response.getChunks()).isEmpty();
    assertThat(response.getDocuments()).containsExactly(doc1, doc2);
    assertThat(response.getProcessingTime()).isNull();
  }

  @Test
  void buildWithProcessingTime() {
    ChunkDocumentResponse response = ChunkDocumentResponse.builder()
        .processingTime(1.234)
        .build();

    assertThat(response.getProcessingTime()).isEqualTo(1.234);
    assertThat(response.getChunks()).isEmpty();
    assertThat(response.getDocuments()).isEmpty();
  }

  @Test
  void buildWithAllFields() {
    Chunk chunk = Chunk.builder()
        .filename("test.pdf")
        .chunkIndex(0)
        .text("Test chunk")
        .numTokens(20)
        .build();

    ExportDocumentResponse exportDoc = ExportDocumentResponse.builder().build();
    Document document = Document.builder()
        .kind("markdown")
        .content(exportDoc)
        .status("success")
        .build();

    ChunkDocumentResponse response = ChunkDocumentResponse.builder()
        .chunk(chunk)
        .document(document)
        .processingTime(2.5)
        .build();

    assertThat(response.getChunks()).containsExactly(chunk);
    assertThat(response.getDocuments()).containsExactly(document);
    assertThat(response.getProcessingTime()).isEqualTo(2.5);
  }

  @Test
  void buildWithListsOfChunksAndDocuments() {
    Chunk chunk1 = Chunk.builder()
        .filename("file.pdf")
        .chunkIndex(0)
        .text("Chunk 1")
        .numTokens(5)
        .build();

    Chunk chunk2 = Chunk.builder()
        .filename("file.pdf")
        .chunkIndex(1)
        .text("Chunk 2")
        .numTokens(8)
        .build();

    ExportDocumentResponse exportDoc = ExportDocumentResponse.builder().build();
    Document doc = Document.builder()
        .kind("json")
        .content(exportDoc)
        .status("success")
        .build();

    List<Chunk> chunks = List.of(chunk1, chunk2);
    List<Document> documents = List.of(doc);

    ChunkDocumentResponse response = ChunkDocumentResponse.builder()
        .chunks(chunks)
        .documents(documents)
        .processingTime(3.0)
        .build();

    assertThat(response.getChunks()).containsExactlyElementsOf(chunks);
    assertThat(response.getDocuments()).containsExactlyElementsOf(documents);
    assertThat(response.getProcessingTime()).isEqualTo(3.0);
  }

  @Test
  void chunkDocumentResponseIsImmutable() {
    Chunk chunk = Chunk.builder()
        .filename("doc.pdf")
        .chunkIndex(0)
        .text("Content")
        .numTokens(10)
        .build();

    ExportDocumentResponse exportDoc = ExportDocumentResponse.builder().build();
    Document doc = Document.builder()
        .kind("markdown")
        .content(exportDoc)
        .status("success")
        .build();

    List<Chunk> chunks = new ArrayList<>(List.of(chunk));
    List<Document> documents = new ArrayList<>(List.of(doc));

    ChunkDocumentResponse response = ChunkDocumentResponse.builder()
        .chunks(chunks)
        .documents(documents)
        .processingTime(1.0)
        .build();

    assertThat(response.getChunks()).containsExactly(chunk);
    assertThat(response.getDocuments()).containsExactly(doc);

    Chunk newChunk = Chunk.builder()
        .filename("new.pdf")
        .chunkIndex(1)
        .text("New")
        .numTokens(5)
        .build();

    ExportDocumentResponse newExportDoc = ExportDocumentResponse.builder().build();
    Document newDoc = Document.builder()
        .kind("json")
        .content(newExportDoc)
        .status("success")
        .build();

    chunks.add(newChunk);
    documents.add(newDoc);

    assertThat(response.getChunks()).hasSize(1).containsExactly(chunk);
    assertThat(response.getDocuments()).hasSize(1).containsExactly(doc);
  }

  @Test
  void toBuilderPreservesAllFields() {
    Chunk chunk = Chunk.builder()
        .filename("original.pdf")
        .chunkIndex(0)
        .text("Original")
        .numTokens(25)
        .build();

    ExportDocumentResponse exportDoc = ExportDocumentResponse.builder().build();
    Document doc = Document.builder()
        .kind("markdown")
        .content(exportDoc)
        .status("success")
        .build();

    ChunkDocumentResponse original = ChunkDocumentResponse.builder()
        .chunk(chunk)
        .document(doc)
        .processingTime(4.5)
        .build();

    ChunkDocumentResponse copy = original.toBuilder().build();

    assertThat(copy.getChunks()).containsExactlyElementsOf(original.getChunks());
    assertThat(copy.getDocuments()).containsExactlyElementsOf(original.getDocuments());
    assertThat(copy.getProcessingTime()).isEqualTo(original.getProcessingTime());
  }

  @Test
  void toBuilderAllowsModification() {
    Chunk originalChunk = Chunk.builder()
        .filename("old.pdf")
        .chunkIndex(0)
        .text("Old")
        .numTokens(10)
        .build();

    ChunkDocumentResponse original = ChunkDocumentResponse.builder()
        .chunk(originalChunk)
        .processingTime(1.0)
        .build();

    Chunk newChunk = Chunk.builder()
        .filename("new.pdf")
        .chunkIndex(1)
        .text("New")
        .numTokens(20)
        .build();

    ChunkDocumentResponse modified = original.toBuilder()
        .chunk(newChunk)
        .processingTime(2.0)
        .build();

    assertThat(original.getChunks()).containsExactly(originalChunk);
    assertThat(original.getProcessingTime()).isEqualTo(1.0);

    assertThat(modified.getChunks()).containsExactly(originalChunk, newChunk);
    assertThat(modified.getProcessingTime()).isEqualTo(2.0);
  }

  @Test
  void buildWithEmptyCollections() {
    ChunkDocumentResponse response = ChunkDocumentResponse.builder()
        .chunks(List.of())
        .documents(List.of())
        .build();

    assertThat(response.getChunks()).isEmpty();
    assertThat(response.getDocuments()).isEmpty();
    assertThat(response.getProcessingTime()).isNull();
  }

  @Test
  void buildWithMultipleChunksAndDocuments() {
    List<Chunk> chunks = List.of(
        Chunk.builder().filename("f1.pdf").chunkIndex(0).text("C1").numTokens(10).build(),
        Chunk.builder().filename("f1.pdf").chunkIndex(1).text("C2").numTokens(12).build(),
        Chunk.builder().filename("f1.pdf").chunkIndex(2).text("C3").numTokens(15).build()
    );

    ExportDocumentResponse exportDoc = ExportDocumentResponse.builder().build();
    List<Document> documents = List.of(
        Document.builder().kind("markdown").content(exportDoc).status("success").build(),
        Document.builder().kind("json").content(exportDoc).status("success").build()
    );

    ChunkDocumentResponse response = ChunkDocumentResponse.builder()
        .chunks(chunks)
        .documents(documents)
        .processingTime(5.75)
        .build();

    assertThat(response.getChunks()).hasSize(3).containsExactlyElementsOf(chunks);
    assertThat(response.getDocuments()).hasSize(2).containsExactlyElementsOf(documents);
    assertThat(response.getProcessingTime()).isEqualTo(5.75);
  }
}
