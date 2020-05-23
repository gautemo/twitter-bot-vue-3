import kotlin.Exception

class Main {
    fun runLambda(){
        try {
            val projects = readProjects()
            for (project in projects) {
                val latestChange = checkChangelog(project.changelog)
                if (latestChange != project.lastRecordedChangelog) {
                    project.lastRecordedChangelog = latestChange
                    tweet(project)
                    updateProjectLastRecordedChange(project)
                }
            }
        }catch (e: Exception){
            println("Exception:  ${e.message}")
        }
    }
}

fun main(){
    val main = Main()
    main.runLambda()
}