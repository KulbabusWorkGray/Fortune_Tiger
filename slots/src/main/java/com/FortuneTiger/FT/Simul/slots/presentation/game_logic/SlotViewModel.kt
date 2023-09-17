package com.FortuneTiger.FT.Simul.slots.presentation.game_logic

import android.media.MediaPlayer
import android.util.Log
import androidx.core.util.toRange
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
        val isWinGame = /*_gameState.value.gamesBeforeWinGame == 0*/ true

        _gameState.update { s ->
            var columnsStopped = s.columnsStopped
            val newColumnsStopped = (s.spinDurationMillis.toInt() / STOPS_DELAY).coerceAtMost(5)

            val secondCondition = if (isWinGame && newColumnsStopped > 1) {
                val firstColWinBitmapId = s.columnStates[0].slots.find { slot -> slot.y in stopRange }!!.bitmapId.also { Log.d("TAG", "winBitmapId = $it") }
                val stopColumnBitmapIdAtPos = s.columnStates[newColumnsStopped-1].slots.find { slot -> slot.y in stopRange }?.bitmapId
                stopColumnBitmapIdAtPos?.let { id -> Log.d("TAG", "bitmapId at pos = $id") }
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
                it.copy(
                    gamePhase = GamePhase.Result(isWin = false)
                )
            }
            job?.cancel()
        }
    }

    private fun setupSpin() {
        _gameState.value = GameState(gamePhase = GamePhase.Spin, screen = SlotScreen.GAME)
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

    companion object {
        private val stopRange = ((SLOT_HEIGHT * 2) - 10 .. ((SLOT_HEIGHT * 2) + 10))
        val WIN_Y = (stopRange.toRange().lower + stopRange.endInclusive) / 2
    }
}