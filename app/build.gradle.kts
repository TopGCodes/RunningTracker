plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    kotlin("plugin.serialization") version "2.0.20"
    alias(libs.plugins.compose.compiler) apply true
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.abhi.runningtracker"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.abhi.runningtracker"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

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

    //material 3
    implementation(jetpack.material3)

    //Hilt
    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")


    //Navigation
    implementation(JetpackNavigation.navigationCompose)
    implementation(JetpackNavigation.navigationUi)
    implementation(JetpackNavigation.navigationFragment)

    // Coroutines
    implementation(Coroutines.android)

    //paging
    implementation(Paging.compose)
    implementation(Paging.runtime)

    //Imagelibrary
    implementation(ImageLibrary.glide)

    //lifecycleAware
    implementation(Lifecycle.lifecyclescope)
    implementation(Lifecycle.viewModel)

    //Animations
    implementation(Animation.lottie)

    //serialization
    implementation(Serialization.json)

    //GsonConvertor
    implementation(Gson.convertor)

    //Workmanager
    implementation(Workmanager.runtime)

    //Room
//    implementation(Room.runtime)
//    kapt(Room.compiler)
//    implementation(Room.ktx)


}