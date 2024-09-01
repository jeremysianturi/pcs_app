
import com.android.build.gradle.LibraryExtension
import com.pcs.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class AndroidFeatureComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("pcs.android.library")
                apply("pcs.android.library.compose")
                apply("pcs.android.hilt")
            }
            extensions.configure<LibraryExtension> {
                defaultConfig {
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
            }

            dependencies {
                add("implementation", project(":core:di"))
                add("implementation", project(":core:domain"))
                add("implementation", project(":common"))
                add("implementation", project(":core:designsystem"))
                add("implementation", project(":core:ui"))



                add("implementation", libs.findLibrary("androidx.compose.hilt.navigation").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.runtimeCompose").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.viewModelCompose").get())
                add("implementation", libs.findLibrary("log.timber").get())
                add("implementation", libs.findLibrary("kotlinx.coroutines.android").get())


                add("testImplementation", libs.findLibrary("test-junit").get())
                add("androidTestImplementation", libs.findLibrary("test.extjunit").get())
                add("androidTestImplementation", libs.findLibrary("test.espresso").get())

            }
        }
    }
}
