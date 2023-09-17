package com.FortuneTiger.FT.Simul.slots.presentation.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.FortuneTiger.FT.Simul.slots.presentation.game_logic.GamePhase
import com.FortuneTiger.FT.Simul.slots.presentation.game_logic.SlotViewModel

@Composable
fun BalanceArea(viewModel: SlotViewModel) {
    val state = viewModel.gameState.collectAsState().value
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .offset(y = (-20).dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .width(150.dp)
                .height(50.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = com.FortuneTiger.FT.Simul.theme.R.drawable.balance),
                contentDescription = "Balance",
                modifier = Modifier
                    .width(150.dp)
                    .height(50.dp),
                contentScale = ContentScale.Fit
            )
            Text(text = state.balance.toString(), style = MaterialTheme.typography.bodyMedium)
        }
        Box(
            modifier = Modifier
                .width(150.dp)
                .height(50.dp)
        ) {
            Image(
                painter = painterResource(id = com.FortuneTiger.FT.Simul.theme.R.drawable.total),
                contentDescription = "Total",
                modifier = Modifier
                    .width(150.dp)
                    .height(50.dp),
                contentScale = ContentScale.Fit
            )
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(1f)
                        .clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = null,
                            onClick = { viewModel.betMinus() },
                            enabled = state.gamePhase is GamePhase.Bet
                        )
                )
                Text(text = state.bet.toString(), style = MaterialTheme.typography.bodyMedium)
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(1f)
                        .clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = null,
                            onClick = { viewModel.betPlus() },
                            enabled = state.gamePhase is GamePhase.Bet
                        )
                )
            }
        }

    }
}