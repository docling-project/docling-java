plugins {
  id("docling-java-shared")
  id("docling-release")
}

description = "Testcontainers for Docling services"

dependencies {
  api(platform(libs.testcontainers.bom))
  api("org.testcontainers:testcontainers")
  testImplementation(platform(libs.jackson.bom))
  testImplementation(libs.jackson.databind)
  testImplementation(libs.testcontainers.junit.jupiter)
}
