plugins {
  id("docling-java-shared")
}

dependencies {
  implementation(platform(libs.jackson.bom))

  implementation("com.fasterxml.jackson.core:jackson-annotations")
  testImplementation(project(":docling-testcontainers"))
}
