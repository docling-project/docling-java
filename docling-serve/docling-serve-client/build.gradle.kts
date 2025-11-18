plugins {
  id("docling-java-shared")
  id("docling-release")
}

description = "Docling Serve Client"

dependencies {
  api(project(":docling-serve-api"))
  implementation(platform(libs.jackson.bom))
  implementation(libs.jackson.databind)
  implementation(libs.jackson2.databind)

  testImplementation(platform(libs.testcontainers.bom))
  testImplementation(libs.testcontainers.junit.jupiter)
  testImplementation(project(":docling-testcontainers"))
}
