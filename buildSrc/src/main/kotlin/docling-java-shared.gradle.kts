import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins {
  id("docling-shared")
  `java-library`
  `maven-publish`
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

publishing {
  publications {
    create<MavenPublication>("maven") {
      from(components["java"])

      pom {
        url = "https://docling-project.github.io/docling-java"
        licenses {
          name = "MIT License"
          url = "https://opensource.org/license/mit"
        }
        properties = project.extra.properties.entries.associate { (k, v) -> k to v.toString() }
        developers {
          developer {
            id = "edeandrea"
            name = "Eric Deandrea"
            email = "eric.deandrea@ibm.com"
            organization = "IBM"
          }
          developer {
            id = "ThomasVitale"
            name = "Thomas Vitale"
            url = "https://thomasvitale.com"
          }
          developer {
            id = "lordofthejars"
            name = "Alex Soto"
            email = "asotobu@gmail.com"
            url = "https://www.lordofthejars.com"
            organization = "IBM"
          }
        }
        scm {
          connection = "scm:git:git://github.com/docling-project/docling-java.git"
          developerConnection = "scm:git:ssh://github.com:docling-project/docling-java.git"
          url = "https://github.com/docling-project/docling-java"
        }
      }
    }
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

tasks.withType<Test> {
  // Use JUnit Platform for unit tests.
  useJUnitPlatform()

  testLogging {
    events("PASSED", "FAILED", "SKIPPED", "STANDARD_OUT", "STANDARD_ERROR")
    showStandardStreams = true
    showCauses = true
    showExceptions = true
    showStackTraces = true
    exceptionFormat = TestExceptionFormat.FULL
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

tasks.withType<Javadoc> {
  isFailOnError = false

  val opts = options as StandardJavadocDocletOptions
  opts.addStringOption("Xdoclint:syntax,html", "-quiet")

  if (JavaVersion.current().isJava9Compatible) {
    opts.addBooleanOption("html5", true)
  }
}
