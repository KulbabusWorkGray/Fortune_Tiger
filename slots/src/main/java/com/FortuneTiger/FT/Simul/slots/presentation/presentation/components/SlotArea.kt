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
import com.FortuneTiger.FT.Simul.slots.presentation.game_logic.SltBrain
import com.FortuneTiger.FT.Simul.theme.R
import com.FortuneTiger.FT.Simul.theme.oidfuOSDUf
import com.FortuneTiger.FT.Simul.theme.screen.SLDjfsdlf
import com.FortuneTiger.FT.Simul.theme.screen.sjdhfjs
import com.FortuneTiger.FT.Simul.theme.screen.dpFromPx

@Composable
fun SlotArea(viewModel: SltBrain) {
    val state = viewModel.gameStwwetwgw.collectAsState().value
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(428f / 470f)
            .paint(
                painter = painterResource(id = R.drawable.pagoda),
                contentScale = ContentScale.Fit
            )
            .padding(bottom = SLDjfsdlf.dpFromPx())
        ,
        contentAlignment = Alignment.BottomCenter
    ) {
        Row(
            modifier = Modifier
                .size(sjdhfjs.dpFromPx())
                .background(oidfuOSDUf),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            SlotCanvas(
                state = state
            )
        }
    }
}