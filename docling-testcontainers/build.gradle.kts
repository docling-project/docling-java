plugins {
  id("docling-java-shared")
  id("docling-release")
  id("org.gradlex.extra-java-module-info") version "1.13.1"
}

description = "Testcontainers for Docling services"

extraJavaModuleInfo {
  automaticModule("org.testcontainers:testcontainers", "org.testcontainers")
  automaticModule("org.testcontainers:testcontainers-junit-jupiter", "org.testcontainers.junit.jupiter")
  automaticModule("org.rnorth.duct-tape:duct-tape", "org.rnorth.ducttape")
  automaticModule("org.rnorth.visible-assertions:visible-assertions", "org.rnorth.visibleassertions")
}

dependencies {
  api(platform(libs.testcontainers.bom))
  api("org.testcontainers:testcontainers")
  api(libs.slf4j.api)
  testImplementation(platform(libs.jackson.bom))
  testImplementation(libs.jackson.databind)
  testImplementation(libs.testcontainers.junit.jupiter)
  testImplementation(libs.slf4j.simple)
  testImplementation(project(":docling-serve-client"))
}
