plugins {
  id("docling-shared")
  `java-library`
  `test-report-aggregation`
}

repositories {
  mavenCentral()
}

dependencies {
  testReportAggregation(project(":docling-serve-api"))
  testReportAggregation(project(":docling-serve-client"))
  testReportAggregation(project(":docling-testcontainers"))
  testReportAggregation(project(":docling-version-tests"))

  api(platform(libs.testcontainers.bom))
  api(platform(libs.jackson.bom))

  constraints {
    testReportAggregation(libs.jackson.databind)
    testReportAggregation(libs.jackson2.databind)
  }
}

tasks.named("check") {
    dependsOn(tasks.named<TestReport>("testAggregateTestReport"))
}
