package ai.docling.serve.api.chunk.request.options;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link HybridChunkerOptions}.
 */
class HybridChunkerOptionsTests {

  @Test
  void buildWithDefaultValues() {
    HybridChunkerOptions options = HybridChunkerOptions.builder().build();

    assertThat(options.isUseMarkdownTables()).isFalse();
    assertThat(options.isIncludeRawText()).isFalse();
    assertThat(options.getMaxTokens()).isNull();
    assertThat(options.getTokenizer()).isNull();
    assertThat(options.getMergePeers()).isNull();
  }

  @Test
  void buildWithUseMarkdownTables() {
    HybridChunkerOptions options = HybridChunkerOptions.builder()
        .useMarkdownTables(true)
        .build();

    assertThat(options.isUseMarkdownTables()).isTrue();
    assertThat(options.isIncludeRawText()).isFalse();
    assertThat(options.getMaxTokens()).isNull();
    assertThat(options.getTokenizer()).isNull();
    assertThat(options.getMergePeers()).isNull();
  }

  @Test
  void buildWithIncludeRawText() {
    HybridChunkerOptions options = HybridChunkerOptions.builder()
        .includeRawText(true)
        .build();

    assertThat(options.isUseMarkdownTables()).isFalse();
    assertThat(options.isIncludeRawText()).isTrue();
    assertThat(options.getMaxTokens()).isNull();
    assertThat(options.getTokenizer()).isNull();
    assertThat(options.getMergePeers()).isNull();
  }

  @Test
  void buildWithMaxTokens() {
    HybridChunkerOptions options = HybridChunkerOptions.builder()
        .maxTokens(512)
        .build();

    assertThat(options.getMaxTokens()).isEqualTo(512);
    assertThat(options.isUseMarkdownTables()).isFalse();
    assertThat(options.isIncludeRawText()).isFalse();
    assertThat(options.getTokenizer()).isNull();
    assertThat(options.getMergePeers()).isNull();
  }

  @Test
  void buildWithTokenizer() {
    HybridChunkerOptions options = HybridChunkerOptions.builder()
        .tokenizer("bert-base-uncased")
        .build();

    assertThat(options.getTokenizer()).isEqualTo("bert-base-uncased");
    assertThat(options.isUseMarkdownTables()).isFalse();
    assertThat(options.isIncludeRawText()).isFalse();
    assertThat(options.getMaxTokens()).isNull();
    assertThat(options.getMergePeers()).isNull();
  }

  @Test
  void buildWithMergePeers() {
    HybridChunkerOptions options = HybridChunkerOptions.builder()
        .mergePeers(true)
        .build();

    assertThat(options.getMergePeers()).isTrue();
    assertThat(options.isUseMarkdownTables()).isFalse();
    assertThat(options.isIncludeRawText()).isFalse();
    assertThat(options.getMaxTokens()).isNull();
    assertThat(options.getTokenizer()).isNull();
  }

  @Test
  void buildWithAllFields() {
    HybridChunkerOptions options = HybridChunkerOptions.builder()
        .useMarkdownTables(true)
        .includeRawText(true)
        .maxTokens(256)
        .tokenizer("gpt-3")
        .mergePeers(false)
        .build();

    assertThat(options.isUseMarkdownTables()).isTrue();
    assertThat(options.isIncludeRawText()).isTrue();
    assertThat(options.getMaxTokens()).isEqualTo(256);
    assertThat(options.getTokenizer()).isEqualTo("gpt-3");
    assertThat(options.getMergePeers()).isFalse();
  }

  @Test
  void hybridChunkerOptionsIsImmutable() {
    HybridChunkerOptions options = HybridChunkerOptions.builder()
        .useMarkdownTables(true)
        .includeRawText(false)
        .maxTokens(1024)
        .tokenizer("original-tokenizer")
        .mergePeers(true)
        .build();

    HybridChunkerOptions modified = options.toBuilder()
        .maxTokens(2048)
        .tokenizer("new-tokenizer")
        .build();

    assertThat(options.isUseMarkdownTables()).isTrue();
    assertThat(options.isIncludeRawText()).isFalse();
    assertThat(options.getMaxTokens()).isEqualTo(1024);
    assertThat(options.getTokenizer()).isEqualTo("original-tokenizer");
    assertThat(options.getMergePeers()).isTrue();

    assertThat(modified.isUseMarkdownTables()).isTrue();
    assertThat(modified.isIncludeRawText()).isFalse();
    assertThat(modified.getMaxTokens()).isEqualTo(2048);
    assertThat(modified.getTokenizer()).isEqualTo("new-tokenizer");
    assertThat(modified.getMergePeers()).isTrue();
  }

  @Test
  void toBuilderPreservesAllFields() {
    HybridChunkerOptions original = HybridChunkerOptions.builder()
        .useMarkdownTables(true)
        .includeRawText(true)
        .maxTokens(768)
        .tokenizer("test-tokenizer")
        .mergePeers(false)
        .build();

    HybridChunkerOptions copy = original.toBuilder().build();

    assertThat(copy.isUseMarkdownTables()).isEqualTo(original.isUseMarkdownTables());
    assertThat(copy.isIncludeRawText()).isEqualTo(original.isIncludeRawText());
    assertThat(copy.getMaxTokens()).isEqualTo(original.getMaxTokens());
    assertThat(copy.getTokenizer()).isEqualTo(original.getTokenizer());
    assertThat(copy.getMergePeers()).isEqualTo(original.getMergePeers());
  }
}
