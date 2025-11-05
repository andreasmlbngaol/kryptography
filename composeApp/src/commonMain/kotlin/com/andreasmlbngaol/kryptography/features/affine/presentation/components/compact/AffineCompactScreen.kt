package com.andreasmlbngaol.kryptography.features.affine.presentation.components.compact

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.andreasmlbngaol.kryptography.core.presentation.BackAppBar
import com.andreasmlbngaol.kryptography.features.affine.data.menuItems
import com.andreasmlbngaol.kryptography.features.affine.presentation.AffineViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AffineCompactScreen(
    onBack: () -> Boolean,
    viewModel: AffineViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            BackAppBar(
                title = "Affine Cipher",
                onBack = onBack
            )
        },
        bottomBar = {
            NavigationBar {
                menuItems.forEachIndexed { index, menuItem ->
                    val isSelected = state.selectedMenuIndex == index
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = { viewModel.selectMenu(index) },
                        icon = {
                            val icon =
                                if (isSelected) menuItem.selectedIcon else menuItem.unselectedIcon
                            Icon(icon, menuItem.name)
                        },
                        label = { Text(menuItem.name) }
                    )
                }
            }
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .fillMaxSize()
        ) {
            AnimatedContent(state.selectedMenuIndex) { selectedMenuIndex ->
                when (selectedMenuIndex) {
                    0 -> {}
                    1 -> AffineCompactDecrypt(
                        state = state,
                        onChangeCipherText = viewModel::changeCipherText,
                        onChangeCipherA = viewModel::changeCipherA,
                        onChangeCipherB = viewModel::changeCipherB,
                        onDecryptText = viewModel::decryptText,
                    )
                }
            }
        }
    }
}