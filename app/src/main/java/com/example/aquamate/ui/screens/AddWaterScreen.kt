package com.example.aquamate.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.aquamate.ui.navigation.Navigator
import com.example.aquamate.ui.theme.Blue100

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddWaterScreen(navController: NavHostController) {
    Box(modifier = Modifier){
        IconButton(
            onClick = {},
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color.Black,
                modifier = Modifier.scale(1.2f)
            )
        }
    }
}