plugins {
  id("io.freefair.lombok")
}

val libs = extensions.getByType(VersionCatalogsExtension::class.java).named("libs")

configure<io.freefair.gradle.plugins.lombok.LombokExtension> {
  version = libs.findVersion("lombok").get().toString()
}

tasks.named<io.freefair.gradle.plugins.lombok.tasks.Delombok>("delombok") {
  val projectLayout = project.layout

  doFirst {
    // Temporarily rename module-info.java to avoid module processing
    val moduleInfo = projectLayout.projectDirectory.file("src/main/java/module-info.java").asFile
    if (moduleInfo.exists()) {
      moduleInfo.renameTo(projectLayout.projectDirectory.file("src/main/java/module-info.java.bak").asFile)
    }
  }

  doLast {
    // Restore module-info.java
    val moduleInfoBak = projectLayout.projectDirectory.file("src/main/java/module-info.java.bak").asFile
    if (moduleInfoBak.exists()) {
      moduleInfoBak.renameTo(projectLayout.projectDirectory.file("src/main/java/module-info.java").asFile)
    }
  }
}

