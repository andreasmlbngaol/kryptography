package com.andreasmlbngaol.kryptography.core.data.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.andreasmlbngaol.kryptography.resources.Delius_Regular
import com.andreasmlbngaol.kryptography.resources.Res
import org.jetbrains.compose.resources.Font

@Composable
fun deliusTypography(): Typography {
    val deliusFont = FontFamily(
        Font(Res.font.Delius_Regular)
    )

    return with(MaterialTheme.typography) {
        copy(
            displayLarge = displayLarge.copy(fontFamily = deliusFont, fontWeight = FontWeight.Bold),
            displayMedium = displayMedium.copy(fontFamily = deliusFont, fontWeight = FontWeight.Bold),
            displaySmall = displaySmall.copy(fontFamily = deliusFont, fontWeight = FontWeight.Bold),
            headlineLarge = headlineLarge.copy(fontFamily = deliusFont, fontWeight = FontWeight.Bold),
            headlineMedium = headlineMedium.copy(fontFamily = deliusFont, fontWeight = FontWeight.Bold),
            headlineSmall = headlineSmall.copy(fontFamily = deliusFont, fontWeight = FontWeight.Bold),
            titleLarge = titleLarge.copy(fontFamily = deliusFont, fontWeight = FontWeight.Bold),
            titleMedium = titleMedium.copy(fontFamily = deliusFont, fontWeight = FontWeight.Bold),
            titleSmall = titleSmall.copy(fontFamily = deliusFont, fontWeight = FontWeight.Bold),
            labelLarge = labelLarge.copy(fontFamily = deliusFont, fontWeight = FontWeight.Normal),
            labelMedium = labelMedium.copy(fontFamily = deliusFont, fontWeight = FontWeight.Normal),
            labelSmall = labelSmall.copy(fontFamily = deliusFont, fontWeight = FontWeight.Normal),
            bodyLarge = bodyLarge.copy(fontFamily = deliusFont, fontWeight = FontWeight.Normal),
            bodyMedium = bodyMedium.copy(fontFamily = deliusFont, fontWeight = FontWeight.Normal),
            bodySmall = bodySmall.copy(fontFamily = deliusFont, fontWeight = FontWeight.Normal),
        )
    }
}