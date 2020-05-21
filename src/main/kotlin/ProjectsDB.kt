import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import software.amazon.awssdk.services.dynamodb.model.*

data class Project(val name: String, val changelog: String, var lastRecordedChangelog: String)

const val tableName = "twitter-bot-vue-3"

fun readProjects(): List<Project>{
    val client = DynamoDbClient.create()

    val result = client.scan {
        it.tableName(tableName)
    }
    return result.items().map {
        Project(it["Project"]!!.s(), it["Changelog"]!!.s(), it["LastRecordedChange"]!!.s())
    }
}

fun updateProjectLastRecordedChange(project: Project){
    val client = DynamoDbClient.create()

    val key = mutableMapOf(Pair("Project", createAttributeValueString(project.name)))
    val updates = mutableMapOf(Pair("LastRecordedChange", AttributeValueUpdate
        .builder()
        .value(createAttributeValueString(project.lastRecordedChangelog))
        .action(AttributeAction.PUT)
        .build()
    ))

    client.updateItem {
        it.tableName(tableName)
        it.key(key)
        it.attributeUpdates(updates)
    }
}

fun createAttributeValueString(value: String): AttributeValue = AttributeValue.builder().s(value).build()