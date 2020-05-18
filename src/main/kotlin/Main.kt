class Main {
    fun runLambda(){
        val projects = readProjects()
        for(project in projects){
            val latestChange = checkChangelog(project.changelog)
            if(latestChange != project.lastRecordedChangelog){
                project.lastRecordedChangelog = latestChange
                tweet(project)
                updateProjectLastRecordedChange(project)
            }
        }
    }
}

fun main(){
    val main = Main()
    main.runLambda()
}