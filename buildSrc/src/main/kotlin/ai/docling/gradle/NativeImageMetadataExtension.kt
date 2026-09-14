package ai.docling.gradle

import org.gradle.api.provider.ListProperty

/** Project-level configuration for the docling-native-image convention plugin. */
abstract class NativeImageMetadataExtension {
  /** Exact exported package names to omit from generated reflection metadata. */
  abstract val excludedPackages: ListProperty<String>

  /** Excludes non-model API packages without excluding their exported subpackages. */
  fun excludePackages(vararg packages: String) {
    excludedPackages.addAll(*packages)
  }
}
