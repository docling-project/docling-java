plugins {
  id("docling-shared")
  `java-library`
  `test-report-aggregation`
  `jacoco-report-aggregation`
}

repositories {
  mavenCentral()
}

dependencies {
  testReportAggregation(project(":docling-core"))
  testReportAggregation(project(":docling-serve-api"))
  testReportAggregation(project(":docling-serve-client"))
  testReportAggregation(project(":docling-testcontainers"))
  testReportAggregation(project(":docling-version-tests"))

  jacocoAggregation(project(":docling-core"))
  jacocoAggregation(project(":docling-serve-api"))
  jacocoAggregation(project(":docling-serve-client"))
  jacocoAggregation(project(":docling-testcontainers"))
  jacocoAggregation(project(":docling-version-tests"))

  api(platform(libs.testcontainers.bom))
  api(platform(libs.jackson.bom))

  constraints {
    testReportAggregation(libs.jackson.databind)
    testReportAggregation(libs.jackson2.databind)
  }
}

tasks.named("check") {
    dependsOn(
      tasks.named<TestReport>("testAggregateTestReport"),
      tasks.named<JacocoReport>("testCodeCoverageReport")
    )
}
