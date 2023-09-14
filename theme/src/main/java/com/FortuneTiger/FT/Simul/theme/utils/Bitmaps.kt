package com.FortuneTiger.FT.Simul.theme.utils

/**
 * Put your resources in theme module to reuse them in controller screen
 */

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import androidx.annotation.DrawableRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.FortuneTiger.FT.Simul.theme.R
import com.FortuneTiger.FT.Simul.theme.screen.SYMBOL_SIZE
import kotlin.math.roundToInt
import kotlin.properties.Delegates

object Bitmaps {

    var isInitialized by mutableStateOf(false)

    var slots: List<ImageBitmap> by Delegates.notNull()

    fun init(resources: Resources) {
        slots = buildList {
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
                    initBitmap(
                        res = it,
                        width = SYMBOL_SIZE.roundToInt(),
                        height = SYMBOL_SIZE.roundToInt(),
                        resources = resources
                    )
                )
            }
        }
        isInitialized = true
    }

    /**
     * @param keepScale use FALSE for background; TRUE for all game objects, this makes sure your
     * game objects keep size relative to device screen resolution, and background goes as is
     */
    private fun initBitmap(
        @DrawableRes res: Int,
        width: Int,
        height: Int,
        resources: Resources,
        keepScale: Boolean = true,
        rotateAngleClockwise: Float = 0f
    ): ImageBitmap {
        val bitmap = Bitmap.createScaledBitmap(
            BitmapFactory.decodeResource(resources, res),
            /*if (keepScale) width.resizeWidth() else */width,
            /*if (keepScale) height.resizeHeight() else */height,
            false
        )
        val matrix = Matrix()
        matrix.setRotate(rotateAngleClockwise)
        return Bitmap.createBitmap(
            bitmap, 0, 0, bitmap.width, bitmap.height, matrix, false
        ).asImageBitmap()
    }
}