package com.FortuneTiger.FT.Simul.target.presentation.webView

import android.annotation.SuppressLint
import android.net.Uri
import android.webkit.ValueCallback
import android.webkit.WebView
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.FortuneTiger.FT.Simul.target.presentation.webView.utils.dlsfjwoeruweouSLJD
import com.FortuneTiger.FT.Simul.target.presentation.webView.utils.oiweuSDLjfoweiru
import com.FortuneTiger.FT.Simul.target.presentation.webView.utils.weorusVLeiwStieto

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun ColntewoWEbVieoiuww(
    oiwqeu: ActivityResultLauncher<Void?>,
    lkjfksdjf: (ValueCallback<Array<Uri>>) -> Unit,
    oweurdlfj: String,
) {
    var canGoBack by remember { mutableStateOf(false) }
    var canGoForward by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }

    val activity = (LocalContext.current as ComponentActivity)
    val webView = remember { WebView(activity) }
    activity.apply {
        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (webView.canGoBack()) {
                        webView.goBack()
                    }
                }
            }
        )
    }

    Column(Modifier.fillMaxSize()) {
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .weight(12f),
            factory = {
                webView.apply {
                    weorusVLeiwStieto()
                    dlsfjwoeruweouSLJD(oiwqeu, lkjfksdjf)
                    oiweuSDLjfoweiru(
                        dfleworuALJ = activity,
                        setCanGoBack = { canGoBack = it },
                        setCanGoForward = { canGoForward = it },
                        setIsLoading = { isLoading = it }
                    )
                    loadUrl(oweurdlfj)
                }
            }
        )
    }
}