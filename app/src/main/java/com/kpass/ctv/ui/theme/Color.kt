package com.kpass.ctv.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

object CtvColor {
    val White = Color(0xFFFFFFFF)
    val Black = Color(0xFF000000)

    val Red = Color(0xFFF93C3C)
    val Blue = Color(0xFF2E81E4)

    val BlueGray = Color(0xFFF4F5F9)

    val Gray100 = Color(0xFFF5F5F5)
    val Gray200 = Color(0xFFD8DBDE)
    val Gray300 = Color(0xFFAFB4B8)
    val Gray400 = Color(0xFF7C7F83)
    val Gray500 = Color(0xFF7C7F83)
    val Gray600 = Color(0xFF626468)
    val Gray700 = Color(0xFF46474A)
    val Gray800 = Color(0xFF2F2F32)
    val Gray900 = Color(0xFF171718)

    val TextFieldStroke = Color(0xFFC6CFD7)

    val Transparent = Color(0x00000000)

}


internal val LocalColor = compositionLocalOf { CtvColor }