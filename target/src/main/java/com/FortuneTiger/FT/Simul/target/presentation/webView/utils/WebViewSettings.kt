package com.FortuneTiger.FT.Simul.target.presentation.webView.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.launch
import androidx.core.net.toUri

@SuppressLint("SetJavaScriptEnabled")
fun WebView.webViewSettings() {
    val userAgent = settings.userAgentString.formatUserAgent()

    scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
    isVerticalScrollBarEnabled = true
    settings.apply {
        domStorageEnabled = true
        javaScriptEnabled = true
        allowContentAccess = true
        allowFileAccess = true
        databaseEnabled = true
        domStorageEnabled = true
        javaScriptCanOpenWindowsAutomatically = true
        loadWithOverviewMode = true
        userAgentString = userAgent
    }
    layoutParams = ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT
    )
}

fun WebView.webChromeClient(
    img : ActivityResultLauncher<Void?>,
    setFilePathCallback : (ValueCallback<Array<Uri>>) -> Unit
) {
    webChromeClient = object : WebChromeClient() {
        override fun onShowFileChooser(
            webView: WebView?,
            filePathCallback: ValueCallback<Array<Uri>>?,
            fileChooserParams: FileChooserParams?
        ): Boolean {

            try {
                img.launch()
            } catch (_: Exception) {}

            if (filePathCallback != null) {
                setFilePathCallback(filePathCallback)
            }
            return true
        }
    }
}

fun WebView.webViewClient(
    activity: Activity,
    setCanGoBack: (Boolean) -> Unit,
    setCanGoForward: (Boolean) -> Unit,
    setIsLoading: (Boolean) -> Unit
) {
    webViewClient = object : WebViewClient() {

        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?,
        ): Boolean = request?.let {
            return try {
                Log.d("TAG", "uri = ${it.url}")
                when {
                    it.url.toString().isPaymentUrl() -> {
                        activity.startActivity(Intent(Intent.ACTION_VIEW).also { intent ->
                            intent.data = Uri.parse(request.url.toString())
                        })
                        true
                    }
                    !it.url.toString().contains("http") -> {
                        val intent = Intent(Intent.ACTION_VIEW, it.url)
                        activity.startActivity(intent)
                        true
                    }

                    it.url.toString().contains("intent://") -> {
                        activity.startActivity(Intent(Intent.ACTION_VIEW).also { intent ->
                            intent.data = "${request.url}".replace("intent", "https").toUri()
                        })
                        true
                    }

                    it.url.toString().contains("http") -> {
                        false
                    }

                    else -> super.shouldOverrideUrlLoading(view, request)
                }
            } catch (e: Exception) {
                true
            }
        } ?: super.shouldOverrideUrlLoading(view, request)

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            setCanGoBack(canGoBack())
            setCanGoForward(canGoForward())
            setIsLoading(true)
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            setCanGoBack(canGoBack())
            setCanGoForward(canGoForward())
            setIsLoading(false)
        }
    }
}