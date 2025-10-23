plugins {
  id("docling-java-shared")
}

dependencies {
  implementation(platform(libs.jackson.bom))

  implementation("com.fasterxml.jackson.core:jackson-annotations")
  implementation("tools.jackson.core:jackson-databind")
  testImplementation(project(":docling-testcontainers"))
}
