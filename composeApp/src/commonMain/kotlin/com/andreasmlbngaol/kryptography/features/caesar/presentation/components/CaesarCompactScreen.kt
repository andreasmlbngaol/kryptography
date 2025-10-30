package com.andreasmlbngaol.kryptography.features.caesar.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.andreasmlbngaol.kryptography.core.domain.AppBarType
import com.andreasmlbngaol.kryptography.core.presentation.BackAppBar
import com.andreasmlbngaol.kryptography.core.presentation.RoundedOutlinedTextField
import com.andreasmlbngaol.kryptography.features.caesar.data.menuItems
import com.andreasmlbngaol.kryptography.features.caesar.presentation.CaesarViewModel
import com.andreasmlbngaol.kryptography.resources.Res
import com.andreasmlbngaol.kryptography.resources.cipher_text_title
import com.andreasmlbngaol.kryptography.resources.encrypt_button_text
import com.andreasmlbngaol.kryptography.resources.plain_text_title
import compose.icons.TablerIcons
import compose.icons.tablericons.Minus
import compose.icons.tablericons.Plus
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CaesarCompactScreen(
    onBack: () -> Boolean,
    viewModel: CaesarViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            BackAppBar(
                title = "Caesar Cipher",
                type = AppBarType.Center,
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
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .fillMaxSize()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // === PLAINTEXT ===
            Text(
                text = stringResource(Res.string.plain_text_title),
                textDecoration = TextDecoration.Underline,
                style = MaterialTheme.typography.titleLarge
            )

            RoundedOutlinedTextField(
                value = state.plainText,
                onValueChange = { viewModel.changePlainText(it) },
                placeholder = { Text(stringResource(Res.string.plain_text_title)) },
                modifier = Modifier.fillMaxWidth()
            )

            // === KEY ===
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Key:",
                    style = MaterialTheme.typography.titleMedium
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    FilledIconButton(onClick = viewModel::decrementPlainKey) {
                        Icon(TablerIcons.Minus, "Decrement")
                    }

                    Text(
                        text = state.plainKey.toString(),
                        style = MaterialTheme.typography.bodyLarge
                    )

                    FilledIconButton(onClick = viewModel::incrementPlainKey) {
                        Icon(TablerIcons.Plus, "Increment")
                    }
                }
            }

            // === ENCRYPT BUTTON ===
            Button(
                enabled = state.encryptedButtonEnabled,
                onClick = { viewModel.encryptText() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(Res.string.encrypt_button_text))
            }

            // === ENCRYPTED RESULT ===
            if (state.encryptedText.isNotEmpty()) {
                HorizontalDivider()
                Text(
                    text = stringResource(Res.string.cipher_text_title),
                    textDecoration = TextDecoration.Underline,
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = state.encryptedText,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}
