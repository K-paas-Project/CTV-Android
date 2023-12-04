package com.kpass.ctv.feature.root

import com.kpass.ctv.R

sealed class BottomNavItem(
    val title: String, val icon: Int, val screenRoute: String
) {
    object Home : BottomNavItem("홈", R.drawable.ic_round_home, NavGroup.Home.MAIN.name)
    object Log : BottomNavItem("기록", R.drawable.ic_log, NavGroup.Log.LOG.name)
}