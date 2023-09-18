package com.FortuneTiger.FT.Simul.slots.presentation.game_logic

import android.media.MediaPlayer
import androidx.core.util.toRange
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.FortuneTiger.FT.Simul.theme.screen.SLDJFSD
import com.FortuneTiger.FT.Simul.theme.screen.LDSJf_sdf
import com.FortuneTiger.FT.Simul.theme.screen.SLDwoeruew
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
import java.util.concurrent.CancellationException
import javax.inject.Inject
import kotlin.system.measureTimeMillis

@HiltViewModel
class SltBrain @Inject constructor(
    val mediaPlayer: MediaPlayer
) : ViewModel() {

    private val _gameStaaateee = MutableStateFlow(GSwoer())
    val gameStwwetwgw = _gameStaaateee.asStateFlow()

    init { mediaPlayer.start() }

    private var job : Job? = null
    fun spInRulletka() {
        if (job?.isActive != true) {
            job = viewModelScope.launch(Dispatchers.IO) {
                setupSpin()
                while (true) {
                    val frameTime = measureTimeMillis { frame() }
                    if (frameTime < SLDwoeruew) delay(SLDwoeruew - frameTime)
                }
            }
        }
    }

    private suspend fun frame() {
        val isWinGame = _gameStaaateee.value.lsdjf1023 == 0

        _gameStaaateee.update { s ->
            var columnsStopped = s.kajasfhl1j23
            val newColumnsStopped = (s.owue123.toInt() / STOPS_DELAY).coerceAtMost(5)

            val secondCondition = if (isWinGame && newColumnsStopped > 1) {
                val firstColWinBitmapId = s.oiweuoiurtoqu2135[0].sltolsdfj.find { slot -> slot.y in stopRange }!!.bitmapId
                val stopColumnBitmapIdAtPos = s.oiweuoiurtoqu2135[newColumnsStopped-1].sltolsdfj.find { slot -> slot.y in stopRange }?.bitmapId
                firstColWinBitmapId == stopColumnBitmapIdAtPos
            } else if (newColumnsStopped > columnsStopped) {
                s.oiweuoiurtoqu2135[newColumnsStopped-1].sltolsdfj.any { slot -> slot.y in stopRange }
            } else true

            if (newColumnsStopped > columnsStopped && secondCondition) {
                columnsStopped ++
            }
            s.copy(kajasfhl1j23 = columnsStopped)
        }
        for ((i, col) in _gameStaaateee.value.oiweuoiurtoqu2135.withIndex()) {
            if (i + 1 > _gameStaaateee.value.kajasfhl1j23) {
                col.sltolsdfj.forEach {
                    it.y += SHIFT_SPEED
                    if (it.y > SLOT_HEIGHT * 10) it.y = -SLOT_HEIGHT
                }
            }
        }
        _gameStaaateee.update { it.copy(
            mskdhvczv = System.currentTimeMillis(),
            owue123 = it.owue123 + SLDwoeruew
        ) }
        if (_gameStaaateee.value.kajasfhl1j23 >= 5) {
            _gameStaaateee.update {
                var newBalance = if (isWinGame) it.owqueo1 + it.bewlrjgou123 else it.owqueo1 - it.bewlrjgou123.coerceAtLeast(0)
                if (newBalance == 0) newBalance = SLDJFSD
                it.copy(
                    oieuoiwqueoqw = KSweoru.ncksd,
                    lsdjf1023 = if (it.lsdjf1023 == 0) 4 else it.lsdjf1023 - 1,
                    owqueo1 = newBalance,
                    bewlrjgou123 = it.bewlrjgou123.coerceIn(0, newBalance)
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
            _gameStaaateee.update {
                it.copy(oieuoiwqueoqw = KSweoru.sdlfOI(weyriweu = true, isCelebrationScreen = true))
            }
            delay(2500)
            _gameStaaateee.update {
                it.copy(oieuoiwqueoqw = KSweoru.ncksd)
            }
        }
    }

    private fun setupSpin() {
        _gameStaaateee.update { it.copy(oieuoiwqueoqw = KSweoru.Skdsf, xbvmnbsjdhfgj = SaltScrelrj.GALJELR, oiweuoiurtoqu2135 = GSwoer.randomColumnStates(), owue123 = 0, kajasfhl1j23 = 0) }
    }

    fun toGame() {
        _gameStaaateee.update { it.copy(xbvmnbsjdhfgj = SaltScrelrj.GALJELR) }
    }

    override fun onCleared() {
        super.onCleared()
        mediaPlayer.stop()
        _gameStaaateee.update {
            it.copy(mskdhvczv = System.currentTimeMillis())
        }
        job?.cancel(CancellationException("onCreared"))
    }

    fun toMenu() {
        _gameStaaateee.value = GSwoer()
    }

    fun betMinus() {
        _gameStaaateee.update { it.copy(bewlrjgou123 = (it.bewlrjgou123 - LDSJf_sdf).coerceAtLeast(0)) }
    }

    fun betPlus() {
        _gameStaaateee.update { it.copy(bewlrjgou123 = (it.bewlrjgou123 + LDSJf_sdf).coerceAtMost(it.owqueo1)) }
    }

    companion object {
        private val stopRange = ((SLOT_HEIGHT * 2) - 10 .. ((SLOT_HEIGHT * 2) + 10))
        val WIN_Y = (stopRange.toRange().lower + stopRange.endInclusive) / 2
    }
}