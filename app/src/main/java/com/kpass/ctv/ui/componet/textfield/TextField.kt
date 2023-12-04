package com.kpass.ctv.ui.componet.textfield

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kpass.ctv.R
import com.kpass.ctv.ui.componet.modifier.ctvClickable
import com.kpass.ctv.ui.theme.Body
import com.kpass.ctv.ui.theme.CtvColor
import com.kpass.ctv.ui.theme.CtvShape
import com.kpass.ctv.ui.theme.CtvTheme
import com.kpass.ctv.ui.theme.CtvTypography
import com.kpass.ctv.ui.theme.CustomText


@Preview(showSystemUi = true)
@Composable
private fun preview() {
    var text by remember { mutableStateOf("") }
    CtvTextField(
        modifier = Modifier.fillMaxWidth(),
        value = text,
        hint = "아이디를 입력하세요.",
        onDeleteButton = {
            text = ""
        },
        type = KeyboardType.Password
    ) {
        text = it
    }
}

@Composable
fun CtvTextField(
    modifier: Modifier = Modifier,
    value: String,
    hint: String = "",
    singleLine: Boolean = true,
    type: KeyboardType = KeyboardType.Text,
    onDone: () ->  Unit = { },
    onDeleteButton: () -> Unit,
    onValueChange: (String) -> Unit,
) {
    val focus = LocalFocusManager.current
    val focusRequester by remember { mutableStateOf(FocusRequester()) }
    var isFocus by remember { mutableStateOf(false) }

    CompositionLocalProvider {
        Box() {
            BasicTextField(
                modifier = modifier
                    .background(CtvColor.BlueGray, CtvTheme.shape.middle)
                    .focusRequester(focusRequester)
                    .onFocusChanged {
                        isFocus = it.isFocused
                    },
                value = value,
                textStyle = CtvTypography.body.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                onValueChange = onValueChange,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = type
                ),
                visualTransformation = if (type == KeyboardType.Password) PasswordVisualTransformation() else VisualTransformation.None,
                singleLine = singleLine,
                cursorBrush = SolidColor(Color.Black),
                keyboardActions = KeyboardActions(
                    onDone = {
                        onDone()
                        focus.clearFocus()
                    }
                )
            ) { innerTextField ->
                Box(modifier = Modifier.padding(20.dp)) {
                    innerTextField()
                    if (!isFocus && value.isEmpty()) {
                        CustomText(
                            text = hint,
                            textColor = Color(0xFF9099A6),
                            style = CtvTypography.body.copy(
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }
                }
            }
            Image(
                modifier = Modifier
                    .padding(end = 12.dp)
                    .size(24.dp)
                    .align(Alignment.CenterEnd)
                    .ctvClickable(
                        rippleEnable = false,
                        onClick = onDeleteButton
                    ),
                painter = painterResource(id = R.drawable.ic_delete),
                contentDescription = "지우기"
            )
        }
    }
}

