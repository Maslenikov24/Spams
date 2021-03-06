import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import org.gradle.kotlin.dsl.extra
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.setValue

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("com.google.gms.google-services")
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.2")

    defaultConfig {
        applicationId = "com.graduate.spams"
        minSdkVersion(23)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures{
        viewBinding = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
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
    implementation("androidx.legacy:legacy-support-v4:1.0.0")


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

    // Room
    implementation(Libs.room_runtime)
    kapt(Libs.room_compiler)
    implementation(Libs.room)

    // Datastore
    implementation(Libs.data_store)

    // Lifecycle
    implementation(Libs.lifecycle_extensions)
    implementation(Libs.lifecycle_livedata)
    implementation(Libs.lifecycle_viewmodel)
    implementation(Libs.lifecycle_runtime)

    // Adapter Delegates
    implementation(Libs.adapter_delegates)
    implementation(Libs.adapter_delegates_viewbinding)
//    implementation(Libs.adapter_delegates_layoutcontainer)
//    implementation(Libs.adapter_delegate_pagination)

    // Networking
    implementation(Libs.okhttp_logging_interceptor)
    implementation(Libs.retrofit)
    implementation(Libs.retrofit_converter)
    implementation(Libs.retrofit_coroutines_adapter)

    // Coroutines
    implementation(Libs.coroutines_android)
    implementation(Libs.coroutines)

    // Timber
    implementation(Libs.timber)

    // ViewBinding
    implementation(Libs.view_binding)

    // Coil
    implementation(Libs.coil)

    // Test
    testImplementation(Libs.junit_test)
    androidTestImplementation(Libs.androidx_test_junit)
    androidTestImplementation(Libs.androidx_test_espresso)

    // Import the BoM for the Firebase platform
    implementation(platform(Libs.firebase_bom))

    // Declare the dependencies for the Firebase Cloud Messaging and Analytics libraries
    implementation(Libs.firebase_messaging)
    implementation(Libs.firebase_analytics)

    // Lottie
    implementation(Libs.lottie)
}
