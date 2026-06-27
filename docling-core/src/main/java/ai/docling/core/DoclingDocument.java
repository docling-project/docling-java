package ai.docling.core;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.jspecify.annotations.Nullable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.Nulls;

/**
 * Represents a document processed by the Docling service with its structure,
 * content, and metadata.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@tools.jackson.databind.annotation.JsonDeserialize(builder = DoclingDocument.Builder.class)
@lombok.extern.jackson.Jacksonized
@lombok.Builder(toBuilder = true)
@lombok.Getter
@lombok.ToString
public class DoclingDocument {

  @JsonProperty("schema_name")
  @Nullable
  private String schemaName;

  @JsonProperty("version")
  @Nullable
  private String version;

  @JsonProperty("name")
  private String name;

  @JsonProperty("origin")
  @Nullable
  private DocumentOrigin origin;

  @JsonProperty("body")
  private GroupItem body;

  @JsonProperty("furniture")
  @Nullable
  private GroupItem furniture;

  @JsonProperty("groups")
  @JsonSetter(nulls = Nulls.AS_EMPTY)
  @lombok.Singular
  private List<GroupItem> groups;

  @JsonProperty("texts")
  @JsonSetter(nulls = Nulls.AS_EMPTY)
  @lombok.Singular
  private List<BaseTextItem> texts;

  @JsonProperty("pictures")
  @JsonSetter(nulls = Nulls.AS_EMPTY)
  @lombok.Singular
  private List<PictureItem> pictures;

  @JsonProperty("tables")
  @JsonSetter(nulls = Nulls.AS_EMPTY)
  @lombok.Singular
  private List<TableItem> tables;

  @JsonProperty("key_value_items")
  @JsonSetter(nulls = Nulls.AS_EMPTY)
  @lombok.Singular
  private List<KeyValueItem> keyValueItems;

  @JsonProperty("form_items")
  @JsonSetter(nulls = Nulls.AS_EMPTY)
  @lombok.Singular
  private List<FormItem> formItems;

  @JsonProperty("field_regions")
  @JsonSetter(nulls = Nulls.AS_EMPTY)
  @lombok.Singular
  private List<FieldRegionItem> fieldRegions;

  @JsonProperty("field_items")
  @JsonSetter(nulls = Nulls.AS_EMPTY)
  @lombok.Singular
  private List<FieldItem> fieldItems;

  @JsonProperty("pages")
  @lombok.Singular
  private Map<String, PageItem> pages;

  // Inner classes for nested types

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @tools.jackson.databind.annotation.JsonDeserialize(builder = DocumentOrigin.Builder.class)
  @lombok.extern.jackson.Jacksonized
  @lombok.Builder(toBuilder = true)
  @lombok.Getter
  @lombok.ToString
  public static class DocumentOrigin {
    @JsonProperty("mimetype")
    private String mimetype;
    
    @JsonProperty("binary_hash")
    private BigInteger binaryHash;
    
    @JsonProperty("filename")
    private String filename;
    
    @JsonProperty("uri")
    @Nullable
    private String uri;

    @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
    public static class Builder { }
  }

  public enum ContentLayer {
    @JsonProperty("body") BODY,
    @JsonProperty("furniture") FURNITURE,
    @JsonProperty("background") BACKGROUND,
    @JsonProperty("invisible") INVISIBLE,
    @JsonProperty("notes") NOTES
  }

  public enum GroupLabel {
    @JsonProperty("unspecified") UNSPECIFIED,
    @JsonProperty("list") LIST,
    @JsonProperty("ordered_list") ORDERED_LIST,
    @JsonProperty("chapter") CHAPTER,
    @JsonProperty("section") SECTION,
    @JsonProperty("sheet") SHEET,
    @JsonProperty("slide") SLIDE,
    @JsonProperty("form_area") FORM_AREA,
    @JsonProperty("key_value_area") KEY_VALUE_AREA,
    @JsonProperty("comment_section") COMMENT_SECTION,
    @JsonProperty("inline") INLINE,
    @JsonProperty("picture_area") PICTURE_AREA
  }

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @tools.jackson.databind.annotation.JsonDeserialize(builder = GroupItem.Builder.class)
  @lombok.extern.jackson.Jacksonized
  @lombok.Builder(toBuilder = true)
  @lombok.Getter
  @lombok.ToString
  public static class GroupItem {
    @JsonProperty("self_ref")
    private String selfRef;
    
    @JsonProperty("parent")
    @Nullable
    private RefItem parent;
    
    @JsonProperty("children")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular("child")
    private List<RefItem> children;
    
    @JsonProperty("content_layer")
    private ContentLayer contentLayer;
    
    @JsonProperty("meta")
    @Nullable
    private BaseMeta meta;
    
    @JsonProperty("name")
    @Nullable
    private String name;
    
    @JsonProperty("label")
    private GroupLabel label;

    @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
    public static class Builder { }
  }

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @tools.jackson.databind.annotation.JsonDeserialize(builder = RefItem.Builder.class)
  @lombok.extern.jackson.Jacksonized
  @lombok.Builder(toBuilder = true)
  @lombok.Getter
  @lombok.ToString
  public static class RefItem {
    @JsonProperty("$ref")
    private String ref;

    @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
    public static class Builder { }
  }

  /**
   * Fine-granular reference item that captures an optional character span range alongside a JSON
   * pointer reference. The {@code range} field serializes as a 2-element JSON integer array
   * {@code [start, end]} to match the Python {@code tuple[int, int]} wire format.
   */
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @tools.jackson.databind.annotation.JsonDeserialize(builder = FineRef.Builder.class)
  @lombok.extern.jackson.Jacksonized
  @lombok.Builder(toBuilder = true)
  @lombok.Getter
  @lombok.ToString
  public static class FineRef {
    @JsonProperty("$ref")
    private String ref;

    /**
     * Optional character span as a 2-element JSON array {@code [start_inclusive, end_exclusive]},
     * matching the Python {@code Optional[tuple[int, int]]} wire format.
     */
    @JsonProperty("range")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular("range")
    private List<Integer> range;

    @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
    public static class Builder { }
  }

  /**
   * Source metadata for a cue extracted from a media track (audio, video, subtitles, etc.).
   * Serialized as a flat JSON object with a {@code "kind": "track"} discriminator field.
   */
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @tools.jackson.databind.annotation.JsonDeserialize(builder = TrackSource.Builder.class)
  @lombok.extern.jackson.Jacksonized
  @lombok.Builder(toBuilder = true)
  @lombok.Getter
  @lombok.ToString
  public static final class TrackSource implements SourceType {
    @JsonProperty("kind")
    @lombok.Builder.Default
    private String kind = "track";

    @JsonProperty("start_time")
    private Double startTime;

    @JsonProperty("end_time")
    private Double endTime;

    @JsonProperty("identifier")
    @Nullable
    private String identifier;

    @JsonProperty("voice")
    @Nullable
    private String voice;

    @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
      /**
       * Guards the {@code kind} discriminator: it is fixed to {@code "track"} and may not be set to
       * any other value. The constant default declared on {@link TrackSource} is always used, so an
       * unsupported discriminator can never be built or serialized.
       */
      @JsonProperty("kind")
      public Builder kind(String kind) {
        if (kind != null && !"track".equals(kind)) {
          throw new IllegalArgumentException("TrackSource.kind must be \"track\" but was: " + kind);
        }
        return this;
      }
    }
  }

  /**
   * Detected human language of a document node, expressed as a BCP 47 code (e.g. {@code "en"}).
   */
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @tools.jackson.databind.annotation.JsonDeserialize(builder = LanguageMetaField.Builder.class)
  @lombok.extern.jackson.Jacksonized
  @lombok.Builder(toBuilder = true)
  @lombok.Getter
  @lombok.ToString
  public static class LanguageMetaField {
    @JsonProperty("confidence")
    @Nullable
    private Double confidence;

    @JsonProperty("created_by")
    @Nullable
    private String createdBy;

    @JsonProperty("code")
    private String code;

    @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
    public static class Builder { }
  }

  /**
   * A named entity mention extracted from text, carrying the entity text, an optional type label,
   * and an optional character span as a 2-element JSON array {@code [start, end]}.
   */
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @tools.jackson.databind.annotation.JsonDeserialize(builder = EntityMention.Builder.class)
  @lombok.extern.jackson.Jacksonized
  @lombok.Builder(toBuilder = true)
  @lombok.Getter
  @lombok.ToString
  public static class EntityMention {
    @JsonProperty("confidence")
    @Nullable
    private Double confidence;

    @JsonProperty("created_by")
    @Nullable
    private String createdBy;

    @JsonProperty("text")
    private String text;

    @JsonProperty("orig")
    @Nullable
    private String orig;

    @JsonProperty("label")
    @Nullable
    private String label;

    /**
     * Character span as a 2-element JSON array {@code [start, end]} (0-indexed,
     * end-exclusive), matching the Python {@code CharSpan = tuple[int, int]} wire format.
     */
    @JsonProperty("charspan")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular("charspan")
    private List<Integer> charspan;

    @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
    public static class Builder { }
  }

  /**
   * Container for named entity mentions associated with a document node.
   */
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @tools.jackson.databind.annotation.JsonDeserialize(builder = EntitiesMetaField.Builder.class)
  @lombok.extern.jackson.Jacksonized
  @lombok.Builder(toBuilder = true)
  @lombok.Getter
  @lombok.ToString
  public static class EntitiesMetaField {
    @JsonProperty("mentions")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular
    private List<EntityMention> mentions;

    @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
    public static class Builder { }
  }

  /**
   * Container for salient keyword or keyphrase metadata associated with a document node.
   * Values are order-preserving and deduplicated.
   */
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @tools.jackson.databind.annotation.JsonDeserialize(builder = KeywordsMetaField.Builder.class)
  @lombok.extern.jackson.Jacksonized
  @lombok.Builder(toBuilder = true)
  @lombok.Getter
  @lombok.ToString
  public static class KeywordsMetaField {
    @JsonProperty("values")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular("value")
    private List<String> values;

    /**
     * Returns the keyword values order-preserving and deduplicated, upholding the same uniqueness
     * invariant as the Python {@code UniqueList} source model.
     */
    public List<String> getValues() {
      return values == null ? List.of() : List.copyOf(new java.util.LinkedHashSet<>(values));
    }

    @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
    public static class Builder { }
  }

  /**
   * Container for higher-level subject category or thematic label metadata associated with a
   * document node. Values are order-preserving and deduplicated.
   */
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @tools.jackson.databind.annotation.JsonDeserialize(builder = TopicsMetaField.Builder.class)
  @lombok.extern.jackson.Jacksonized
  @lombok.Builder(toBuilder = true)
  @lombok.Getter
  @lombok.ToString
  public static class TopicsMetaField {
    @JsonProperty("values")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular("value")
    private List<String> values;

    /**
     * Returns the topic values order-preserving and deduplicated, upholding the same uniqueness
     * invariant as the Python {@code UniqueList} source model.
     */
    public List<String> getValues() {
      return values == null ? List.of() : List.copyOf(new java.util.LinkedHashSet<>(values));
    }

    @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
    public static class Builder { }
  }

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @tools.jackson.databind.annotation.JsonDeserialize(builder = BaseMeta.Builder.class)
  @lombok.extern.jackson.Jacksonized
  @lombok.Builder(toBuilder = true)
  @lombok.Getter
  @lombok.ToString
  public static class BaseMeta {
    @JsonProperty("summary")
    @Nullable
    private SummaryMetaField summary;

    @JsonProperty("language")
    @Nullable
    private LanguageMetaField language;

    @JsonProperty("entities")
    @Nullable
    private EntitiesMetaField entities;

    @JsonProperty("keywords")
    @Nullable
    private KeywordsMetaField keywords;

    @JsonProperty("topics")
    @Nullable
    private TopicsMetaField topics;

    @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
    public static class Builder { }
  }

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @tools.jackson.databind.annotation.JsonDeserialize(builder = SummaryMetaField.Builder.class)
  @lombok.extern.jackson.Jacksonized
  @lombok.Builder(toBuilder = true)
  @lombok.Getter
  @lombok.ToString
  public static class SummaryMetaField {
    @JsonProperty("confidence")
    @Nullable
    private Double confidence;

    @JsonProperty("created_by")
    @Nullable
    private String createdBy;

    @JsonProperty("text")
    private String text;

    @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
    public static class Builder { }
  }

  @JsonTypeInfo(
      use = JsonTypeInfo.Id.NAME,
      include = JsonTypeInfo.As.EXISTING_PROPERTY,
      property = "kind",
      visible = true
  )
  @JsonSubTypes({
      @Type(value = TrackSource.class, name = "track")
  })
  public sealed interface SourceType permits TrackSource { }

  public enum DocItemLabel {
    @JsonProperty("caption") CAPTION,
    @JsonProperty("chart") CHART,
    @JsonProperty("checkbox_selected") CHECKBOX_SELECTED,
    @JsonProperty("checkbox_unselected") CHECKBOX_UNSELECTED,
    @JsonProperty("code") CODE,
    @JsonProperty("document_index") DOCUMENT_INDEX,
    @JsonProperty("empty_value") EMPTY_VALUE,
    @JsonProperty("footnote") FOOTNOTE,
    @JsonProperty("form") FORM,
    @JsonProperty("formula") FORMULA,
    @JsonProperty("grading_scale") GRADING_SCALE,
    @JsonProperty("handwritten_text") HANDWRITTEN_TEXT,
    @JsonProperty("key_value_region") KEY_VALUE_REGION,
    @JsonProperty("list_item") LIST_ITEM,
    @JsonProperty("page_footer") PAGE_FOOTER,
    @JsonProperty("page_header") PAGE_HEADER,
    @JsonProperty("paragraph") PARAGRAPH,
    @JsonProperty("picture") PICTURE,
    @JsonProperty("reference") REFERENCE,
    @JsonProperty("section_header") SECTION_HEADER,
    @JsonProperty("table") TABLE,
    @JsonProperty("text") TEXT,
    @JsonProperty("title") TITLE,
    @JsonProperty("field_region") FIELD_REGION,
    @JsonProperty("field_heading") FIELD_HEADING,
    @JsonProperty("field_item") FIELD_ITEM,
    @JsonProperty("field_key") FIELD_KEY,
    @JsonProperty("field_value") FIELD_VALUE,
    @JsonProperty("field_hint") FIELD_HINT,
    @JsonProperty("marker") MARKER
  }

  public enum Orientation {
    @JsonProperty("rot_0") ROT_0,
    @JsonProperty("rot_90") ROT_90,
    @JsonProperty("rot_180") ROT_180,
    @JsonProperty("rot_270") ROT_270
  }

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @JsonTypeInfo(
      use = JsonTypeInfo.Id.NAME,
      include = JsonTypeInfo.As.EXISTING_PROPERTY,
      property = "label",
      visible = true
  )
  @JsonSubTypes({
      @Type(value = TitleItem.class, name = "title"),
      @Type(value = SectionHeaderItem.class, name = "section_header"),
      @Type(value = ListItem.class, name = "list_item"),
      @Type(value = CodeItem.class, name = "code"),
      @Type(value = FormulaItem.class, name = "formula"),
      @Type(value = FieldHeadingItem.class, name = "field_heading"),
      @Type(value = FieldValueItem.class, name = "field_value"),
      @Type(value = TextItem.class, name = "text"),
      @Type(value = TextItem.class, name = "paragraph"),
      @Type(value = TextItem.class, name = "caption"),
      @Type(value = TextItem.class, name = "footnote"),
      @Type(value = TextItem.class, name = "page_header"),
      @Type(value = TextItem.class, name = "page_footer"),
      @Type(value = TextItem.class, name = "reference"),
      @Type(value = TextItem.class, name = "checkbox_selected"),
      @Type(value = TextItem.class, name = "checkbox_unselected"),
      @Type(value = TextItem.class, name = "empty_value"),
      @Type(value = TextItem.class, name = "field_key"),
      @Type(value = TextItem.class, name = "field_hint"),
      @Type(value = TextItem.class, name = "marker"),
      @Type(value = TextItem.class, name = "handwritten_text")
  })
  public sealed interface BaseTextItem
      permits TitleItem, SectionHeaderItem, FieldHeadingItem, FieldValueItem, ListItem, CodeItem, FormulaItem, TextItem {

    String getSelfRef();

    @Nullable
    RefItem getParent();

    List<RefItem> getChildren();

    ContentLayer getContentLayer();

    DocItemLabel getLabel();

    List<ProvenanceItem> getProv();

    String getOrig();

    String getText();

    @Nullable
    Formatting getFormatting();

    @Nullable
    String getHyperlink();

    List<SourceType> getSource();

    List<FineRef> getComments();
  }

  public enum Script {
    @JsonProperty("baseline") BASELINE,
    @JsonProperty("sub") SUB,
    @JsonProperty("super") SUPER
  }

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @tools.jackson.databind.annotation.JsonDeserialize(builder = Formatting.Builder.class)
  @lombok.extern.jackson.Jacksonized
  @lombok.Builder(toBuilder = true)
  @lombok.Getter
  @lombok.ToString
  public static class Formatting {
    @JsonProperty("bold")
    private boolean bold;

    @JsonProperty("italic")
    private boolean italic;

    @JsonProperty("underline")
    private boolean underline;

    @JsonProperty("strikethrough")
    private boolean strikethrough;

    @JsonProperty("script")
    private Script script;

    @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
    public static class Builder { }
  }

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @tools.jackson.databind.annotation.JsonDeserialize(builder = TitleItem.Builder.class)
  @lombok.extern.jackson.Jacksonized
  @lombok.Builder(toBuilder = true)
  @lombok.Getter
  @lombok.ToString
  public static final class TitleItem implements BaseTextItem {
    @JsonProperty("self_ref")
    private String selfRef;

    @JsonProperty("parent")
    @Nullable
    private RefItem parent;

    @JsonProperty("children")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular("child")
    private List<RefItem> children;

    @JsonProperty("content_layer")
    private ContentLayer contentLayer;

    @JsonProperty("meta")
    @Nullable
    private BaseMeta meta;

    @JsonProperty("label")
    private DocItemLabel label;

    @JsonProperty("prov")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular("prov")
    private List<ProvenanceItem> prov;

    @JsonProperty("orig")
    private String orig;

    @JsonProperty("text")
    private String text;

    @JsonProperty("formatting")
    @Nullable
    private Formatting formatting;

    @JsonProperty("hyperlink")
    @Nullable
    private String hyperlink;

    @JsonProperty("source")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular("source")
    private List<SourceType> source;

    @JsonProperty("comments")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular
    private List<FineRef> comments;

    @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
    public static class Builder { }
  }

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @tools.jackson.databind.annotation.JsonDeserialize(builder = SectionHeaderItem.Builder.class)
  @lombok.extern.jackson.Jacksonized
  @lombok.Builder(toBuilder = true)
  @lombok.Getter
  @lombok.ToString
  public static final class SectionHeaderItem implements BaseTextItem {
    @JsonProperty("self_ref")
    private String selfRef;

    @JsonProperty("parent")
    @Nullable
    private RefItem parent;

    @JsonProperty("children")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular("child")
    private List<RefItem> children;

    @JsonProperty("content_layer")
    private ContentLayer contentLayer;

    @JsonProperty("meta")
    @Nullable
    private BaseMeta meta;

    @JsonProperty("label")
    private DocItemLabel label;

    @JsonProperty("prov")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular("prov")
    private List<ProvenanceItem> prov;

    @JsonProperty("orig")
    private String orig;

    @JsonProperty("text")
    private String text;

    @JsonProperty("formatting")
    @Nullable
    private Formatting formatting;

    @JsonProperty("hyperlink")
    @Nullable
    private String hyperlink;

    @JsonProperty("source")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular("source")
    private List<SourceType> source;

    @JsonProperty("comments")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular
    private List<FineRef> comments;

    @JsonProperty("level")
    private Integer level;

    @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
    public static class Builder { }
  }

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @tools.jackson.databind.annotation.JsonDeserialize(builder = FieldHeadingItem.Builder.class)
  @lombok.extern.jackson.Jacksonized
  @lombok.Builder(toBuilder = true)
  @lombok.Getter
  @lombok.ToString
  public static final class FieldHeadingItem implements BaseTextItem {
    @JsonProperty("self_ref")
    private String selfRef;

    @JsonProperty("parent")
    @Nullable
    private RefItem parent;

    @JsonProperty("children")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular("child")
    private List<RefItem> children;

    @JsonProperty("content_layer")
    private ContentLayer contentLayer;

    @JsonProperty("meta")
    @Nullable
    private BaseMeta meta;

    @JsonProperty("label")
    private DocItemLabel label;

    @JsonProperty("prov")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular("prov")
    private List<ProvenanceItem> prov;

    @JsonProperty("orig")
    private String orig;

    @JsonProperty("text")
    private String text;

    @JsonProperty("formatting")
    @Nullable
    private Formatting formatting;

    @JsonProperty("hyperlink")
    @Nullable
    private String hyperlink;

    @JsonProperty("source")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular("source")
    private List<SourceType> source;

    @JsonProperty("comments")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular
    private List<FineRef> comments;

    @JsonProperty("level")
    private Integer level;

    @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
    public static class Builder { }
  }

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @tools.jackson.databind.annotation.JsonDeserialize(builder = ListItem.Builder.class)
  @lombok.extern.jackson.Jacksonized
  @lombok.Builder(toBuilder = true)
  @lombok.Getter
  @lombok.ToString
  public static final class ListItem implements BaseTextItem {
    @JsonProperty("self_ref")
    private String selfRef;

    @JsonProperty("parent")
    @Nullable
    private RefItem parent;

    @JsonProperty("children")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular("child")
    private List<RefItem> children;

    @JsonProperty("content_layer")
    private ContentLayer contentLayer;

    @JsonProperty("meta")
    @Nullable
    private BaseMeta meta;

    @JsonProperty("label")
    private DocItemLabel label;

    @JsonProperty("prov")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular("prov")
    private List<ProvenanceItem> prov;

    @JsonProperty("orig")
    private String orig;

    @JsonProperty("text")
    private String text;

    @JsonProperty("formatting")
    @Nullable
    private Formatting formatting;

    @JsonProperty("hyperlink")
    @Nullable
    private String hyperlink;

    @JsonProperty("source")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular("source")
    private List<SourceType> source;

    @JsonProperty("comments")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular
    private List<FineRef> comments;

    @JsonProperty("enumerated")
    private boolean enumerated;

    @JsonProperty("marker")
    @Nullable
    private String marker;

    @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
    public static class Builder { }
  }

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @tools.jackson.databind.annotation.JsonDeserialize(builder = CodeItem.Builder.class)
  @lombok.extern.jackson.Jacksonized
  @lombok.Builder(toBuilder = true)
  @lombok.Getter
  @lombok.ToString
  public static final class CodeItem implements BaseTextItem {
    @JsonProperty("self_ref")
    private String selfRef;

    @JsonProperty("parent")
    @Nullable
    private RefItem parent;

    @JsonProperty("children")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular("child")
    private List<RefItem> children;

    @JsonProperty("content_layer")
    private ContentLayer contentLayer;

    @JsonProperty("meta")
    @Nullable
    private FloatingMeta meta;

    @JsonProperty("label")
    private DocItemLabel label;

    @JsonProperty("prov")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular("prov")
    private List<ProvenanceItem> prov;

    @JsonProperty("orig")
    private String orig;

    @JsonProperty("text")
    private String text;

    @JsonProperty("formatting")
    @Nullable
    private Formatting formatting;

    @JsonProperty("hyperlink")
    @Nullable
    private String hyperlink;

    @JsonProperty("source")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular("source")
    private List<SourceType> source;

    @JsonProperty("comments")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular
    private List<FineRef> comments;

    @JsonProperty("captions")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular
    private List<RefItem> captions;

    @JsonProperty("references")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular
    private List<RefItem> references;

    @JsonProperty("footnotes")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular
    private List<RefItem> footnotes;

    @JsonProperty("image")
    @Nullable
    private ImageRef image;

    @JsonProperty("code_language")
    @Nullable
    private String codeLanguage;

    @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
    public static class Builder { }
  }

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @tools.jackson.databind.annotation.JsonDeserialize(builder = FormulaItem.Builder.class)
  @lombok.extern.jackson.Jacksonized
  @lombok.Builder(toBuilder = true)
  @lombok.Getter
  @lombok.ToString
  public static final class FormulaItem implements BaseTextItem {
    @JsonProperty("self_ref")
    private String selfRef;

    @JsonProperty("parent")
    @Nullable
    private RefItem parent;

    @JsonProperty("children")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular("child")
    private List<RefItem> children;

    @JsonProperty("content_layer")
    private ContentLayer contentLayer;

    @JsonProperty("meta")
    @Nullable
    private BaseMeta meta;

    @JsonProperty("label")
    private DocItemLabel label;

    @JsonProperty("prov")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular("prov")
    private List<ProvenanceItem> prov;

    @JsonProperty("orig")
    private String orig;

    @JsonProperty("text")
    private String text;

    @JsonProperty("formatting")
    @Nullable
    private Formatting formatting;

    @JsonProperty("hyperlink")
    @Nullable
    private String hyperlink;

    @JsonProperty("source")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular("source")
    private List<SourceType> source;

    @JsonProperty("comments")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular
    private List<FineRef> comments;

    @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
    public static class Builder { }
  }

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @tools.jackson.databind.annotation.JsonDeserialize(builder = TextItem.Builder.class)
  @lombok.extern.jackson.Jacksonized
  @lombok.Builder(toBuilder = true)
  @lombok.Getter
  @lombok.ToString
  public static final class TextItem implements BaseTextItem {
    @JsonProperty("self_ref")
    private String selfRef;

    @JsonProperty("parent")
    @Nullable
    private RefItem parent;

    @JsonProperty("children")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular("child")
    private List<RefItem> children;

    @JsonProperty("content_layer")
    private ContentLayer contentLayer;

    @JsonProperty("meta")
    @Nullable
    private BaseMeta meta;

    @JsonProperty("label")
    private DocItemLabel label;

    @JsonProperty("prov")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular("prov")
    private List<ProvenanceItem> prov;

    @JsonProperty("orig")
    private String orig;

    @JsonProperty("text")
    private String text;

    @JsonProperty("formatting")
    @Nullable
    private Formatting formatting;

    @JsonProperty("hyperlink")
    @Nullable
    private String hyperlink;

    @JsonProperty("source")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular("source")
    private List<SourceType> source;

    @JsonProperty("comments")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular
    private List<FineRef> comments;

    @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
    public static class Builder { }
  }

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @tools.jackson.databind.annotation.JsonDeserialize(builder = FieldValueItem.Builder.class)
  @lombok.extern.jackson.Jacksonized
  @lombok.Builder(toBuilder = true)
  @lombok.Getter
  @lombok.ToString
  public static final class FieldValueItem implements BaseTextItem {
    @JsonProperty("self_ref")
    private String selfRef;

    @JsonProperty("parent")
    @Nullable
    private RefItem parent;

    @JsonProperty("children")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular("child")
    private List<RefItem> children;

    @JsonProperty("content_layer")
    private ContentLayer contentLayer;

    @JsonProperty("meta")
    @Nullable
    private BaseMeta meta;

    @JsonProperty("label")
    private DocItemLabel label;

    @JsonProperty("prov")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular("prov")
    private List<ProvenanceItem> prov;

    @JsonProperty("orig")
    private String orig;

    @JsonProperty("text")
    private String text;

    @JsonProperty("formatting")
    @Nullable
    private Formatting formatting;

    @JsonProperty("hyperlink")
    @Nullable
    private String hyperlink;

    @JsonProperty("source")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular("source")
    private List<SourceType> source;

    @JsonProperty("comments")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular
    private List<FineRef> comments;

    @JsonProperty("kind")
    @Nullable
    private String kind;

    @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
    public static class Builder { }
  }

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @tools.jackson.databind.annotation.JsonDeserialize(builder = PictureItem.Builder.class)
  @lombok.extern.jackson.Jacksonized
  @lombok.Builder(toBuilder = true)
  @lombok.Getter
  @lombok.ToString
  public static class PictureItem {
    @JsonProperty("self_ref")
    @Nullable
    private String selfRef;
    
    @JsonProperty("parent")
    private RefItem parent;
    
    @JsonProperty("children")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular("child")
    private List<RefItem> children;
    
    @JsonProperty("content_layer")
    private ContentLayer contentLayer;
    
    @JsonProperty("meta")
    @Nullable
    private PictureMeta meta;
    
    @JsonProperty("label")
    private String label;
    
    @JsonProperty("prov")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular("prov")
    private List<ProvenanceItem> prov;
    
    @JsonProperty("captions")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    private List<RefItem> captions;
    
    @JsonProperty("references")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    private List<RefItem> references;
    
    @JsonProperty("footnotes")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    private List<RefItem> footnotes;
    
    @JsonProperty("image")
    @Nullable
    private ImageRef image;

    @JsonProperty("source")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular("source")
    private List<SourceType> source;

    @JsonProperty("comments")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular
    private List<FineRef> comments;

    @JsonProperty("annotations")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular
    private List<Map<String, Object>> annotations;

    @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
    public static class Builder { }
  }

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @tools.jackson.databind.annotation.JsonDeserialize(builder = PictureMeta.Builder.class)
  @lombok.extern.jackson.Jacksonized
  @lombok.Builder(toBuilder = true)
  @lombok.Getter
  @lombok.ToString
  public static class PictureMeta {
    @JsonProperty("summary")
    @Nullable
    private SummaryMetaField summary;

    @JsonProperty("language")
    @Nullable
    private LanguageMetaField language;

    @JsonProperty("entities")
    @Nullable
    private EntitiesMetaField entities;

    @JsonProperty("keywords")
    @Nullable
    private KeywordsMetaField keywords;

    @JsonProperty("topics")
    @Nullable
    private TopicsMetaField topics;

    @JsonProperty("description")
    @Nullable
    private DescriptionMetaField description;

    @JsonProperty("classification")
    @Nullable
    private PictureClassificationMetaField classification;

    @JsonProperty("molecule")
    @Nullable
    private MoleculeMetaField molecule;

    @JsonProperty("tabular_chart")
    @Nullable
    private TabularChartMetaField tabularChart;

    @JsonProperty("code")
    @Nullable
    private CodeMetaField code;

    @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
    public static class Builder { }
  }

  /**
   * Source-code representation associated with a picture node (e.g. the code that renders a chart
   * or diagram). Corresponds to the Python {@code CodeMetaField}; {@code language} is carried as a
   * raw string to mirror the lenient handling of {@code code_language} elsewhere in the model.
   */
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @tools.jackson.databind.annotation.JsonDeserialize(builder = CodeMetaField.Builder.class)
  @lombok.extern.jackson.Jacksonized
  @lombok.Builder(toBuilder = true)
  @lombok.Getter
  @lombok.ToString
  public static class CodeMetaField {
    @JsonProperty("confidence")
    @Nullable
    private Double confidence;

    @JsonProperty("created_by")
    @Nullable
    private String createdBy;

    @JsonProperty("text")
    private String text;

    @JsonProperty("language")
    @Nullable
    private String language;

    @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
    public static class Builder { }
  }

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @tools.jackson.databind.annotation.JsonDeserialize(builder = DescriptionMetaField.Builder.class)
  @lombok.extern.jackson.Jacksonized
  @lombok.Builder(toBuilder = true)
  @lombok.Getter
  @lombok.ToString
  public static class DescriptionMetaField {
    @JsonProperty("confidence")
    @Nullable
    private Double confidence;
    
    @JsonProperty("created_by")
    @Nullable
    private String createdBy;
    
    @JsonProperty("text")
    private String text;

    @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
    public static class Builder { }
  }

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @tools.jackson.databind.annotation.JsonDeserialize(builder = PictureClassificationMetaField.Builder.class)
  @lombok.extern.jackson.Jacksonized
  @lombok.Builder(toBuilder = true)
  @lombok.Getter
  @lombok.ToString
  public static class PictureClassificationMetaField {
    @JsonProperty("predictions")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular
    private List<PictureClassificationPrediction> predictions;

    @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
    public static class Builder { }
  }

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @tools.jackson.databind.annotation.JsonDeserialize(builder = PictureClassificationPrediction.Builder.class)
  @lombok.extern.jackson.Jacksonized
  @lombok.Builder(toBuilder = true)
  @lombok.Getter
  @lombok.ToString
  public static class PictureClassificationPrediction {
    @JsonProperty("confidence")
    @Nullable
    private Double confidence;
    
    @JsonProperty("created_by")
    @Nullable
    private String createdBy;
    
    @JsonProperty("class_name")
    private String className;

    @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
    public static class Builder { }
  }

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @tools.jackson.databind.annotation.JsonDeserialize(builder = MoleculeMetaField.Builder.class)
  @lombok.extern.jackson.Jacksonized
  @lombok.Builder(toBuilder = true)
  @lombok.Getter
  @lombok.ToString
  public static class MoleculeMetaField {
    @JsonProperty("confidence")
    @Nullable
    private Double confidence;
    
    @JsonProperty("created_by")
    @Nullable
    private String createdBy;
    
    @JsonProperty("smi")
    private String smi;

    @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
    public static class Builder { }
  }

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @tools.jackson.databind.annotation.JsonDeserialize(builder = TabularChartMetaField.Builder.class)
  @lombok.extern.jackson.Jacksonized
  @lombok.Builder(toBuilder = true)
  @lombok.Getter
  @lombok.ToString
  public static class TabularChartMetaField {
    @JsonProperty("confidence")
    @Nullable
    private Double confidence;
    
    @JsonProperty("created_by")
    @Nullable
    private String createdBy;
    
    @JsonProperty("title")
    @Nullable
    private String title;
    
    @JsonProperty("chart_data")
    private TableData chartData;

    @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
    public static class Builder { }
  }

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @tools.jackson.databind.annotation.JsonDeserialize(builder = ProvenanceItem.Builder.class)
  @lombok.extern.jackson.Jacksonized
  @lombok.Builder(toBuilder = true)
  @lombok.Getter
  @lombok.ToString
  public static class ProvenanceItem {
    @JsonProperty("page_no")
    private Integer pageNo;
    
    @JsonProperty("bbox")
    private BoundingBox bbox;
    
    @JsonProperty("charspan")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular("charspan")
    private List<Integer> charspan;

    @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
    public static class Builder { }
  }

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @tools.jackson.databind.annotation.JsonDeserialize(builder = BoundingBox.Builder.class)
  @lombok.extern.jackson.Jacksonized
  @lombok.Builder(toBuilder = true)
  @lombok.Getter
  @lombok.ToString
  public static class BoundingBox {
    @JsonProperty("l")
    private Double l;
    
    @JsonProperty("t")
    private Double t;
    
    @JsonProperty("r")
    private Double r;
    
    @JsonProperty("b")
    private Double b;
    
    @JsonProperty("coord_origin")
    @Nullable
    private String coordOrigin;

    @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
    public static class Builder { }
  }

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @tools.jackson.databind.annotation.JsonDeserialize(builder = ImageRef.Builder.class)
  @lombok.extern.jackson.Jacksonized
  @lombok.Builder(toBuilder = true)
  @lombok.Getter
  @lombok.ToString
  public static class ImageRef {
    @JsonProperty("mimetype")
    private String mimetype;
    
    @JsonProperty("dpi")
    private Integer dpi;
    
    @JsonProperty("size")
    private Size size;
    
    @JsonProperty("uri")
    private String uri;

    @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
    public static class Builder { }
  }

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @tools.jackson.databind.annotation.JsonDeserialize(builder = Size.Builder.class)
  @lombok.extern.jackson.Jacksonized
  @lombok.Builder(toBuilder = true)
  @lombok.Getter
  @lombok.ToString
  public static class Size {
    @JsonProperty("width")
    private Double width;
    
    @JsonProperty("height")
    private Double height;

    @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
    public static class Builder { }
  }

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @tools.jackson.databind.annotation.JsonDeserialize(builder = TableItem.Builder.class)
  @lombok.extern.jackson.Jacksonized
  @lombok.Builder(toBuilder = true)
  @lombok.Getter
  @lombok.ToString
  public static class TableItem {
    @JsonProperty("self_ref")
    private String selfRef;
    
    @JsonProperty("parent")
    @Nullable
    private RefItem parent;
    
    @JsonProperty("children")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular("child")
    private List<RefItem> children;
    
    @JsonProperty("content_layer")
    private ContentLayer contentLayer;
    
    @JsonProperty("meta")
    @Nullable
    private FloatingMeta meta;
    
    @JsonProperty("label")
    private String label;
    
    @JsonProperty("prov")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular("prov")
    private List<ProvenanceItem> prov;
    
    @JsonProperty("captions")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular
    private List<RefItem> captions;
    
    @JsonProperty("references")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular
    private List<RefItem> references;
    
    @JsonProperty("footnotes")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular
    private List<RefItem> footnotes;
    
    @JsonProperty("image")
    @Nullable
    private ImageRef image;
    
    @JsonProperty("data")
    private TableData data;

    @JsonProperty("source")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular("source")
    private List<SourceType> source;

    @JsonProperty("comments")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular
    private List<FineRef> comments;

    @JsonProperty("annotations")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular
    private List<Map<String, Object>> annotations;

    @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
    public static class Builder { }
  }

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @tools.jackson.databind.annotation.JsonDeserialize(builder = FloatingMeta.Builder.class)
  @lombok.extern.jackson.Jacksonized
  @lombok.Builder(toBuilder = true)
  @lombok.Getter
  @lombok.ToString
  public static class FloatingMeta {
    @JsonProperty("summary")
    @Nullable
    private SummaryMetaField summary;

    @JsonProperty("language")
    @Nullable
    private LanguageMetaField language;

    @JsonProperty("entities")
    @Nullable
    private EntitiesMetaField entities;

    @JsonProperty("keywords")
    @Nullable
    private KeywordsMetaField keywords;

    @JsonProperty("topics")
    @Nullable
    private TopicsMetaField topics;

    @JsonProperty("description")
    @Nullable
    private DescriptionMetaField description;

    @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
    public static class Builder { }
  }

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @tools.jackson.databind.annotation.JsonDeserialize(builder = TableData.Builder.class)
  @lombok.extern.jackson.Jacksonized
  @lombok.Builder(toBuilder = true)
  @lombok.Getter
  @lombok.ToString
  public static class TableData {
    @JsonProperty("table_cells")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular
    private List<TableCell> tableCells;
    
    @JsonProperty("num_rows")
    private Integer numRows;
    
    @JsonProperty("num_cols")
    private Integer numCols;
    
    @JsonProperty("grid")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular("grid")
    private List<List<TableCell>> grid;

    @JsonProperty("orientation")
    @Nullable
    private Orientation orientation;

    @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
    public static class Builder { }
  }

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @tools.jackson.databind.annotation.JsonDeserialize(builder = TableCell.Builder.class)
  @lombok.extern.jackson.Jacksonized
  @lombok.Builder(toBuilder = true)
  @lombok.Getter
  @lombok.ToString
  public static class TableCell {
    @JsonProperty("bbox")
    private BoundingBox bbox;
    
    @JsonProperty("row_span")
    private Integer rowSpan;
    
    @JsonProperty("col_span")
    private Integer colSpan;
    
    @JsonProperty("start_row_offset_idx")
    private Integer startRowOffsetIdx;
    
    @JsonProperty("end_row_offset_idx")
    private Integer endRowOffsetIdx;
    
    @JsonProperty("start_col_offset_idx")
    private Integer startColOffsetIdx;
    
    @JsonProperty("end_col_offset_idx")
    private Integer endColOffsetIdx;
    
    @JsonProperty("text")
    private String text;
    
    @JsonProperty("column_header")
    private boolean columnHeader;
    
    @JsonProperty("row_header")
    private boolean rowHeader;
    
    @JsonProperty("row_section")
    private boolean rowSection;
    
    @JsonProperty("fillable")
    private boolean fillable;

    @JsonProperty("ref")
    @Nullable
    private RefItem ref;

    @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
    public static class Builder { }
  }

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @tools.jackson.databind.annotation.JsonDeserialize(builder = KeyValueItem.Builder.class)
  @lombok.extern.jackson.Jacksonized
  @lombok.Builder(toBuilder = true)
  @lombok.Getter
  @lombok.ToString
  public static class KeyValueItem {
    @JsonProperty("self_ref")
    private String selfRef;
    
    @JsonProperty("parent")
    @Nullable
    private RefItem parent;
    
    @JsonProperty("children")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular("child")
    private List<RefItem> children;
    
    @JsonProperty("content_layer")
    private ContentLayer contentLayer;
    
    @JsonProperty("meta")
    @Nullable
    private FloatingMeta meta;
    
    @JsonProperty("label")
    private String label;
    
    @JsonProperty("prov")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular("prov")
    private List<ProvenanceItem> prov;
    
    @JsonProperty("captions")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular
    private List<RefItem> captions;
    
    @JsonProperty("references")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular
    private List<RefItem> references;
    
    @JsonProperty("footnotes")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular
    private List<RefItem> footnotes;
    
    @JsonProperty("image")
    @Nullable
    private ImageRef image;
    
    @JsonProperty("graph")
    private GraphData graph;

    @JsonProperty("source")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular("source")
    private List<SourceType> source;

    @JsonProperty("comments")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular
    private List<FineRef> comments;

    @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
    public static class Builder { }
  }

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @tools.jackson.databind.annotation.JsonDeserialize(builder = GraphData.Builder.class)
  @lombok.extern.jackson.Jacksonized
  @lombok.Builder(toBuilder = true)
  @lombok.Getter
  @lombok.ToString
  public static class GraphData {
    @JsonProperty("cells")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular
    private List<GraphCell> cells;
    
    @JsonProperty("links")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular
    private List<GraphLink> links;

    @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
    public static class Builder { }
  }

  public enum GraphCellLabel {
    @JsonProperty("unspecified") UNSPECIFIED,
    @JsonProperty("key") KEY,
    @JsonProperty("value") VALUE,
    @JsonProperty("checkbox") CHECKBOX
  }

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @tools.jackson.databind.annotation.JsonDeserialize(builder = GraphCell.Builder.class)
  @lombok.extern.jackson.Jacksonized
  @lombok.Builder(toBuilder = true)
  @lombok.Getter
  @lombok.ToString
  public static class GraphCell {
    @JsonProperty("label")
    private GraphCellLabel label;
    
    @JsonProperty("cell_id")
    private Integer cellId;
    
    @JsonProperty("text")
    private String text;
    
    @JsonProperty("orig")
    private String orig;
    
    @JsonProperty("prov")
    @Nullable
    private ProvenanceItem prov;
    
    @JsonProperty("item_ref")
    @Nullable
    private RefItem itemRef;

    @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
    public static class Builder { }
  }

  public enum GraphLinkLabel {
    @JsonProperty("unspecified") UNSPECIFIED,
    @JsonProperty("to_value") TO_VALUE,
    @JsonProperty("to_key") TO_KEY,
    @JsonProperty("to_parent") TO_PARENT,
    @JsonProperty("to_child") TO_CHILD
  }

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @tools.jackson.databind.annotation.JsonDeserialize(builder = GraphLink.Builder.class)
  @lombok.extern.jackson.Jacksonized
  @lombok.Builder(toBuilder = true)
  @lombok.Getter
  @lombok.ToString
  public static class GraphLink {
    @JsonProperty("label")
    private GraphLinkLabel label;
    
    @JsonProperty("source_cell_id")
    private Integer sourceCellId;
    
    @JsonProperty("target_cell_id")
    private Integer targetCellId;

    @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
    public static class Builder { }
  }

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @tools.jackson.databind.annotation.JsonDeserialize(builder = FormItem.Builder.class)
  @lombok.extern.jackson.Jacksonized
  @lombok.Builder(toBuilder = true)
  @lombok.Getter
  @lombok.ToString
  public static class FormItem {
    @JsonProperty("self_ref")
    private String selfRef;
    
    @JsonProperty("parent")
    @Nullable
    private RefItem parent;
    
    @JsonProperty("children")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular("child")
    private List<RefItem> children;
    
    @JsonProperty("content_layer")
    private ContentLayer contentLayer;
    
    @JsonProperty("meta")
    @Nullable
    private FloatingMeta meta;
    
    @JsonProperty("label")
    private String label;
    
    @JsonProperty("prov")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular("prov")
    private List<ProvenanceItem> prov;
    
    @JsonProperty("captions")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular
    private List<RefItem> captions;
    
    @JsonProperty("references")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular
    private List<RefItem> references;
    
    @JsonProperty("footnotes")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular
    private List<RefItem> footnotes;
    
    @JsonProperty("image")
    @Nullable
    private ImageRef image;
    
    @JsonProperty("graph")
    private GraphData graph;

    @JsonProperty("source")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular("source")
    private List<SourceType> source;

    @JsonProperty("comments")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular
    private List<FineRef> comments;

    @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
    public static class Builder { }
  }

  /**
   * Represents a form field region container, grouping one or more related field items within a
   * document. Corresponds to the Python {@code FieldRegionItem} with label {@code "field_region"}.
   */
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @tools.jackson.databind.annotation.JsonDeserialize(builder = FieldRegionItem.Builder.class)
  @lombok.extern.jackson.Jacksonized
  @lombok.Builder(toBuilder = true)
  @lombok.Getter
  @lombok.ToString
  public static class FieldRegionItem {
    @JsonProperty("self_ref")
    private String selfRef;

    @JsonProperty("parent")
    @Nullable
    private RefItem parent;

    @JsonProperty("children")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular("child")
    private List<RefItem> children;

    @JsonProperty("content_layer")
    private ContentLayer contentLayer;

    @JsonProperty("meta")
    @Nullable
    private BaseMeta meta;

    @JsonProperty("label")
    private DocItemLabel label;

    @JsonProperty("prov")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular("prov")
    private List<ProvenanceItem> prov;

    @JsonProperty("source")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular("source")
    private List<SourceType> source;

    @JsonProperty("comments")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular
    private List<FineRef> comments;

    @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
    public static class Builder { }
  }

  /**
   * Represents a single form field item within a document, typically nested inside a
   * {@link FieldRegionItem}. Corresponds to the Python {@code FieldItem} with label
   * {@code "field_item"}.
   */
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @tools.jackson.databind.annotation.JsonDeserialize(builder = FieldItem.Builder.class)
  @lombok.extern.jackson.Jacksonized
  @lombok.Builder(toBuilder = true)
  @lombok.Getter
  @lombok.ToString
  public static class FieldItem {
    @JsonProperty("self_ref")
    private String selfRef;

    @JsonProperty("parent")
    @Nullable
    private RefItem parent;

    @JsonProperty("children")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular("child")
    private List<RefItem> children;

    @JsonProperty("content_layer")
    private ContentLayer contentLayer;

    @JsonProperty("meta")
    @Nullable
    private BaseMeta meta;

    @JsonProperty("label")
    private DocItemLabel label;

    @JsonProperty("prov")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular("prov")
    private List<ProvenanceItem> prov;

    @JsonProperty("source")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular("source")
    private List<SourceType> source;

    @JsonProperty("comments")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    @lombok.Singular
    private List<FineRef> comments;

    @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
    public static class Builder { }
  }

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @tools.jackson.databind.annotation.JsonDeserialize(builder = PageItem.Builder.class)
  @lombok.extern.jackson.Jacksonized
  @lombok.Builder(toBuilder = true)
  @lombok.Getter
  @lombok.ToString
  public static class PageItem {
    @JsonProperty("size")
    private Size size;
    
    @JsonProperty("image")
    @Nullable
    private ImageRef image;
    
    @JsonProperty("page_no")
    private Integer pageNo;

    @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
    public static class Builder { }
  }

  @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
  public static class Builder { }

}
