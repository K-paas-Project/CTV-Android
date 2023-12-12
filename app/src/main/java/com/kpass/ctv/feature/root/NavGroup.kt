package com.kpass.ctv.feature.root

import androidx.annotation.DrawableRes
import com.kpass.ctv.R

sealed class NavGroup(@DrawableRes val icon: Int, val group: String, val icName: String) {
    sealed class Home(val name: String): NavGroup(R.drawable.ic_round_home, "home", "홈") {
        object MAIN: Home("home")
        object INFO: Home("info/{category}/{videoId}/{location}/{detailLocation}")
    }
    sealed class Log(val name: String): NavGroup(R.drawable.ic_log, "log", "기록") {
        object LOG: Log("log")
    }

    sealed class Test(val name: String): NavGroup(R.drawable.ic_log, "test", "기록") {
        object TEST: Test("test")
    }
}
