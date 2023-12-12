package com.kpass.ctv.feature.home.info

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Canvas
import android.icu.text.IDNA.Info
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.accompanist.web.AccompanistWebChromeClient

import com.google.accompanist.web.WebContent
import com.google.accompanist.web.WebView
import com.google.accompanist.web.WebViewState
import com.google.accompanist.web.rememberWebViewState
import com.kpass.ctv.R
import com.kpass.ctv.feature.root.LocalNavigationState
import com.kpass.ctv.ui.componet.button.ButtonType
import com.kpass.ctv.ui.componet.button.CtvButton
import com.kpass.ctv.ui.componet.button.CtvImageButton
import com.kpass.ctv.ui.componet.modifier.ctvClickable
import com.kpass.ctv.ui.componet.textfield.CtvEditTextField
import com.kpass.ctv.ui.theme.Caption
import com.kpass.ctv.ui.theme.CtvColor
import com.kpass.ctv.ui.theme.CtvShape
import com.kpass.ctv.ui.theme.CtvTheme
import com.kpass.ctv.ui.theme.CtvTypography
import com.kpass.ctv.ui.theme.CustomText
import com.kpass.ctv.ui.theme.Label
import com.kpass.ctv.utiles.getCategoryBackgroundColor
import com.kpass.ctv.utiles.getCategoryColor
import com.kpass.ctv.utiles.setStatusBarOrigin
import com.kpass.ctv.utiles.setStatusBarTransparent
import kotlinx.coroutines.launch

//@Preview
@Composable
fun HomeInfoScreen(
    viewModel: HomeInfoViewModel = viewModel(),
    navController: NavController,
    category: String,
    videoId: String,
    location: String,
    detailLocation: String
) {
    var isFullScreen by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val webViewState = rememberWebViewState(url = "http://223.130.136.187:8000/$videoId")
    val context = LocalContext.current
    val test = LocalNavigationState.current
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }
    Column {
        if (bitmap.value != null) {
            WarringScreen(
                bitmap = bitmap.value!!,
                category = category,
                location = location,
                detailLocation = detailLocation,
                onBack = {
                    coroutineScope.launch {
                        test.value = true
                        bitmap.value = null
                    }
                }
            )
//            Image(
//                modifier = Modifier.size(200.dp),
//                bitmap = bitmap.value!!.asImageBitmap(),
//                contentDescription = "이미지"
//            )
        } else {
            InfoScreen(
                webViewState = webViewState,
                isFullScreen = isFullScreen,
                activity = context as Activity,
                category = category,
                location = location,
                detailLocation = detailLocation,
                onClickWarring = {
                    Log.d("TAG", "HomeInfoScreen: $category $videoId")
                    Log.d("TAG", "HomeInfoScreen: $it")
                    coroutineScope.launch {
                        bitmap.value = it
                        test.value = false
                    }
                }
            ) {
                coroutineScope.launch {
                    isFullScreen = !isFullScreen
                    test.value = !test.value
                }
            }
        }
    }
//    if (isFullScreen.not()) {
//        NotFullScreen(
//            webViewState = webViewState,
//            isFullScreen = isFullScreen
//        ) {
//            coroutineScope.launch {
//                isFullScreen = true
//            }
//        }
//    } else {
//        WebView(
//            state = webViewState,
//            modifier = Modifier
//                .aspectRatio(16f / 9f)
//                .fillMaxWidth(),
//            onCreated = {
//                with(it.settings) {
//                    loadWithOverviewMode = true
//                    useWideViewPort = true
//
//                }
//
//            }
//        )
//    }

}

