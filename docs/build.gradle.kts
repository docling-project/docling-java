plugins {
  id("docling-java-shared")
  id("ru.vyarus.mkdocs") version "4.0.1"
}

dependencies {
  compileOnly(project(":docling-api"))
  compileOnly(project(":docling-client"))
  compileOnly(project(":docling-testing"))
  compileOnly(project(":docling-testcontainers"))
}

python {
  pip(
      "mkdocs:1.6.1",
      "mkdocs-material:9.6.22",
      "mkdocs-material-extensions:1.3.1",
      "mkdocs-autorefs:1.4.3",
      "mkdocs-click:0.9.0",
      "mkdocs-macros-plugin:1.3.7",
      "mkdocstrings:0.30.1",
      "mkdocstrings-python:1.18.2",
      "griffe-pydantic:1.1.8"
  )
}

mkdocs {
  extras = mutableMapOf(
      "project-version" to "${project.version}",
      "project-groupId" to "${project.group}",
      "project-artifactId" to "${rootProject.name}",
      "api-artifactId" to project(":docling-api").name,
      "client-artifactId" to project(":docling-client").name,
      "testing-artifactId" to project(":docling-testing").name,
      "testcontainers-artifactId" to project(":docling-testcontainers").name
  )
}

tasks.withType<PublishToMavenRepository>().configureEach {
  enabled = false
}

tasks.withType<PublishToMavenLocal>().configureEach {
  enabled = false
}
