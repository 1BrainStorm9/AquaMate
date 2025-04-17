package com.example.aquamate.ui.navigation

import AddWaterScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.aquamate.ui.screens.MainScreen

object Navigator {
    const val MAIN = "main_screen"
    const val ADD_WATER = "add_water_screen"
}

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Navigator.MAIN,
        modifier = modifier
    ) {
        composable(Navigator.MAIN) {
            MainScreen(
                navController = navController,
                modifier = modifier
            )
        }
        composable(Navigator.ADD_WATER) {
            AddWaterScreen(navController = navController)
        }
    }
}
