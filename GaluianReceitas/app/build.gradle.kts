
plugins {
        alias(libs.plugins.android.application)
        alias(libs.plugins.kotlin.android)
        alias(libs.plugins.kotlin.compose)
        id("org.jetbrains.kotlin.kapt")
    }

    android {
        namespace = "com.example.galuianreceitas"
        compileSdk = 34

        defaultConfig {
            applicationId = "com.example.galuianreceitas"
            minSdk = 24
            targetSdk = 34
            versionCode = 1
            versionName = "1.0"

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        buildTypes {
            release {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }
        kotlinOptions {
            jvmTarget = "11"
        }
        buildFeatures {
            compose = true
        }
    }

    dependencies {

        // ViewModel e LiveData para MVVM
        implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
        implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")

        // Retrofit para consumir APIs
        implementation ("com.squareup.retrofit2:retrofit:2.9.0")
        implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

        // Room para banco de dados local
        implementation ("androidx.room:room-runtime:2.6.1")
        kapt ("androidx.room:room-compiler:2.6.1")


        // Material Design
        implementation ("com.google.android.material:material:1.9.0")

        implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
        implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")

        implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
        implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
        implementation ("com.squareup.retrofit2:retrofit:2.9.0")
        implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
        implementation ("androidx.room:room-runtime:2.6.1")
        implementation(libs.androidx.room.common)
        implementation ("com.google.android.material:material:1.9.0")
        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.lifecycle.runtime.ktx)
        implementation(libs.androidx.activity.compose)
        implementation(platform(libs.androidx.compose.bom))
        implementation(libs.androidx.ui)
        implementation(libs.androidx.ui.graphics)
        implementation(libs.androidx.ui.tooling.preview)
        implementation(libs.androidx.material3)
        testImplementation(libs.junit)
        androidTestImplementation(libs.androidx.junit)
        androidTestImplementation(libs.androidx.espresso.core)
        androidTestImplementation(platform(libs.androidx.compose.bom))
        androidTestImplementation(libs.androidx.ui.test.junit4)
        debugImplementation(libs.androidx.ui.tooling)
        debugImplementation(libs.androidx.ui.test.manifest)
    }
