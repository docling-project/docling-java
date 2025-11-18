tasks.register("jreleaserFullRelease") {
    dependsOn(subprojects.mapNotNull { it.tasks.findByName("jreleaserFullRelease") })
}
