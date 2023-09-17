package com.FortuneTiger.FT.Simul.slots.presentation.game_logic

import android.media.MediaPlayer
import androidx.core.util.toRange
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.FortuneTiger.FT.Simul.theme.screen.BALANCE
import com.FortuneTiger.FT.Simul.theme.screen.BET_STEP
import com.FortuneTiger.FT.Simul.theme.screen.FRAME_TIME
import com.FortuneTiger.FT.Simul.theme.screen.SHIFT_SPEED
import com.FortuneTiger.FT.Simul.theme.screen.SLOT_HEIGHT
import com.FortuneTiger.FT.Simul.theme.screen.STOPS_DELAY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.system.measureTimeMillis

@HiltViewModel
class SlotViewModel @Inject constructor(
    val mediaPlayer: MediaPlayer
) : ViewModel() {

    private val _gameState = MutableStateFlow(GameState())
    val gameState = _gameState.asStateFlow()

    init { mediaPlayer.start() }

    private var job : Job? = null
    fun spin() {
        if (job?.isActive != true) {
            job = viewModelScope.launch(Dispatchers.IO) {
                setupSpin()
                while (true) {
                    val frameTime = measureTimeMillis { frame() }
                    if (frameTime < FRAME_TIME) delay(FRAME_TIME - frameTime)
                }
            }
        }
    }

    private suspend fun frame() {
        val isWinGame = _gameState.value.gamesBeforeWinGame == 0

        _gameState.update { s ->
            var columnsStopped = s.columnsStopped
            val newColumnsStopped = (s.spinDurationMillis.toInt() / STOPS_DELAY).coerceAtMost(5)

            val secondCondition = if (isWinGame && newColumnsStopped > 1) {
                val firstColWinBitmapId = s.columnStates[0].slots.find { slot -> slot.y in stopRange }!!.bitmapId
                val stopColumnBitmapIdAtPos = s.columnStates[newColumnsStopped-1].slots.find { slot -> slot.y in stopRange }?.bitmapId
                firstColWinBitmapId == stopColumnBitmapIdAtPos
            } else if (newColumnsStopped > columnsStopped) {
                s.columnStates[newColumnsStopped-1].slots.any { slot -> slot.y in stopRange }
            } else true

            if (newColumnsStopped > columnsStopped && secondCondition) {
                columnsStopped ++
            }
            s.copy(columnsStopped = columnsStopped)
        }
        for ((i, col) in _gameState.value.columnStates.withIndex()) {
            if (i + 1 > _gameState.value.columnsStopped) {
                col.slots.forEach {
                    it.y += SHIFT_SPEED
                    if (it.y > SLOT_HEIGHT * 10) it.y = -SLOT_HEIGHT
                }
            }
        }
        _gameState.update { it.copy(
            timeMillis = System.currentTimeMillis(),
            spinDurationMillis = it.spinDurationMillis + FRAME_TIME
        ) }
        if (_gameState.value.columnsStopped >= 5) {
            _gameState.update {
                var newBalance = if (isWinGame) it.balance + it.bet else it.balance - it.bet.coerceAtLeast(0)
                if (newBalance == 0) newBalance = BALANCE
                it.copy(
                    gamePhase = GamePhase.Bet,
                    gamesBeforeWinGame = if (it.gamesBeforeWinGame == 0) 4 else it.gamesBeforeWinGame - 1,
                    balance = newBalance,
                    bet = it.bet.coerceIn(0, newBalance)
                )
            }
            if (isWinGame) {
                toCelebrationScreen()
            }
            job?.cancel()
        }
    }

    private fun toCelebrationScreen() {
        viewModelScope.launch {
            delay(1000)
            _gameState.update {
                it.copy(gamePhase = GamePhase.Result(isWin = true, isCelebrationScreen = true))
            }
            delay(2500)
            _gameState.update {
                it.copy(gamePhase = GamePhase.Bet)
            }
        }
    }

    private fun setupSpin() {
        _gameState.update { it.copy(gamePhase = GamePhase.Spin, screen = SlotScreen.GAME, columnStates = GameState.randomColumnStates(), spinDurationMillis = 0, columnsStopped = 0) }
    }

    fun toGame() {
        _gameState.update { it.copy(screen = SlotScreen.GAME) }
    }

    override fun onCleared() {
        super.onCleared()
        mediaPlayer.stop()
    }

    fun toMenu() {
        _gameState.value = GameState()
    }

    fun betMinus() {
        _gameState.update { it.copy(bet = (it.bet - BET_STEP).coerceAtLeast(0)) }
    }

    fun betPlus() {
        _gameState.update { it.copy(bet = (it.bet + BET_STEP).coerceAtMost(it.balance)) }
    }

    companion object {
        private val stopRange = ((SLOT_HEIGHT * 2) - 10 .. ((SLOT_HEIGHT * 2) + 10))
        val WIN_Y = (stopRange.toRange().lower + stopRange.endInclusive) / 2
    }
}