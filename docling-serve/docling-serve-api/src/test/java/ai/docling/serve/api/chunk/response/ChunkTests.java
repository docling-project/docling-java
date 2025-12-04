package ai.docling.serve.api.chunk.response;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link Chunk}.
 */
class ChunkTests {

  @Test
  void buildWithDefaultValues() {
    Chunk chunk = Chunk.builder()
        .filename("test.pdf")
        .chunkIndex(0)
        .text("Sample text")
        .numTokens(10)
        .build();

    assertThat(chunk.getFilename()).isEqualTo("test.pdf");
    assertThat(chunk.getChunkIndex()).isZero();
    assertThat(chunk.getText()).isEqualTo("Sample text");
    assertThat(chunk.getNumTokens()).isEqualTo(10);
    assertThat(chunk.getRawText()).isNull();
    assertThat(chunk.getHeadings()).isEmpty();
    assertThat(chunk.getCaptions()).isEmpty();
    assertThat(chunk.getDocItems()).isEmpty();
    assertThat(chunk.getPageNumbers()).isEmpty();
    assertThat(chunk.getMetadata()).isEmpty();
  }

  @Test
  void buildWithAllFields() {
    Chunk chunk = Chunk.builder()
        .filename("document.pdf")
        .chunkIndex(5)
        .text("Complete chunk text")
        .rawText("Raw chunk text")
        .numTokens(25)
        .heading("Chapter 1")
        .heading("Section 1.1")
        .caption("Figure 1")
        .docItem("item1")
        .docItem("item2")
        .pageNumber(1)
        .pageNumber(2)
        .metadata("key1", "value1")
        .metadata("key2", 42)
        .build();

    assertThat(chunk.getFilename()).isEqualTo("document.pdf");
    assertThat(chunk.getChunkIndex()).isEqualTo(5);
    assertThat(chunk.getText()).isEqualTo("Complete chunk text");
    assertThat(chunk.getRawText()).isEqualTo("Raw chunk text");
    assertThat(chunk.getNumTokens()).isEqualTo(25);
    assertThat(chunk.getHeadings()).containsExactly("Chapter 1", "Section 1.1");
    assertThat(chunk.getCaptions()).containsExactly("Figure 1");
    assertThat(chunk.getDocItems()).containsExactly("item1", "item2");
    assertThat(chunk.getPageNumbers()).containsExactly(1, 2);
    assertThat(chunk.getMetadata())
        .hasSize(2)
        .containsEntry("key1", "value1")
        .containsEntry("key2", 42);
  }

  @Test
  void buildWithListsAndMaps() {
    List<String> headings = List.of("Title", "Subtitle");
    List<String> captions = List.of("Caption 1", "Caption 2");
    List<String> docItems = List.of("doc1", "doc2", "doc3");
    List<Integer> pageNumbers = List.of(10, 11, 12);
    Map<String, Object> metadata = Map.of("author", "John Doe", "year", 2024);

    Chunk chunk = Chunk.builder()
        .filename("paper.pdf")
        .chunkIndex(3)
        .text("Content")
        .numTokens(50)
        .headings(headings)
        .captions(captions)
        .docItems(docItems)
        .pageNumbers(pageNumbers)
        .metadata(metadata)
        .build();

    assertThat(chunk.getHeadings()).containsExactlyElementsOf(headings);
    assertThat(chunk.getCaptions()).containsExactlyElementsOf(captions);
    assertThat(chunk.getDocItems()).containsExactlyElementsOf(docItems);
    assertThat(chunk.getPageNumbers()).containsExactlyElementsOf(pageNumbers);
    assertThat(chunk.getMetadata()).containsAllEntriesOf(metadata);
  }

  @Test
  void chunkIsImmutable() {
    List<String> headings = new ArrayList<>(List.of("Heading 1"));
    List<String> captions = new ArrayList<>(List.of("Caption 1"));
    List<String> docItems = new ArrayList<>(List.of("item1"));
    List<Integer> pageNumbers = new ArrayList<>(List.of(1));
    Map<String, Object> metadata = new HashMap<>(Map.of("key", "value"));

    Chunk chunk = Chunk.builder()
        .filename("test.pdf")
        .chunkIndex(0)
        .text("Text")
        .numTokens(5)
        .headings(headings)
        .captions(captions)
        .docItems(docItems)
        .pageNumbers(pageNumbers)
        .metadata(metadata)
        .build();

    assertThat(chunk.getHeadings()).containsExactly("Heading 1");
    assertThat(chunk.getCaptions()).containsExactly("Caption 1");
    assertThat(chunk.getDocItems()).containsExactly("item1");
    assertThat(chunk.getPageNumbers()).containsExactly(1);
    assertThat(chunk.getMetadata()).containsEntry("key", "value");

    headings.add("Heading 2");
    captions.add("Caption 2");
    docItems.add("item2");
    pageNumbers.add(2);
    metadata.put("newKey", "newValue");

    assertThat(chunk.getHeadings()).containsExactly("Heading 1");
    assertThat(chunk.getCaptions()).containsExactly("Caption 1");
    assertThat(chunk.getDocItems()).containsExactly("item1");
    assertThat(chunk.getPageNumbers()).containsExactly(1);
    assertThat(chunk.getMetadata())
        .hasSize(1)
        .containsEntry("key", "value")
        .doesNotContainKey("newKey");
  }

