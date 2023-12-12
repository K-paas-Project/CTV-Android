package com.kpass.ctv.utiles

import android.app.Activity
import android.os.Build
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.kpass.ctv.ui.theme.CtvColor


internal fun String.getCategoryColor() =
    when(this) {
        "산불" -> CtvColor.Red
        "쓰나미" -> CtvColor.Blue
        "홍수" -> CtvColor.Brown
        "안전" -> CtvColor.Green
        else -> CtvColor.Transparent
    }

internal fun String.getCategoryBackgroundColor() =
    when(this) {
        "산불" -> CtvColor.RedBackground
        "쓰나미" -> Color(0xFFDEE3FF)
        "홍수" -> Color(0xFFF8D5C6)
        "안전" -> Color(0xFFCCF9E6)
        else -> CtvColor.Transparent
    }

fun Activity.setStatusBarTransparent() {
//    getS
//    getS?.hide()
    if (Build.VERSION.SDK_INT < 30) {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                // 컨텐츠를 시스템 바 밑에 보이도록 설정
                // 시스템 바가 숨겨지거나 보여질 때 컨텐츠 부분이
                // 리사이징 되는 것을 막기 위한 설정
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // 네비게이션과 상태바를 사라지게하기
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    } else {
        // 이 값이 True이면 내부적으로 SYSTEM UI LAYOUT FLAG들을 살펴보고
        // 해당 설정값들을 토대로 화면을 구성하게 됩니다.
        // 따라서 False로 설정을 해야 이제 사라질(Deprecated)될
        // Flag값들을 무시하고 Window Insets를 통해 화면을 구성할 수 있습니다
        window.setDecorFitsSystemWindows(false)

        val controller = window.insetsController
        if(controller != null){
            // 상태바와 네비게이션을 사라지게한다
            controller.hide(WindowInsets.Type.statusBars() or
                    WindowInsets.Type.navigationBars())
            // 특정 행동(화면 끝을 스와이프하는 등)을 했을 때에만
            // 시스템 바가 나타나도록 설정
            controller.systemBarsBehavior =
                WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
//    window.apply {
//        setFlags(
//            WindowManager.LayoutParams.FLAG_FULLSCREEN,
//            WindowManager.LayoutParams.FLAG_FULLSCREEN
//        )
//    }
//    if(Build.VERSION.SDK_INT >= 30) {	// API 30 에 적용
//        WindowCompat.setDecorFitsSystemWindows(window, false)
//    }
}

fun Activity.setStatusBarOrigin() {
    if (Build.VERSION.SDK_INT < 30) {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    } else {
        window.setDecorFitsSystemWindows(true)

        val controller = window.insetsController
        if(controller != null){
            // 상태바와 네비게이션을 다시 보이게 한다.
            controller.show(WindowInsets.Type.statusBars() or
                    WindowInsets.Type.navigationBars())
            // 시스템 바가 항상 보이도록 설정
            controller.systemBarsBehavior =
                WindowInsetsController.BEHAVIOR_DEFAULT
        }
    }

//    window.apply {
//        clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
//    }
//    if(Build.VERSION.SDK_INT >= 30) {	// API 30 에 적용
//        WindowCompat.setDecorFitsSystemWindows(window, true)
//    }
}