import org.jetbrains.kotlin.config.JvmTarget

plugins {
    val kotlinVersion = "1.7.10"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion
    id("net.mamoe.mirai-console") version "2.14.0"
}

group = "io.github.xtyuns.sujectiverepeatermirai"
version = "1.0.0-SNAPSHOT"

repositories {
    maven("https://mirrors.cloud.tencent.com/nexus/repository/maven-public/")
    mavenCentral()
}

val vertxVersion = "4.4.1"
dependencies {
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.vertx:vertx-core")
    implementation("io.vertx:vertx-web")
    implementation("io.vertx:vertx-lang-kotlin")
    implementation(platform("io.vertx:vertx-stack-depchain:$vertxVersion"))
    implementation(kotlin("stdlib-jdk8"))
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.compileKotlin {
    kotlinOptions.jvmTarget = JvmTarget.JVM_17.description
}
