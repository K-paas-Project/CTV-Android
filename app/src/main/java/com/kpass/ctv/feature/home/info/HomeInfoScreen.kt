package com.kpass.ctv.feature.home.info

import android.app.Activity
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
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
import com.kpass.ctv.ui.componet.button.CtvImageButton
import com.kpass.ctv.ui.componet.modifier.ctvClickable
import com.kpass.ctv.ui.theme.Caption
import com.kpass.ctv.ui.theme.CtvColor
import com.kpass.ctv.ui.theme.CtvShape
import com.kpass.ctv.ui.theme.CtvTheme
import com.kpass.ctv.ui.theme.CtvTypography
import com.kpass.ctv.ui.theme.CustomText
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
    NotFullScreen(
        webViewState = webViewState,
        isFullScreen = isFullScreen,
        activity = context as Activity,
        category = category,
        location = location,
        detailLocation = detailLocation,
        onClickWarring = {
            Log.d("TAG", "HomeInfoScreen: $category $videoId")

        }
    ) {
        coroutineScope.launch {
            isFullScreen = !isFullScreen
            test.value = !test.value
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
private fun NotFullScreen(
    webViewState: WebViewState,
    isFullScreen: Boolean,
    category: String,
    location: String,
    detailLocation: String,
    activity: Activity,
    onClickWarring: () -> Unit,
    onClickFull: () -> Unit,
) {
    val test = if (isFullScreen) Modifier.scale(16f/9f) else Modifier.aspectRatio(if (isFullScreen) 9f / 16f else 16f / 9f)
    var yPadding by remember { mutableStateOf(0f) }
    var test2 by remember { mutableStateOf(0f) }
    val context = LocalContext.current
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
                    onClick = onClickWarring
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