package com.FortuneTiger.FT.Simul.slots.presentation.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import com.FortuneTiger.FT.Simul.slots.presentation.game_logic.GameState
import com.FortuneTiger.FT.Simul.slots.presentation.game_logic.SlotViewModel
import com.FortuneTiger.FT.Simul.theme.SlotAreaColumnColors
import com.FortuneTiger.FT.Simul.theme.screen.SLOT_AREA_PADDING
import com.FortuneTiger.FT.Simul.theme.screen.SLOT_COLUMNS
import com.FortuneTiger.FT.Simul.theme.screen.SLOT_COLUMN_HEIGHT
import com.FortuneTiger.FT.Simul.theme.screen.SLOT_COLUMN_WIDTH
import com.FortuneTiger.FT.Simul.theme.screen.SYMBOL_SIZE

@Composable
fun SlotCanvas(
    state : GameState
) {
    val tm = rememberTextMeasurer()
    Canvas(
        modifier = Modifier.fillMaxSize()/*.clipToBounds()*/,
    ) {
        drawText(
            textMeasurer = tm,
            text = state.timeMillis.toString(),
            style = TextStyle.Default.copy(color = Color.Transparent)
        )
        repeat(SLOT_COLUMNS) {
            val colStartX = SLOT_AREA_PADDING * (1+it) + SLOT_COLUMN_WIDTH * it
            drawRect(
                brush = Brush.verticalGradient(colors = SlotAreaColumnColors),
                topLeft = Offset(
                    x = colStartX,
                    y = SLOT_AREA_PADDING
                ),
                size = Size(
                    width = SLOT_COLUMN_WIDTH,
                    height = SLOT_COLUMN_HEIGHT
                )
            )
            for (slot in state.columnStates[it].slots) {
                drawImage(
                    image = slot.bitmap,
                    topLeft = Offset(
                        x = colStartX,
                        y = slot.y
                    )
                )
                drawLine(
                    color = Color.Red,
                    start = Offset(
                        x = colStartX,
                        y = slot.y + SYMBOL_SIZE
                    ),
                    end = Offset(
                        x = colStartX + SYMBOL_SIZE,
                        y = slot.y + SYMBOL_SIZE
                    )
                )
            }
        }
        drawLine(
            color = Color.Blue,
            start = Offset(
                x = 0f,
                y = SlotViewModel.WIN_Y
            ),
            end = Offset(
                x = 1000f,
                y = SlotViewModel.WIN_Y
            ),
            strokeWidth = 10f
        )
    }
}