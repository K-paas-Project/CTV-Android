package com.kpass.ctv.feature.root

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kpass.ctv.feature.home.HomeScreen
import com.kpass.ctv.feature.home.info.HomeInfoScreen

@Composable
fun NavigationGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = NavGroup.Home.MAIN.name) {

        composable(NavGroup.Home.MAIN.name) {
            HomeScreen(navController = navController)
//            Button(onClick = { navController.navigate(NavGroup.Home.INFO.name) }) {
//                Text(text = "123213213")
//            }
        }
        composable(
            NavGroup.Home.INFO.name,
            arguments = listOf(
                navArgument("category") { type = NavType.StringType },
                navArgument("videoId") { type = NavType.StringType },
                navArgument("location") { type = NavType.StringType }
                ,navArgument("detailLocation") { type = NavType.StringType }
            )
        ) {entry ->
            val category = entry.arguments?.getString("category")?: ""
            val videoId = entry.arguments?.getString("videoId")?: ""
            val location = entry.arguments?.getString("location")?: ""
            val detailLocation = entry.arguments?.getString("detailLocation")?: ""
            HomeInfoScreen(
                navController = navController,
                category = category,
                videoId = videoId,
                location = location,
                detailLocation = detailLocation
            )
        }

        composable(NavGroup.Test.TEST.name) {
            Text(text = "123213123213")
        }
    }
}