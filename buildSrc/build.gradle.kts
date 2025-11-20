plugins {
  `kotlin-dsl`
}

repositories {
  gradlePluginPortal()
  mavenCentral()
}

dependencies {
  implementation("org.yaml:snakeyaml:2.5")
}

// Read root gradle.properties
projectDir.parentFile
    .resolve("gradle.properties")
    .readLines()
    .forEach { line ->
      val pieces = line.split("=").map { it.trim() }
      project.extra[pieces.first()] = pieces.last()
    }

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(property("java.version").toString())
  }
}

kotlin {
  jvmToolchain {
    languageVersion = JavaLanguageVersion.of(property("java.version").toString())
  }
}
