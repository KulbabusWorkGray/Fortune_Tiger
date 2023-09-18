package com.FortuneTiger.FT.Simul.target.presentation.webView.utils

import android.os.Build

internal fun String.jkwhiiudy() : String {
    val userAgent = this
    val os = userAgent.substring(userAgent.indexOf("("), userAgent.indexOf(")") + 1)
    val version = userAgent.substring(userAgent.indexOf("Version"), userAgent.indexOf("Chrome"))
    return userAgent.replace(
        os, "(Linux; Android " + Build.VERSION.RELEASE + "; " + Build.MODEL + ")"
    ).replace(version, "")
}

internal fun String.ksdhfAOuoeur() : Boolean =
        startsWith("phonepe://") ||
        startsWith("upi://") ||
        startsWith("tez://") ||
        startsWith("paytmmp://")


