package ai.docling.serve.api.clear.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ai.docling.serve.api.auth.AuthenticatedRequest;
import ai.docling.serve.api.auth.Authentication;

/**
 * Represents a request to clear or reset configured converters using the Docling Serve API.
 *
 * This class provides a mechanism to manage converter configurations through explicit
 * reset operations. It includes authentication information required to authorize the request.
 *
 * Features:
 * - {@code authentication}: Provides the authentication details needed to validate
 *   and authorize the reset operation. A default authentication instance is used
 *   when none is explicitly provided.
 *
 * This class is immutable and can be constructed or modified using a builder
 * that is generated via Lombok annotations.
 */
@lombok.Builder(toBuilder = true)
@lombok.Getter
@lombok.ToString
public class ClearConvertersRequest implements AuthenticatedRequest {
  @JsonIgnore
  @lombok.NonNull
  @lombok.Builder.Default
  private Authentication authentication = Authentication.builder().build();
}
