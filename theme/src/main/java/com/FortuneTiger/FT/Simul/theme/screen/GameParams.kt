package com.FortuneTiger.FT.Simul.theme.screen

import android.content.res.Resources
import kotlin.time.Duration.Companion.seconds

var INSET_SCREEN_WIDTH: Int = minOf(Resources.getSystem().displayMetrics.heightPixels, Resources.getSystem().displayMetrics.widthPixels)
var INSET_SCREEN_HEIGHT: Int = maxOf(Resources.getSystem().displayMetrics.heightPixels, Resources.getSystem().displayMetrics.widthPixels)
var SCREEN_HEIGHT : Int = INSET_SCREEN_HEIGHT
var SCREEN_WIDTH : Int = INSET_SCREEN_WIDTH

// FPS
val FRAME_TIME = 1.seconds.inWholeMilliseconds / 60

// Slot Area
const val SLOT_COLUMNS : Int = 5
val SLOT_AREA_SIZE : Float = SCREEN_WIDTH * 0.9f
val SLOT_AREA_PADDING : Float = SLOT_AREA_SIZE * 0.02f
val SLOT_COLUMN_HEIGHT : Float = SLOT_AREA_SIZE - SLOT_AREA_PADDING * 2
val SLOT_COLUMN_WIDTH : Float = (SLOT_AREA_SIZE - SLOT_AREA_PADDING * 6) / SLOT_COLUMNS
val SYMBOL_SIZE : Float = SLOT_COLUMN_WIDTH * 0.9f

// Shifting
const val VISIBLE_SLOTS_IN_COLUMN = 5
val SLOT_HEIGHT = SLOT_COLUMN_HEIGHT / VISIBLE_SLOTS_IN_COLUMN
const val STOPS_DELAY = 1_000
 /* amount of px to shift in frame */
val SHIFT_SPEED : Float = SLOT_HEIGHT / 20

val FIRST_SLOT_Y = -SLOT_HEIGHT + SLOT_AREA_PADDING
val TOTAL_SPIN_DURATION = STOPS_DELAY * SLOT_COLUMNS