@Composable
private fun WarringScreen(
    bitmap: Bitmap,
    category: String,
    location: String,
    detailLocation: String,
    onBack: () -> Unit
) {
    var text by remember { mutableStateOf("") }
    val focus = LocalFocusManager.current
    BackHandler(
        onBack = onBack
    )
    Column(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState())
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focus.clearFocus()

                })
            }
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        Caption(text = "2023/11/11")
        Spacer(modifier = Modifier.height(4.dp))
        CustomText(
            text = "${category} 의심 긴급 신고",
            style = CtvTypography.headline.copy(
                fontWeight = FontWeight.ExtraBold
            )
        )
        Spacer(modifier = Modifier.height(28.dp))
        Divider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = Color(0xFFF5F5F5)
        )
        Spacer(modifier = Modifier.height(28.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                CustomCategoryText(text = "카테고리")
                Spacer(modifier = Modifier.height(3.dp))
                WarringCategoryView(modifier = Modifier, text = category)
            }
            Column(
                modifier = Modifier.weight(1f)
            ) {
                CustomCategoryText(text = "신고자명")
                Spacer(modifier = Modifier.height(4.dp))
                Label(
                    text = "시청공무원 - 박병준",
                    textColor = Color(0xFF7C7F83)
                )
            }
        }
        Spacer(modifier = Modifier.height(28.dp))
        CustomCategoryText(text = "위치 & 시간")
        Spacer(modifier = Modifier.height(4.dp))
        Caption(
            text = "$detailLocation \n2023-11-11-21:30:41",
            textColor = Color(0xFF7C7F83)
        )
        Spacer(modifier = Modifier.height(28.dp))
        Divider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = Color(0xFFF5F5F5)
        )
        Spacer(modifier = Modifier.height(28.dp))
        CustomCategoryText(text = "이미지")
        Spacer(modifier = Modifier.height(7.dp))
        Image(
            modifier = Modifier
                .aspectRatio(16f / 9f)
                .clip(RoundedCornerShape(10.dp)),
            bitmap = bitmap.asImageBitmap(),
            contentDescription = "사건사고"
        )
        Spacer(modifier = Modifier.height(20.dp))
        CustomCategoryText(text = "설명")
        Spacer(modifier = Modifier.height(12.dp))
        CtvEditTextField(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 115.dp),
            value = text,
            hint = "설명을 입력하세요.",
            singleLine = false,
            onValueChange = { text = it }
        )
        Spacer(modifier = Modifier.height(51.dp))
        CtvButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            text = "신고하기",
            type = ButtonType.Red,
            style = CtvTypography.headline.copy(
                fontWeight = FontWeight.Bold
            ),
            shape = CtvTheme.shape.veryLarge,
        ) {
            
        }
        Spacer(modifier = Modifier.height(10.dp))
        CtvButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            text = "취소하기",
            type = ButtonType.Black,
            style = CtvTypography.headline.copy(
                fontWeight = FontWeight.Bold
            ),
            shape = CtvTheme.shape.veryLarge,
        ) {

        }
        Spacer(modifier = Modifier.height(30.dp))

    }
}

