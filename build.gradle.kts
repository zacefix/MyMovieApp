
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.1.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${rootProject.extra["kotlinGradlePluginVersion"]}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${rootProject.extra["hiltPluginVersion"]}")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${rootProject.extra["navigationSafeArgsPluginApiVersion"]}")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

tasks.register("clean", Delete::class) { delete(rootProject.buildDir) }