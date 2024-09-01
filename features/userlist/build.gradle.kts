@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.pcs.android.feature.compose)
}

android {
    namespace = "com.pcs.repolist"
}
dependencies{
    implementation(libs.image.coil.compose)
    implementation(libs.androidx.core.i18n)
}