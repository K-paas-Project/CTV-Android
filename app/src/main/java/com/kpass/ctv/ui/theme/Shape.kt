package com.kpass.ctv.ui.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

object CtvRadius {
    val small = 4.dp
    val normal = 8.dp
    val middle = 12.dp
    val veryLarge = 16.dp
    val infinity = 9999.dp
}

class CtvShape(
    val small: CornerBasedShape =
        RoundedCornerShape(CtvRadius.small),
    val normal: CornerBasedShape =
        RoundedCornerShape(CtvRadius.normal),
    val middle: CornerBasedShape =
        RoundedCornerShape(CtvRadius.middle),
    val large: CornerBasedShape =
        RoundedCornerShape(CtvRadius.middle),
    val veryLarge: CornerBasedShape =
        RoundedCornerShape(CtvRadius.infinity),
    val infinity: CornerBasedShape =
        RoundedCornerShape(CtvRadius.infinity),
    val circle: CornerBasedShape = CircleShape
)

internal val LocalShape = staticCompositionLocalOf { CtvShape() }