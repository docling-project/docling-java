plugins {
    // Apply the foojay-resolver plugin to allow automatic download of JDKs
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "docling-java"
include("docling-core", "docling-serve-api", "docling-serve-client", "docs", "docling-testcontainers", "docling-version-tests", "test-report-aggregation")

project(":docling-serve-api").projectDir = file("docling-serve/docling-serve-api")
project(":docling-serve-client").projectDir = file("docling-serve/docling-serve-client")
project(":docling-version-tests").projectDir = file("docling-testing/docling-version-tests")
