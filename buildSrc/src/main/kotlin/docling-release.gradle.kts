plugins {
  id("docling-sbom")
  `maven-publish`
}

publishing {
  repositories {
    maven {
      url = uri(rootProject.layout.buildDirectory.dir("staging-deploy"))
    }
  }

  publications {
    create<MavenPublication>("maven") {
      from(components["java"])

      // Attach SBOM artifacts to publication
      val cyclonedxTask = tasks.named<org.cyclonedx.gradle.CyclonedxDirectTask>("cyclonedxDirectBom")
      artifact(cyclonedxTask.flatMap { it.jsonOutput }) {
        classifier = "cyclonedx"
        extension = "json"
      }

      artifact(cyclonedxTask.flatMap { it.xmlOutput }) {
        classifier = "cyclonedx"
        extension = "xml"
      }

      pom {
        url = "https://docling-project.github.io/docling-java"
        name = project.name
        description.set(provider { project.description })
        licenses {
          license {
            name = "MIT License"
            url = "https://opensource.org/license/mit"
          }
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
