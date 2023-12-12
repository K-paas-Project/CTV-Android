package com.kpass.ctv.feature.home

import com.google.accompanist.web.WebViewState

data class HomeState(
    val test: String,
    val webViewState: WebViewState
)


sealed class HomeSideEffect() {

}