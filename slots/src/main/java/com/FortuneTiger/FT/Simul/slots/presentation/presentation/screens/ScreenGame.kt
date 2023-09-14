package com.FortuneTiger.FT.Simul.slots.presentation.presentation.screens

import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.FortuneTiger.FT.Simul.slots.presentation.game_logic.SlotViewModel
import com.FortuneTiger.FT.Simul.slots.presentation.presentation.components.SlotArea
import com.FortuneTiger.FT.Simul.slots.presentation.presentation.components.SpinButton
import com.FortuneTiger.FT.Simul.theme.BackgroundGradientColors
import com.FortuneTiger.FT.Simul.theme.R

@Composable
fun ScreenGame(
    viewModel: SlotViewModel
) {
    val activity = (LocalContext.current as ComponentActivity)
    activity.apply {
        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    viewModel.toMenu()
                }
            }
        )
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = BackgroundGradientColors
                    )
                )
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(428f/353f)
                    .paint(
                        painter = painterResource(id = R.drawable.casi_777),
                        contentScale = ContentScale.FillBounds
                    )
                    .offset(
                        y = 0.dp
                    )
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(
                space = 16.dp,
                alignment = Alignment.Bottom
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            SlotArea(viewModel = viewModel)
            SpinButton(viewModel = viewModel)
        }
    }
}