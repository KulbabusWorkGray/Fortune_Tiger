package com.FortuneTiger.FT.Simul.slots.presentation.presentation

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.FortuneTiger.FT.Simul.slots.presentation.game_logic.KSweoru
import com.FortuneTiger.FT.Simul.slots.presentation.game_logic.SaltScrelrj
import com.FortuneTiger.FT.Simul.slots.presentation.game_logic.SltBrain
import com.FortuneTiger.FT.Simul.slots.presentation.presentation.screens.ScljewMenuuu
import com.FortuneTiger.FT.Simul.slots.presentation.presentation.screens.ScreenGame
import com.FortuneTiger.FT.Simul.slots.presentation.presentation.screens.ScreenWin

const val targetOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

@Composable
fun SclejwrSOureu() {

    val activity = LocalContext.current as Activity
    DisposableEffect(targetOrientation) {
        val originalOrientation = activity.requestedOrientation
        activity.requestedOrientation = targetOrientation
        onDispose {
            activity.requestedOrientation = originalOrientation
        }
    }

    val vlsdfj : SltBrain = hiltViewModel()
    val oweiuroweu = vlsdfj.gameStwwetwgw.collectAsState().value

    when(oweiuroweu.xbvmnbsjdhfgj) {
        SaltScrelrj.MENASUO -> {
            ScljewMenuuu(viewModel = vlsdfj)
        }
        SaltScrelrj.GALJELR -> {
            if (oweiuroweu.oieuoiwqueoqw is KSweoru.sdlfOI && oweiuroweu.oieuoiwqueoqw.isCelebrationScreen) {
                ScreenWin()
            } else {
                ScreenGame(viewModel = vlsdfj)
            }
        }

        else -> {}
    }
}