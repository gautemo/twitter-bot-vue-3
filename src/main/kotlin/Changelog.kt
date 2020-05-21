import java.net.URL

fun checkChangelog(url: String): String{
    val rawGithubUrl = url.replace("https://github.com/", "https://raw.githubusercontent.com/").replace("blob/", "")
    val text = URL(rawGithubUrl).readText()
    return text.lines().first()
}