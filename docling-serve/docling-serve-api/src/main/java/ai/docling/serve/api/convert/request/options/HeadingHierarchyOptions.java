package ai.docling.serve.api.convert.request.options;

import java.util.List;

import org.jspecify.annotations.Nullable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

/**
 * Options for configuring PDF heading hierarchy inference.
 * This class mirrors the Python {@code HeadingHierarchyOptions} from docling-core and is
 * used to reconstruct a true heading hierarchy (H1–H6) from PDF bookmarks, outline numbering,
 * and visual style instead of emitting all detected section headers flat at level 1.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@lombok.extern.jackson.Jacksonized
@lombok.Builder(toBuilder = true)
@lombok.Getter
@lombok.ToString
public class HeadingHierarchyOptions {

  /**
   * Use the PDF outline / table of contents as the authoritative signal for heading levels.
   *
   * @param useBookmarks true to use bookmarks, or null for the default (true)
   * @return the use-bookmarks flag
   */
  @JsonProperty("use_bookmarks")
  @Nullable
  private Boolean useBookmarks;

  /**
   * Use the leading numbering marker of the heading text (e.g., "1.", "1.1.2", "(a)")
   * as a signal for heading levels.
   *
   * @param useNumbering true to use numbering, or null for the default (true)
   * @return the use-numbering flag
   */
  @JsonProperty("use_numbering")
  @Nullable
  private Boolean useNumbering;

  /**
   * Fall back to the heading's visual style (font size, weight, slant, letter case) when
   * neither bookmarks nor numbering provide a level. Requires {@code generate_parsed_pages=True}
   * in the pipeline options.
   *
   * @param useStyle true to use visual style, or null for the default (true)
   * @return the use-style flag
   */
  @JsonProperty("use_style")
  @Nullable
  private Boolean useStyle;

  /**
   * Refine the style fallback with font weight, slant and all-caps detection. Ignored when
   * {@link #useStyle()} is off.
   *
   * @param useFontStyle true to use font style, or null for the default (true)
   * @return the use-font-style flag
   */
  @JsonProperty("use_font_style")
  @Nullable
  private Boolean useFontStyle;

  /**
   * Relative difference below which two font sizes count as one. Higher values merge more
   * sizes into a single level.
   *
   * @param styleSizeTolerance the tolerance value, or null for the default (0.05)
   * @return the style-size-tolerance value
   */
  @JsonProperty("style_size_tolerance")
  @Nullable
  private Double styleSizeTolerance;

  /**
   * Override the numbering-scheme precedence order (highest level first). When null, the
   * built-in ordering is used.
   *
   * @param numberingSchemes the ordered list of scheme names, or null for the default
   * @return the numbering-schemes list
   */
  @JsonProperty("numbering_schemes")
  @JsonSetter(nulls = Nulls.AS_EMPTY)
  @lombok.Singular("numberingScheme")
  private List<String> numberingSchemes;

  /**
   * Deepest heading level assigned by the inference stage; headings deeper than this are
   * clamped to this level.
   *
   * @param maxLevel the maximum level, or null for the default (6)
   * @return the max-level value
   */
  @JsonProperty("max_level")
  @Nullable
  private Integer maxLevel;

  /**
   * Minimum title similarity (0–1) for a bookmark to claim a heading. Higher values are
   * stricter.
   *
   * @param bookmarkMatchThreshold the threshold, or null for the default (0.8)
   * @return the bookmark-match-threshold value
   */
  @JsonProperty("bookmark_match_threshold")
  @Nullable
  private Double bookmarkMatchThreshold;

  /**
   * Builder for creating {@link HeadingHierarchyOptions} instances.
   */
  public static class Builder {
  }
}
