package ai.docling.serve.api.task.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the possible statuses of a Task within the system.
 * Each status is mapped to a specific JSON property for serialization and deserialization.
 *
 * <ul>
 *   <li>{@code PENDING}: Indicates that the task has been created but has not yet started execution.</li>
 *   <li>{@code STARTED}: Indicates that the task is currently in progress.</li>
 *   <li>{@code SUCCESS}: Indicates that the task has completed successfully.</li>
 *   <li>{@code FAILURE}: Indicates that the task has failed to complete successfully.</li>
 * </ul>
 */
public enum TaskStatus {
  @JsonProperty("pending") PENDING,
  @JsonProperty("started") STARTED,
  @JsonProperty("success") SUCCESS,
  @JsonProperty("failure") FAILURE;
}
