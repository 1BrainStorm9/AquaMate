package com.example.aquamate.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

val LightColorScheme = lightColorScheme(
    primary             = Blue100,
    onPrimary           = Color.White,
    secondary           = Grey30,
    onSecondary         = Color.Black,
    background          = Background,
    onBackground        = Color.Black,
    surface             = Background,
    onSurface           = Color.Black,
    error               = Color(0xFFB00020),
    onError             = Color.White
)

val DarkColorScheme = darkColorScheme(
    primary             = Blue100,
    onPrimary           = Color.Black,
    secondary           = Grey30,
    onSecondary         = Color.White,
    background          = Color(0xFF121212),
    onBackground        = Color.White,
    surface             = Color(0xFF121212),
    onSurface           = Color.White,
    error               = Color(0xFFCF6679),
    onError             = Color.Black
)

@Composable
fun AquaMateTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
        val context = LocalContext.current
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }
      darkTheme -> DarkColorScheme
      else -> LightColorScheme
    }

    MaterialTheme(
      colorScheme = colorScheme,
      typography = Typography,
      content = content
    )
}