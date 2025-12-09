package ai.docling.serve.api.task.request;

@lombok.Builder(toBuilder = true)
@lombok.Getter
@lombok.ToString
public class TaskResultRequest {
  @lombok.NonNull
  private String taskId;
}
