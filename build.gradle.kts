plugins {
    kotlin("jvm") version "1.3.72"
    kotlin("plugin.serialization") version "1.3.70"
}

group = "app.gautemo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(platform("software.amazon.awssdk:bom:2.13.23"))
    implementation("software.amazon.awssdk:dynamodb")
    implementation("software.amazon.awssdk:secretsmanager")
    implementation("org.twitter4j", "twitter4j-core", "4.0.7")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.20.0")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }

    jar {
        archiveFileName.set("twitter-bot-vue-3.jar")
        from(configurations
            .runtimeClasspath.get().files.filter { it.exists() }
            .map { if (it.isDirectory) it else zipTree(it) })
    }

    register<Exec>("deploy"){
        dependsOn(build)
        val updateScript = "aws lambda update-function-code --function-name twitter-bot-vue-3 --zip-file fileb://./build/libs/twitter-bot-vue-3.jar"
        if(org.apache.tools.ant.taskdefs.condition.Os.isFamily(org.apache.tools.ant.taskdefs.condition.Os.FAMILY_WINDOWS)){
            commandLine("cmd", "/c", updateScript)
        }else{
            commandLine(updateScript)
        }
    }
}