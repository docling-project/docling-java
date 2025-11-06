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
  testReportAggregation(project(":docling-testing"))
  testReportAggregation(project(":docling-testcontainers"))

  api(platform(libs.testcontainers.bom))
  api(platform(libs.jackson.bom))

  constraints {
    testReportAggregation(libs.jackson.databind)
    testReportAggregation(libs.jackson2.databind)
  }
}

//reporting {
//    reports {
//        val testAggregateTestReport by creating(AggregateTestReport::class) {
//            testSuiteName = "test"
//        }
//    }
//}

tasks.named("check") {
    dependsOn(tasks.named<TestReport>("testAggregateTestReport"))
}
