package ai.docling.serve.api.convert.response;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ai.docling.api.core.DoclingDocument;
import ai.docling.api.core.DoclingDocument.DocItemLabel;
import ai.docling.api.core.DoclingDocument.TitleItem;

/**
 * Unit tests for {@link DocumentResponse}.
 */
class DocumentResponseTests {
  @Test
  void createResponseWithAllFields() {
    String doctagsContent = "doctags content";
    String filename = "test-document.pdf";
    String htmlContent = "<html><body>Test content</body></html>";
    DoclingDocument doclingDocument = DoclingDocument.builder()
        .name("test-file.pdf")
        .text(TitleItem.builder().label(DocItemLabel.TITLE).text("Docling Rocks!").build())
        .build();
    String markdownContent = "# Test Document\n\nThis is a test document.";
    String textContent = "Test Document\n\nThis is a test document.";

    DocumentResponse response = DocumentResponse.builder()
        .doctagsContent(doctagsContent)
        .filename(filename)
        .htmlContent(htmlContent)
        .jsonContent(doclingDocument)
        .markdownContent(markdownContent)
        .textContent(textContent)
        .build();

    assertThat(response.getDoctagsContent()).isEqualTo(doctagsContent);
    assertThat(response.getFilename()).isEqualTo(filename);
    assertThat(response.getHtmlContent()).isEqualTo(htmlContent);
    assertThat(response.getJsonContent()).isNotNull();
    assertThat(response.getJsonContent().getName()).isEqualTo(doclingDocument.getName());
    assertThat(response.getJsonContent().getTexts()).hasSize(1);
    assertThat(response.getMarkdownContent()).isEqualTo(markdownContent);
    assertThat(response.getTextContent()).isEqualTo(textContent);
  }

  @Test
  void createResponseWithNullFields() {
    DocumentResponse response = DocumentResponse.builder().build();

    assertThat(response.getDoctagsContent()).isNull();
    assertThat(response.getFilename()).isNull();
    assertThat(response.getHtmlContent()).isNull();
    assertThat(response.getJsonContent()).isNull();
    assertThat(response.getMarkdownContent()).isNull();
    assertThat(response.getTextContent()).isNull();
  }

  @Test
  void createResponseWithEmptyFields() {
    String filename = "empty-document.txt";
    String markdownContent = "";
    String textContent = "";

    DocumentResponse response = DocumentResponse.builder()
        .filename(filename)
        .markdownContent(markdownContent)
        .textContent(textContent)
        .build();

    assertThat(response.getDoctagsContent()).isNull();
    assertThat(response.getFilename()).isEqualTo(filename);
    assertThat(response.getHtmlContent()).isNull();
    assertThat(response.getJsonContent()).isNull();
    assertThat(response.getMarkdownContent()).isEmpty();
    assertThat(response.getTextContent()).isEmpty();
  }

  @Test
  void documentResponseJsonContentIsImmutable() {
    List<TitleItem> texts = new ArrayList<>();
    texts.add(TitleItem.builder().text("Text content").build());
    DoclingDocument doclingDocument = DoclingDocument.builder()
        .name("test-document.pdf")
        .texts(texts)
        .build();

    DocumentResponse response = DocumentResponse.builder()
        .jsonContent(doclingDocument)
        .build();

    assertThat(response.getJsonContent()).isNotNull();
    assertThat(response.getJsonContent().getName()).isEqualTo(doclingDocument.getName());
    assertThat(response.getJsonContent().getTexts()).containsExactlyInAnyOrderElementsOf(doclingDocument.getTexts());

    texts.add(TitleItem.builder().text("new value").build());

    assertThat(response.getJsonContent().getTexts()).hasSize(1);
  }

}
