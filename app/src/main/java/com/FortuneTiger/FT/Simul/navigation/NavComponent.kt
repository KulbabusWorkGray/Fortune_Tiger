package com.FortuneTiger.FT.Simul.navigation

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.FortuneTiger.FT.Simul.R
import com.FortuneTiger.FT.Simul.controller.ControllerEvent
import com.FortuneTiger.FT.Simul.controller.ControllerViewModel
import com.FortuneTiger.FT.Simul.slots.presentation.presentation.ScreenSlots
import com.FortuneTiger.FT.Simul.target.presentation.GrayWebViewHolder
import com.FortuneTiger.FT.Simul.target.presentation.ScreenTarget
import com.FortuneTiger.FT.Simul.theme.BackgroundGradientColors
import com.FortuneTiger.FT.Simul.theme.utils.Bitmaps
import kotlinx.coroutines.flow.collectLatest

@[Composable]
fun GrayWebViewHolder.NavComponent() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Loader.name
    ) {
        composable(
            route = Screen.Loader.name
        ) {
            ScreenLoader(
                navController = navController
            )
        }
        composable(
            route = Screen.Slots.name
        ) {
            ScreenSlots()
        }
        composable(
            route = Screen.Target.name
        ) {
            ScreenTarget()
        }
        composable(
            route = Screen.Reconnect.name
        ) {
            ScreenReconnect(
                onReconnect = { navController.navigate(Screen.Loader.name) }
            )
        }

    }
}

@Composable
fun ScreenLoader(
    viewModel: ControllerViewModel = hiltViewModel(),
    navController: NavController
) {
    val activity = LocalContext.current as Activity
    LaunchedEffect(Unit) {
        viewModel.regulate()
        viewModel.events.collectLatest {
            Log.d("TAG", "collect event : $it")
            when (it) {
                is ControllerEvent.EventToTarget -> {
                    navController.navigate(Screen.Target.name) {
                        popUpTo(Screen.Loader.name) { inclusive = true }
                    }
                }

                ControllerEvent.EventToReconnect -> {
                    navController.navigate(Screen.Reconnect.name) {
                        popUpTo(Screen.Loader.name) { inclusive = true }
                    }
                }

                ControllerEvent.EventToSlots -> {
                    Bitmaps.init(activity.resources)
                    navController.navigate(Screen.Slots.name) {
                        popUpTo(Screen.Loader.name) { inclusive = true }
                    }
                }
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Transparent, Color.Black.copy(alpha = 0.7f)
                    )
                )
            )
    ) {
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
            Loader()
            Text(
                text = stringResource(id = R.string.loading)
            )
        }
    }
}

enum class Screen {
    Loader, Slots, Target, Reconnect
}