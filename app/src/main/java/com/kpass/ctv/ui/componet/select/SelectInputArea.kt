package com.kpass.ctv.ui.componet.select

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.MenuItemColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.kpass.ctv.R
import com.kpass.ctv.ui.theme.Body
import com.kpass.ctv.ui.theme.CtvTheme

@Preview(showSystemUi = true)
@Composable
private fun previews() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterEnd
    ) {
        CtvButtonSelectMenu(
            modifier = Modifier.align(Alignment.CenterEnd),
            itemList = listOf("테스트", "테스트1", "테스트2"),
            onSelectItemListener = {

            }
        )
    }
}


@Composable
fun CtvButtonSelectMenu(
    itemList: List<String>,
    modifier: Modifier = Modifier,
    hint: String = "",
    onSelectItemListener: (String) -> Unit
) {
    // 1. DropDownMenu의 펼쳐짐 상태 정의
    var isDropDownMenuExpanded by remember { mutableStateOf(false) }
    var buttonSize by remember { mutableStateOf(Size.Zero) }
    var buttonText by remember { mutableStateOf(hint) }
    val focusRequester = remember { FocusRequester() }
    Column {
        Button(
            modifier = modifier
                .onGloballyPositioned { coordinates ->
                    buttonSize = coordinates.size.toSize()
                }
                .focusRequester(focusRequester),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFFBFF), contentColor = CtvTheme.color.Black),
            shape = RoundedCornerShape(15.dp),
            onClick = { isDropDownMenuExpanded = true }
        ) {
            Row(modifier = Modifier) {
                Body(
                    modifier = Modifier
                        .padding(
                            start = 0.dp
                        ),
                    text = buttonText
                )
                Spacer(modifier = Modifier.width(8.dp))
                Image(
                    modifier = Modifier
                        .padding(
                            end = 0.dp
                        ),
                    painter = painterResource(id = R.drawable.ic_bottom_arrow),
                    contentDescription = "아래 화살표"
                )
            }

        }

        // 3. DropDownMenu 정의
        DropdownMenu(
            modifier = Modifier
                .width(
                    with(LocalDensity.current) {
                        buttonSize.width.toDp()
                    }
                )
                .heightIn(max = 200.dp)
                .background(CtvTheme.color.White),
            expanded = isDropDownMenuExpanded,
            onDismissRequest = { isDropDownMenuExpanded = false }
        ) {
            itemList.forEachIndexed { index, label ->
                DropdownMenuItem(
                    modifier = Modifier.background(Color(0xFFFFFBFF)),
                    text = {
                        Body(text = label)
                    },
                    onClick = {
                        buttonText = label
                        onSelectItemListener(label)
                        isDropDownMenuExpanded = false
                    }
                )
            }
        }
    }

}