  @Test
  void toBuilderPreservesAllFields() {
    Chunk original = Chunk.builder()
        .filename("original.pdf")
        .chunkIndex(7)
        .text("Original text")
        .rawText("Original raw text")
        .numTokens(100)
        .heading("Original Heading")
        .caption("Original Caption")
        .docItem("original-item")
        .pageNumber(5)
        .metadata("originalKey", "originalValue")
        .build();

    Chunk copy = original.toBuilder().build();

    assertThat(copy.getFilename()).isEqualTo(original.getFilename());
    assertThat(copy.getChunkIndex()).isEqualTo(original.getChunkIndex());
    assertThat(copy.getText()).isEqualTo(original.getText());
    assertThat(copy.getRawText()).isEqualTo(original.getRawText());
    assertThat(copy.getNumTokens()).isEqualTo(original.getNumTokens());
    assertThat(copy.getHeadings()).containsExactlyElementsOf(original.getHeadings());
    assertThat(copy.getCaptions()).containsExactlyElementsOf(original.getCaptions());
    assertThat(copy.getDocItems()).containsExactlyElementsOf(original.getDocItems());
    assertThat(copy.getPageNumbers()).containsExactlyElementsOf(original.getPageNumbers());
    assertThat(copy.getMetadata()).containsAllEntriesOf(original.getMetadata());
  }

  @Test
  void toBuilderAllowsModification() {
    Chunk original = Chunk.builder()
        .filename("file1.pdf")
        .chunkIndex(1)
        .text("Text 1")
        .numTokens(20)
        .heading("Heading 1")
        .build();

    Chunk modified = original.toBuilder()
        .filename("file2.pdf")
        .chunkIndex(2)
        .text("Text 2")
        .numTokens(30)
        .heading("Heading 2")
        .build();

    assertThat(original.getFilename()).isEqualTo("file1.pdf");
    assertThat(original.getChunkIndex()).isEqualTo(1);
    assertThat(original.getText()).isEqualTo("Text 1");
    assertThat(original.getNumTokens()).isEqualTo(20);
    assertThat(original.getHeadings()).containsExactly("Heading 1");

    assertThat(modified.getFilename()).isEqualTo("file2.pdf");
    assertThat(modified.getChunkIndex()).isEqualTo(2);
    assertThat(modified.getText()).isEqualTo("Text 2");
    assertThat(modified.getNumTokens()).isEqualTo(30);
    assertThat(modified.getHeadings()).containsExactly("Heading 1", "Heading 2");
  }

  @Test
  void buildWithRawTextNull() {
    Chunk chunk = Chunk.builder()
        .filename("test.pdf")
        .chunkIndex(0)
        .text("Text")
        .numTokens(5)
        .rawText(null)
        .build();

    assertThat(chunk.getRawText()).isNull();
  }

  @Test
  void buildWithEmptyCollections() {
    Chunk chunk = Chunk.builder()
        .filename("empty.pdf")
        .chunkIndex(0)
        .text("Text with no metadata")
        .numTokens(8)
        .headings(List.of())
        .captions(List.of())
        .docItems(List.of())
        .pageNumbers(List.of())
        .metadata(Map.of())
        .build();

    assertThat(chunk.getHeadings()).isEmpty();
    assertThat(chunk.getCaptions()).isEmpty();
    assertThat(chunk.getDocItems()).isEmpty();
    assertThat(chunk.getPageNumbers()).isEmpty();
    assertThat(chunk.getMetadata()).isEmpty();
  }

  @Test
  void buildWithMultiplePageNumbers() {
    Chunk chunk = Chunk.builder()
        .filename("multi-page.pdf")
        .chunkIndex(0)
        .text("Content spanning multiple pages")
        .numTokens(15)
        .pageNumber(1)
        .pageNumber(2)
        .pageNumber(3)
        .pageNumber(4)
        .pageNumber(5)
        .build();

    assertThat(chunk.getPageNumbers()).containsExactly(1, 2, 3, 4, 5);
  }

  @Test
  void buildWithComplexMetadata() {
    Map<String, Object> nestedMap = Map.of("nested", "value");
    List<String> listValue = List.of("a", "b", "c");

    Chunk chunk = Chunk.builder()
        .filename("complex.pdf")
        .chunkIndex(0)
        .text("Text")
        .numTokens(10)
        .metadata("string", "value")
        .metadata("number", 123)
        .metadata("boolean", true)
        .metadata("map", nestedMap)
        .metadata("list", listValue)
        .build();

    assertThat(chunk.getMetadata())
        .hasSize(5)
        .containsEntry("string", "value")
        .containsEntry("number", 123)
        .containsEntry("boolean", true)
        .containsEntry("map", nestedMap)
        .containsEntry("list", listValue);
  }
}
