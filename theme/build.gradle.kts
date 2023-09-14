@file:Suppress("UnstableApiUsage")

plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
}

android {
    namespace = Modules.Theme.nameSpace
    compileSdk = Android.compileSdk

    defaultConfig { minSdk = Android.DefaultConfig.minSdk }

    buildTypes {
        release {
            isMinifyEnabled = true
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
    composeOptions {
        kotlinCompilerExtensionVersion = ComposeOptions.kotlinCompilerExtensionVersion
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    // Compose
    implementation(Compose.material3)
    implementation(Compose.composeUiGraphics)
    implementation(Compose.composeUiToolingPreview)
    debugImplementation(Compose.composeUiTooling)
    // Accompanist for painting status bar
    implementation(Accompanist.systemUiController)
}