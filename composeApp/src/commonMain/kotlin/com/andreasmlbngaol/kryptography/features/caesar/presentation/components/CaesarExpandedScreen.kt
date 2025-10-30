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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipAnchorPosition
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.WideNavigationRail
import androidx.compose.material3.WideNavigationRailItem
import androidx.compose.material3.WideNavigationRailValue
import androidx.compose.material3.rememberTooltipState
import androidx.compose.material3.rememberWideNavigationRailState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.style.TextAlign
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
import compose.icons.tablericons.Menu
import compose.icons.tablericons.Menu2
import compose.icons.tablericons.Minus
import compose.icons.tablericons.Plus
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun CaesarExpandedScreen(
    onBack: () -> Boolean,
    viewModel: CaesarViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val railState = rememberWideNavigationRailState()
    val scope = rememberCoroutineScope()
    val isExpanded = railState.targetValue == WideNavigationRailValue.Expanded
    val headerDescription = if (isExpanded) "Collapse" else "Expand"

    Scaffold(
        topBar = {
            BackAppBar(
                title = "Caesar Cipher",
                type = AppBarType.Center,
                onBack = onBack,
            )
        }
    ) { innerPadding ->
        Row(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            WideNavigationRail(
                state = railState,
                header = {
                    TooltipBox(
                        positionProvider = TooltipDefaults.rememberTooltipPositionProvider(
                            TooltipAnchorPosition.Above
                        ),
                        tooltip = { PlainTooltip { Text(headerDescription)  }},
                        state = rememberTooltipState()
                    ) {
                        IconButton(
                            modifier =
                                Modifier.padding(start = 24.dp).semantics {
                                    // The button must announce the expanded or collapsed state of the
                                    // rail for accessibility.
                                    stateDescription = if (isExpanded) "Expanded" else "Collapsed"
                                },
                            onClick = {
                                scope.launch {
                                    if(isExpanded) railState.collapse() else railState.expand()
                                }
                            }
                        ) {
                            if (isExpanded) {
                                Icon(TablerIcons.Menu, headerDescription)
                            } else {
                                Icon(TablerIcons.Menu2, headerDescription)
                            }

                        }
                    }
                },
//                colors = WideNavigationRailDefaults.colors().copy(
//                    containerColor = MaterialTheme.colorScheme.surfaceContainer
//                )
            ){
                menuItems.forEachIndexed { index, menuItem ->
                    val isSelected = state.selectedMenuIndex == index
                    WideNavigationRailItem(
                        railExpanded = isExpanded,
                        icon = {
                            val icon = if(isSelected) menuItem.selectedIcon else menuItem.unselectedIcon
                            Icon(icon, menuItem.name)
                        },
                        label = { Text(menuItem.name) },
                        selected = isSelected,
                        onClick = { viewModel.selectMenu(index) }
                    )
                }
            }

            Column(
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .verticalScroll(rememberScrollState())
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(Res.string.plain_text_title),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center,
                    textDecoration = TextDecoration.Underline,
                    style = MaterialTheme.typography.titleLarge
                )
//
                RoundedOutlinedTextField(
                    value = state.plainText,
                    onValueChange = { viewModel.changePlainText(it) },
                    placeholder = { Text(stringResource(Res.string.plain_text_title)) },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    Text(
                        text = "Key:",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {

                        FilledIconButton(
                            onClick = viewModel::decrementPlainKey,
                            shapes = IconButtonDefaults.shapes()
                        ) {
                            Icon(
                                TablerIcons.Minus, "Decrement"
                            )
                        }

                        Text(
                            state.plainKey.toString(),
                            style = MaterialTheme.typography.bodyLarge
                        )

                        FilledIconButton(
                            onClick = viewModel::incrementPlainKey,
                            shapes = IconButtonDefaults.shapes()
                        ) {
                            Icon(
                                TablerIcons.Plus, "Increment"
                            )
                        }
                    }
                }

                Button(
                    enabled = state.encryptedButtonEnabled,
                    onClick = {
                        viewModel.encryptText()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shapes = ButtonDefaults.shapes()
                ) {
                    Text(
                        text = stringResource(Res.string.encrypt_button_text)
                    )
                }

            }
            VerticalDivider()
            Column(
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .verticalScroll(rememberScrollState())
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(Res.string.cipher_text_title),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center,
                    textDecoration = TextDecoration.Underline,
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = state.encryptedText,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
    }
}