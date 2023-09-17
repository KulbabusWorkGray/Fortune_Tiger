package com.FortuneTiger.FT.Simul.slots.presentation.presentation.screens

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.FortuneTiger.FT.Simul.theme.BackgroundGradientColors
import com.FortuneTiger.FT.Simul.theme.R
import com.FortuneTiger.FT.Simul.theme.SlotAreaBackgroundColor2
import com.FortuneTiger.FT.Simul.theme.screen.SCREEN_WIDTH

@Composable
fun ScreenWin() {
    val imageSize = remember { androidx.compose.animation.core.Animatable(SCREEN_WIDTH / 2f) }
    LaunchedEffect(Unit) {
        imageSize.animateTo(SCREEN_WIDTH * 0.7f, animationSpec = tween(durationMillis = 700))
    }
    Column(
        verticalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = BackgroundGradientColors
                )
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize(0.8f)
                    .offset(y = 10.dp)
                    .background(color = SlotAreaBackgroundColor2)
            )
            Image(
                painter = painterResource(id = R.drawable.pagoda),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
            Image(
                painter = painterResource(id = R.drawable.big_win),
                contentDescription = null,
                modifier = Modifier.size(LocalDensity.current.run { imageSize.value.toDp() }).offset(y = 30.dp)
            )

        }
    }
}