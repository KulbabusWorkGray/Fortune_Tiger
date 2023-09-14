import org.gradle.api.JavaVersion

object Java {
    val sourceCompatibility = JavaVersion.VERSION_17
    val targetCompatibility = JavaVersion.VERSION_17
    const val jvmTarget = "17"
}

object Plugins {
    const val kotlinJvm = "org.jetbrains.kotlin.jvm"
    const val javaLibrary = "java-library"
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "org.jetbrains.kotlin.android"
    const val androidLibrary = "com.android.library"
    const val ksp = "com.google.devtools.ksp"
    const val hilt = "com.google.dagger.hilt.android"
}

object ClassPath {
    internal const val kotlin_version = "1.9.10"
    internal const val gradle_version = "8.1.0"
    internal const val hilt_version = "2.48"
    internal const val ksp_version = "1.9.10-1.0.13"

    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
    const val gradle = "com.android.tools.build:gradle:$gradle_version"
    const val hilt = "com.google.dagger:hilt-android-gradle-plugin:$hilt_version"
    const val ksp = "com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:$ksp_version"
}

object Android {

    const val nameSpace = "com.FortuneTiger.FT.Simul"
    const val compileSdk = 34

    object DefaultConfig {
        const val applicationId = nameSpace
        const val minSdk = 24
        const val targetSdk = compileSdk
        const val versionCode = 1
        const val versionName = "1.0"
        const val useSupportLibrary = true
    }

    object Proguard {
        const val proguardFileName = "proguard-android-optimize.txt"
        const val proguardRulesPro = "proguard-rules.pro"
    }

    object Packaging {
        const val excludes = "/META-INF/{AL2.0,LGPL2.1}"
    }
}

object ComposeOptions {
    const val kotlinCompilerExtensionVersion = "1.5.3"
}
