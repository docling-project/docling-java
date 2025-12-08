module ai.docling.serve.api {
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

  // Serialization helpers
  exports ai.docling.serve.api.serialization;

  // Open DTO packages for reflection-based JSON serialization
  opens ai.docling.serve.api.chunk.request to com.fasterxml.jackson.databind, tools.jackson.databind;
  opens ai.docling.serve.api.chunk.request.options to com.fasterxml.jackson.databind, tools.jackson.databind;
  opens ai.docling.serve.api.chunk.response to com.fasterxml.jackson.databind, tools.jackson.databind;
  opens ai.docling.serve.api.convert.request to com.fasterxml.jackson.databind, tools.jackson.databind;
  opens ai.docling.serve.api.convert.request.options to com.fasterxml.jackson.databind, tools.jackson.databind;
  opens ai.docling.serve.api.convert.request.source to com.fasterxml.jackson.databind, tools.jackson.databind;
  opens ai.docling.serve.api.convert.request.target to com.fasterxml.jackson.databind, tools.jackson.databind;
  opens ai.docling.serve.api.convert.response to com.fasterxml.jackson.databind, tools.jackson.databind;
}
