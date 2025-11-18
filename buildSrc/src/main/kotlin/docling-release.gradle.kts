plugins {
  `maven-publish`
  id("org.jreleaser")
}

jreleaser {
  gitRootSearch = true
  strict = true

  project {
    license = "MIT"
    inceptionYear = "2025"
    tags = listOf("docling", "java", "AI")
    vendor = "IBM"
    authors = listOf("Eric Deandrea", "Thomas Vitale", "Alex Soto")
    maintainers = listOf("Eric Deandrea", "Thomas Vitale", "Alex Soto")
    copyright = "2025-Present IBM"
    description = "A Java API for Docling"

    languages {
      java {
        version = property("java.version").toString()
      }
    }

    links {
      homepage = "https://docling-project.github.io/docling-java"
      documentation = "https://docling-project.github.io/docling-java"
      license = "https://opensource.org/license/mit"
      bugTracker = "https://github.com/docling-project/docling-java/issues"
      help = "https://github.com/docling-project/docling-java/discussions"
      contribute = "https://github.com/docling-project/docling-java/blob/main/CONTRIBUTING.md"
    }
  }

  signing {
    active = org.jreleaser.model.Active.ALWAYS
    armored = true
    verify = true
  }

  release {
    github {
      repoOwner = "docling-project"
      name = rootProject.name
      sign = true

      changelog {
        links = true
        preset = "conventional-commits"
      }

//      releaseNotes {
//        enabled = true
//      }

      issues {
        enabled = true
        comment = "🎉 This issue has been resolved in `{{tagName}}` ([Release Notes]({{releaseNotesUrl}}))"
      }
    }
  }

  deploy {
    maven {
      mavenCentral {
        create("sonatype") {
          active = org.jreleaser.model.Active.ALWAYS
          url = "https://central.sonatype.com/api/v1/publisher"
          applyMavenCentralRules = true
          stagingRepository(layout.buildDirectory.dir("staging-deploy").get().asFile.absolutePath)
        }
      }
    }
  }

  announce {
    active = org.jreleaser.model.Active.ALWAYS

    discussions {
      organization = "docling-project"
      team = "docling-java"
      title = "{{projectNameCapitalized}} {{projectVersion}} released!"
      message = "🚀 {{projectNameCapitalized}} {{projectVersion}} has been released! {{releaseNotesUrl}}"
    }
  }
}

publishing {
  repositories {
    maven {
      url = uri(layout.buildDirectory.dir("staging-deploy"))
    }
  }

  publications {
    create<MavenPublication>("maven") {
      from(components["java"])

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
