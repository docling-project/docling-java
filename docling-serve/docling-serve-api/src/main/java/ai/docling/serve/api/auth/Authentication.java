package ai.docling.serve.api.auth;

import org.jspecify.annotations.Nullable;

/**
 * Represents authentication details required for secure API usage.
 *
 * The {@code Authentication} class encapsulates the necessary credentials
 * for accessing restricted resources, such as an API key. It is designed
 * to be immutable and thread-safe.
 *
 * This class uses Lombok annotations to provide a builder pattern, generate
 * a getter for its fields, and produce a string representation.
 *
 * Features:
 * - {@code apiKey}: Optional API key to authorize requests.
 */
@lombok.Builder(toBuilder = true)
@lombok.Getter
@lombok.ToString
public class Authentication {
  /**
   * Represents an optional API key used to authenticate requests to a secure service.
   *
   * This field is nullable, indicating that the API key may not always be provided
   * or required, depending on the specific use case or configuration of the client.
   * When present, the API key is used to authorize access to restricted resources.
   */
  @Nullable
  private String apiKey;
}
