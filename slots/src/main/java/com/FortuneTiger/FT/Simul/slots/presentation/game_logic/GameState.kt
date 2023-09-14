package com.FortuneTiger.FT.Simul.slots.presentation.game_logic

data class GameState(
    val screen : SlotScreen = SlotScreen.MENU,
    val gamePhase : GamePhase = GamePhase.Bet,
    val timeMillis : Long = System.currentTimeMillis(),
    val columnStates : List<ColumnState> = List(5) { ColumnState.startColumnState() },
    val spinDurationMillis : Long = 0,
    val columnsStopped : Int = 0
)

enum class SlotScreen {
    MENU, GAME, WIN
}

sealed interface GamePhase {
    data object Bet : GamePhase
    data object Spin : GamePhase
    data class Result(val isWin: Boolean = false) : GamePhase
}
