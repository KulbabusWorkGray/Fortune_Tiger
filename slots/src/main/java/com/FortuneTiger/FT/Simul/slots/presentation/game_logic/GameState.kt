package com.FortuneTiger.FT.Simul.slots.presentation.game_logic

import com.FortuneTiger.FT.Simul.theme.screen.GAMES_BEFORE_WIN_GAME

data class GameState(
    val screen : SlotScreen = SlotScreen.MENU,
    val gamePhase : GamePhase = GamePhase.Bet,
    val timeMillis : Long = System.currentTimeMillis(),
    val columnStates : List<ColumnState> = List(5) { ColumnState.startColumnState() },
    val spinDurationMillis : Long = 0,
    val columnsStopped : Int = 0,

    val gamesBeforeWinGame : Int = GAMES_BEFORE_WIN_GAME - 3
)

enum class SlotScreen {
    MENU, GAME, WIN
}

sealed interface GamePhase {
    data object Bet : GamePhase
    data object Spin : GamePhase
    data class Result(val isWin: Boolean = false) : GamePhase
}
