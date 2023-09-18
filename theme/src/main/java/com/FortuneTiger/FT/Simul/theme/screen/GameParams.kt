package com.FortuneTiger.FT.Simul.theme.screen

import android.content.res.Resources
import kotlin.time.Duration.Companion.seconds

var LSKDjwoeiru: Int = minOf(Resources.getSystem().displayMetrics.heightPixels, Resources.getSystem().displayMetrics.widthPixels)
var I_scljwe: Int = maxOf(Resources.getSystem().displayMetrics.heightPixels, Resources.getSystem().displayMetrics.widthPixels)
var S_HEIGHTLJWLRJ : Int = I_scljwe
var S_WIDlkwljg : Int = LSKDjwoeiru

// FPS
val SLDwoeruew = 1.seconds.inWholeMilliseconds / 60

// Slot Area
const val lsdfjw : Int = 5
val sjdhfjs : Float = S_WIDlkwljg * 0.9f
val SLDjfsdlf : Float = sjdhfjs * 0.02f
val sadljfwer : Float = sjdhfjs - SLDjfsdlf * 2
val nmbjdshfs : Float = (sjdhfjs - SLDjfsdlf * 6) / lsdfjw
val SLdkjfsdf : Float = nmbjdshfs * 0.9f

// Shifting
const val VISIBLE_SLOTS_IN_COLUMN = 5
val SLOT_HEIGHT = sadljfwer / VISIBLE_SLOTS_IN_COLUMN
const val STOPS_DELAY = 1_000
 /* amount of px to shift in frame */
val SHIFT_SPEED : Float = SLOT_HEIGHT / 5

val FIRST_SLOT_Y = -SLOT_HEIGHT + SLDjfsdlf
val TOTAL_SPIN_DURATION = STOPS_DELAY * lsdfjw

// Win
const val GAMES_BEFORE_WIN_GAME = 4
const val WIN_SYMBOL_POS = 3

// Bet
const val SLDJFSD = 10_000
const val BMDSKHJF = 500
const val LDSJf_sdf = 100