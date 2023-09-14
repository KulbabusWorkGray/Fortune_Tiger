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
import com.FortuneTiger.FT.Simul.target.presentation.GrayWebViewHolder
import com.FortuneTiger.FT.Simul.target.presentation.webView.utils.MyGalleryContract
import com.FortuneTiger.FT.Simul.theme.FortuneTigerTheme
import com.FortuneTiger.FT.Simul.theme.screen.SCREEN_HEIGHT
import com.FortuneTiger.FT.Simul.theme.screen.SCREEN_WIDTH
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity(), GrayWebViewHolder {

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
        SCREEN_HEIGHT = windowMetrics.bounds.run { maxOf(height(), width()) }
        SCREEN_WIDTH = windowMetrics.bounds.run { minOf(height(), width()) }
    }
}