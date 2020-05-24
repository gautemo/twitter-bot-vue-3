import twitter4j.TwitterFactory
import twitter4j.conf.ConfigurationBuilder

fun tweet(project: Project){
    val regex = """\[.+]""".toRegex()
    val version = regex.find(project.lastRecordedChangelog)?.value?.trim('[', ']')
    val message = "${project.name}, ${if(version.isNullOrEmpty()) "new version" else version} is out! (${project.changelog}) #VueJS"
    sendTweet(message)
}

fun sendTweet(message: String){
    val secrets = Secrets()

    val cb = ConfigurationBuilder()
    cb.setOAuthConsumerKey(secrets.consumerKey)
    cb.setOAuthConsumerSecret(secrets.consumerSecret)
    cb.setOAuthAccessToken(secrets.accessToken)
    cb.setOAuthAccessTokenSecret(secrets.accessTokenSecret)
    val twitter = TwitterFactory(cb.build()).instance
    twitter.updateStatus(message)
}