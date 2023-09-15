package com.FortuneTiger.FT.Simul.slots.presentation.game_logic

import androidx.compose.ui.graphics.ImageBitmap
import com.FortuneTiger.FT.Simul.theme.screen.SLOT_HEIGHT
import com.FortuneTiger.FT.Simul.theme.screen.VISIBLE_SLOTS_IN_COLUMN
import com.FortuneTiger.FT.Simul.theme.utils.Bitmaps
import kotlin.math.roundToInt

data class ColumnState (
    val slots : List<Slot>
) {
    companion object {
        fun startColumnState() : ColumnState = ColumnState(
            slots = buildList {
                val usedSlotsIndices = mutableSetOf<Int>()
                repeat(11) {
                    val availableSlots : Set<Int> = (0..10).toSet().minus(usedSlotsIndices)
                    val slotIndex = availableSlots.random()
                    usedSlotsIndices.add(slotIndex)
                    add(
                        Slot(
                            bitmap = Bitmaps.slots[slotIndex],
                            y = (it * SLOT_HEIGHT) - SLOT_HEIGHT
                        )
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
        get() = (y - SLOT_HEIGHT).roundToInt() / VISIBLE_SLOTS_IN_COLUMN
}