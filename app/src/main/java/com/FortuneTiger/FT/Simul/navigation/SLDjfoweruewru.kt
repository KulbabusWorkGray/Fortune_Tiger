package com.FortuneTiger.FT.Simul.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.FortuneTiger.FT.Simul.R
import com.FortuneTiger.FT.Simul.theme.iquwye

@Composable
fun SLDjfoweruewru(
    onReconnect : () -> Unit
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
        Button(onClick = onReconnect) {
            Text(
                text = stringResource(id = R.string.reconnect)
            )
        }
    }
}