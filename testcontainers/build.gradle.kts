plugins {
  id("docling-java-shared")
}

dependencies {
  api(platform(libs.testcontainers.bom))
  api("org.testcontainers:testcontainers")
  testImplementation(platform(libs.jackson.bom))
  testImplementation("org.testcontainers:testcontainers-junit-jupiter")
  testImplementation("tools.jackson.core:jackson-databind")
}
