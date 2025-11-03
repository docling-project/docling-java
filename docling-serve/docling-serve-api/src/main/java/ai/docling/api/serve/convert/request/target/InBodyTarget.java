package ai.docling.api.serve.convert.request.target;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Target for including the converted document in the response body.
 *
 * <p>This is a concrete implementation of {@link Target}, where the {@code Kind} is set to
 * {@code INBODY}. The converted document will be delivered directly in the response body.
 *
 * <p>Uses JSON serialization annotations to include only non-empty fields in the output.
 *
 * <p>This class overrides {@link Object#toString()} for a string representation of the instance.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@tools.jackson.databind.annotation.JsonDeserialize(builder = InBodyTarget.Builder.class)
@lombok.extern.jackson.Jacksonized
@lombok.Builder(toBuilder = true)
@lombok.Getter
@lombok.ToString
public final class InBodyTarget extends Target {

  @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
  public static class Builder { }
}
