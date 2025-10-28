plugins {
  id("docling-java-shared")
}

dependencies {
  api(platform(libs.junit.bom))
  api(libs.junit.jupiter)
  api(libs.assertj.core)
}
