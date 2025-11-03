package ai.docling.api.serve.convert.request.options;

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

}
