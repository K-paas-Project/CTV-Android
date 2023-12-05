package com.kpass.ctv.utiles

import androidx.compose.ui.graphics.Color
import com.kpass.ctv.ui.theme.CtvColor

internal fun String.getCategoryColor() =
    when(this) {
        "산불" -> CtvColor.Red
        "홍수" -> CtvColor.Blue
        "안전" -> CtvColor.Green
        else -> CtvColor.Transparent
    }