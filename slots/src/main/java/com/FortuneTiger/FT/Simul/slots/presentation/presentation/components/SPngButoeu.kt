package com.FortuneTiger.FT.Simul.slots.presentation.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.FortuneTiger.FT.Simul.slots.presentation.game_logic.KSweoru
import com.FortuneTiger.FT.Simul.slots.presentation.game_logic.SltBrain
import com.FortuneTiger.FT.Simul.theme.R

@Composable
fun SPngButoeu(viewModel: SltBrain) {
    val state = viewModel.gameStwwetwgw.collectAsState().value
    Image(
        painter = if (state.oieuoiwqueoqw !is KSweoru.Skdsf) {
            painterResource(R.drawable.spin)
        } else {
            painterResource(R.drawable.spin_disabled)
        },
        contentDescription = "Play",
        modifier = Modifier
            .fillMaxWidth(0.6f)
            .aspectRatio(2f)
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null,
                onClick = { viewModel.spInRulletka() },
                enabled = state.oieuoiwqueoqw !is KSweoru.Skdsf
            )
    )
}