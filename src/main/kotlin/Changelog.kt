import java.net.URL

fun checkChangelog(url: String): String{
    val text = URL(url).readText()
    return text.lines().first()
}

fun main(){
    checkChangelog("https://raw.githubusercontent.com/vuejs/vuex/4.0/CHANGELOG.md")
}