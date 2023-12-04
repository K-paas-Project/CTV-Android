package com.kpass.ctv.ui.theme

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.kpass.ctv.R
import com.kpass.ctv.ui.componet.modifier.ctvClickable

private val pretendard = FontFamily(
    Font(R.font.pretendard_thin, FontWeight.Thin),
    Font(R.font.pretendard_extra_light, FontWeight.ExtraLight),
    Font(R.font.pretendard_light, FontWeight.Light),
    Font(R.font.pretendard_regular, FontWeight.Normal),
    Font(R.font.pretendard_medium, FontWeight.Medium),
    Font(R.font.pretendard_semi_bold, FontWeight.SemiBold),
    Font(R.font.pretendard_bold, FontWeight.Bold),
    Font(R.font.pretendard_extra_bold, FontWeight.ExtraBold),
    Font(R.font.pretendard_black, FontWeight.Black),
)

// Set of Material typography styles to start with
object CtvTypography {

    @Stable
    val title = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        ),
    )

    @Stable
    val headline = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        ),
    )

    @Stable
    val body = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        ),
    )

    @Stable
    val label = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        ),
    )

    @Stable
    val caption = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        ),
    )
}

@Composable
fun Title(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = LocalContentColor.current,
    textAlign: TextAlign = TextAlign.Start,
    textDecoration: TextDecoration? = null,
    textOverflow: TextOverflow = TextOverflow.Clip,
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    rippleColor: Color = Color.Unspecified,
    rippleEnable: Boolean = false,
    bounded: Boolean = true,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    onClick: (() -> Unit)? = null,
) {
    Text(
        modifier = modifier.ctvClickable(
            onClick = onClick,
            interactionSource = interactionSource,
            rippleColor = rippleColor,
            rippleEnable = rippleEnable,
            bounded = bounded
        ),
        text = text,
        style = CtvTypography.title,
        color = textColor,
        textAlign = textAlign,
        textDecoration = textDecoration,
        overflow = textOverflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout,
    )
}

@Composable
fun Headline(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = LocalContentColor.current,
    textAlign: TextAlign = TextAlign.Start,
    textDecoration: TextDecoration? = null,
    textOverflow: TextOverflow = TextOverflow.Clip,
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    rippleColor: Color = Color.Unspecified,
    rippleEnable: Boolean = false,
    bounded: Boolean = true,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    onClick: (() -> Unit)? = null,
) {
    Text(
        modifier = modifier.ctvClickable(
            onClick = onClick,
            interactionSource = interactionSource,
            rippleColor = rippleColor,
            rippleEnable = rippleEnable,
            bounded = bounded
        ),
        text = text,
        style = CtvTypography.headline,
        color = textColor,
        textAlign = textAlign,
        textDecoration = textDecoration,
        overflow = textOverflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout,
    )
}

@Composable
fun Body(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = LocalContentColor.current,
    textAlign: TextAlign = TextAlign.Start,
    textDecoration: TextDecoration? = null,
    textOverflow: TextOverflow = TextOverflow.Clip,
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    rippleColor: Color = Color.Unspecified,
    rippleEnable: Boolean = false,
    bounded: Boolean = true,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    onClick: (() -> Unit)? = null,
) {
    Text(
        modifier = modifier.ctvClickable(
            onClick = onClick,
            interactionSource = interactionSource,
            rippleColor = rippleColor,
            rippleEnable = rippleEnable,
            bounded = bounded
        ),
        text = text,
        style = CtvTypography.body,
        color = textColor,
        textAlign = textAlign,
        textDecoration = textDecoration,
        overflow = textOverflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout,
    )
}

@Composable
fun Label(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = LocalContentColor.current,
    textAlign: TextAlign = TextAlign.Start,
    textDecoration: TextDecoration? = null,
    textOverflow: TextOverflow = TextOverflow.Clip,
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    rippleColor: Color = Color.Unspecified,
    rippleEnable: Boolean = false,
    bounded: Boolean = true,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    onClick: (() -> Unit)? = null,
) {
    Text(
        modifier = modifier.ctvClickable(
            onClick = onClick,
            interactionSource = interactionSource,
            rippleColor = rippleColor,
            rippleEnable = rippleEnable,
            bounded = bounded
        ),
        text = text,
        style = CtvTypography.label,
        color = textColor,
        textAlign = textAlign,
        textDecoration = textDecoration,
        overflow = textOverflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout,
    )
}

@Composable
fun Caption(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = LocalContentColor.current,
    textAlign: TextAlign = TextAlign.Start,
    textDecoration: TextDecoration? = null,
    textOverflow: TextOverflow = TextOverflow.Clip,
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    rippleColor: Color = Color.Unspecified,
    rippleEnable: Boolean = false,
    bounded: Boolean = true,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    onClick: (() -> Unit)? = null,
) {
    Text(
        modifier = modifier.ctvClickable(
            onClick = onClick,
            interactionSource = interactionSource,
            rippleColor = rippleColor,
            rippleEnable = rippleEnable,
            bounded = bounded
        ),
        text = text,
        style = CtvTypography.caption,
        color = textColor,
        textAlign = textAlign,
        textDecoration = textDecoration,
        overflow = textOverflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout,
    )
}

@Composable
fun CustomText(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle,
    textColor: Color = LocalContentColor.current,
    textAlign: TextAlign = TextAlign.Start,
    textDecoration: TextDecoration? = null,
    textOverflow: TextOverflow = TextOverflow.Clip,
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    rippleColor: Color = Color.Unspecified,
    rippleEnable: Boolean = false,
    bounded: Boolean = true,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    onClick: (() -> Unit)? = null,
) {
    Text(
        modifier = modifier.ctvClickable(
            onClick = onClick,
            interactionSource = interactionSource,
            rippleColor = rippleColor,
            rippleEnable = rippleEnable,
            bounded = bounded
        ),
        text = text,
        style = style,
        color = textColor,
        textAlign = textAlign,
        textDecoration = textDecoration,
        overflow = textOverflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout,
    )
}

val LocalTypography = staticCompositionLocalOf { CtvTypography }
