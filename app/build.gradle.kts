plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.example.cardvascular"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.cardvascular"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
}


dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.legacy.support.v4)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.annotation.jvm)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.8.0")
    implementation("com.squareup.picasso:picasso:2.71828")

    implementation("com.github.denzcoskun:ImageSlideshow:0.1.2")
    implementation("com.github.ismaeldivita:chip-navigation-bar:1.4.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.3.70")

    implementation("androidx.viewpager2:viewpager2:1.0.0")
    implementation("com.google.android.material:material:1.1.0")

    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")
}