package com.FortuneTiger.FT.Simul.controller

sealed class ControllerEvent(
    val reason : String = "default"
) {
    data object EventToSlots : ControllerEvent()
    data class EventToTarget(val url : String) : ControllerEvent(url)
    class EventToReconnect(reason: String) : ControllerEvent(reason)
}