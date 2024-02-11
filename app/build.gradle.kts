plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    namespace = "com.demo.Kinopoisk"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.demo.Kinopoisk"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures{
        viewBinding = true
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
}

dependencies {
    implementation(project(":core"))

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    val fragment_version = "1.6.2"
    implementation("androidx.fragment:fragment-ktx:$fragment_version")

    val dagger_version = "2.49"
    implementation("com.google.dagger:dagger:$dagger_version")
    kapt("com.google.dagger:dagger-compiler:$dagger_version")

    val retrofit_version = "2.9.0"

    implementation("com.squareup.retrofit2:converter-gson:$retrofit_version")
    implementation("com.squareup.retrofit2:retrofit:$retrofit_version")


    val coroutines_version = "1.7.3"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")




    val lifeCycleVersion ="2.6.2"
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifeCycleVersion")

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifeCycleVersion")

    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifeCycleVersion")

    val glideVersion = "4.16.0"
    implementation("com.github.bumptech.glide:glide:$glideVersion")

   // implementation("com.mikepenz.fastadapter:fastadapter:3.2.6")
}