@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.pcs.android.library)
    alias(libs.plugins.pcs.android.hilt)
    alias(libs.plugins.pcs.android.retrofit)
}
android {
    namespace = "com.pcs.di"
}
dependencies {
    api(libs.log.timber)
    api(libs.bundles.network)
}
