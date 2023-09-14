package com.FortuneTiger.FT.Simul.theme.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

@Composable
fun Float.dpFromPx() : Dp = LocalDensity.current.run { toDp() }

@Composable
fun Int.dpFromPx() : Dp = LocalDensity.current.run { toDp() }

