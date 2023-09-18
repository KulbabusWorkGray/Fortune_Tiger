package com.FortuneTiger.FT.Simul.controller

sealed class SDlfkjwoerweOIU(
    val reason : String = "default"
) {
    data object EventToSlots : SDlfkjwoerweOIU()
    data class EventToTarget(val url : String) : SDlfkjwoerweOIU(url)
    class EventToReconnect(reason: String) : SDlfkjwoerweOIU(reason)
}