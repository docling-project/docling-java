plugins {
  id("docling-java-shared")
}

dependencies {
  api(platform(libs.testcontainers.bom))
  api("org.testcontainers:testcontainers")
  testImplementation("org.testcontainers:testcontainers-junit-jupiter")
  testImplementation(libs.jackson.databind)
}
