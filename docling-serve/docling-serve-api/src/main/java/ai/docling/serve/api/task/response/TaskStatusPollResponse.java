package ai.docling.serve.api.task.response;

import org.jspecify.annotations.Nullable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the response for polling the status of a task.
 *
 * This class encapsulates details about a specific task's current status,
 * including its identifier, type, position in a queue, and metadata regarding
 * its progress and outcomes. The object is serialized and deserialized using
 * Jackson annotations, ensuring compatibility with JSON-based APIs.
 *
 * Features:
 * - Uses a builder pattern with {@code lombok.Builder} for creating immutable objects.
 * - Serialized to JSON only if fields are non-empty.
 * - Metadata field for detailed task status is optional.
 *
 * Fields:
 * - {@code taskId}: The unique identifier for the task.
 * - {@code taskType}: The type of task being processed (optional).
 * - {@code taskStatus}: The current status of the task, as defined in the {@code TaskStatus} enum.
 * - {@code taskPosition}: A numerical representation of the task's position in the processing queue.
 * - {@code taskStatusMetadata}: Metadata providing detailed statistics and progress information (optional).
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@tools.jackson.databind.annotation.JsonDeserialize(builder = TaskStatusPollResponse.Builder.class)
@lombok.extern.jackson.Jacksonized
@lombok.Builder(toBuilder = true)
@lombok.Getter
@lombok.ToString
public class TaskStatusPollResponse {
  @JsonProperty("task_id")
  private String taskId;

  @JsonProperty("task_type")
  @Nullable
  private String taskType;

  @JsonProperty("task_status")
  private TaskStatus taskStatus;

  @JsonProperty("task_position")
  @Nullable
  private Long taskPosition;

  @JsonProperty("task_meta")
  @Nullable
  private TaskStatusMetadata taskStatusMetadata;

  @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
  public static class Builder { }
}
