package com.FortuneTiger.FT.Simul.target.presentation

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import com.FortuneTiger.FT.Simul.target.presentation.webView.ColntewoWEbVieoiuww

@Composable
fun GrwriuVIewWljlj.ScreenTarget() {
    var url : String? by remember { mutableStateOf(null) }
    val sp = LocalContext.current.getSharedPreferences("sp", Context.MODE_PRIVATE)
    val activity = LocalContext.current as Activity
    LaunchedEffect(Unit) {
        url = sp.getString("key", null)
        WindowCompat.setDecorFitsSystemWindows(activity.window, true)
    }
    url?.let {
        Log.d("TAG", "url = $it")
        ColntewoWEbVieoiuww(
            oiwqeu = img,
            lkjfksdjf = { filePathCallback = it },
            oweurdlfj = it
        )
    }
}