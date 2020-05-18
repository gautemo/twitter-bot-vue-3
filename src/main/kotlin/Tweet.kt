fun tweet(project: Project){
    val regex = """\[.+]""".toRegex()
    val version = regex.find(project.lastRecordedChangelog)?.value?.trim('[', ']')
    val message = "${project.name}, $version (${project.changelog})"
    println(message)
}