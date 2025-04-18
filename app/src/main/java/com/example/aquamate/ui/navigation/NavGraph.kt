package com.example.aquamate.ui.navigation

import AddWaterScreen
import SystemBarsConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.aquamate.ui.model.AddWaterViewModel
import com.example.aquamate.ui.screens.MainScreen
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.getValue
import com.example.aquamate.ui.model.MainViewModel
import com.example.aquamate.ui.theme.Background

object Navigator {
    const val MAIN = "main_screen"
    const val ADD_WATER = "add_water_screen"
}

@Composable
fun NavGraph(
    navController: NavHostController,
    vm: MainViewModel = koinViewModel()
) {

    val state by vm.uiState.collectAsState()
    val modifier = Modifier
        .fillMaxSize()
        .background(Background)
        .windowInsetsPadding(WindowInsets.systemBars)

    SystemBarsConfiguration(
        statusBarDarkIcons = true,
        navigationBarDarkIcons = true
    )

    Scaffold (
        modifier = modifier
    ){ paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Navigator.MAIN,
            modifier = Modifier.then(Modifier.padding(paddingValues))
        ) {
            composable(Navigator.MAIN) {
                MainScreen(
                    navController = navController,
                    modifier = modifier,
                    state = state
                )
            }
            composable(Navigator.ADD_WATER) {
                AddWaterScreen(
                    modifier = modifier,
                    onBack = { navController.popBackStack() },
                    onAdd = { amount -> vm.addProgress(amount) }
                )
            }
        }
    }
}
