package ai.docling.serve.api.convert.request.options;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link ConvertDocumentOptions}.
 */
class ConvertDocumentOptionsTests {
  @Test
  void convertDocumentOptionsIsImmutable() {
    List<InputFormat> fromFormats = new ArrayList<>(List.of(InputFormat.PDF));
    List<OutputFormat> toFormats = new ArrayList<>(List.of(OutputFormat.MARKDOWN));
    List<String> ocrLang = new ArrayList<>(List.of("en", "de"));

    ConvertDocumentOptions options = ConvertDocumentOptions.builder()
        .fromFormat(InputFormat.PDF)
        .toFormat(OutputFormat.MARKDOWN)
        .ocrLang(ocrLang)
        .build();

    assertThat(options.getFromFormats()).isEqualTo(fromFormats);
    assertThat(options.getToFormats()).isEqualTo(toFormats);
    assertThat(options.getOcrLang()).containsExactlyInAnyOrderElementsOf(ocrLang);

    fromFormats.add(InputFormat.DOCX);
    toFormats.add(OutputFormat.JSON);
    ocrLang.add("fr");

    assertThat(options.getFromFormats()).hasSize(1);
    assertThat(options.getFromFormats().get(0)).isEqualTo(InputFormat.PDF);
    assertThat(options.getToFormats()).hasSize(1);
    assertThat(options.getToFormats().get(0)).isEqualTo(OutputFormat.MARKDOWN);
    assertThat(options.getOcrLang()).hasSize(2);
    assertThat(options.getOcrLang()).containsExactly("en", "de");
  }

  @Test
  void convertDocumentOptionsWithHeadingHierarchy() {
    var hierarchyOptions = HeadingHierarchyOptions.builder()
        .useBookmarks(true)
        .useNumbering(false)
        .maxLevel(4)
        .bookmarkMatchThreshold(0.85)
        .build();

    var options = ConvertDocumentOptions.builder()
        .headingHierarchyOptions(hierarchyOptions)
        .build();

    assertThat(options)
        .extracting(
            ConvertDocumentOptions::doPdfHeadingHierarchy, ConvertDocumentOptions::getHeadingHierarchyOptions, o -> o.getHeadingHierarchyOptions().getUseBookmarks(), o -> o
                .getHeadingHierarchyOptions().getUseNumbering(), o -> o.getHeadingHierarchyOptions().getMaxLevel(), o -> o.getHeadingHierarchyOptions().getBookmarkMatchThreshold())
        .containsExactly(true, hierarchyOptions, true, false, 4, 0.85);
  }

  @Test
  void convertDocumentOptionsWithoutHeadingHierarchy() {
    var options = ConvertDocumentOptions.builder().build();

    assertThat(options)
        .satisfies(o -> assertThat(o.doPdfHeadingHierarchy()).isFalse())
        .satisfies(o -> assertThat(o.getHeadingHierarchyOptions()).isNull());
  }

  @Test
  void convertDocumentOptionsToBuilderWithHeadingHierarchy() {
    var hierarchyOptions = HeadingHierarchyOptions.builder()
        .maxLevel(3)
        .build();

    var original = ConvertDocumentOptions.builder()
        .headingHierarchyOptions(hierarchyOptions)
        .build();

    var rebuilt = original.toBuilder().build();

    assertThat(rebuilt)
        .extracting(
            ConvertDocumentOptions::doPdfHeadingHierarchy, ConvertDocumentOptions::getHeadingHierarchyOptions, o -> o.getHeadingHierarchyOptions().getMaxLevel())
        .containsExactly(original.doPdfHeadingHierarchy(), original.getHeadingHierarchyOptions(), 3);
  }
}
