package com.FortuneTiger.FT.Simul.slots.presentation.game_logic

import com.FortuneTiger.FT.Simul.theme.screen.BMDSKHJF
import com.FortuneTiger.FT.Simul.theme.screen.GAMES_BEFORE_WIN_GAME
import com.FortuneTiger.FT.Simul.theme.screen.SLDJFSD

data class GSwoer(
    val xbvmnbsjdhfgj : SaltScrelrj = SaltScrelrj.MENASUO,
    val oieuoiwqueoqw : KSweoru = KSweoru.ncksd,
    val mskdhvczv : Long = System.currentTimeMillis(),
    val oiweuoiurtoqu2135 : List<ColsdjfSOofuew> = randomColumnStates(),
    val owue123 : Long = 0,
    val kajasfhl1j23 : Int = 0,

    val lsdjf1023 : Int = GAMES_BEFORE_WIN_GAME,

    val owqueo1 : Int = SLDJFSD,
    val bewlrjgou123 : Int = BMDSKHJF
) {
    companion object {
        fun randomColumnStates() = List(5) { ColsdjfSOofuew.startColumnState() }
    }
}

enum class SaltScrelrj {
    MENASUO, GALJELR, WIN
}

sealed interface KSweoru {
    data object ncksd : KSweoru
    data object Skdsf : KSweoru
    data class sdlfOI(val weyriweu: Boolean = false, val isCelebrationScreen: Boolean = false) : KSweoru
}
