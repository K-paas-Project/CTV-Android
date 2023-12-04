package com.kpass.ctv.feature.root

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = NavGroup.Home.MAIN.name) {

        composable(NavGroup.Home.MAIN.name) {
            Button(onClick = { navController.navigate(NavGroup.Home.INFO.name) }) {
                Text(text = "123213213")
            }
        }
        composable(NavGroup.Home.INFO.name) {
            Text(text = "22132132222")
        }

        composable(NavGroup.Test.TEST.name) {
            Text(text = "123213123213")
        }
    }
}