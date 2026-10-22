package ai.docling.serve.api.convert.request.options;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link HeadingHierarchyOptions}.
 */
class HeadingHierarchyOptionsTests {

  @Test
  void createWithAllFields() {
    var options = HeadingHierarchyOptions.builder()
        .useBookmarks(true)
        .useNumbering(false)
        .useStyle(true)
        .useFontStyle(true)
        .styleSizeTolerance(0.1)
        .numberingScheme("chapter")
        .numberingScheme("section")
        .maxLevel(4)
        .bookmarkMatchThreshold(0.9)
        .build();

    assertThat(options)
        .extracting(
            HeadingHierarchyOptions::getUseBookmarks, HeadingHierarchyOptions::getUseNumbering, HeadingHierarchyOptions::getUseStyle, HeadingHierarchyOptions::getUseFontStyle, HeadingHierarchyOptions::getStyleSizeTolerance, HeadingHierarchyOptions::getNumberingSchemes, HeadingHierarchyOptions::getMaxLevel, HeadingHierarchyOptions::getBookmarkMatchThreshold)
        .containsExactly(true, false, true, true, 0.1, List.of("chapter", "section"), 4, 0.9);
  }

  @Test
  void createWithMinimalFields() {
    var options = HeadingHierarchyOptions.builder()
        .useBookmarks(false)
        .build();

    assertThat(options.getUseBookmarks()).isFalse();
    assertThat(options.getUseNumbering()).isNull();
    assertThat(options.getUseStyle()).isNull();
    assertThat(options.getUseFontStyle()).isNull();
    assertThat(options.getStyleSizeTolerance()).isNull();
    assertThat(options.getNumberingSchemes()).isEmpty();
    assertThat(options.getMaxLevel()).isNull();
    assertThat(options.getBookmarkMatchThreshold()).isNull();
  }

  @Test
  void createWithDefaultValues() {
    var options = HeadingHierarchyOptions.builder().build();

    assertThat(options.getUseBookmarks()).isNull();
    assertThat(options.getUseNumbering()).isNull();
    assertThat(options.getUseStyle()).isNull();
    assertThat(options.getUseFontStyle()).isNull();
    assertThat(options.getStyleSizeTolerance()).isNull();
    assertThat(options.getNumberingSchemes()).isEmpty();
    assertThat(options.getMaxLevel()).isNull();
    assertThat(options.getBookmarkMatchThreshold()).isNull();
  }

  @Test
  void headingHierarchyOptionsIsImmutable() {
    var options = HeadingHierarchyOptions.builder()
        .styleSizeTolerance(0.05)
        .numberingScheme("chapter")
        .build();

    assertThat(options.getNumberingSchemes()).hasSize(1);
    assertThat(options.getNumberingSchemes()).containsExactly("chapter");

    // Mutating the returned list should not affect the options
    assertThatThrownBy(() -> options.getNumberingSchemes().add("section"))
        .isInstanceOf(UnsupportedOperationException.class);
  }

  @Test
  void toBuilderReturnsEquivalentInstance() {
    var original = HeadingHierarchyOptions.builder()
        .useBookmarks(false)
        .maxLevel(3)
        .numberingScheme("article")
        .numberingScheme("section")
        .bookmarkMatchThreshold(0.85)
        .build();

    var rebuilt = original.toBuilder().build();

    assertThat(rebuilt.getUseBookmarks()).isEqualTo(original.getUseBookmarks());
    assertThat(rebuilt.getUseNumbering()).isEqualTo(original.getUseNumbering());
    assertThat(rebuilt.getUseStyle()).isEqualTo(original.getUseStyle());
    assertThat(rebuilt.getUseFontStyle()).isEqualTo(original.getUseFontStyle());
    assertThat(rebuilt.getStyleSizeTolerance()).isEqualTo(original.getStyleSizeTolerance());
    assertThat(rebuilt.getNumberingSchemes()).containsExactlyElementsOf(original.getNumberingSchemes());
    assertThat(rebuilt.getMaxLevel()).isEqualTo(original.getMaxLevel());
    assertThat(rebuilt.getBookmarkMatchThreshold()).isEqualTo(original.getBookmarkMatchThreshold());
  }

  @Test
  void nonNullFieldsOnlyArentOmittedFromJson() {
    var options = HeadingHierarchyOptions.builder()
        .useBookmarks(true)
        .build();

    assertThat(options.getUseBookmarks()).isTrue();
    assertThat(options.getUseNumbering()).isNull();
    assertThat(options.getUseStyle()).isNull();
    assertThat(options.getUseFontStyle()).isNull();
    assertThat(options.getStyleSizeTolerance()).isNull();
    assertThat(options.getNumberingSchemes()).isEmpty();
    assertThat(options.getMaxLevel()).isNull();
    assertThat(options.getBookmarkMatchThreshold()).isNull();
  }
}
