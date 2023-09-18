package com.FortuneTiger.FT.Simul.theme.utils

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.FortuneTiger.FT.Simul.theme.R
import com.FortuneTiger.FT.Simul.theme.screen.SLdkjfsdf
import kotlin.math.roundToInt
import kotlin.properties.Delegates

object Btpowpoer {

    var oewudkfs: List<ImageBitmap> by Delegates.notNull()

    fun init(resources: Resources) {
        oewudkfs = buildList {
            listOf(
                R.drawable.symbol_1,
                R.drawable.symbol_2,
                R.drawable.symbol_3,
                R.drawable.symbol_4,
                R.drawable.symbol_5,
                R.drawable.symbol_6,
                R.drawable.symbol_7,
                R.drawable.symbol_8,
                R.drawable.symbol_9,
                R.drawable.symbol_10,
                R.drawable.symbol_11
            ).forEach {
                add(
                    oiuedkjhvksdLJA(
                        res = it,
                        width = SLdkjfsdf.roundToInt(),
                        height = SLdkjfsdf.roundToInt(),
                        resources = resources
                    )
                )
            }
        }
    }

    private fun oiuedkjhvksdLJA(
        @DrawableRes res: Int,
        width: Int,
        height: Int,
        resources: Resources,
    ): ImageBitmap {
        return Bitmap.createScaledBitmap(
            BitmapFactory.decodeResource(resources, res),
            width,
            height,
            false
        ).asImageBitmap()
    }
}