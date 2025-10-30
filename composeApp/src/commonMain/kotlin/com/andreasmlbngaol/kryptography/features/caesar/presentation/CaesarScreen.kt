package com.andreasmlbngaol.kryptography.features.caesar.presentation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.andreasmlbngaol.kryptography.core.domain.WindowType
import com.andreasmlbngaol.kryptography.core.presentation.rememberWindowType
import com.andreasmlbngaol.kryptography.features.caesar.presentation.components.CaesarCompactScreen
import com.andreasmlbngaol.kryptography.features.caesar.presentation.components.CaesarExpandedScreen
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CaesarScreen(
    onBack: () -> Boolean,
    viewModel: CaesarViewModel = koinViewModel()
) {
    val windowType = rememberWindowType()

    when (windowType) {
        WindowType.Compact -> CaesarCompactScreen(
            onBack = onBack,
            viewModel = viewModel
        )
        else -> CaesarExpandedScreen(
            onBack = onBack,
            viewModel = viewModel
        )
    }
}