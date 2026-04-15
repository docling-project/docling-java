plugins {
  id("org.cyclonedx.bom")
}

tasks.named("cyclonedxDirectBom") {
  group = "build"
  description = "Generates a per-project CycloneDX Software Bill of Materials (SBOM)"
}
