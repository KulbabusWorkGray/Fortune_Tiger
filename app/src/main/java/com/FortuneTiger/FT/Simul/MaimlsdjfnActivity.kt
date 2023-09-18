package com.FortuneTiger.FT.Simul

import android.net.Uri
import android.os.Bundle
import android.webkit.ValueCallback
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.core.view.WindowCompat
import androidx.window.layout.WindowMetricsCalculator
import com.FortuneTiger.FT.Simul.navigation.NavComponent
import com.FortuneTiger.FT.Simul.target.presentation.GrwriuVIewWljlj
import com.FortuneTiger.FT.Simul.target.presentation.webView.utils.MyGalleryContract
import com.FortuneTiger.FT.Simul.theme.FortuneTigerTheme
import com.FortuneTiger.FT.Simul.theme.screen.S_HEIGHTLJWLRJ
import com.FortuneTiger.FT.Simul.theme.screen.S_WIDlkwljg
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MaimlsdjfnActivity : ComponentActivity(), GrwriuVIewWljlj {

    override lateinit var filePathCallback: ValueCallback<Array<Uri>>
    override lateinit var img: ActivityResultLauncher<Void?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initInsetsWithWindowManager()
        img = registerForActivityResult(MyGalleryContract()) { result ->
            result?.let { filePathCallback.onReceiveValue(arrayOf(result)) }
        }
        setContent {
            FortuneTigerTheme {
                NavComponent()
            }
        }
    }

    private fun initInsetsWithWindowManager() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val windowMetrics = WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(this)
        S_HEIGHTLJWLRJ = windowMetrics.bounds.run { maxOf(height(), width()) }
        S_WIDlkwljg = windowMetrics.bounds.run { minOf(height(), width()) }
    }
}