@Composable
private fun InfoScreen(
    webViewState: WebViewState,
    isFullScreen: Boolean,
    category: String,
    location: String,
    detailLocation: String,
    activity: Activity,
    onClickWarring: (Bitmap) -> Unit,
    onClickFull: () -> Unit,
) {
    val test = if (isFullScreen) Modifier.scale(16f/9f) else Modifier.aspectRatio(if (isFullScreen) 9f / 16f else 16f / 9f)
    var yPadding by remember { mutableStateOf(0f) }
    var test2 by remember { mutableStateOf(0f) }
    val context = LocalContext.current
    var webView by remember { mutableStateOf<WebView?>(null) }
    LaunchedEffect(key1 = isFullScreen) {
        if (isFullScreen) {
            activity.setStatusBarTransparent()
        } else {
            activity.setStatusBarOrigin()
        }
    }


    BackHandler(enabled = isFullScreen) {
        onClickFull()
    }

    Column(
        modifier = Modifier
            .background(if (isFullScreen) CtvColor.Black else CtvColor.Transparent)
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
        ) {
//            Box(modifier = Modifier
//                .width(20.dp)
//                .height((yPadding + test2).dp)
//                .background(Color.Red)
//                .align(Alignment.BottomStart)
//                .padding(top = (yPadding + test2).dp)
//                .offset(y = (yPadding + test2).dp)
//            )
            WebView(
                state = webViewState,
                modifier = Modifier
                    .rotate(if (isFullScreen) 90f else 0f)
//                    .aspectRatio(if (isFullScreen) 9f / 16f else 16f / 9f)
                    .then(test)
                    .fillMaxSize()
                    .align(Alignment.Center)
                    .background(Color.Green)
                    .onGloballyPositioned { coordinates ->
                        val position = coordinates.positionInWindow()
                        yPadding = position.y - 100
                        test2 = coordinates.size.height.toFloat()
                        Log.d(
                            "TAG",
                            "NotFullScreen: ${position.x} ${position.y} ${position.y - coordinates.size.height}"
                        )
                        Log.d(
                            "TAG",
                            "NotFullScreen: ${coordinates.size.width} ${coordinates.size.height}"
                        )
//                        x = position.x + coordinates.size.width
//                        y = position.y
                    },
                onCreated = {
                    webView = it

                    with(it.settings) {
                        loadWithOverviewMode = true
                        useWideViewPort = true

                    }

                }
            )
            Image(
                modifier = Modifier
                    .align(if (isFullScreen) Alignment.BottomStart else Alignment.BottomEnd)
                    .size(28.dp)
                    .offset(y = -20.dp) //(yPadding + test2)
                    .padding(
                        start = if (isFullScreen) 4.dp else 0.dp,
                        end = if (isFullScreen) 0.dp else 4.dp,
                        bottom = 0.dp
                    )//if (isFullScreen) yPadding.dp else
                    .ctvClickable(
                        rippleEnable = true,
                        onClick = onClickFull
                    ),
                painter = painterResource(id = R.drawable.ic_full_screen),
                contentDescription = "전체화면"
            )

        }
        if (isFullScreen.not()) {
            Spacer(modifier = Modifier.height(12.dp))
            CustomText(
                modifier = Modifier.padding(horizontal = 24.dp),
                text = location,
                style = CtvTypography.body.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Caption(
                modifier = Modifier.padding(horizontal = 24.dp),
                text = detailLocation, // · 2023-11-19 13시 7분 31초",
                textColor = Color(0xFF76767B)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {

                CategoryView(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(bottom = 4.dp),
                    text = category
                )
                CtvImageButton(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    text = "신고하기",
                    type = ButtonType.Red,
                    image = R.drawable.ic_warring,
                    contentPadding = PaddingValues(vertical = 13.dp, horizontal = 17.dp),
                    shape = CtvTheme.shape.middle,
                    onClick = {
                        if (webView == null) {
                            return@CtvImageButton
                        }
                        val bitmap = Bitmap.createBitmap(webView!!.width, webView!!.height, Bitmap.Config.ARGB_8888)
                        val canvas = Canvas(bitmap)
                        webView!!.draw(canvas)
                        onClickWarring(bitmap)
                    }
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Divider(
                thickness = 1.dp,
                color = Color(0xFFDBDCE0)
            )

        }
    }

}

@Composable
private fun CategoryView(
    modifier: Modifier,
    text: String
) {
    Surface(
        modifier = modifier,
        color = text.getCategoryBackgroundColor(),
        shape = RoundedCornerShape(10.dp)
    ) {
        CustomText(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            text = text,
            textColor = text.getCategoryColor(),
            style = CtvTypography.body.copy(
                fontWeight = FontWeight.Normal
            )
        )
    }
}

@Composable
private fun WarringCategoryView(
    modifier: Modifier,
    text: String
) {
    Surface(
        modifier = modifier,
        color = text.getCategoryBackgroundColor(),
        shape = RoundedCornerShape(10.dp)
    ) {
        CustomText(
            modifier = Modifier.padding(horizontal = 9.dp, vertical = 3.dp),
            text = text,
            textColor = text.getCategoryColor(),
            style = CtvTypography.body.copy(
                fontWeight = FontWeight.Normal
            )
        )
    }
}

@Composable
private fun CustomCategoryText(
    text: String
) {
    CustomText(
        text = text,
        style = CtvTypography.body.copy(
            fontWeight = FontWeight.Bold
        )
    )
}