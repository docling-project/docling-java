package ai.docling.serve.api.auth;

/**
 * Represents a request that requires authentication for secure access to resources.
 *
 * Implementations of this interface define a contract for providing authentication
 * details associated with the request. This is used to ensure authorized interaction
 * with APIs or protected services.
 *
 * Methods:
 * - {@code getAuthentication()}: Retrieves the {@link Authentication} details
 *   required to authorize the request.
 */
public interface AuthenticatedRequest {
  /**
   * Retrieves the authentication details associated with the request.
   *
   * This method provides the {@link Authentication} object required to authorize
   * interaction with secure resources or APIs. Implementations should ensure
   * the returned object contains the necessary credentials for successful
   * authentication.
   *
   * @return an {@link Authentication} instance containing the authentication
   *         details required for the request, or {@code null} if authentication
   *         is not applicable.
   */
  Authentication getAuthentication();
}
