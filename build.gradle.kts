// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        mavenCentral()
        maven("http://repository.jetbrains.com/all")
        maven("https://maven.fabric.io/public")
        maven("https://www.jitpack.io")
        
    }
    dependencies {
        classpath(Libs.android_gradle_plugin)
        classpath(Libs.kotlin_gradle_plugin)
        classpath (Libs.google_services)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("http://repository.jetbrains.com/all")
    }
}

task("clean") {
    delete(rootProject.buildDir)
}
