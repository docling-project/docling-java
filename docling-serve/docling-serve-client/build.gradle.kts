plugins {
  id("docling-java-shared")
  id("docling-release")
}

description = "Docling Serve Client"

dependencies {
  api(project(":docling-serve-api"))
  api(libs.slf4j.api)
  compileOnly(platform(libs.jackson.bom))
  compileOnly(libs.jackson.databind)
  compileOnly(libs.jackson2.databind)

  testImplementation(platform(libs.testcontainers.bom))
  testImplementation(libs.testcontainers.junit.jupiter)
  testImplementation(project(":docling-testcontainers"))
  testImplementation(libs.slf4j.simple)

  testImplementation(platform(libs.jackson.bom))
  testImplementation(libs.jackson.databind)
  testImplementation(libs.jackson2.databind)

  testImplementation(libs.awaitility)
}
