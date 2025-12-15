plugins {
  id("docling-shared")
  `java-library`
  `jacoco`
}

repositories {
  mavenCentral()
}

java {
  withJavadocJar()
  withSourcesJar()
  toolchain {
    languageVersion = JavaLanguageVersion.of(property("java.version").toString())
  }
}

val libs = extensions.getByType(VersionCatalogsExtension::class.java).named("libs")

dependencies {
  // These dependencies are exported to consumers, that is to say found on their compile classpath.
  api(libs.findLibrary("jspecify").get())

  // These dependencies are used internally, and not exposed to consumers on their own compile classpath.
  testImplementation(platform(libs.findLibrary("junit.bom").get()))
  testImplementation(libs.findLibrary("junit.jupiter").get())
  testImplementation(libs.findLibrary("assertj-core").get())
  testRuntimeOnly(libs.findLibrary("junit.platform").get())
}

testing {
  suites {
    val test by getting(JvmTestSuite::class) {
      useJUnitJupiter()
    }
  }
}

jacoco {
  toolVersion = libs.findVersion("jacoco").get().toString()
}

tasks.withType<Test>().configureEach {
  // Use JUnit Platform for unit tests.
  useJUnitPlatform()

  maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).coerceAtLeast(1)
  forkEvery = 100
  
  finalizedBy(tasks.named("jacocoTestReport"))

  systemProperties(
    mapOf(
      "org.slf4j.simpleLogger.showDateTime" to "true",
      "org.slf4j.simpleLogger.levelInBrackets" to "true",
      "org.slf4j.simpleLogger.log.ai.docling" to "debug",
      "org.slf4j.simpleLogger.dateTimeFormat" to "[MM/dd/yyyy hh:mm:ss.SSS a z]"
    )
  )

  testLogging {
    events("PASSED", "FAILED", "SKIPPED", "STANDARD_OUT", "STANDARD_ERROR")
    showStandardStreams = true
    showCauses = true
    showExceptions = true
    showStackTraces = true
    exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
  }

  reports {
    val currentLocation = junitXml.outputLocation.getOrElse(
      layout.buildDirectory.dir("test-results/$name").get()
    )

    html.required.set(true)
    junitXml.required.set(true)
    junitXml.outputLocation.set(
      junitXml.outputLocation.getOrElse(
        layout.buildDirectory.dir("test-results/$name").get()
      )
      .dir("java${project.property("java.version").toString()}")
    )
  }
}

tasks.withType<JavaCompile>().configureEach {
  options.isFork = true
}

tasks.withType<Javadoc> {
  isFailOnError = false

  val opts = options as StandardJavadocDocletOptions
  opts.addStringOption("Xdoclint:syntax,html", "-quiet")

  if (JavaVersion.current().isJava9Compatible) {
    opts.addBooleanOption("html5", true)
  }

  exclude("**/lombok.config")
  exclude("**/*.bak")
}
