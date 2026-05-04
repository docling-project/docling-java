plugins {
  `java-platform`
  id("docling-shared")
  id("docling-release")
}

description = "Docling Java BOM"

dependencies {
  constraints {
    api(project(":docling-core"))
    api(project(":docling-serve-api"))
    api(project(":docling-serve-client"))
    api(project(":docling-testcontainers"))
  }
}
