package ai.docling.serve.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletionStage;

import ai.docling.serve.api.chunk.request.HierarchicalChunkDocumentRequest;
import ai.docling.serve.api.chunk.request.HybridChunkDocumentRequest;
import ai.docling.serve.api.chunk.response.ChunkDocumentResponse;
import ai.docling.serve.api.clear.request.ClearConvertersRequest;
import ai.docling.serve.api.clear.request.ClearResultsRequest;
import ai.docling.serve.api.clear.response.ClearResponse;
import ai.docling.serve.api.convert.request.BatchConvertDocumentRequest;
import ai.docling.serve.api.convert.request.ConvertDocumentRequest;
import ai.docling.serve.api.convert.response.ConvertDocumentResponse;
import ai.docling.serve.api.health.HealthCheckResponse;
import ai.docling.serve.api.spi.DoclingServeApiProvider;
import ai.docling.serve.api.task.request.TaskResultRequest;
import ai.docling.serve.api.task.request.TaskStatusPollRequest;
import ai.docling.serve.api.task.response.TaskStatusPollResponse;

/**
 * Test doubles shared by the api module tests.
 */
final class TestApis {
  private TestApis() {
  }

  /**
   * A {@link DoclingServeApi} created from a configuration. It reports that configuration from
   * {@link #config()}, as the provider contract requires, and throws from every operation: the tests
   * only care about its identity and its configuration.
   */
  static final class StubDoclingServeApi implements DoclingServeApi {
    private final DoclingServeApiConfig config;

    StubDoclingServeApi(DoclingServeApiConfig config) {
      this.config = config;
    }

    @Override
    public DoclingServeApiConfig config() {
      return this.config;
    }

    @Override
    @SuppressWarnings("removal")
    public <T extends DoclingServeApi, B extends DoclingApiBuilder<T, B>> DoclingApiBuilder<T, B> toBuilder() {
      throw notStubbed("toBuilder");
    }

    @Override
    public HealthCheckResponse health() {
      throw notStubbed("health");
    }

    @Override
    public ConvertDocumentResponse convertSource(ConvertDocumentRequest request) {
      throw notStubbed("convertSource");
    }

    @Override
    public CompletionStage<ConvertDocumentResponse> convertSourceAsync(ConvertDocumentRequest request) {
      throw notStubbed("convertSourceAsync");
    }

    @Override
    public TaskStatusPollResponse convertSourceBatch(BatchConvertDocumentRequest request) {
      throw notStubbed("convertSourceBatch");
    }

    @Override
    public CompletionStage<ConvertDocumentResponse> convertSourceBatchAsync(BatchConvertDocumentRequest request) {
      throw notStubbed("convertSourceBatchAsync");
    }

    @Override
    public ChunkDocumentResponse chunkSourceWithHierarchicalChunker(HierarchicalChunkDocumentRequest request) {
      throw notStubbed("chunkSourceWithHierarchicalChunker");
    }

    @Override
    public ChunkDocumentResponse chunkSourceWithHybridChunker(HybridChunkDocumentRequest request) {
      throw notStubbed("chunkSourceWithHybridChunker");
    }

    @Override
    public CompletionStage<ChunkDocumentResponse> chunkSourceWithHierarchicalChunkerAsync(HierarchicalChunkDocumentRequest request) {
      throw notStubbed("chunkSourceWithHierarchicalChunkerAsync");
    }

    @Override
    public CompletionStage<ChunkDocumentResponse> chunkSourceWithHybridChunkerAsync(HybridChunkDocumentRequest request) {
      throw notStubbed("chunkSourceWithHybridChunkerAsync");
    }

    @Override
    public ClearResponse clearConverters(ClearConvertersRequest request) {
      throw notStubbed("clearConverters");
    }

    @Override
    public ClearResponse clearResults(ClearResultsRequest request) {
      throw notStubbed("clearResults");
    }

    @Override
    public TaskStatusPollResponse pollTaskStatus(TaskStatusPollRequest request) {
      throw notStubbed("pollTaskStatus");
    }

    @Override
    public ConvertDocumentResponse convertTaskResult(TaskResultRequest request) {
      throw notStubbed("convertTaskResult");
    }

    @Override
    public ChunkDocumentResponse chunkTaskResult(TaskResultRequest request) {
      throw notStubbed("chunkTaskResult");
    }

    @Override
    public String toString() {
      return "StubDoclingServeApi[config=%s]".formatted(this.config);
    }

    private static UnsupportedOperationException notStubbed(String method) {
      return new UnsupportedOperationException("%s is not stubbed".formatted(method));
    }
  }

  /**
   * A provider creating a {@link StubDoclingServeApi}, and recording every API it creates.
   */
  static final class RecordingProvider implements DoclingServeApiProvider {
    private final Map<ConfigOption<?>, Unsupported> unsupportedOptions;
    private final List<DoclingServeApi> created = new ArrayList<>();

    RecordingProvider() {
      this(Map.of());
    }

    RecordingProvider(Map<ConfigOption<?>, Unsupported> unsupportedOptions) {
      this.unsupportedOptions = unsupportedOptions;
    }

    @Override
    public DoclingServeApi create(DoclingServeApiConfig config) {
      var api = new StubDoclingServeApi(config);
      this.created.add(api);
      return api;
    }

    @Override
    public Map<ConfigOption<?>, Unsupported> unsupportedOptions() {
      return this.unsupportedOptions;
    }

    List<DoclingServeApi> created() {
      return List.copyOf(this.created);
    }
  }
}
