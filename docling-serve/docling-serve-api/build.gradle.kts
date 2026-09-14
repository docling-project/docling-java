plugins {
  id("docling-java-shared")
  id("docling-release")
  id("docling-lombok")
  id("docling-native-image")
}

description = "Docling Serve API"

nativeImageMetadata {
  // These packages expose operations, service discovery, and utilities rather than JSON models.
  excludePackages(
    "ai.docling.serve.api",
    "ai.docling.serve.api.spi",
    "ai.docling.serve.api.util"
  )
}

dependencies {
  api(project(":docling-core"))
  compileOnly(platform(libs.jackson.bom))
  compileOnly(libs.jackson.annotations)
  compileOnly(libs.jackson.databind)
  compileOnly(libs.jackson2.databind)
  testImplementation(project(":docling-testcontainers"))
}
