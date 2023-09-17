package com.FortuneTiger.FT.Simul.slots.presentation.game_logic

import android.util.Log
import androidx.compose.ui.graphics.ImageBitmap
import com.FortuneTiger.FT.Simul.theme.screen.SLOT_HEIGHT
import com.FortuneTiger.FT.Simul.theme.utils.Bitmaps
import kotlin.math.roundToInt

data class ColumnState (
    val slots : List<Slot>
) {
    companion object {
        fun startColumnState() : ColumnState = ColumnState(
            slots = buildList {
                val usedSlotsIndices = mutableSetOf<Int>()
                repeat(11) { i ->
                    val availableSlots : Set<Int> = (0..10).toSet().minus(usedSlotsIndices)
                    val slotIndex = availableSlots.random()
                    usedSlotsIndices.add(slotIndex)
                    add(
                        Slot(
                            bitmap = Bitmaps.slots[slotIndex],
                            y = ((i * SLOT_HEIGHT) - SLOT_HEIGHT)
                        ).also { Log.d("TAG", "y = ${it.y}, pos = ${it.position}") }
                    )
                }
            }
        )
    }
}

data class Slot(
    val bitmap: ImageBitmap,
    var y : Float
) {
    val position : Int
        get() = ((y + SLOT_HEIGHT) / SLOT_HEIGHT).roundToInt()
}