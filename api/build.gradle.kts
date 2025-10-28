plugins {
  id("docling-java-shared")
}

dependencies {
  implementation(platform(libs.jackson.bom))
  implementation(libs.jackson.annotations)
  testImplementation(project(":docling-testcontainers"))
}
