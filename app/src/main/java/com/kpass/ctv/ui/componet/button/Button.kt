package com.kpass.ctv.ui.componet.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kpass.ctv.ui.theme.Body
import com.kpass.ctv.ui.theme.CtvColor
import com.kpass.ctv.ui.theme.CtvTheme
import com.kpass.ctv.ui.theme.CtvTypography
import com.kpass.ctv.ui.theme.CustomText
import com.kpass.ctv.ui.theme.Headline

sealed class ButtonType(val buttonColor: Color, val disableColor: Color = CtvColor.Gray200) {
    object Black: ButtonType(buttonColor = CtvColor.Black)
    object Gray: ButtonType(buttonColor = CtvColor.Gray100)
    object White: ButtonType(buttonColor = CtvColor.White)
    object Red: ButtonType(buttonColor = CtvColor.Red)
    object Blue: ButtonType(buttonColor = CtvColor.Blue)
    object Transparent: ButtonType(buttonColor = CtvColor.Transparent)

}

@Composable
fun CtvButton(
    modifier: Modifier = Modifier,
    text: String,
    type: ButtonType = ButtonType.Black,
    isShadow: Boolean = true,
    enabled: Boolean = true,
    shape: Shape = CtvTheme.shape.semiLarge,
    border: BorderStroke? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    contentPadding: PaddingValues = PaddingValues(vertical = 13.dp, horizontal = 24.dp),
    onClick: () -> Unit,
) {
        val colors = ButtonDefaults.buttonColors(
                containerColor = type.buttonColor,
                disabledContainerColor = type.disableColor
            )
        Button(
            modifier = modifier,
            onClick = onClick,
            enabled = enabled,
            shape = shape,
            colors = colors,
            interactionSource = interactionSource,
            contentPadding = PaddingValues(),
            border = border,
        ) {
            Surface(
                color = CtvColor.Transparent,
                modifier = Modifier,
            ) {
                Column(
                    modifier = Modifier
                        .padding(contentPadding)
                ) {
                    CustomText(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = text,
                        textColor = contentColorFor(
                            if (enabled) type.buttonColor
                            else type.disableColor
                        ),
                        style = CtvTypography.body.copy(fontWeight = FontWeight.Medium)
                    )
                }
            }
        }
}

@Preview
@Composable
fun ButtonPreview() {

    Column {

        CtvButton(text = "신고하기", modifier = Modifier.height(52.dp)) {
        }

        CtvButton(text = "신고하기", type = ButtonType.Red) {

        }
        CtvButton(text = "신고하기", type = ButtonType.Blue) {

        }
        CtvButton(text = "신고하기", type = ButtonType.Blue, enabled = false) {

        }
        CtvButton(text = "신고하기", type = ButtonType.Transparent) {

        }
    }
}