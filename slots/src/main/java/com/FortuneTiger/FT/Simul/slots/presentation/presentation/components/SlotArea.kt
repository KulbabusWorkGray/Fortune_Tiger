package com.FortuneTiger.FT.Simul.slots.presentation.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.FortuneTiger.FT.Simul.slots.presentation.game_logic.SlotViewModel
import com.FortuneTiger.FT.Simul.theme.R
import com.FortuneTiger.FT.Simul.theme.SlotAreaBackgroundColor
import com.FortuneTiger.FT.Simul.theme.screen.SLOT_AREA_PADDING
import com.FortuneTiger.FT.Simul.theme.screen.SLOT_AREA_SIZE
import com.FortuneTiger.FT.Simul.theme.screen.dpFromPx

@Composable
fun SlotArea(viewModel: SlotViewModel) {
    val state = viewModel.gameState.collectAsState().value
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(428f / 470f)
            .paint(
                painter = painterResource(id = R.drawable.pagoda),
                contentScale = ContentScale.Fit
            )
            .padding(bottom = SLOT_AREA_PADDING.dpFromPx())
        ,
        contentAlignment = Alignment.BottomCenter
    ) {
        Row(
            modifier = Modifier
                .size(SLOT_AREA_SIZE.dpFromPx())
                .background(SlotAreaBackgroundColor),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            SlotCanvas(
                state = state
            )
        }
    }
}