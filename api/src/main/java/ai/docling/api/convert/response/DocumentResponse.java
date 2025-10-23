package ai.docling.api.convert.response;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.jspecify.annotations.Nullable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import tools.jackson.databind.annotation.JsonDeserialize;
import tools.jackson.databind.annotation.JsonPOJOBuilder;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(builder = DocumentResponse.Builder.class)
public interface DocumentResponse {
  /**
   * Retrieves the content of the doc tags, if available.
   *
   * @return the content of the doc tags, or null if not present
   */
  @Nullable
  String doctagsContent();

  /**
   * Retrieves the filename associated with the document.
   *
   * @return the filename of the document as a string
   */
  String filename();

  /**
   * Retrieves the HTML content associated with the document, if available.
   *
   * @return the HTML content as a string, or null if not present
   */
  @Nullable
  String htmlContent();

  /**
   * Retrieves the JSON content associated with the document.
   *
   * @return a map representing the JSON content, or an empty map if no JSON content is present
   */
  Map<String, Object> jsonContent();

  /**
   * Retrieves the Markdown content associated with the document, if available.
   *
   * @return the Markdown content as a string, or null if no Markdown content is present
   */
  @Nullable
  String markdownContent();

  /**
   * Retrieves the plain text content associated with the document, if available.
   *
   * @return the plain text content as a string, or null if no text content is present
   */
  @Nullable
  String textContent();

  /**
   * Creates a new {@code Builder} instance initialized with the current state of the {@code DocumentResponse}.
   *
   * @return a {@code Builder} instance populated with the values from this {@code DocumentResponse}
   */
  default Builder toBuilder() {
    return new Builder(this);
  }

  /**
   * Creates and returns a new instance of the {@code Builder} class, which can be used to
   * construct a {@code DocumentResponse} object in a step-by-step manner.
   *
   * @return a new {@code Builder} instance
   */
  static Builder builder() {
    return new Builder();
  }

  /**
   * Default implementation of the {@link DocumentResponse} interface.
   * This record represents the response containing document data in various formats.
   * It is an immutable data structure that consolidates information related to a document,
   * such as its filename, content in multiple formats, and metadata.
   *
   * Each instance ensures the provided JSON content is unmodifiable by copying
   * the input map if it is present, or initializing it to an empty map otherwise.
   */
  record DefaultDocumentResponse(String doctagsContent,
                                 String filename,
                                 String htmlContent,
                                 Map<String, Object> jsonContent,
                                 String markdownContent,
                                 String textContent) implements DocumentResponse {

    public DefaultDocumentResponse {
      jsonContent = Optional.ofNullable(jsonContent)
          .map(Map::copyOf)
          .orElseGet(Map::of);
    }

    public DefaultDocumentResponse(Builder builder) {
      this(builder.doctagsContent,
          builder.filename,
          builder.htmlContent,
          builder.jsonContent,
          builder.markdownContent,
          builder.textContent);
    }
  }

  /**
   * A builder class for constructing instances of {@code DocumentResponse}.
   *
   * This class provides a step-by-step approach to configure and create a
   * {@code DocumentResponse} object. Each method in this class sets a specific
   * property of the object being built. Once all the desired properties are set,
   * the {@code build} method is used to create the final {@code DocumentResponse}
   * instance.
   *
   * The builder supports customization of various document-related attributes,
   * including doc tags content, filename, HTML content, JSON content, Markdown
   * content, and plain text content.
   *
   * By default, the builder initializes attributes with an empty state or default
   * values. If a {@code DocumentResponse} instance is provided to the constructor,
   * the builder is pre-populated with the attributes from the given response.
   *
   * This class is intended for internal use and is protected to restrict its
   * accessibility outside the defining package or class hierarchy.
   */
  @JsonPOJOBuilder(withPrefix = "")
  class Builder {
    protected String doctagsContent;
    protected String filename;
    protected String htmlContent;
    protected Map<String, Object> jsonContent = new HashMap<>();
    protected String markdownContent;
    protected String textContent;

