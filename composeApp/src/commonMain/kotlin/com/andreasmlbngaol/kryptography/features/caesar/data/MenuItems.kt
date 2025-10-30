package com.andreasmlbngaol.kryptography.features.caesar.data

import com.andreasmlbngaol.kryptography.features.caesar.domain.MenuItem
import compose.icons.TablerIcons
import compose.icons.tablericons.Key
import compose.icons.tablericons.Lock

val menuItems = listOf(
    MenuItem(
        name = "Encrypt",
        unselectedIcon = TablerIcons.Lock,
        selectedIcon = TablerIcons.Lock
    ),
    MenuItem(
        name = "Decrypt",
        unselectedIcon = TablerIcons.Key,
        selectedIcon = TablerIcons.Key
    )
)