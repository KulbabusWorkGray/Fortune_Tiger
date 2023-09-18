package com.FortuneTiger.FT.Simul.slots.presentation.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import com.FortuneTiger.FT.Simul.slots.presentation.game_logic.KSweoru
import com.FortuneTiger.FT.Simul.slots.presentation.game_logic.GSwoer
import com.FortuneTiger.FT.Simul.slots.presentation.game_logic.SltBrain
import com.FortuneTiger.FT.Simul.theme.SDlkfj
import com.FortuneTiger.FT.Simul.theme.screen.SLDjfsdlf
import com.FortuneTiger.FT.Simul.theme.screen.sjdhfjs
import com.FortuneTiger.FT.Simul.theme.screen.lsdfjw
import com.FortuneTiger.FT.Simul.theme.screen.sadljfwer
import com.FortuneTiger.FT.Simul.theme.screen.nmbjdshfs
import com.FortuneTiger.FT.Simul.theme.screen.SLOT_HEIGHT

@Composable
fun SlotCanvas(
    state : GSwoer
) {
    val tm = rememberTextMeasurer()
    Canvas(
        modifier = Modifier.fillMaxSize().clipToBounds(),
    ) {
        drawText(
            textMeasurer = tm,
            text = state.mskdhvczv.toString(),
            style = TextStyle.Default.copy(color = Color.Transparent)
        )
        repeat(lsdfjw) {
            val colStartX = SLDjfsdlf * (1+it) + nmbjdshfs * it
            drawRect(
                brush = Brush.verticalGradient(colors = SDlkfj),
                topLeft = Offset(
                    x = colStartX,
                    y = SLDjfsdlf
                ),
                size = Size(
                    width = nmbjdshfs,
                    height = sadljfwer
                )
            )
            for (slot in state.oiweuoiurtoqu2135[it].sltolsdfj) {
                drawImage(
                    image = slot.btmappss,
                    topLeft = Offset(
                        x = colStartX,
                        y = slot.y
                    )
                )
            }
        }
        if (state.oieuoiwqueoqw is KSweoru.sdlfOI && state.oieuoiwqueoqw.weyriweu) drawBox()
    }
}

fun DrawScope.drawBox() {
    val topLeft = Offset(x = 0f, y = SltBrain.WIN_Y - (SLOT_HEIGHT*0.2f))
    val topRight = Offset(x = sjdhfjs, y = SltBrain.WIN_Y - (SLOT_HEIGHT*0.2f))
    val bottomLeft = Offset(x = 0f, y = SltBrain.WIN_Y + SLOT_HEIGHT)
    val bottomRight = Offset(x = sjdhfjs, y = SltBrain.WIN_Y + SLOT_HEIGHT)
    drawLine(
        color = Color.Red,
        strokeWidth = 10f,
        start = topLeft,
        end = topRight
    )
    drawLine(
        color = Color.Red,
        strokeWidth = 10f,
        start = topLeft,
        end = bottomLeft
    )
    drawLine(
        color = Color.Red,
        strokeWidth = 10f,
        start = topRight,
        end = bottomRight
    )
    drawLine(
        color = Color.Red,
        strokeWidth = 10f,
        start = bottomLeft,
        end = bottomRight
    )

}