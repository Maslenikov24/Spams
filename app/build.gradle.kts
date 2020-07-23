import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import org.gradle.kotlin.dsl.extra
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.setValue

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(29)
    buildToolsVersion("29.0.2")

    defaultConfig {
        applicationId = "com.univer.mvvm_coroutines_toothpick_room"
        minSdkVersion(23)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

}

dependencies {

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Libs.kotlin_stdlib)
    implementation(Libs.androidx_app_compat)
    implementation(Libs.androidx_core)
    implementation(Libs.androidx_constraintlayout)
    implementation(Libs.androidx_material)
    implementation(Libs.androidx_cardview)
    implementation(Libs.androidx_recyclerview)


    testImplementation(Libs.junit_test)
    androidTestImplementation(Libs.androidx_test_junit)
    androidTestImplementation(Libs.androidx_test_espresso)

    // DI
    implementation(Libs.toothpick_ktp)
    implementation(Libs.toothpick_smoothie_lifecycle)
    implementation(Libs.toothpick_smoothie_viewmodel)
    kapt(Libs.toothpick_kapt)

    // Cicerone
    implementation(Libs.cicerone)

    // Lifecycle
    implementation(Libs.lifecycle_extensions)
    implementation(Libs.lifecycle_livedata)
    implementation(Libs.lifecycle_viewmodel)

    // Adapter Delegates
    implementation(Libs.adapter_delegates)
    implementation(Libs.adapter_delegates_dsl)
    implementation(Libs.adapter_delegate_pagination)

    //Networking
    implementation(Libs.okhttp_logging_interceptor)
    implementation(Libs.retrofit)

    // Coroutines
    implementation(Libs.coroutines_android)
    implementation(Libs.coroutines)

    //Timber
    //implementation(Libs.timber)

    testImplementation(Libs.junit_test)
    androidTestImplementation(Libs.androidx_test_junit)
    androidTestImplementation(Libs.androidx_test_espresso)

    /*// DI
    implementation("com.github.stephanenicolas.toothpick:ktp:${Versions.toothpick}")
    implementation("com.github.stephanenicolas.toothpick:toothpick-compiler:${Versions.toothpick}")
    implementation("com.github.stephanenicolas.toothpick:smoothie-lifecycle-viewmodel-ktp:${Versions.toothpick}")
    kapt("com.github.stephanenicolas.toothpick:smoothie-lifecycle-ktp:${Versions.toothpick}")

    // Cicerone
    implementation("ru.terrakok.cicerone:cicerone:${Versions.cicerone}")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}")

    // Adapter Delegates
    //implementation("com.hannesdorfmann:adapterdelegates4:${Versions.adapter_delegates4}")
    //implementation("com.hannesdorfmann:adapterdelegates4-kotlin-dsl-layoutcontainer:${Versions.adapter_delegates4}")
    //implementation("com.hannesdorfmann:adapterdelegates4-pagination:${Versions.adapter_delegates4}")

    implementation(Libs.adapter_delegates)
    implementation(Libs.adapter_delegates_dsl)
    implementation(Libs.adapter_delegate_pagination)

    //Networking
    implementation("com.squareup.okhttp3:logging-interceptor:${Versions.okhttp_logging_interceptor}")
    implementation("com.squareup.retrofit2:retrofit:${Versions.retrofit}")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlin_coroutines}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlin_coroutines}")

    //Timber
    implementation("com.jakewharton.timber:timber:4.7.1")*/
}
