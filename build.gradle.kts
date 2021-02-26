// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        jcenter()
        
    }
    dependencies {
        classpath(Libs.android_gradle_plugin)
        classpath(Libs.kotlin_gradle_plugin)
        "classpath"("com.android.tools.build:gradle:4.0.0")
		classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.30")

		// NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        
    }
}

task("clean") {
    delete(rootProject.buildDir)
}
