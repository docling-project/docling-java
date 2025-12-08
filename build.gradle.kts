import org.jreleaser.model.Active

plugins {
  `base`
  id("docling-shared")
  alias(libs.plugins.jreleaser)
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
  }

  release {
    github {
      enabled = true
      overwrite = true
      repoOwner = "docling-project"
      name = rootProject.name
      sign = true

      changelog {
        formatted = Active.ALWAYS
        links = true
        preset = "conventional-commits"
        contributors {
          format = "- {{contributorName}}{{#contributorUsernameAsLink}} ({{.}}){{/contributorUsernameAsLink}}"
        }
        hide {
          categories = listOf("merge")
          contributors = listOf("GitHub", "dependabot", "docling-java-ops")
        }
        extraProperties.put("categorizeScopes", true)
      }

      issues {
        enabled = true
        comment = "🎉 This issue has been resolved in `{{tagName}}` ([Release Notes]({{releaseNotesUrl}}))"
      }

      commitAuthor {
        name = "docling-java-ops"
        email = "deepsearch-core@zurich.ibm.com"
      }
    }
  }

  deploy {
    maven {
      mavenCentral {
        create("sonatype") {
          retryDelay = 100
          maxRetries = 100
          connectTimeout = 18000
          active = org.jreleaser.model.Active.ALWAYS
          url = "https://central.sonatype.com/api/v1/publisher"
          applyMavenCentralRules = true
          stagingRepository(rootProject.layout.buildDirectory.dir("staging-deploy").get().asFile.absolutePath)
        }
      }
    }
  }

  announce {
    discord {
      active = org.jreleaser.model.Active.RELEASE
    }

    discussions {
      active = org.jreleaser.model.Active.RELEASE
      organization = "docling-project"
      team = "docling-java"
      title = "{{projectNameCapitalized}} {{projectVersion}} released!"
    }
  }
}
