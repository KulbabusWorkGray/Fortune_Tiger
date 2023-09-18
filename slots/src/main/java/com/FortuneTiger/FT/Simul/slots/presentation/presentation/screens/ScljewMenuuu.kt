package com.FortuneTiger.FT.Simul.slots.presentation.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.FortuneTiger.FT.Simul.slots.R
import com.FortuneTiger.FT.Simul.slots.presentation.game_logic.SltBrain
import com.FortuneTiger.FT.Simul.theme.iquwye
import com.FortuneTiger.FT.Simul.theme.screen.S_HEIGHTLJWLRJ
import com.FortuneTiger.FT.Simul.theme.screen.S_WIDlkwljg
import com.FortuneTiger.FT.Simul.theme.screen.dpFromPx
import com.FortuneTiger.FT.Simul.theme.R as RTheme

@Composable
fun ScljewMenuuu(
    viewModel: SltBrain
) {
    val toggleMusic = {
        if (viewModel.mediaPlayer.isPlaying) viewModel.mediaPlayer.pause()
        else viewModel.mediaPlayer.start()
    }
    var isDialogOpen by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        if (isDialogOpen) {
            SettingsDialog(
                onMusicToggle = toggleMusic,
                onClose = { isDialogOpen = false },
                musicOn = viewModel.mediaPlayer.isPlaying
            )
        }
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = iquwye
                    )
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Image(
                    painter = painterResource(id = RTheme.drawable.settings_button_menu),
                    contentDescription = stringResource(id = R.string.settings),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(50.dp)
                        .clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = null,
                            onClick = { isDialogOpen = true }
                        )
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(
                    space = 16.dp,
                    alignment = Alignment.Bottom
                ),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = RTheme.drawable.play_button_menu),
                    contentDescription = stringResource(R.string.play),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxWidth(0.4f)
                        .aspectRatio(1.7f)
                        .clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = null,
                            onClick = { viewModel.toGame() }
                        )
                )
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Image(
                        painter = painterResource(id = RTheme.drawable.tiger_menu),
                        contentDescription = null,
                        modifier = Modifier
                            .height((S_HEIGHTLJWLRJ / 1.7f).dpFromPx())
                            .fillMaxWidth(0.9f)
                            .offset(x = (S_WIDlkwljg / 10).dpFromPx()),
                        contentScale = ContentScale.Fit
                    )
                    Image(
                        painter = painterResource(id = RTheme.drawable.coins_menu),
                        contentDescription = null,
                        modifier = Modifier.width((S_WIDlkwljg * 3).dpFromPx()),
                        contentScale = ContentScale.FillWidth
                    )
                }
            }
        }
    }

}

@Composable
fun SettingsDialog(
    onMusicToggle: () -> Unit,
    onClose: () -> Unit,
    musicOn : Boolean
) {
    var size by remember { mutableStateOf(IntSize.Zero) }
    Dialog(onDismissRequest = onClose) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .aspectRatio(378f / 326f)
                .paint(
                    painter = painterResource(id = RTheme.drawable.cheese_good),
                    contentScale = ContentScale.FillBounds
                )
                .onSizeChanged {
                    size = it
                }
        ) {
            Image(
                painter = painterResource(id = RTheme.drawable.off),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .width(40.dp)
                    .height(20.dp)
                    .offset(
                        x = (size.width * 0.65f).dpFromPx(),
                        y = (size.height * 0.35f).dpFromPx()
                    )
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null,
                        onClick = { }
                    )
                )
            Image(
                painter = painterResource(id = if (musicOn) RTheme.drawable.on else RTheme.drawable.off),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .width(40.dp)
                    .height(20.dp)
                    .offset(
                        x = (size.width * 0.65f).dpFromPx(),
                        y = (size.height * 0.62f).dpFromPx()
                    )
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null,
                        onClick = onMusicToggle
                    )
            )
        }
    }
}