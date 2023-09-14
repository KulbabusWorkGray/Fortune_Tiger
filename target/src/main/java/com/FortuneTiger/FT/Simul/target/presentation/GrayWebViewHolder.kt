package com.FortuneTiger.FT.Simul.target.presentation

import android.net.Uri
import android.webkit.ValueCallback
import androidx.activity.result.ActivityResultLauncher

interface GrayWebViewHolder {
    var filePathCallback: ValueCallback<Array<Uri>>
    var img: ActivityResultLauncher<Void?>
}