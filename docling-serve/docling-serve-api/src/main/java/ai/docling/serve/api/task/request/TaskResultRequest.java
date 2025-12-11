package ai.docling.serve.api.task.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ai.docling.serve.api.auth.AuthenticatedRequest;
import ai.docling.serve.api.auth.Authentication;

/**
 * Represents a request to retrieve the result of a task.
 *
 * This class is used to encapsulate the necessary information required to
 * request the result corresponding to the task identified by its unique task ID.
 * Instances of this class are immutable and can be built using the provided
 * builder methods.
 *
 * Attributes:
 * - `taskId`: The unique identifier of the task whose result is being requested.
 *             This field is mandatory and must not be null.
 */
@lombok.Builder(toBuilder = true)
@lombok.Getter
@lombok.ToString
public class TaskResultRequest implements AuthenticatedRequest {
  @lombok.NonNull
  private String taskId;

  @JsonIgnore
  @lombok.NonNull
  @lombok.Builder.Default
  private Authentication authentication = Authentication.builder().build();
}
