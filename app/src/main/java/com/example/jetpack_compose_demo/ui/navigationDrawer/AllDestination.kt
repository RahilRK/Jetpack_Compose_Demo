package com.example.jetpack_compose_demo.ui.navigationDrawer

import androidx.navigation.NavController
import com.example.jetpack_compose_demo.ui.navigationDrawer.AllDestination.HOME
import com.example.jetpack_compose_demo.ui.navigationDrawer.AllDestination.SETTINGS

object AllDestination {
    const val HOME = "Home"
    const val PROFILE = "Profile"
    const val SETTINGS = "Settings"
}

class AppNavigationAction(private val navController: NavController) {

    fun navigateToHome() {
        navController.navigate(HOME) {
            popUpTo(HOME)
        }
    }

    fun navigateToSettings() {
        navController.navigate(SETTINGS) {
            launchSingleTop = true
            restoreState = true
        }
    }
}