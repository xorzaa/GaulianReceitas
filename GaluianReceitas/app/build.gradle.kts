plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "com.example.galuianreceitas"
    compileSdk = 35

    // Habilitando ViewBinding corretamente
    viewBinding {
        enable = true // Sintaxe correta para habilitar
    }

    // Habilita a geração de BuildConfig para permitir campos personalizados
    buildFeatures {
        compose = true
        buildConfig = true // Habilita o BuildConfig
    }

    defaultConfig {
        applicationId = "com.example.galuianreceitas"
        minSdk = 21
        targetSdk = 35
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
            // A chave da API deve ser definida dentro do bloco `buildTypes`
            buildConfigField("String", "SPOONACULAR_API_KEY", "\"${findProperty("SPOONACULAR_API_KEY")}\"")
        }
        debug {
            // A chave da API também é necessária no buildType debug
            buildConfigField("String", "SPOONACULAR_API_KEY", "\"${findProperty("SPOONACULAR_API_KEY")}\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    // Lifecycle (ViewModel, LiveData)
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")

    // Retrofit for API consumption
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Room for local database
    implementation("androidx.room:room-ktx:2.5.0")
    kapt("androidx.room:room-compiler:2.6.1")

    // Material Design (Atualizado para a versão mais recente)
    implementation("com.google.android.material:material:1.8.0") // ou tente 1.10.0 se disponível

    // UI - Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // Core Android Libraries
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    // Debug
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
