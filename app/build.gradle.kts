plugins {
    id("com.android.application")
    id("kotlin-android")
    kotlin("android.extensions")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs")
}

android {
    compileSdk = Versions.compile_sdk

    defaultConfig {
        applicationId = Build.application_id
        minSdk = Versions.min_sdk
        targetSdk = Versions.target_sdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = Versions.jvm_target
    }
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {
    implementation(Jetbrains.kotlin_stdlib)
    implementation(Jetbrains.kotlin_coroutines)

    implementation(AndroidX.core_ktx)
    implementation(AndroidX.app_compat)
    implementation(AndroidX.lifecycle)
    implementation(AndroidX.swipe_refresh)
    implementation(AndroidX.preferences)
    implementation(AndroidX.navigation_fragment)
    implementation(AndroidX.navigation_ui)

    implementation(Google.material)

    implementation(Square.picasso)
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")

    testImplementation(Test.junit)

    androidTestImplementation(Test.androidx_junit)
    androidTestImplementation(Test.espresso)

}