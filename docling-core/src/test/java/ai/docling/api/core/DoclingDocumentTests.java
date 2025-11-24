package ai.docling.api.core;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import ai.docling.api.core.DoclingDocument.DocItemLabel;
import ai.docling.api.core.DoclingDocument.TitleItem;

/**
 * Unit tests for {@link DoclingDocument}.
 */
class DoclingDocumentTests {

  @Test
  void shouldBuildEmptyDocument() {
    DoclingDocument document = DoclingDocument.builder().build();
    assertThat(document).isNotNull();
  }

  @Test
  void shouldBuildDocumentWithProperties() {
    DoclingDocument document = DoclingDocument.builder()
        .name("test-document")
        .text(TitleItem.builder()
            .label(DocItemLabel.TITLE)
            .text("Docling Rocks!")
            .build())
        .build();
    assertThat(document.getName()).isEqualTo("test-document");
    assertThat(document.getTexts().get(0)).isInstanceOf(TitleItem.class);

    TitleItem titleItem = (TitleItem) document.getTexts().get(0);
    assertThat(titleItem.getLabel()).isEqualTo(DocItemLabel.TITLE);
    assertThat(titleItem.getText()).isEqualTo("Docling Rocks!");
  }

}
