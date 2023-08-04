package com.example.jetpack_compose_demo.ui.navigationDrawer.model

import androidx.compose.ui.graphics.vector.ImageVector

data class MenuItem(
    val route: String,
    val title: String,
    val icon: ImageVector,
    val isSelected: Boolean,
    val navigate: () -> Unit = {},
)
