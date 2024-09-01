@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.pcs.android.feature.compose)
}
android {
    namespace = "com.pcs.profile"
}

dependencies{
    implementation(libs.image.coil.compose)
}