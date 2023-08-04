package com.example.jetpack_compose_demo.ui.navigationBottomBar

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jetpack_compose_demo.ui.navigationDrawer.AllDestination
import com.example.jetpack_compose_demo.ui.navigationDrawer.NavAppBar
import com.example.jetpack_compose_demo.ui.navigationDrawer.home.HomeScreen
import com.example.jetpack_compose_demo.ui.navigationDrawer.model.MenuItem
import com.example.jetpack_compose_demo.ui.navigationDrawer.profile.ProfileScreen
import com.example.jetpack_compose_demo.ui.navigationDrawer.settings.SettingsScreen

@Composable
fun NavigationBottomBar() {

    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentNavBackStackEntry?.destination?.route ?: AllDestination.HOME

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = currentRoute) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                ),
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
            ) {
                menuItemList().forEachIndexed { i, model ->

                    val selected = model.route == backStackEntry?.destination?.route

                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            navController.navigate(model.route) {
                                navController.graph.startDestinationRoute?.let { route ->
                                    Log.d("BottomNavigationBar", "startDestinationRoute: $route")
                                    popUpTo(route) {
                                        saveState = true
                                    }
                                }

                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        label = {
                            Text(
                                text = model.title,
                                fontWeight = FontWeight.SemiBold,
                            )
                        },
                        icon = {
                            Icon(
                                imageVector = model.icon,
                                contentDescription = "${model.title} Icon",
                            )
                        }
                    )
                }
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = AllDestination.HOME,
            modifier = Modifier.padding(it)
        ) {

            composable(AllDestination.HOME) {
                HomeScreen()
            }

            composable(AllDestination.PROFILE) {
                ProfileScreen()
            }

            composable(AllDestination.SETTINGS) {
                SettingsScreen()
            }
        }
    }

}


fun menuItemList(): MutableList<MenuItem> {
    val list = mutableListOf<MenuItem>()
    list.add(
        MenuItem(
            AllDestination.HOME, "Home", Icons.Filled.Home, true,
            navigate = {


            })
    )
    list.add(
        MenuItem(
            AllDestination.PROFILE, "Profile", Icons.Filled.AccountCircle, true,
            navigate = {


            })
    )
    list.add(
        MenuItem(
            AllDestination.SETTINGS, "Settings", Icons.Filled.Settings, false,
            navigate = {


            })
    )
    return list
}