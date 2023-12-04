package com.kpass.ctv.ui.componet.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kpass.ctv.R
import com.kpass.ctv.ui.componet.modifier.ctvClickable
import com.kpass.ctv.ui.theme.CtvColor
import com.kpass.ctv.ui.theme.Label

@Composable
fun CtvBottomButton(
    modifier: Modifier = Modifier,
    text: String,
    @DrawableRes iconId: Int,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    enable: Boolean = true,
    selected: Boolean = false,
    onClick: () -> Unit,
) {
    val color = if (selected) CtvColor.Black else CtvColor.Gray200
    Surface(
        color = CtvColor.Transparent,
        modifier = modifier
            .ctvClickable(
                interactionSource = interactionSource,
                rippleEnable = enable,
                bounded = false,
                onClick = onClick
            )
    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = null,
                tint = color
            )
            Spacer(modifier = Modifier.height(6.dp))
            Label(
                modifier = Modifier,
                text = text,
                textColor = color
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    CtvBottomButton(text = "qq", iconId = R.drawable.ic_launcher_foreground) {

    }
}