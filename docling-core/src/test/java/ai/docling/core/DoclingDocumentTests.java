package ai.docling.core;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import ai.docling.core.DoclingDocument.ContentLayer;
import ai.docling.core.DoclingDocument.DocItemLabel;
import ai.docling.core.DoclingDocument.GroupItem;
import ai.docling.core.DoclingDocument.GroupLabel;
import ai.docling.core.DoclingDocument.TitleItem;

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

  @Test
  void shouldSerializeFurnitureField() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    GroupItem furniture = GroupItem.builder()
        .selfRef("#/furniture")
        .contentLayer(ContentLayer.FURNITURE)
        .label(GroupLabel.UNSPECIFIED)
        .name("_root_")
        .build();

    DoclingDocument document = DoclingDocument.builder()
        .name("test-document")
        .furniture(furniture)
        .build();

    String json = mapper.writeValueAsString(document);

    assertThat(json).contains("\"furniture\"");
    assertThat(json).contains("\"content_layer\":\"furniture\"");
  }

  @Test
  void shouldDeserializeFurnitureField() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    String json = """
        {
          "name": "test-document",
          "furniture": {
            "self_ref": "#/furniture",
            "children": [],
            "content_layer": "furniture",
            "name": "_root_",
            "label": "unspecified"
          }
        }
        """;

    DoclingDocument document = mapper.readValue(json, DoclingDocument.class);

    assertThat(document.getFurniture()).isNotNull();
    assertThat(document.getFurniture().getSelfRef()).isEqualTo("#/furniture");
    assertThat(document.getFurniture().getContentLayer()).isEqualTo(ContentLayer.FURNITURE);
    assertThat(document.getFurniture().getLabel()).isEqualTo(GroupLabel.UNSPECIFIED);
  }

}
