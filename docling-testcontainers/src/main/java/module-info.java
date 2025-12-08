module ai.docling.testcontainers {
  requires transitive org.slf4j;
  requires static org.jspecify;
  requires org.testcontainers;
  requires com.github.dockerjava.api;

  exports ai.docling.testcontainers.serve;
  exports ai.docling.testcontainers.serve.config;
}
