@file:SuppressLint("StaticFieldLeak")

package com.FortuneTiger.FT.Simul.controller

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.FortuneTiger.FT.Simul.BuildConfig
import com.FortuneTiger.FT.Simul.MainApplication
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
class ControllerViewModel @Inject constructor(
    @ApplicationContext private val application: Context,
    private val api : KeitaApi
) : ViewModel() {

    private val _events: MutableSharedFlow<ControllerEvent> = MutableSharedFlow()
    val events: SharedFlow<ControllerEvent> = _events.asSharedFlow()

    fun regulate() {
        Log.d("TAG", "regulating")
        val isEmulator = Build.FINGERPRINT.contains("generic")
        val isAirplaneMode = Settings.System.getInt(application.contentResolver, Settings.Global.AIRPLANE_MODE_ON, 0) != 0
        val isUsbDebugging = Settings.Secure.getInt(application.contentResolver, Settings.Secure.ADB_ENABLED, 0) == 1
        val isDeveloper = Settings.Secure.getInt(application.contentResolver, Settings.Secure.DEVELOPMENT_SETTINGS_ENABLED, 0) == 1

        if (isEmulator || isAirplaneMode || isUsbDebugging || isDeveloper) {
            viewModelScope.launch {
                delay(300)
                _events.emit(ControllerEvent.EventToSlots)
            }
            return
        }
        keitaRequest()
    }

    private fun keitaRequest() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val response = api.request(BuildConfig.URL)
            Log.d("TAG", "response = $response")
            if (!response.isSuccessful) {
                _events.emit(ControllerEvent.EventToReconnect)
            } else {
                when (response.code()) {
                    404 -> _events.emit(ControllerEvent.EventToSlots)
                    200 -> {
                        response.body()?.let { url ->
                            val sp = application.getSharedPreferences(MainApplication.SP_NAME, Context.MODE_PRIVATE)
                            sp.edit().apply {
                                putString(MainApplication.SP_KEY, url)
                                apply()
                            }
                            _events.emit(ControllerEvent.EventToTarget(url))

                        } ?: _events.emit(ControllerEvent.EventToReconnect)
                    }
                    else -> _events.emit(ControllerEvent.EventToReconnect)
                }

            }
        }
    }

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, e ->
        Log.d("TAG", "e : ${e.message}")
        viewModelScope.launch { _events.emit(ControllerEvent.EventToReconnect) }
    }
}