    /**
     * Constructs a new {@code Builder} instance.
     *
     * This constructor initializes a builder with default or empty states for all
     * attributes. It is protected to restrict direct instantiation outside of the
     * defining package or class hierarchy.
     *
     * The {@code Builder} class is primarily used to facilitate the creation of
     * {@code DocumentResponse} objects through a step-by-step configuration process.
     */
    protected Builder() {

    }

    /**
     * Constructs a new {@code Builder} instance using the provided {@code DocumentResponse}.
     *
     * This constructor initializes the builder's fields with the data from the given
     * {@code DocumentResponse} object. It allows for the creation of a {@code Builder}
     * instance pre-populated with the state of an existing {@code DocumentResponse}.
     *
     * @param documentResponse the {@code DocumentResponse} instance whose data will
     *                          populate the fields of this builder
     */
    protected Builder(DocumentResponse documentResponse) {
      this.doctagsContent = documentResponse.doctagsContent();
      this.filename = documentResponse.filename();
      this.htmlContent = documentResponse.htmlContent();
      this.jsonContent = documentResponse.jsonContent();
      this.markdownContent = documentResponse.markdownContent();
      this.textContent = documentResponse.textContent();
    }

    /**
     * Sets the doctags content for the builder instance.
     *
     * @param doctagsContent the doctags content to be set
     * @return this Builder instance for method chaining
     */
    @JsonProperty("doctags_content")
    public Builder doctagsContent(String doctagsContent) {
      this.doctagsContent = doctagsContent;
      return this;
    }

    /**
     * Sets the filename for the builder instance.
     *
     * @param filename the filename to be set
     * @return this Builder instance for method chaining
     */
    @JsonProperty("filename")
    public Builder filename(String filename) {
      this.filename = filename;
      return this;
    }

    /**
     * Sets the HTML content for the builder instance.
     *
     * @param htmlContent the HTML content to be set
     * @return this Builder instance for method chaining
     */
    @JsonProperty("html_content")
    public Builder htmlContent(String htmlContent) {
      this.htmlContent = htmlContent;
      return this;
    }

    /**
     * Sets the JSON content for the builder instance.
     *
     * The JSON content is represented as a map of key-value pairs, where the keys
     * are {@code String} objects, and the values are {@code Object} instances.
     *
     * @param jsonContent the JSON content to be set, represented as a {@code Map<String, Object>}
     * @return this {@link Builder} instance for method chaining
     */
    @JsonProperty("json_content")
    public Builder jsonContent(Map<String, Object> jsonContent) {
      this.jsonContent = jsonContent;
      return this;
    }

    /**
     * Sets the Markdown content for this builder instance.
     *
     * The Markdown content represents the textual data formatted in Markdown syntax,
     * which can include headings, lists, links, and other Markdown elements.
     *
     * @param markdownContent the Markdown content to be set, represented as a {@code String}
     * @return this {@link Builder} instance for method chaining
     */
    @JsonProperty("md_content")
    public Builder markdownContent(String markdownContent) {
      this.markdownContent = markdownContent;
      return this;
    }

    /**
     * Sets the plain text content for this builder instance.
     *
     * The plain text content represents unformatted textual data that can be
     * used for display or processing purposes within the application.
     *
     * @param textContent the plain text content to be set, represented as a {@code String}
     * @return this {@link Builder} instance for method chaining
     */
    @JsonProperty("text_content")
    public Builder textContent(String textContent) {
      this.textContent = textContent;
      return this;
    }

    /**
     * Creates and returns a {@link DocumentResponse} instance based on the current state of this {@link Builder}.
     *
     * <p>The returned {@link DocumentResponse} will encapsulate the values configured in the builder,
     * and further modifications to the builder instance will not affect the created {@code DocumentResponse}.
     *
     * @return a new {@code DocumentResponse} instance constructed from the builder's state
     */
    public DocumentResponse build() {
      return new DefaultDocumentResponse(this);
    }
  }
}
