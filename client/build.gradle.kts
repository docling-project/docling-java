plugins {
  id("docling-java-shared")
}

dependencies {
  api(platform(libs.jackson.bom))
  api(project(":docling-api"))
  api(libs.jackson.databind)

  testImplementation(platform(libs.testcontainers.bom))
  testImplementation(libs.testcontainers.junit.jupiter)
  testImplementation(project(":docling-testcontainers"))
}
