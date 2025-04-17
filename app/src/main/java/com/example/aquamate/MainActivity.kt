package com.example.aquamate

import SystemBarsConfiguration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.aquamate.ui.theme.AquaMateTheme
import com.example.aquamate.ui.theme.Background
import com.example.aquamate.ui.navigation.NavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val mainColor = Background

            AquaMateTheme {
                SystemBarsConfiguration(
                    statusBarDarkIcons = true,
                    navigationBarDarkIcons = true
                )
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(mainColor)
                        .windowInsetsPadding(WindowInsets.systemBars)
                ) { paddingValues ->
                    NavGraph(
                        navController = navController,
                        modifier = Modifier
                            .background(mainColor)
                            .fillMaxSize()
                            .windowInsetsPadding(WindowInsets.systemBars)
                            .then(Modifier.padding(paddingValues))
                    )
                }
            }
        }
    }
}
