package com.kpass.ctv.ui.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

object CtvRadius {
    val small = 5.dp
    val semiMiddle = 10.dp
    val middle = 12.dp
    val semiLarge = 16.dp
    val large = 20.dp
}

class CtvShape(
    val small: CornerBasedShape =
        RoundedCornerShape(CtvRadius.small),
    val semiMiddle: CornerBasedShape =
        RoundedCornerShape(CtvRadius.semiMiddle),
    val middle: CornerBasedShape =
        RoundedCornerShape(CtvRadius.middle),
    val semiLarge: CornerBasedShape =
        RoundedCornerShape(CtvRadius.semiLarge),
    val large: CornerBasedShape =
        RoundedCornerShape(CtvRadius.large),
    val circle: CornerBasedShape = CircleShape
)

internal val LocalShape = staticCompositionLocalOf { CtvShape() }