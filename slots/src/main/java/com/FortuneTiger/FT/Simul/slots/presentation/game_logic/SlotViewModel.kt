package com.FortuneTiger.FT.Simul.slots.presentation.game_logic

import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.FortuneTiger.FT.Simul.theme.screen.FRAME_TIME
import com.FortuneTiger.FT.Simul.theme.screen.SHIFT_SPEED
import com.FortuneTiger.FT.Simul.theme.screen.SLOT_HEIGHT
import com.FortuneTiger.FT.Simul.theme.screen.STOPS_DELAY
import com.FortuneTiger.FT.Simul.theme.screen.TOTAL_SPIN_DURATION
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
        _gameState.update {
            var columnsStopped = it.columnsStopped
            val newColumnsStopped = it.spinDurationMillis.toInt() / STOPS_DELAY
            if (newColumnsStopped > columnsStopped && it.columnStates[newColumnsStopped-1].slots.any { slot -> slot.y in ((SLOT_HEIGHT * 4) - 10 .. ((SLOT_HEIGHT * 4) + 10)) }) {
                columnsStopped ++
            }
            it.copy(columnsStopped = columnsStopped)
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
        if (_gameState.value.spinDurationMillis > TOTAL_SPIN_DURATION) {
            viewModelScope.launch {
                delay(1000)
                _gameState.update {
                    it.copy(
                        gamePhase = GamePhase.Result(isWin = false)
                    )
                }
                job?.cancel()
            }
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
}