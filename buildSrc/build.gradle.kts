import org.gradle.kotlin.dsl.`kotlin-dsl`

repositories {
    mavenCentral()
    maven("http://repository.jetbrains.com/all")
    maven("https://maven.fabric.io/public")
    maven("https://www.jitpack.io")
}

plugins {
    `kotlin-dsl`
}
