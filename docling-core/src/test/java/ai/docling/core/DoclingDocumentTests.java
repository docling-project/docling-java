package ai.docling.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import ai.docling.core.DoclingDocument.ContentLayer;
import ai.docling.core.DoclingDocument.DocItemLabel;
import ai.docling.core.DoclingDocument.GroupItem;
import ai.docling.core.DoclingDocument.GroupLabel;
import ai.docling.core.DoclingDocument.Orientation;
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

  @Test
  void shouldDeserializeCurrentBaseMetaFields() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    String json = """
        {
          "name": "meta-document",
          "body": {
            "self_ref": "#/body",
            "content_layer": "body",
            "label": "unspecified",
            "meta": {
              "summary": {
                "confidence": 0.91,
                "created_by": "summary-model",
                "text": "Short summary"
              },
              "language": {
                "confidence": 0.99,
                "created_by": "language-detector",
                "code": "en"
              },
              "entities": {
                "mentions": [
                  {
                    "confidence": 0.88,
                    "created_by": "ner-model",
                    "text": "Docling",
                    "orig": "Docling",
                    "label": "ORG",
                    "charspan": [0, 7]
                  }
                ]
              },
              "keywords": { "values": ["document", "conversion"] },
              "topics": { "values": ["engineering"] }
            }
          }
        }
        """;

    DoclingDocument document = mapper.readValue(json, DoclingDocument.class);

    assertThat(document.getBody().getMeta().getSummary().getText()).isEqualTo("Short summary");
    assertThat(document.getBody().getMeta().getLanguage().getCode()).isEqualTo("en");
    assertThat(document.getBody().getMeta().getEntities().getMentions()).hasSize(1);
    assertThat(document.getBody().getMeta().getEntities().getMentions().get(0).getCharspan()).containsExactly(0, 7);
    assertThat(document.getBody().getMeta().getKeywords().getValues()).containsExactly("document", "conversion");
    assertThat(document.getBody().getMeta().getTopics().getValues()).containsExactly("engineering");

    String serialized = mapper.writeValueAsString(document);

    assertThat(serialized).contains("\"language\"");
    assertThat(serialized).contains("\"entities\"");
    assertThat(serialized).contains("\"keywords\"");
    assertThat(serialized).contains("\"topics\"");
  }

  @Test
  void shouldDeserializeFieldRegionsAndFieldItems() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    String json = """
        {
          "name": "field-document",
          "field_regions": [
            {
              "self_ref": "#/field_regions/0",
              "children": [{"$ref": "#/field_items/0"}],
              "content_layer": "body",
              "label": "field_region",
              "source": [
                {
                  "kind": "track",
                  "start_time": 1.25,
                  "end_time": 2.5,
                  "identifier": "caption-track",
                  "voice": "speaker-1"
                }
              ],
              "comments": [
                {
                  "$ref": "#/texts/0",
                  "range": [1, 5]
                }
              ]
            }
          ],
          "field_items": [
            {
              "self_ref": "#/field_items/0",
              "parent": {"$ref": "#/field_regions/0"},
              "content_layer": "body",
              "label": "field_item"
            }
          ]
        }
        """;

    DoclingDocument document = mapper.readValue(json, DoclingDocument.class);

    assertThat(document.getFieldRegions()).hasSize(1);
    assertThat(document.getFieldRegions().get(0).getLabel()).isEqualTo(DocItemLabel.FIELD_REGION);
    assertThat(document.getFieldRegions().get(0).getSource().get(0))
        .isInstanceOfSatisfying(
            DoclingDocument.TrackSource.class,
            track -> assertThat(track.getIdentifier()).isEqualTo("caption-track"));
    assertThat(document.getFieldRegions().get(0).getComments().get(0).getRef()).isEqualTo("#/texts/0");
    assertThat(document.getFieldRegions().get(0).getComments().get(0).getRange()).containsExactly(1, 5);
    assertThat(document.getFieldItems()).hasSize(1);
    assertThat(document.getFieldItems().get(0).getLabel()).isEqualTo(DocItemLabel.FIELD_ITEM);
  }

  @Test
  void shouldSerializeFieldSourcesAndFineRefsUsingDoclingJsonShape() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    DoclingDocument.FieldRegionItem fieldRegion = DoclingDocument.FieldRegionItem.builder()
        .selfRef("#/field_regions/0")
        .contentLayer(ContentLayer.BODY)
        .label(DocItemLabel.FIELD_REGION)
        .source(DoclingDocument.TrackSource.builder()
            .startTime(1.25)
            .endTime(2.5)
            .identifier("caption-track")
            .voice("speaker-1")
            .build())
        .comment(DoclingDocument.FineRef.builder()
            .ref("#/texts/0")
            .range(java.util.List.of(1, 5))
            .build())
        .build();
    DoclingDocument document = DoclingDocument.builder()
        .name("field-source-document")
        .fieldRegion(fieldRegion)
        .build();

    String json = mapper.writeValueAsString(document);

    assertThat(json).contains("\"kind\":\"track\"");
    assertThat(json).contains("\"start_time\":1.25");
    assertThat(json).contains("\"$ref\":\"#/texts/0\"");
    assertThat(json).contains("\"range\":[1,5]");
    assertThat(json).doesNotContain("\"track\":{");
    assertThat(json).doesNotContain("\"ref\":\"#/texts/0\"");
    // The polymorphic discriminator must reuse TrackSource's own "kind" property,
    // not emit a duplicate one (As.EXISTING_PROPERTY, like BaseTextItem/label).
    assertThat(json.split("\"kind\":\"track\"", -1).length - 1).isEqualTo(1);
  }

  @Test
  void shouldDeserializeNullFieldCollectionsAsEmptyLists() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    String json = """
        {
          "name": "null-fields-document",
          "field_regions": null,
          "field_items": null
        }
        """;

    DoclingDocument document = mapper.readValue(json, DoclingDocument.class);

    assertThat(document.getFieldRegions()).isEmpty();
    assertThat(document.getFieldItems()).isEmpty();
  }

  @Test
  void shouldDeserializeFieldItemWithMetaProvenanceSourceAndComments() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    String json = """
        {
          "name": "rich-field-document",
          "field_items": [
            {
              "self_ref": "#/field_items/0",
              "children": [{"$ref": "#/texts/0"}],
              "content_layer": "body",
              "label": "field_item",
              "meta": {
                "language": {
                  "confidence": 0.98,
                  "created_by": "language-detector",
                  "code": "de"
                }
              },
              "prov": [
                {
                  "page_no": 3,
                  "bbox": {
                    "l": 10.0,
                    "t": 20.0,
                    "r": 100.0,
                    "b": 40.0,
                    "coord_origin": "top_left"
                  },
                  "charspan": [2, 12]
                }
              ],
              "source": [
                {
                  "kind": "track",
                  "start_time": 10.0,
                  "end_time": 12.0
                }
              ],
              "comments": [
                {
                  "$ref": "#/texts/1",
                  "range": [4, 9]
                }
              ]
            }
          ]
        }
        """;

    DoclingDocument document = mapper.readValue(json, DoclingDocument.class);

    assertThat(document.getFieldItems()).hasSize(1);
    DoclingDocument.FieldItem fieldItem = document.getFieldItems().get(0);
    assertThat(fieldItem.getLabel()).isEqualTo(DocItemLabel.FIELD_ITEM);
    assertThat(fieldItem.getChildren().get(0).getRef()).isEqualTo("#/texts/0");
    assertThat(fieldItem.getMeta().getLanguage().getCode()).isEqualTo("de");
    assertThat(fieldItem.getProv().get(0).getPageNo()).isEqualTo(3);
    assertThat(fieldItem.getProv().get(0).getCharspan()).containsExactly(2, 12);
    assertThat(fieldItem.getSource().get(0)).isInstanceOf(DoclingDocument.TrackSource.class);
    assertThat(fieldItem.getComments().get(0).getRef()).isEqualTo("#/texts/1");
    assertThat(fieldItem.getComments().get(0).getRange()).containsExactly(4, 9);
  }

  @Test
  void shouldDeserializeCurrentTableDataFields() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    String json = """
        {
          "name": "table-document",
          "tables": [
            {
              "self_ref": "#/tables/0",
              "label": "table",
              "data": {
                "num_rows": 1,
                "num_cols": 1,
                "orientation": "rot_90",
                "grid": [
                  [
                    {
                      "text": "Amount",
                      "column_header": true,
                      "ref": {"$ref": "#/texts/0"}
                    }
                  ]
                ]
              }
            }
          ]
        }
        """;

    DoclingDocument document = mapper.readValue(json, DoclingDocument.class);

    assertThat(document.getTables()).hasSize(1);
    assertThat(document.getTables().get(0).getData().getOrientation()).isEqualTo(Orientation.ROT_90);
    assertThat(document.getTables().get(0).getData().getGrid().get(0).get(0).getRef().getRef()).isEqualTo("#/texts/0");
  }

  @Test
  void shouldDeserializeCurrentFieldTextVariants() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    String json = """
        {
          "name": "field-text-document",
          "texts": [
            {
              "self_ref": "#/texts/0",
              "content_layer": "body",
              "label": "field_heading",
              "orig": "Account",
              "text": "Account",
              "level": 2,
              "source": [
                {
                  "kind": "track",
                  "start_time": 0.0,
                  "end_time": 1.0
                }
              ],
              "comments": [
                {
                  "$ref": "#/texts/2",
                  "range": [0, 4]
                }
              ]
            },
            {
              "self_ref": "#/texts/1",
              "content_layer": "body",
              "label": "field_value",
              "orig": "12345",
              "text": "12345",
              "kind": "fillable"
            }
          ]
        }
        """;

    DoclingDocument document = mapper.readValue(json, DoclingDocument.class);

    assertThat(document.getTexts()).hasSize(2);
    assertThat(document.getTexts().get(0))
        .isInstanceOfSatisfying(
            DoclingDocument.FieldHeadingItem.class,
            heading -> {
              assertThat(heading.getLevel()).isEqualTo(2);
              assertThat(heading.getSource().get(0)).isInstanceOf(DoclingDocument.TrackSource.class);
              assertThat(heading.getComments().get(0).getRange()).containsExactly(0, 4);
            });
    assertThat(document.getTexts().get(1))
        .isInstanceOfSatisfying(
            DoclingDocument.FieldValueItem.class,
            value -> {
              assertThat(value.getKind()).isEqualTo("fillable");
              assertThat(value.getLabel()).isEqualTo(DocItemLabel.FIELD_VALUE);
            });
  }

  @Test
  void shouldDeserializePictureMetaIncludingCode() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    String json = """
        {
          "name": "picture-meta-document",
          "pictures": [
            {
              "self_ref": "#/pictures/0",
              "content_layer": "body",
              "label": "picture",
              "meta": {
                "description": { "text": "A bar chart", "created_by": "vlm" },
                "classification": {
                  "predictions": [
                    { "class_name": "bar_chart", "confidence": 0.97, "created_by": "classifier" }
                  ]
                },
                "molecule": { "smi": "C1=CC=CC=C1", "confidence": 0.9 },
                "tabular_chart": {
                  "title": "Sales",
                  "chart_data": { "num_rows": 0, "num_cols": 0 }
                },
                "code": {
                  "text": "plt.bar(x, y)",
                  "language": "Python",
                  "confidence": 0.8,
                  "created_by": "code-model"
                }
              }
            }
          ]
        }
        """;

    DoclingDocument document = mapper.readValue(json, DoclingDocument.class);

    DoclingDocument.PictureMeta meta = document.getPictures().get(0).getMeta();
    assertThat(meta.getDescription().getText()).isEqualTo("A bar chart");
    assertThat(meta.getClassification().getPredictions().get(0).getClassName()).isEqualTo("bar_chart");
    assertThat(meta.getMolecule().getSmi()).isEqualTo("C1=CC=CC=C1");
    assertThat(meta.getTabularChart().getTitle()).isEqualTo("Sales");
    assertThat(meta.getCode().getText()).isEqualTo("plt.bar(x, y)");
    assertThat(meta.getCode().getLanguage()).isEqualTo("Python");
    assertThat(meta.getCode().getConfidence()).isEqualTo(0.8);

    String serialized = mapper.writeValueAsString(document);

    assertThat(serialized).contains("\"code\"");
    assertThat(serialized).contains("\"language\":\"Python\"");
  }

  @Test
  void shouldDeserializeTableCellsAsTypedTableCells() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    String json = """
        {
          "name": "table-cells-document",
          "tables": [
            {
              "self_ref": "#/tables/0",
              "label": "table",
              "data": {
                "num_rows": 1,
                "num_cols": 2,
                "table_cells": [
                  {
                    "text": "Header",
                    "column_header": true,
                    "start_row_offset_idx": 0,
                    "end_row_offset_idx": 1,
                    "start_col_offset_idx": 0,
                    "end_col_offset_idx": 1
                  },
                  {
                    "text": "Value",
                    "start_row_offset_idx": 0,
                    "end_row_offset_idx": 1,
                    "start_col_offset_idx": 1,
                    "end_col_offset_idx": 2,
                    "ref": {"$ref": "#/texts/0"}
                  }
                ]
              }
            }
          ]
        }
        """;

    DoclingDocument document = mapper.readValue(json, DoclingDocument.class);

    List<DoclingDocument.TableCell> cells = document.getTables().get(0).getData().getTableCells();
    assertThat(cells).hasSize(2);
    assertThat(cells.get(0)).isInstanceOf(DoclingDocument.TableCell.class);
    assertThat(cells.get(0).getText()).isEqualTo("Header");
    assertThat(cells.get(0).isColumnHeader()).isTrue();
    assertThat(cells.get(0).getRef()).isNull();
    assertThat(cells.get(1).getText()).isEqualTo("Value");
    assertThat(cells.get(1).getRef().getRef()).isEqualTo("#/texts/0");
  }

  @Test
  void shouldDeduplicateKeywordsAndTopicsPreservingOrder() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    String json = """
        {
          "name": "dedup-document",
          "body": {
            "self_ref": "#/body",
            "content_layer": "body",
            "label": "unspecified",
            "meta": {
              "keywords": { "values": ["alpha", "beta", "alpha", "gamma", "beta"] },
              "topics": { "values": ["x", "x", "y"] }
            }
          }
        }
        """;

    DoclingDocument document = mapper.readValue(json, DoclingDocument.class);

    // Order-preserving, deduplicated — matching the Python UniqueList invariant.
    assertThat(document.getBody().getMeta().getKeywords().getValues())
        .containsExactly("alpha", "beta", "gamma");
    assertThat(document.getBody().getMeta().getTopics().getValues())
        .containsExactly("x", "y");
  }

  @Test
  void shouldRejectNonTrackKindOnTrackSource() {
    assertThatThrownBy(
            () -> DoclingDocument.TrackSource.builder().kind("bogus").startTime(0.0).endTime(1.0).build())
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("track");

    // The fixed discriminator default is still produced without setting it.
    DoclingDocument.TrackSource source =
        DoclingDocument.TrackSource.builder().startTime(0.0).endTime(1.0).build();
    assertThat(source.getKind()).isEqualTo("track");
  }

}
