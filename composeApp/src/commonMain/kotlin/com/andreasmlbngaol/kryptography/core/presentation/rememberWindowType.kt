package com.andreasmlbngaol.kryptography.core.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.dp
import com.andreasmlbngaol.kryptography.core.domain.WindowType

@Composable
fun rememberWindowType(): WindowType {
    val density = LocalDensity.current
    val windowSize = LocalWindowInfo.current.containerSize

    val widthDp = with(density) { windowSize.width.toDp() }

    return when {
        widthDp < 600.dp -> WindowType.Compact
        widthDp < 840.dp -> WindowType.Medium
        else -> WindowType.Expanded
    }
}