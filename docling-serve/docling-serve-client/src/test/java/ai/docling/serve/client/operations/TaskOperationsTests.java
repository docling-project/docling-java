package ai.docling.serve.client.operations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.jupiter.api.Test;

import ai.docling.serve.api.task.request.TaskResultRequest;
import ai.docling.serve.client.DoclingServeClientException;

class TaskOperationsTests {
  @Test
  void convertTaskResultClosesBodyOnUnexpectedContentType() {
    var closed = new AtomicBoolean();
    var body = new ByteArrayInputStream(new byte[]{
        1,
        2,
        3
    }) {
      @Override
      public void close() {
        closed.set(true);
      }
    };
    var taskOperations = new TaskOperations(new StubHttpOperations(body, "text/html"));
    var request = TaskResultRequest.builder().taskId("task-1").build();

    assertThatThrownBy(() -> taskOperations.convertTaskResult(request))
        .isInstanceOf(DoclingServeClientException.class)
        .hasMessageContaining("Invalid Content-Type");
    assertThat(closed).isTrue();
  }

  private static final class StubHttpOperations extends HttpOperations {
    private final InputStream body;
    private final String contentType;

    StubHttpOperations(InputStream body, String contentType) {
      this.body = body;
      this.contentType = contentType;
    }

    @Override
    protected <I> StreamResponse executeGetWithStreamResponse(RequestContext<I, StreamResponse> requestContext) {
      return StreamResponse.builder()
          .body(body)
          .headers(name -> CONTENT_TYPE_HEADER.equals(name) ? Optional.of(contentType) : Optional.empty())
          .build();
    }

    @Override
    protected <I, O> O executeGet(RequestContext<I, O> requestContext) {
      throw new UnsupportedOperationException();
    }

    @Override
    protected <I, O> O executePost(RequestContext<I, O> requestContext) {
      throw new UnsupportedOperationException();
    }

    @Override
    protected <I> StreamResponse executePostWithStreamResponse(RequestContext<I, StreamResponse> requestContext) {
      throw new UnsupportedOperationException();
    }

    @Override
    protected <T> T readValue(String json, Class<T> valueType) {
      throw new UnsupportedOperationException();
    }
  }
}
