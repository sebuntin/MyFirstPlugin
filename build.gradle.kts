plugins {
    id("java")
    id("org.jetbrains.intellij") version "1.10.1"
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}


// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    version.set("2021.3") // 编译的IDEA版本
    type.set("IC") // Target IDE Platform 支持的目标IDE平台: IC表示社区版, IU表示商用版

//    plugins.set(listOf(/* Plugin Dependencies */)) // 依赖的插件
    plugins.set(listOf("mobi.hsz.idea.gitignore:4.4.0"))
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "11"
        targetCompatibility = "11"

        // 需要增加文件编码(否则Win10上汉子会出现乱码)
        options.encoding = "UTF-8"
    }

    // 修改插件兼容的IDE版本范围为2021.3(since)到2022.3(until)
    patchPluginXml {
        sinceBuild.set("213")
        untilBuild.set("231.*")
    }


    // 插件签名
    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    // 自动发布插件任务(可忽略)
    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}
