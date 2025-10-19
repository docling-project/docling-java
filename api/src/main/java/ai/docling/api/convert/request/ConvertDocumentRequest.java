package ai.docling.api.convert.request;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.jspecify.annotations.Nullable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import ai.docling.api.convert.request.options.ConvertDocumentOptions;
import ai.docling.api.convert.request.source.FileSource;
import ai.docling.api.convert.request.source.HttpSource;
import ai.docling.api.convert.request.source.Source;
import ai.docling.api.convert.request.target.Target;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ConvertDocumentRequest(

    @JsonProperty("sources") List<? extends Source> sources,

    @JsonProperty("options") ConvertDocumentOptions options,

    @JsonProperty("target") @Nullable Target target

) {

  public ConvertDocumentRequest {
    if (sources == null || sources.isEmpty()) {
      throw new IllegalArgumentException("sources cannot be null or empty");
    }
    if (options == null) {
      throw new IllegalArgumentException("options cannot be null");
    }
    if (! (sources.stream().allMatch(HttpSource.class::isInstance) || sources.stream().allMatch(FileSource.class::isInstance))) {
      throw new IllegalArgumentException("All sources must be of the same type (HttpSource or FileSource)");
    }
    sources = new ArrayList<>(sources);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private List<Source> sources = new ArrayList<>();
    private ConvertDocumentOptions options = ConvertDocumentOptions.builder().build();
    @Nullable
    Target target;

    private Builder() {
    }

    public Builder httpSources(List<HttpSource> httpSources) {
      if (httpSources == null) {
        throw new IllegalArgumentException("httpSources cannot be null");
      }
      this.sources = new ArrayList<>(httpSources);
      return this;
    }

    public Builder addHttpSources(String... urls) {
      if (urls == null) {
        throw new IllegalArgumentException("urls cannot be null");
      }
      for (String url : urls) {
        if (url == null || url.isBlank()) {
          throw new IllegalArgumentException("url cannot be null or empty");
        }
        this.sources.add(HttpSource.from(url));
      }
      return this;
    }

    public Builder addHttpSources(URI... urls) {
      if (urls == null) {
        throw new IllegalArgumentException("urls cannot be null");
      }
      for (URI url : urls) {
        if (url == null) {
          throw new IllegalArgumentException("url cannot be null");
        }
        this.sources.add(HttpSource.from(url));
      }
      return this;
    }

    public Builder fileSources(List<FileSource> fileSources) {
      if (fileSources == null) {
        throw new IllegalArgumentException("fileSources cannot be null");
      }
      this.sources = new ArrayList<>(fileSources);
      return this;
    }

    public Builder addFileSources(String filename, String base64String) {
      if (filename == null || filename.isBlank()) {
        throw new IllegalArgumentException("filename cannot be null or empty");
      }
      if (base64String == null || base64String.isBlank()) {
        throw new IllegalArgumentException("base64String cannot be null or empty");
      }
      this.sources.add(FileSource.from(filename, base64String));
      return this;
    }

    public Builder options(ConvertDocumentOptions options) {
      if (options == null) {
        throw new IllegalArgumentException("options cannot be null");
      }
      this.options = options;
      return this;
    }

    public Builder target(@Nullable Target target) {
      this.target = target;
      return this;
    }

    public ConvertDocumentRequest build() {
      return new ConvertDocumentRequest(sources, options, target);
    }

  }

}
