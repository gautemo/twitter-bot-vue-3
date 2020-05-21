import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.json.content
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest

const val secretName = "twitter-bot-vue-3/auth"

class Secrets {
    val consumerKey: String
    val consumerSecret: String
    val accessToken: String
    val accessTokenSecret: String

    init {
        val client = SecretsManagerClient.builder().region(Region.EU_WEST_1).build()
        val secrets = client.getSecretValue(GetSecretValueRequest.builder().secretId(secretName).build())
        val json = Json(JsonConfiguration.Default)
        val jsonObject = json.parseJson(secrets.secretString()).jsonObject
        consumerKey = jsonObject["consumer-key"]!!.content
        consumerSecret = jsonObject["consumer-secret"]!!.content
        accessToken = jsonObject["access-token"]!!.content
        accessTokenSecret = jsonObject["access-token-secret"]!!.content
    }
}