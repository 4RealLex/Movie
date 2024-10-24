import java.util.Properties
import java.io.FileInputStream

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    localProperties.load(FileInputStream(localPropertiesFile))
}

val tmdbApiKey: String? = localProperties.getProperty("TMDB_API_KEY")
if (tmdbApiKey == null || tmdbApiKey.isEmpty()) {
    throw GradleException("TMDB_API_KEY is missing. Please add it to your local.properties file.")
}

android {
    namespace = "com.example.movieapp2"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.movieapp2"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // Securely pass the API key
        buildConfigField("String", "TMDB_API_KEY", "\"$tmdbApiKey\"")
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}


dependencies {
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")

    // Jetpack Compose dependencies
    implementation("androidx.compose.ui:ui:1.7.4")
    implementation("androidx.compose.ui:ui-tooling-preview:1.7.4")
    implementation("androidx.compose.material3:material3:1.3.0")
    implementation("androidx.activity:activity-compose:1.9.3")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.6")
    implementation("androidx.compose.runtime:runtime:1.7.4")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.6")

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.8.3")

    // Retrofit for API requests
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

    // Coil for image loading
    implementation("io.coil-kt:coil-compose:2.7.0")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")

    // Unit testing dependencies
    testImplementation("junit:junit:4.13.2")

    // Android instrumented test dependencies
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

    // Compose Testing dependencies
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.7.4")
    debugImplementation("androidx.compose.ui:ui-tooling:1.7.4")

    // Material Icons
    implementation("androidx.compose.material:material-icons-extended:1.7.4")

    // Foundation
    implementation("androidx.compose.foundation:foundation:1.7.4")
}

