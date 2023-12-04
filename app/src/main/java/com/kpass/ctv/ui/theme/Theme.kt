package com.kpass.ctv.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat



    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */

@Composable
fun CtvTheme(
    typography: CtvTypography = CtvTheme.typography,
    color: CtvColor = CtvTheme.color,
    shape: CtvShape = CtvTheme.shape,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalColor provides color,
        LocalTypography provides typography,
        LocalShape provides shape
    ) {
        content()
    }
}

object CtvTheme {
    val color: CtvColor
        @Composable
        @ReadOnlyComposable
        get() = LocalColor.current
    val typography: CtvTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
    val shape: CtvShape
        @Composable
        @ReadOnlyComposable
        get() = LocalShape.current
}