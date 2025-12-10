open module ai.docling.serve.api {
  requires transitive ai.docling.core;
  requires static org.jspecify;
  requires static lombok;
  requires static com.fasterxml.jackson.annotation;

  // Optional JSON libraries (compileOnly)
  requires static com.fasterxml.jackson.core;
  requires static com.fasterxml.jackson.databind;
  requires static tools.jackson.core;
  requires static tools.jackson.databind;

  exports ai.docling.serve.api;
  exports ai.docling.serve.api.health;
  exports ai.docling.serve.api.util;

  // Chunking API
  exports ai.docling.serve.api.chunk.request;
  exports ai.docling.serve.api.chunk.request.options;
  exports ai.docling.serve.api.chunk.response;

  // Conversion API
  exports ai.docling.serve.api.convert.request;
  exports ai.docling.serve.api.convert.request.options;
  exports ai.docling.serve.api.convert.request.source;
  exports ai.docling.serve.api.convert.request.target;
  exports ai.docling.serve.api.convert.response;

  // Clear API
  exports ai.docling.serve.api.clear.response;

  // Task API
  exports ai.docling.serve.api.task.response;

  // Serialization helpers
  exports ai.docling.serve.api.serialization;
}
