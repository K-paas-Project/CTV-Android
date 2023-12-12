package com.kpass.ctv.feature.root

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.kpass.ctv.ui.componet.button.CtvBottomButton

private val navHorizontalMargin = 16.dp

@Composable
fun BottomNavigation(
    selectedTab: NavGroup,
    selectedTabCallback: (NavGroup) -> Unit,
    navController: NavController
) {
    val visible by LocalNavigationState.current
    if (visible) {
        val items = listOf(
            NavGroup.Home.MAIN,
            NavGroup.Test.TEST
        )
        Row(
            modifier = Modifier
                .selectableGroup()
                .height(64.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Spacer(modifier = Modifier.width(navHorizontalMargin))
            items.forEachIndexed { index, item ->
//            Log.d("TAG", "뭐: ${item}")
//            Log.d("TAG", "뭐노: ${items[index].icName}")
//            val selected = item == selectedTab
                CtvBottomButton(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(0.dp)),
                    text = item.icName,
                    onClick = {
                        Log.d("TAG", "BottomNavigation: ${item.icName}")
                        if (selectedTab.group == item.group) {
                            return@CtvBottomButton
                        }
                        selectedTabCallback(item)
                        navController.navigate(item.group) {
                            navController.graph.startDestinationRoute?.let {
                                popUpTo(it) { saveState = true }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    selected = selectedTab.group == item.group,
                    enable = true,
                    iconId = item.icon
                )
            }
            Spacer(modifier = Modifier.width(navHorizontalMargin))
        }
    }
}