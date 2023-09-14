package com.FortuneTiger.FT.Simul.controller

sealed interface ControllerEvent {
    data object EventToSlots : ControllerEvent
    data class EventToTarget(val url : String) : ControllerEvent
    data object EventToReconnect : ControllerEvent
}