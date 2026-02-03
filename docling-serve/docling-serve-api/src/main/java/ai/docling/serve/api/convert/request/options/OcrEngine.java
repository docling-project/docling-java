package ai.docling.serve.api.convert.request.options;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * OCR engines supported by Docling.
 */
public enum OcrEngine {

  @JsonProperty("auto") AUTO,
  @JsonProperty("easyocr") EASYOCR,
  @JsonProperty("ocrmac") OCRMAC,
  @JsonProperty("rapidocr") RAPIDOCR,
  @JsonProperty("tesserocr") TESSEROCR,
  @JsonProperty("tesseract") TESSERACT

}
