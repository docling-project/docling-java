plugins {
  id("docling-shared")
  id("ru.vyarus.mkdocs") version "4.0.1"
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
      "griffe-pydantic:1.1.8",
      "mkdocs-include-markdown-plugin:7.2.0"
  )
}

mkdocs {
  extras = mapOf(
      "project-version" to "${project.version}",
      "project-groupId" to "${project.group}",
      "project-artifactId" to "${rootProject.name}",
      "serve-api-artifactId" to project(":docling-serve-api").name,
      "serve-client-artifactId" to project(":docling-serve-client").name,
      "testcontainers-artifactId" to project(":docling-testcontainers").name,
      "core-artifactId" to project(":docling-core").name
  )

  publish {
    val alias = findProperty("docs.alias")?.toString() ?: "dev"
    val versionsFile = findProperty("docs.versionsFile")?.toString() ?: layout.buildDirectory.dir("mkdocs").get().file("versions.json").asFile.absolutePath

    // This is a hack because versionAliases is created final as an array without a setter
    // So it isn't friendly to the Gradle Kotlin DSL
    org.codehaus.groovy.runtime.InvokerHelper.setProperty(this, "versionAliases", arrayOf(alias))
    docPath = "${project.version}"
    rootRedirectTo = alias
    generateVersionsFile = true
    existingVersionsFile = versionsFile
  }
}

tasks.register("build") {
  dependsOn(tasks.named("mkdocsBuild"))
}

tasks.register("clean") {
  delete(layout.buildDirectory.asFile.get())
}
