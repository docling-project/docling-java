plugins {
  `java`
  id("docling-java-shared")
  alias(libs.plugins.quarkus)
}

dependencies {
  implementation(enforcedPlatform(libs.quarkus.bom))
  implementation("io.quarkus:quarkus-picocli")
  implementation("io.quarkus:quarkus-config-yaml")
  implementation("io.quarkus:quarkus-arc")
  implementation("io.quarkus:quarkus-info")
  implementation("io.quarkus:quarkus-rest-client-jackson")
  implementation("io.quarkus:quarkus-qute")
  implementation(libs.semver4j)
  implementation(libs.assertj.core)
  implementation(libs.quarkus.github.api)
  implementation(project(":docling-serve-client"))
  implementation(project(":docling-testcontainers"))

  compileOnly(libs.quarkus.wiremock)

  testImplementation("io.quarkus:quarkus-junit5")
  testImplementation("io.quarkus:quarkus-junit5-mockito")
  testImplementation(libs.quarkus.wiremock.test)
  testImplementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
}

tasks.withType<Test> {
  systemProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager")
  jvmArgs("--add-opens", "java.base/java.lang=ALL-UNNAMED")
}

tasks.withType<JavaCompile> {
  options.encoding = "UTF-8"
  options.compilerArgs.add("-parameters")
}
