package ai.docling.serve.api.chunk.request.options;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link HierarchicalChunkerOptions}.
 */
class HierarchicalChunkerOptionsTests {

  @Test
  void buildWithDefaultValues() {
    HierarchicalChunkerOptions options = HierarchicalChunkerOptions.builder().build();

    assertThat(options.isUseMarkdownTables()).isFalse();
    assertThat(options.isIncludeRawText()).isFalse();
  }

  @Test
  void buildWithUseMarkdownTables() {
    HierarchicalChunkerOptions options = HierarchicalChunkerOptions.builder()
        .useMarkdownTables(true)
        .build();

    assertThat(options.isUseMarkdownTables()).isTrue();
    assertThat(options.isIncludeRawText()).isFalse();
  }

  @Test
  void buildWithIncludeRawText() {
    HierarchicalChunkerOptions options = HierarchicalChunkerOptions.builder()
        .includeRawText(true)
        .build();

    assertThat(options.isUseMarkdownTables()).isFalse();
    assertThat(options.isIncludeRawText()).isTrue();
  }

  @Test
  void buildWithAllFields() {
    HierarchicalChunkerOptions options = HierarchicalChunkerOptions.builder()
        .useMarkdownTables(true)
        .includeRawText(true)
        .build();

    assertThat(options.isUseMarkdownTables()).isTrue();
    assertThat(options.isIncludeRawText()).isTrue();
  }

  @Test
  void buildWithBothFieldsFalse() {
    HierarchicalChunkerOptions options = HierarchicalChunkerOptions.builder()
        .useMarkdownTables(false)
        .includeRawText(false)
        .build();

    assertThat(options.isUseMarkdownTables()).isFalse();
    assertThat(options.isIncludeRawText()).isFalse();
  }

  @Test
  void hierarchicalChunkerOptionsIsImmutable() {
    HierarchicalChunkerOptions options = HierarchicalChunkerOptions.builder()
        .useMarkdownTables(true)
        .includeRawText(false)
        .build();

    HierarchicalChunkerOptions modified = options.toBuilder()
        .includeRawText(true)
        .build();

    assertThat(options.isUseMarkdownTables()).isTrue();
    assertThat(options.isIncludeRawText()).isFalse();

    assertThat(modified.isUseMarkdownTables()).isTrue();
    assertThat(modified.isIncludeRawText()).isTrue();
  }

  @Test
  void toBuilderPreservesAllFields() {
    HierarchicalChunkerOptions original = HierarchicalChunkerOptions.builder()
        .useMarkdownTables(true)
        .includeRawText(true)
        .build();

    HierarchicalChunkerOptions copy = original.toBuilder().build();

    assertThat(copy.isUseMarkdownTables()).isEqualTo(original.isUseMarkdownTables());
    assertThat(copy.isIncludeRawText()).isEqualTo(original.isIncludeRawText());
  }

  @Test
  void toBuilderAllowsModification() {
    HierarchicalChunkerOptions original = HierarchicalChunkerOptions.builder()
        .useMarkdownTables(false)
        .includeRawText(false)
        .build();

    HierarchicalChunkerOptions modified = original.toBuilder()
        .useMarkdownTables(true)
        .includeRawText(true)
        .build();

    assertThat(original.isUseMarkdownTables()).isFalse();
    assertThat(original.isIncludeRawText()).isFalse();
    assertThat(modified.isUseMarkdownTables()).isTrue();
    assertThat(modified.isIncludeRawText()).isTrue();
  }
}
