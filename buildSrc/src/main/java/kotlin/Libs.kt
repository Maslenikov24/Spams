object Libs {

    const val android_gradle_plugin = "com.android.tools.build:gradle:${Versions.android_gradle_plugin}"
    const val kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin_version}"
    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin_version}"

    // AndroidX
    const val androidx_app_compat = "androidx.appcompat:appcompat:${Versions.androidx_appcompat}"
    const val androidx_recyclerview = "androidx.recyclerview:recyclerview:${Versions.androidx_recyclerview}"
    const val androidx_cardview = "androidx.cardview:cardview:${Versions.androidx_cardview}"
    const val androidx_material = "com.google.android.material:material:${Versions.androidx_material}"
    const val androidx_core = "androidx.core:core-ktx:${Versions.ktx}"
    const val androidx_constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.androidx_constraint_layout}"

    // Test
    const val junit_test = "junit:junit:${Versions.junit_test}"
    const val androidx_test_junit = "androidx.test.ext:junit:${Versions.androidx_test_junit}"
    const val androidx_test_espresso = "androidx.test.espresso:espresso-core:${Versions.androidx_test_espresso}"

    // Kotlin
    const val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlin_coroutines}"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlin_coroutines}"

    // Toothpick
    const val toothpick_ktp = "com.github.stephanenicolas.toothpick:ktp:${Versions.toothpick}"
    const val toothpick_kapt = "com.github.stephanenicolas.toothpick:toothpick-compiler:${Versions.toothpick}"
    const val toothpick_smoothie_viewmodel = "com.github.stephanenicolas.toothpick:smoothie-lifecycle-viewmodel-ktp:${Versions.toothpick}"
    const val toothpick_smoothie_lifecycle = "com.github.stephanenicolas.toothpick:smoothie-lifecycle-ktp:${Versions.toothpick}"

    // Cicerone
    const val cicerone = "ru.terrakok.cicerone:cicerone:${Versions.cicerone}"

    // Lifecycle
    const val lifecycle_extensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val lifecycle_livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val lifecycle_viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"

    // AdapterDelegates
    const val adapter_delegates = "com.hannesdorfmann:adapterdelegates4:${Versions.adapter_delegates4}"
    const val adapter_delegates_dsl = "com.hannesdorfmann:adapterdelegates4-kotlin-dsl-layoutcontainer:${Versions.adapter_delegates4}"
    const val adapter_delegate_pagination = "com.hannesdorfmann:adapterdelegates4-pagination:${Versions.adapter_delegates4}"

    // Networking
    const val okhttp_logging_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp_logging_interceptor}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofit_converter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val retrofit_coroutines_adapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.retrofit_coroutines}"

    // Glide
    const val glide_runtime = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glide_compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"

    // Timber
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    // Unused
    const val plugins_android_junit5 = "1.5.2.0"
    const val androidx_paging_ktx = "androidx.paging:paging-runtime-ktx:${Versions.android_paging_ktx}"
    const val androidx_fragment = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    const val androidx_multidex = "androidx.multidex:multidex:${Versions.multidex}"
    const val flexbox_layout = "com.google.android:flexbox:${Versions.flexbox_layout}"
    const val pull_refresh_layout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.refresh_pull_layout}"
    const val transition_transition = "androidx.transition:transition:${Versions.transition_transition}"
}