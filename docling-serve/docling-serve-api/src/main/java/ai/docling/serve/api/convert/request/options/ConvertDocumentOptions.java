package ai.docling.serve.api.convert.request.options;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.jspecify.annotations.Nullable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ai.docling.serve.api.serialization.DurationSerializationFormat;
import ai.docling.serve.api.serialization.Jackson2DurationSerializer;
import ai.docling.serve.api.serialization.Jackson3DurationSerializer;

/**
 * Options for configuring the document conversion process with Docling.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@tools.jackson.databind.annotation.JsonDeserialize(builder = ConvertDocumentOptions.Builder.class)
@lombok.extern.jackson.Jacksonized
@lombok.Builder(toBuilder = true)
@lombok.Getter
@lombok.ToString
public class ConvertDocumentOptions {
  /**
   * Input format(s) to convert from. String or list of strings. Allowed values: docx, pptx, html, image, pdf, asciidoc, md, csv, xlsx, xml_uspto, xml_jats, mets_gbs, json_docling, audio, vtt. Optional, defaults to all formats.
   */
  @JsonProperty("from_formats")
  @JsonSetter(nulls = Nulls.AS_EMPTY)
  @lombok.Singular
  private List<InputFormat> fromFormats;

  /**
   * Output format(s) to convert to. String or list of strings. Allowed values: md, json, html, html_split_page, text, doctags. Optional, defaults to Markdown.
   */
  @JsonProperty("to_formats")
  @JsonSetter(nulls = Nulls.AS_EMPTY)
  @lombok.Singular
  private List<OutputFormat> toFormats;

  /**
   * Image export mode for the document (in case of JSON, Markdown or HTML). Allowed values: placeholder, embedded, referenced. Optional, defaults to Embedded.
   */
  @JsonProperty("image_export_mode")
  @Nullable
  private ImageRefMode imageExportMode;

  /**
   * If enabled, the bitmap content will be processed using OCR. Boolean. Optional, defaults to true.
   */
  @JsonProperty("do_ocr")
  @Nullable
  private Boolean doOcr;

  /**
   * If enabled, replace existing text with OCR-generated text over content. Boolean. Optional, defaults to false.
   */
  @JsonProperty("force_ocr")
  @Nullable
  private Boolean forceOcr;

  /**
   * The OCR engine to use. String. Allowed values: auto, easyocr, ocrmac, rapidocr, tesserocr, tesseract. Optional, defaults to easyocr.
   */
  @JsonProperty("ocr_engine")
  @Nullable
  private OcrEngine ocrEngine;

  /**
   * List of languages used by the OCR engine. Note that each OCR engine has different values for the language names. String or list of strings. Optional, defaults to empty.
   */
  @JsonProperty("ocr_lang")
  @JsonSetter(nulls = Nulls.AS_EMPTY)
  @lombok.Singular("ocrLang")
  private List<String> ocrLang;

  /**
   * The PDF backend to use. String. Allowed values: pypdfium2, dlparse_v1, dlparse_v2, dlparse_v4. Optional, defaults to dlparse_v4.
   */
  @JsonProperty("pdf_backend")
  @Nullable
  private PdfBackend pdfBackend;

  /**
   * Mode to use for table structure, String. Allowed values: fast, accurate. Optional, defaults to accurate.
   */
  @JsonProperty("table_mode")
  @Nullable
  private TableFormerMode tableMode;

  /**
   * If true, matches table cells predictions back to PDF cells. Can break table output if PDF cells are merged across table columns. If false, let table structure model define the text cells, ignore PDF cells.
   */
  @JsonProperty("table_cell_matching")
  @Nullable
  private Boolean tableCellMatching;

  /**
   * Choose the pipeline to process PDF or image files.
   */
  @JsonProperty("pipeline")
  @Nullable
  private ProcessingPipeline pipeline;

  /**
   * Only convert a range of pages. The page number starts at 1.
   */
  @JsonProperty("page_range")
  @JsonSetter(nulls = Nulls.AS_EMPTY)
  @lombok.Singular("pageRange")
  private List<Integer> pageRange;

  /**
   * The timeout for processing each document, in seconds.
   */
  @JsonProperty("document_timeout")
  @JsonSerialize(using = Jackson2DurationSerializer.class)
  @tools.jackson.databind.annotation.JsonSerialize(using = Jackson3DurationSerializer.class)
  @DurationSerializationFormat(ChronoUnit.SECONDS)
  @Nullable
  private Duration documentTimeout;

  /**
   * Abort on error if enabled. Boolean. Optional, defaults to false.
   */
  @JsonProperty("abort_on_error")
  @Nullable
  private Boolean abortOnError;

  /**
   * If enabled, the table structure will be extracted. Boolean. Optional, defaults to true.
   */
  @JsonProperty("do_table_structure")
  @Nullable
  private Boolean doTableStructure;

  /**
   * If enabled, images will be extracted from the document. Boolean. Optional, defaults to true.
   */
  @JsonProperty("include_images")
  @Nullable
  private Boolean includeImages;

  /**
   * Scale factor for images. Float. Optional, defaults to 2.0.
   */
  @JsonProperty("images_scale")
  @Nullable
  private Double imagesScale;

  /**
   * Add this placeholder between pages in the markdown output.
   */
  @JsonProperty("md_page_break_placeholder")
  @Nullable
  private String mdPageBreakPlaceholder;

  /**
   * If enabled, perform OCR code enrichment. Boolean. Optional, defaults to false.
   */
  @JsonProperty("do_code_enrichment")
  @Nullable
  private Boolean doCodeEnrichment;

  /**
   * If enabled, perform formula OCR, return LaTeX code. Boolean. Optional, defaults to false.
   */
  @JsonProperty("do_formula_enrichment")
  @Nullable
  private Boolean doFormulaEnrichment;

  /**
   * If enabled, classify pictures in documents. Boolean. Optional, defaults to false.
   */
  @JsonProperty("do_picture_classification")
  @Nullable
  private Boolean doPictureClassification;

  /**
   * If enabled, describe pictures in documents. Boolean. Optional, defaults to false.
   */
  @JsonProperty("do_picture_description")
  @Nullable
  private Boolean doPictureDescription;

  /**
   * Minimum percentage of the area for a picture to be processed with the models.
   */
  @JsonProperty("picture_description_area_threshold")
  @Nullable
  private Double pictureDescriptionAreaThreshold;

  /**
   * Options for running a local vision-language model in the picture description. The parameters refer to a model hosted on Hugging Face. This parameter is mutually exclusive with picture_description_api.
   */
  @JsonProperty("picture_description_local")
  @Nullable
  private PictureDescriptionLocal pictureDescriptionLocal;

  /**
   * API details for using a vision-language model in the picture description. This parameter is mutually exclusive with picture_description_local.
   */
  @JsonProperty("picture_description_api")
  @Nullable
  private PictureDescriptionApi pictureDescriptionApi;

  /**
   * Preset of local and API models for the vlm pipeline. This parameter is mutually exclusive with vlm_pipeline_model_local and vlm_pipeline_model_api. Use the other options for more parameters.
   */
  @JsonProperty("vlm_pipeline_model")
  @Nullable
  private VlmModelType vlmPipelineModel;

  /**
   * Options for running a local vision-language model for the vlm pipeline. The parameters refer to a model hosted on Hugging Face. This parameter is mutually exclusive with vlm_pipeline_model_api and vlm_pipeline_model.
   */
  @JsonProperty("vlm_pipeline_model_local")
  @Nullable
  private String vlmPipelineModelLocal;

  /**
   * API details for using a vision-language model for the vlm pipeline. This parameter is mutually exclusive with vlm_pipeline_model_local and vlm_pipeline_model.
   */
  @JsonProperty("vlm_pipeline_model_api")
  @Nullable
  private String vlmPipelineModelApi;

  @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
  public static class Builder { }
}
