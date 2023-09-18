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
import com.FortuneTiger.FT.Simul.controller.SDlfkjwoerweOIU
import com.FortuneTiger.FT.Simul.controller.SAkjdhiweIUiyds
import com.FortuneTiger.FT.Simul.slots.presentation.presentation.SclejwrSOureu
import com.FortuneTiger.FT.Simul.target.presentation.GrwriuVIewWljlj
import com.FortuneTiger.FT.Simul.target.presentation.ScreenTarget
import com.FortuneTiger.FT.Simul.theme.iquwye
import com.FortuneTiger.FT.Simul.theme.utils.Btpowpoer
import kotlinx.coroutines.flow.collectLatest

@[Composable]
fun GrwriuVIewWljlj.NavComponent() {

    val navaldjf = rememberNavController()

    NavHost(
        navController = navaldjf,
        startDestination = Screen.Loader.name
    ) {
        composable(
            route = Screen.Loader.name
        ) {
            ASsldfLodf(
                nacConter = navaldjf
            )
        }
        composable(
            route = Screen.Slots.name
        ) {
            SclejwrSOureu()
        }
        composable(
            route = Screen.Target.name
        ) {
            ScreenTarget()
        }
        composable(
            route = Screen.Reconnect.name
        ) {
            SLDjfoweruewru(
                onReconnect = { navaldjf.navigate(Screen.Loader.name) }
            )
        }

    }
}

@Composable
fun ASsldfLodf(
    vwMdl: SAkjdhiweIUiyds = hiltViewModel(),
    nacConter: NavController
) {
    val activity = LocalContext.current as Activity
    LaunchedEffect(Unit) {
        vwMdl.mzxbcdf()
        vwMdl.eventsFlowControl.collectLatest {
            Log.d("TAG", "collect event : $it")
//            Toast.makeText(activity, it.reason, Toast.LENGTH_LONG).show()
            when (it) {
                is SDlfkjwoerweOIU.EventToTarget -> {
                    nacConter.navigate(Screen.Target.name) {
                        popUpTo(Screen.Loader.name) { inclusive = true }
                    }
                }

                is SDlfkjwoerweOIU.EventToReconnect -> {
                    nacConter.navigate(Screen.Reconnect.name) {
                        popUpTo(Screen.Loader.name) { inclusive = true }
                    }
                }

                SDlfkjwoerweOIU.EventToSlots -> {
                    Btpowpoer.init(activity.resources)
                    nacConter.navigate(Screen.Slots.name) {
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
                        colors = iquwye
                    )
                )
        ) {
            Loasdwer()
            Text(
                text = stringResource(id = R.string.loading)
            )
        }
    }
}

enum class Screen {
    Loader, Slots, Target, Reconnect
}