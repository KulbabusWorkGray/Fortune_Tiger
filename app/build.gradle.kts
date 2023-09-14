@file:Suppress("UnstableApiUsage")

import java.io.FileInputStream
import java.util.Properties

val keysFile = rootProject.file("keys.properties")
val keyProperties = Properties()
keyProperties.load(FileInputStream(keysFile))

plugins {
    id(Plugins.androidApplication)
    id(Plugins.kotlinAndroid)
    id(Plugins.hilt)
    id(Plugins.ksp)
}

android {
    namespace = Modules.App.nameSpace
    compileSdk = Android.compileSdk

    defaultConfig {
        applicationId = Android.DefaultConfig.applicationId
        minSdk = Android.DefaultConfig.minSdk
        targetSdk = Android.DefaultConfig.targetSdk
        versionCode = Android.DefaultConfig.versionCode
        versionName = Android.DefaultConfig.versionName

        vectorDrawables { useSupportLibrary = Android.DefaultConfig.useSupportLibrary }

        // Gray Part keys
        buildConfigField("String", "URL", keyProperties["URL"] as String)
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            signingConfig = signingConfigs.getByName("debug")
            proguardFiles(
                getDefaultProguardFile(Android.Proguard.proguardFileName),
                Android.Proguard.proguardRulesPro
            )
        }
    }
    compileOptions {
        sourceCompatibility = Java.sourceCompatibility
        targetCompatibility = Java.targetCompatibility
    }
    kotlinOptions {
        jvmTarget = Java.jvmTarget
    }
    packaging {
        resources {
            excludes += Android.Packaging.excludes
        }
    }
    composeOptions {
        kotlinCompilerExtensionVersion = ComposeOptions.kotlinCompilerExtensionVersion
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

    implementation(project(Modules.Slots.path))
    implementation(project(Modules.Target.path))
    implementation(project(Modules.Theme.path))

    // Compose
    implementation(Compose.activityCompose)
    implementation(Compose.material3)
    implementation(Compose.composeUiGraphics)
    implementation(Compose.composeUiToolingPreview)
    debugImplementation(Compose.composeUiTooling)
    // Navigation
    implementation(Compose.navigation)

    // Hilt
    implementation(Hilt.hiltAndroid)
    implementation(Hilt.hiltNavigationCompose)
    ksp(Hilt.hiltCompiler)

    // Retrofit
    implementation(Retrofit.converterGson)
    implementation(Retrofit.converterScalars)

    // Window Manager (to calculate screen size)
    implementation(Window.windowManager)
}