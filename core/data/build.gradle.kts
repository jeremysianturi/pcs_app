@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pcs.android.library)
    alias(libs.plugins.pcs.android.hilt)
}
android {
    namespace = "com.pcs.data"

}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.di)
    api(projects.model.apiresponse)
}