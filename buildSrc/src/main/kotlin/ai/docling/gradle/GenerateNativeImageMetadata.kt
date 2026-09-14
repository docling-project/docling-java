package ai.docling.gradle

import java.lang.module.ModuleDescriptor

import org.gradle.api.DefaultTask
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.provider.ListProperty
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.TaskAction

/**
 * Generates GraalVM reflection metadata for compiled classes in exported module packages.
 * Scanning after compilation includes Lombok-generated builders and nested classes automatically.
 * The docling-native-image convention plugin packages the resulting JSON in each model JAR.
 *
 * Declared inputs and relative path sensitivity allow Gradle to reuse cached output across checkouts.
 */
@CacheableTask
abstract class GenerateNativeImageMetadata : DefaultTask() {
  /** Production class output directories supplied by the module's compileJava task. */
  @get:InputFiles
  @get:PathSensitive(PathSensitivity.RELATIVE)
  abstract val classesDirectories: ConfigurableFileCollection

  /** Exact exported package names to omit, supplied by the nativeImageMetadata extension. */
  @get:Input
  abstract val excludedPackages: ListProperty<String>

  /** Directory containing the generated reflect-config.json, before it is copied into JAR resources. */
  @get:OutputDirectory
  abstract val outputDirectory: DirectoryProperty

  @TaskAction
  fun generate() {
    // Read the compiler's module descriptor using the public JDK API. This handles comments and
    // qualified exports without parsing Java source, loading classes, or resolving requires clauses.
    // module-info.class is already tracked as part of classesDirectories for incremental builds.
    val packages = classesDirectories.files.flatMap { root ->
      val moduleInfo = root.resolve("module-info.class")
      check(moduleInfo.isFile) { "Missing compiled module descriptor: $moduleInfo" }
      moduleInfo.inputStream().use { ModuleDescriptor.read(it) }.exports().map { it.source() }
    }.toSet() - excludedPackages.get().toSet()

    // Discover types from class-file paths without loading them or resolving optional Jackson
    // dependencies. Package membership determines inclusion; Jackson annotations are not inspected.
    // JPMS exports name exact packages, not their descendants. For example, excluding the base API
    // package must not also exclude its exported request/response packages. An open module does not
    // make every package a model package; only its explicit exports are used here.
    // package-info describes a package rather than a model, and module-info has no matching package.
    // Deduplication handles overlapping roots; sorting makes the generated JSON reproducible.
    val names = classesDirectories.files.flatMap { root ->
      root.walkTopDown()
        .filter { it.isFile && (it.extension == "class") && (it.name != "package-info.class") }
        .map { it.relativeTo(root).invariantSeparatorsPath }
        .filter { path -> path.substringBeforeLast('/', "").replace('/', '.') in packages }
        // Preserve '$' in binary names such as DoclingDocument$SectionHeaderItem$Builder.
        .map { it.removeSuffix(".class").replace('/', '.') }
        .toList()
    }.distinct().sorted()

    val entries = names.map { name ->
      // Follow the existing Jackson2*/Jackson3* helper naming convention. Register these helpers
      // only when the corresponding ObjectMapper is reachable in the native application, allowing
      // consumers to use either Jackson version independently. Other model entries are unconditional.
      val mapper = when {
        name.contains(".serialization.Jackson2") -> "com.fasterxml.jackson.databind.ObjectMapper"
        name.contains(".serialization.Jackson3") -> "tools.jackson.databind.ObjectMapper"
        else -> null
      }
      val condition = mapper?.let { "\n    \"condition\": { \"typeReachable\": \"$it\" }," } ?: ""

      // Register members for reflective construction, accessor/builder calls, and inspection of
      // field annotations (including enum constants). This broad policy also registers members
      // Jackson may not use; it does not attempt to infer the minimum required reflection access.
      """
        |  {$condition
        |    "name": "$name",
        |    "allDeclaredConstructors": true,
        |    "allDeclaredMethods": true,
        |    "allDeclaredFields": true
        |  }
      """.trimMargin()
    }

    // Emit the legacy reflection configuration format understood by GraalVM. The convention plugin
    // copies this file to META-INF/native-image/<group>/<artifact>/ for automatic discovery.
    val output = outputDirectory.file("reflect-config.json").get().asFile
    output.parentFile.mkdirs()
    output.writeText(entries.joinToString(",\n", "[\n", "\n]\n"))
  }
}
