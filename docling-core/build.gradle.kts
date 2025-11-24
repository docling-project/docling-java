plugins {
  id("docling-java-shared")
  id("docling-release")
  alias(libs.plugins.lombok)
}

description = "Docling Core"

lombok {
  version = libs.versions.lombok.asProvider().get()
}

dependencies {
  compileOnly(platform(libs.jackson.bom))
  compileOnly(libs.jackson.annotations)
  compileOnly(libs.jackson.databind)
  compileOnly(libs.jackson2.databind)
}

tasks.withType<Javadoc> {
  exclude("**/lombok.config")
}
