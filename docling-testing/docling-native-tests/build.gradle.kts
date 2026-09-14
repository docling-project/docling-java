import org.graalvm.buildtools.gradle.tasks.BuildNativeImageTask

plugins {
  id("docling-java-shared")
  application
  alias(libs.plugins.graalvm.native)
}

description = "Native Image serialization and client smoke tests"

val jacksonVersion = providers.gradleProperty("nativeTestJacksonVersion").getOrElse("3")
require(jacksonVersion in listOf("2", "3")) { "nativeTestJacksonVersion must be 2 or 3" }

sourceSets.main {
  java.srcDir("src/jackson$jacksonVersion/java")
}

dependencies {
  implementation(project(":docling-serve-api"))
  runtimeOnly(project(":docling-serve-client"))
  testImplementation(libs.wiremock)
  if (jacksonVersion == "2") {
    implementation(libs.jackson2.databind)
  } else {
    implementation(platform(libs.jackson.bom))
    implementation(libs.jackson.databind)
  }
}

application {
  mainClass.set("ai.docling.testing.nativeimage.JacksonSmokeTest")
}

tasks.withType<Test>().configureEach {
  systemProperty("nativeTestJacksonVersion", jacksonVersion)
}

tasks.test {
  // The child JVM gets only the application dependencies, excluding WireMock's Jackson 2.
  val smokeClasspath = sourceSets.main.get().runtimeClasspath
  inputs.files(smokeClasspath).withPropertyName("smokeTestClasspath").withNormalizer(ClasspathNormalizer::class.java)
  doFirst {
    systemProperty("smokeTestClasspath", smokeClasspath.asPath)
  }
}

graalvmNative {
  // Verify metadata shipped in the libraries without additions from the external repository.
  metadataRepository {
    enabled.set(false)
  }
  // Use the GraalVM installation selected by GRAALVM_HOME or JAVA_HOME.
  toolchainDetection.set(false)
  binaries.configureEach {
    // docling-java-shared applies java-library, which otherwise defaults to a shared library.
    sharedLibrary.set(false)
    buildArgs.add("--exact-reachability-metadata=ai.docling")
    // Fail verification even if a library catches a missing-registration error.
    runtimeArgs.add("-XX:MissingRegistrationReportingMode=Exit")
  }
  binaries.named("main") {
    imageName.set("docling-jackson$jacksonVersion-smoke-test")
  }
  // A separate entry point keeps direct mapper use in the serialization test from
  // making classes reachable and hiding failures in the client's Jackson detection.
  binaries.create("client") {
    mainClass.set("ai.docling.testing.nativeimage.ClientSmokeTest")
    imageName.set("docling-jackson$jacksonVersion-client-smoke-test")
  }
}

tasks.named("nativeCompile") {
  mustRunAfter(tasks.test)
}

tasks.named("nativeClientCompile") {
  // Avoid two memory-intensive native compilations running concurrently in CI.
  mustRunAfter(tasks.test, tasks.named("nativeCompile"))
}

tasks.register<Test>("nativeClientTest") {
  description = "Tests the native client executable against a JVM-hosted WireMock server."
  group = "verification"
  testClassesDirs = sourceSets.test.get().output.classesDirs
  classpath = sourceSets.test.get().runtimeClasspath
  include("**/ClientSmokeTests.class")

  val nativeExecutable = tasks.named<BuildNativeImageTask>("nativeClientCompile").flatMap { it.outputFile }
  // Tracking the executable also makes native compilation a dependency of this task.
  inputs.file(nativeExecutable).withPropertyName("nativeExecutable")
  doFirst {
    systemProperty("nativeTestExecutable", nativeExecutable.get().asFile.absolutePath)
  }
}
