module ai.docling.serve.client {
  requires transitive ai.docling.serve.api;
  requires java.net.http;
  requires org.slf4j;
  requires static org.jspecify;
  requires static com.fasterxml.jackson.core;
  requires static com.fasterxml.jackson.databind;
  requires static tools.jackson.core;
  requires static tools.jackson.databind;

  exports ai.docling.serve.client;
}
