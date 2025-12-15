plugins {
  id("docling-java-shared")
  id("docling-release")
  id("docling-lombok")
}

description = "Docling Serve API"

dependencies {
  api(project(":docling-core"))
  compileOnly(platform(libs.jackson.bom))
  compileOnly(libs.jackson.annotations)
  compileOnly(libs.jackson.databind)
  compileOnly(libs.jackson2.databind)
  testImplementation(project(":docling-testcontainers"))
}
