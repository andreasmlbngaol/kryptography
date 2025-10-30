package com.andreasmlbngaol.kryptography.features.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.andreasmlbngaol.kryptography.core.domain.NavKey
import com.andreasmlbngaol.kryptography.features.home.data.algorithms
import com.andreasmlbngaol.kryptography.features.home.presentation.components.AlgorithmCard
import com.andreasmlbngaol.kryptography.resources.Res
import com.andreasmlbngaol.kryptography.resources.app_name
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToAlgorithm: (NavKey) -> Unit,
//    viewModel: HomeViewModel = koinViewModel()
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(Res.string.app_name))
                }
            )
        }
    ) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 150.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            items(algorithms) { algorithm ->
                AlgorithmCard(
                    name = algorithm.name,
                    imageRes = algorithm.imageRes,
                    modifier = Modifier.fillMaxWidth()
                ) { onNavigateToAlgorithm(algorithm.navKey) }
            }
        }
    }
}