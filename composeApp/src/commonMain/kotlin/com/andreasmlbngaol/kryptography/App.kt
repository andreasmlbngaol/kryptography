package com.andreasmlbngaol.kryptography

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.andreasmlbngaol.kryptography.core.config.Router
import com.andreasmlbngaol.kryptography.core.data.theme.deliusTypography

@Composable
@Preview
fun App() {
    MaterialTheme(
        colorScheme = colorScheme,
        typography = deliusTypography()
    ) {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            Router()
        }
    }
}

expect val colorScheme: ColorScheme
val nonAndroidColorScheme
    @Composable
    get() = if(isSystemInDarkTheme()) darkColorScheme() else lightColorScheme()