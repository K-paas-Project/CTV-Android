package com.kpass.ctv.feature.root

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import androidx.navigation.findNavController
import com.kpass.ctv.ui.theme.CtvTheme
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

val LocalNavigationState = compositionLocalOf { mutableStateOf(true) }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val coroutineScope = rememberCoroutineScope()
            var selectedTab: NavGroup by remember { mutableStateOf(NavGroup.Home.MAIN) }
            val localNavigationState = remember { mutableStateOf(true) }
            CtvTheme {
                // A surface container using the 'background' color from the theme
                CompositionLocalProvider(LocalNavigationState provides localNavigationState) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        Scaffold(
                            bottomBar = {
                                BottomNavigation(
                                    selectedTab = selectedTab,
                                    selectedTabCallback = {
                                        coroutineScope.launch {
                                            selectedTab = it
                                        }
                                    },
                                    navController = navController)
                            }
                        ) {
                            Column(
                                modifier = Modifier.padding(it)
                            ) {
                                NavigationGraph(navController)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CtvTheme {
        Greeting("Android")
    }
}