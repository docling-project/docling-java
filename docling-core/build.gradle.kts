plugins {
  id("docling-java-shared")
  id("docling-release")
  id("docling-lombok")
}

description = "Docling Core"

dependencies {
  compileOnly(platform(libs.jackson.bom))
  compileOnly(libs.jackson.annotations)
  compileOnly(libs.jackson.databind)
  compileOnly(libs.jackson2.databind)

  testImplementation(platform(libs.jackson.bom))
  testImplementation(libs.jackson.annotations)
  testImplementation(libs.jackson.databind)
  testImplementation(libs.jackson2.databind)
}
