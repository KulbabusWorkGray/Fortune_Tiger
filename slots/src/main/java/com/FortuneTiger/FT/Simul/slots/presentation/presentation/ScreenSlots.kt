package com.FortuneTiger.FT.Simul.slots.presentation.presentation

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.FortuneTiger.FT.Simul.slots.presentation.game_logic.GamePhase
import com.FortuneTiger.FT.Simul.slots.presentation.game_logic.SlotScreen
import com.FortuneTiger.FT.Simul.slots.presentation.game_logic.SlotViewModel
import com.FortuneTiger.FT.Simul.slots.presentation.presentation.screens.ScreenGame
import com.FortuneTiger.FT.Simul.slots.presentation.presentation.screens.ScreenMenu
import com.FortuneTiger.FT.Simul.slots.presentation.presentation.screens.ScreenWin

const val targetOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

@Composable
fun ScreenSlots() {

    val activity = LocalContext.current as Activity
    DisposableEffect(targetOrientation) {
        val originalOrientation = activity.requestedOrientation
        activity.requestedOrientation = targetOrientation
        onDispose {
            activity.requestedOrientation = originalOrientation
        }
    }

    val viewModel : SlotViewModel = hiltViewModel()
    val state = viewModel.gameState.collectAsState().value

    when(state.screen) {
        SlotScreen.MENU -> {
            ScreenMenu(viewModel = viewModel)
        }
        SlotScreen.GAME -> {
            if (state.gamePhase is GamePhase.Result && state.gamePhase.isCelebrationScreen) {
                ScreenWin()
            } else {
                ScreenGame(viewModel = viewModel)
            }
        }

        else -> {}
    }
}