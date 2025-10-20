plugins {
  id("docling-java-shared")
}

dependencies {
  implementation(platform(libs.jackson.bom))
  implementation(platform(libs.testcontainers.bom))

  api(project(":docling-api"))
  api("tools.jackson.core:jackson-databind")

  testImplementation("org.testcontainers:testcontainers-junit-jupiter")
  testImplementation(project(":docling-testcontainers"))
}
