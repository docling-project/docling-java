package ai.docling.api.serve.convert.request.target;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A concrete {@link Target} implementation for delivering a converted document to a specified URI via an HTTP PUT operation.
 *
 * <p>The {@link PutTarget} class represents a delivery method where the document is sent to the given {@code URL}.
 * Supported serialized fields include:
 * <ul>
 *   <li>{@code kind} - inherited from the {@link Target} base class and always set to {@code PUT} for this implementation.</li>
 *   <li>{@code url} - the URI to which the document should be delivered.</li>
 * </ul>
 *
 * <p>This class provides JSON serialization annotations to include only non-empty fields in the serialization output.
 * The {@code url} is nullable, allowing the absence of a URI to be explicitly represented.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@tools.jackson.databind.annotation.JsonDeserialize(builder = PutTarget.Builder.class)
@lombok.extern.jackson.Jacksonized
@lombok.Builder(toBuilder = true)
@lombok.Getter
@lombok.ToString
public final class PutTarget extends Target {
  @JsonProperty("url")
  @lombok.NonNull
  private URI url;

  @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
  public static class Builder { }
}
