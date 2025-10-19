package ai.docling.api.convert.request.options;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * OCR engines supported by Docling.
 */
public enum OcrEngine {

  @JsonProperty("easyocr") EASYOCR,
  @JsonProperty("mac") OCRMAC,
  @JsonProperty("rapidocr") RAPIDOCR,
  @JsonProperty("tesseract") TESSEROCR,
  @JsonProperty("tesseract_cloud") TESSERACT

}
