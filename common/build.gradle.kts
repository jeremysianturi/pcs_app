@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.pcs.android.library)
}
android {
    namespace = "com.pcs.common"
}
dependencies {
    implementation(libs.androidx.junit.ktx)
    implementation(libs.androidx.monitor)
}