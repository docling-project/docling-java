import ai.docling.gradle.GenerateNativeImageMetadata
import ai.docling.gradle.NativeImageMetadataExtension

plugins {
  `java-library`
}

val nativeImageMetadata = extensions.create<NativeImageMetadataExtension>("nativeImageMetadata").apply {
  excludedPackages.convention(emptyList())
}

val generateNativeImageMetadata = tasks.register<GenerateNativeImageMetadata>("generateNativeImageMetadata") {
  // Keep this linked to the extension so configuration in the project's DSL block is evaluated lazily.
  excludedPackages.convention(nativeImageMetadata.excludedPackages)
  classesDirectories.from(tasks.named<JavaCompile>("compileJava").flatMap { it.destinationDirectory })
  outputDirectory.set(layout.buildDirectory.dir("generated/native-image"))
}

tasks.named<ProcessResources>("processResources") {
  from(generateNativeImageMetadata) {
    into("META-INF/native-image/${project.group}/${project.name}")
  }
}
