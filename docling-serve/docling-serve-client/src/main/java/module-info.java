module ai.docling.serve.client {
  requires transitive ai.docling.serve.api;
  requires transitive org.slf4j;
  requires static org.jspecify;
  requires static com.fasterxml.jackson.core;
  requires static com.fasterxml.jackson.databind;
  requires static tools.jackson.core;
  requires static tools.jackson.databind;
  requires java.net.http;

  exports ai.docling.serve.client;
}
