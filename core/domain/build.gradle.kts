@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pcs.android.library)
    alias(libs.plugins.pcs.android.hilt)
}

android {
    namespace = "com.pcs.domain"
}

dependencies {
    api(projects.model.entity)
    implementation(libs.androidx.corektx)
    implementation(libs.kotlinx.coroutines.android)
}