plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}
apply(from = rootProject.file("tools/dependencies/dependencies.versions.gradle.kts"))

val core: String by extra
val appcompat: String by extra
val androidMaterial: String by extra
val constraintLayout: String by extra
val workRuntime: String by extra
val junit: String by extra
val testExtJunit: String by extra
val testEspressoJunit: String by extra
val retrofit: String by extra
val lifecycle: String by extra
val lifecycleCommon: String by extra
val coroutinesCore: String by extra
val coroutinesAndroid: String by extra
val koin: String by extra

android {
    namespace = "com.example.blogdtitest"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.blogdtitest"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:$core")
    implementation("androidx.appcompat:appcompat:$appcompat")
    implementation("com.google.android.material:material:$androidMaterial")
    implementation("androidx.constraintlayout:constraintlayout:$constraintLayout")
    testImplementation("junit:junit:$junit")
    androidTestImplementation("androidx.test.ext:junit:$testExtJunit")
    androidTestImplementation("androidx.test.espresso:espresso-core:$testEspressoJunit")

    //Retrofit2
    implementation("com.squareup.retrofit2:retrofit:$retrofit")
    implementation("com.squareup.retrofit2:converter-gson:$retrofit")

    // Lifecycle components
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle")
    implementation("androidx.lifecycle:lifecycle-extensions:$lifecycleCommon")
    implementation("androidx.lifecycle:lifecycle-common-java8:$lifecycle")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle")

    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesCore")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesAndroid")

    implementation("io.insert-koin:koin-core:$koin")
    implementation("io.insert-koin:koin-android-compat:$koin")
    implementation("io.insert-koin:koin-androidx-workmanager:$koin")

    implementation("androidx.work:work-runtime-ktx:$workRuntime")
}