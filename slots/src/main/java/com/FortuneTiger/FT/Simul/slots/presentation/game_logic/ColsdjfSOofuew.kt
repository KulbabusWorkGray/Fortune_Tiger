package com.FortuneTiger.FT.Simul.slots.presentation.game_logic

import androidx.compose.ui.graphics.ImageBitmap
import com.FortuneTiger.FT.Simul.theme.screen.SLOT_HEIGHT
import com.FortuneTiger.FT.Simul.theme.utils.Btpowpoer

data class ColsdjfSOofuew (
    val sltolsdfj : List<SLoweu>
) {
    companion object {
        fun startColumnState() : ColsdjfSOofuew = ColsdjfSOofuew(
            sltolsdfj = buildList {
                val usedSlotsIndices = mutableSetOf<Int>()
                repeat(11) { i ->
                    val availableSlots : Set<Int> = (0..10).toSet().minus(usedSlotsIndices)
                    val slotIndex = availableSlots.random()
                    usedSlotsIndices.add(slotIndex)
                    add(
                        SLoweu(
                            btmappss = Btpowpoer.oewudkfs[slotIndex],
                            y = ((i * SLOT_HEIGHT) - SLOT_HEIGHT),
                            bitmapId = slotIndex+1
                        )
                    )
                }
            }
        )
    }
}

data class SLoweu(
    val btmappss: ImageBitmap,
    var y : Float,
    val bitmapId : Int
)