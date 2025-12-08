module ai.docling.core {
  requires static org.jspecify;
  requires static lombok;
  requires static com.fasterxml.jackson.annotation;
  requires static com.fasterxml.jackson.databind;
  requires static tools.jackson.databind;

  exports ai.docling.core;

  // Open for JSON (Jackson 2 and 3) reflection-based serialization
  opens ai.docling.core to com.fasterxml.jackson.databind, tools.jackson.databind;
}
