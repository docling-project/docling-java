plugins {
  id("docling-java-shared")
  alias(libs.plugins.lombok)
}

lombok {
  version = libs.versions.lombok.asProvider().get()
}

dependencies {
  compileOnly(platform(libs.jackson.bom))
  compileOnly(libs.jackson.annotations)
  compileOnly(libs.jackson.databind)
  compileOnly(libs.jackson2.databind)
  testImplementation(project(":docling-testcontainers"))
}

tasks.withType<Javadoc> {
  exclude("**/lombok.config")
}
