package com.FortuneTiger.FT.Simul.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val FortuneTigerColorScheme = darkColorScheme(
    primary = CLjsdfwoeru,
    secondary = lksdfjLDfj,
)

@Composable
fun FortuneTigerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    DisposableEffect(systemUiController, darkTheme) {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = darkTheme
        )
        onDispose {}
    }

    MaterialTheme(
        colorScheme = FortuneTigerColorScheme,
        typography = LDjfds,
        shapes = Sooweru,
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.linearGradient(
                            colors = iquwye,
                            tileMode = TileMode.Decal
                        )
                    )
            ) {
                content()
            }
        }
    )
}