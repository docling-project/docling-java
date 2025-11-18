// This is just here to make it easy to run a full release for all child projects
tasks.register("jreleaserFullRelease") {
    dependsOn(subprojects.mapNotNull { it.tasks.findByName("jreleaserFullRelease") })
}
