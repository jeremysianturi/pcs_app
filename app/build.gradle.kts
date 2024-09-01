@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pcs.android.application)
    alias(libs.plugins.pcs.android.hilt)
    alias(libs.plugins.pcs.android.application.compose)
}

android {
    namespace = "com.pcs.modularization"
    defaultConfig {
        applicationId = "com.pcs.compose"
        versionCode = 1
        versionName = "1.0.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures{
        buildConfig = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}
dependencies {
    implementation(projects.core.di)
    implementation(projects.core.domain)
    implementation(projects.core.data)
    implementation(projects.common)
    implementation(projects.core.ui)
    implementation(projects.core.designsystem)

    implementation(projects.features.userlist)
    implementation(projects.features.profile)

    implementation(libs.androidx.compose.activity)
    implementation(libs.androidx.compose.navigation)
    implementation(libs.androidx.compose.hilt.navigation)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.lifecycle.viewModelCompose)

    testImplementation(libs.test.junit)
    androidTestImplementation(libs.test.extjunit)
    androidTestImplementation(libs.test.espresso)
    androidTestImplementation(libs.test.compose.ui.junit)
    debugImplementation(libs.androidx.compose.ui.manifest)
}