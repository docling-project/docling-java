package ai.docling.api.serve.convert.request.options;

import java.time.Duration;
import java.util.List;

import org.jspecify.annotations.Nullable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

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
  @JsonProperty("from_formats")
  @JsonSetter(nulls = Nulls.AS_EMPTY)
  @lombok.Singular
  private List<InputFormat> fromFormats;

  @JsonProperty("to_formats")
  @JsonSetter(nulls = Nulls.AS_EMPTY)
  @lombok.Singular
  private List<OutputFormat> toFormats;

  @JsonProperty("image_export_mode")
  @Nullable
  private ImageRefMode imageExportMode;

  @JsonProperty("do_ocr")
  @Nullable
  private Boolean doOcr;

  @JsonProperty("force_ocr")
  @Nullable
  private Boolean forceOcr;

  @JsonProperty("ocr_engine")
  @Nullable
  private OcrEngine ocrEngine;

  @JsonProperty("ocr_lang")
  @JsonSetter(nulls = Nulls.AS_EMPTY)
  @lombok.Singular("ocrLang")
  private List<String> ocrLang;

  @JsonProperty("pdf_backend")
  @Nullable
  private PdfBackend pdfBackend;

  @JsonProperty("table_mode")
  @Nullable
  private TableFormerMode tableMode;

  @JsonProperty("table_cell_matching")
  @Nullable
  private Boolean tableCellMatching;

  @JsonProperty("pipeline")
  @Nullable
  private ProcessingPipeline pipeline;

  @JsonProperty("page_range")
  @JsonSetter(nulls = Nulls.AS_EMPTY)
  @lombok.Singular("pageRange")
  private List<Integer> pageRange;

  @JsonProperty("document_timeout")
  @Nullable
  private Duration documentTimeout;

  @JsonProperty("abort_on_error")
  @Nullable
  private Boolean abortOnError;

  @JsonProperty("do_table_structure")
  @Nullable
  private Boolean doTableStructure;

  @JsonProperty("include_images")
  @Nullable
  private Boolean includeImages;

  @JsonProperty("images_scale")
  @Nullable
  private Double imagesScale;

  @JsonProperty("md_page_break_placeholder")
  @Nullable
  private String mdPageBreakPlaceholder;

  @JsonProperty("do_code_enrichment")
  @Nullable
  private Boolean doCodeEnrichment;

  @JsonProperty("do_formula_enrichment")
  @Nullable
  private Boolean doFormulaEnrichment;

  @JsonProperty("do_picture_classification")
  @Nullable
  private Boolean doPictureClassification;

  @JsonProperty("do_picture_description")
  @Nullable
  private Boolean doPictureDescription;

  @JsonProperty("picture_description_area_threshold")
  @Nullable
  private Double pictureDescriptionAreaThreshold;

  @JsonProperty("picture_description_local")
  @Nullable
  private PictureDescriptionLocal pictureDescriptionLocal;

  @JsonProperty("picture_description_api")
  @Nullable
  private PictureDescriptionApi pictureDescriptionApi;

  @JsonProperty("vlm_pipeline_model")
  @Nullable
  private VlmModelType vlmPipelineModel;

  @JsonProperty("vlm_pipeline_model_local")
  @Nullable
  private String vlmPipelineModelLocal;

  @JsonProperty("vlm_pipeline_model_api")
  @Nullable
  private String vlmPipelineModelApi;

  @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
  public static class Builder { }
}
