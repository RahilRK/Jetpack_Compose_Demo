package com.example.jetpack_compose_demo.ui.navigationDrawer

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jetpack_compose_demo.ui.navigationDrawer.AllDestination.HOME
import com.example.jetpack_compose_demo.ui.navigationDrawer.AllDestination.SETTINGS
import com.example.jetpack_compose_demo.ui.navigationDrawer.home.HomeScreen
import com.example.jetpack_compose_demo.ui.navigationDrawer.settings.SettingsScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun NavigationDrawer() {

    val TAG = "NavigationDrawer"

    val navController: NavHostController = rememberNavController()
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    val drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentNavBackStackEntry?.destination?.route ?: HOME
    val appNavigationAction = remember(navController) {
        AppNavigationAction(navController)
    }

    ModalNavigationDrawer(drawerContent = {
        NavDrawerBody(
            currentRoute = currentRoute,
            closeDrawer = { coroutineScope.launch { drawerState.close() } },
            appNavigationAction = appNavigationAction,
            modifier = Modifier
        )
    }, drawerState = drawerState, ) {

        Scaffold(
            topBar = {
                NavAppBar(currentRoute, onNavigationIconClick = {
                    coroutineScope.launch { drawerState.open() }
                })
            }, modifier = Modifier
        ) {
            NavHost(
                navController = navController,
                startDestination = HOME,
                modifier = Modifier.padding(it)
            ) {

                composable(HOME) {
                    HomeScreen()
                }

                composable(SETTINGS) {
                    SettingsScreen()
                }
            }
        }
    }
}