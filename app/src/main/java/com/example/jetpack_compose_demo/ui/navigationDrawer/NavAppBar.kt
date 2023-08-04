package com.example.jetpack_compose_demo.ui.navigationDrawer

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable

@Composable
fun NavAppBar(
    currentRoute: String,
    onNavigationIconClick: () -> Unit
) {
    TopAppBar(
        title = { Text(text = currentRoute) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ), navigationIcon = {
            IconButton(onClick = { onNavigationIconClick() }) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "")
            }
        }
    )
}