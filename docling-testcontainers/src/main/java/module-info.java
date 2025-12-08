module ai.docling.testcontainers {
  requires org.testcontainers;
  requires org.slf4j;
  requires static org.jspecify;
  requires static com.github.dockerjava.api;

  exports ai.docling.testcontainers.serve;
  exports ai.docling.testcontainers.serve.config;
}
