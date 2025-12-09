package ai.docling.serve.api.task.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents metadata for the status of a task, providing detailed information
 * about the task's progress and outcomes.
 *
 * This class is used to encapsulate the statistical data related to the execution
 * of a task, such as the number of documents processed, succeeded, or failed. It
 * is intended for use in tracking task processing and is serialized to JSON for
 * communication between system components.
 *
 * Features:
 * - Serialization and deserialization using Jackson annotations.
 * - All fields are included in the JSON only if they are non-empty.
 * - Immutable structure with a builder pattern for object creation.
 *
 * Fields:
 * - {@code numDocs}: The total number of documents associated with the task.
 * - {@code numProcessed}: The number of documents that have been processed.
 * - {@code numSucceeded}: The number of documents that were successfully processed.
 * - {@code numFailed}: The number of documents that failed processing.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@tools.jackson.databind.annotation.JsonDeserialize(builder = TaskStatusMetadata.Builder.class)
@lombok.extern.jackson.Jacksonized
@lombok.Builder(toBuilder = true)
@lombok.Getter
@lombok.ToString
public class TaskStatusMetadata {
  @JsonProperty("num_docs")
  private Long numDocs;

  @JsonProperty("num_processed")
  private Long numProcessed;

  @JsonProperty("num_succeeded")
  private Long numSucceeded;

  @JsonProperty("num_failed")
  private Long numFailed;

  @tools.jackson.databind.annotation.JsonPOJOBuilder(withPrefix = "")
  public static class Builder { }
}
