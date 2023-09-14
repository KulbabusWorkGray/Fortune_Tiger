package com.FortuneTiger.FT.Simul

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application() {

    companion object {
        const val SP_NAME = "sp"
        const val SP_KEY = "key"
    }
}