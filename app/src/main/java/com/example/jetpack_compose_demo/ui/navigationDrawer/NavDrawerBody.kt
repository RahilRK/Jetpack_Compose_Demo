package com.example.jetpack_compose_demo.ui.navigationDrawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_demo.R
import com.example.jetpack_compose_demo.ui.navigationDrawer.AllDestination.HOME
import com.example.jetpack_compose_demo.ui.navigationDrawer.AllDestination.SETTINGS
import com.example.jetpack_compose_demo.ui.navigationDrawer.model.MenuItem

@Composable
fun NavDrawerBody(
    items: MutableList<MenuItem> = menuItemList(),
    currentRoute: String = HOME,
    modifier: Modifier = Modifier,
    closeDrawer: () -> Unit = {},
    appNavigationAction: AppNavigationAction
) {
    ModalDrawerSheet(modifier = Modifier) {

        DrawerHeader(modifier)

        Spacer(modifier = Modifier.padding(4.dp))

        for (model in items) {
            NavigationDrawerItem(
                label = {
                    Text(
                        text = model.title,
                        style = MaterialTheme.typography.labelSmall
                    )
                }, selected = model.route == currentRoute, onClick = {

                    if (model.route == HOME) {
                        appNavigationAction.navigateToHome()
                    } else if (model.route == SETTINGS) {
                        appNavigationAction.navigateToSettings()
                    }
                    closeDrawer()
                }, icon = {
                    Icon(
                        imageVector = model.icon,
                        contentDescription = null
                    )
                }, shape = MaterialTheme.shapes.small
            )

        }
    }
}

fun menuItemList(): MutableList<MenuItem> {
    val list = mutableListOf<MenuItem>()
    list.add(
        MenuItem(HOME, "Home", Icons.Filled.Home, true,
            navigate = {


            })
    )
    list.add(
        MenuItem(
            SETTINGS, "Settings", Icons.Filled.Settings, false,
            navigate = {


            })
    )
    return list
}

@Composable
fun DrawerHeader(modifier: Modifier) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .background(MaterialTheme.colorScheme.secondary)
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.mom),
            contentScale = ContentScale.Crop,
            contentDescription = "Header Image",
            modifier = modifier
                .size(64.dp)
                .padding(4.dp)
                .clip(CircleShape)
                .border(2.dp, androidx.compose.ui.graphics.Color.LightGray, CircleShape),
        )
        Spacer(modifier = Modifier.padding(4.dp))
        Text(
            text = stringResource(id = R.string.app_name),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimary,
        )

    }
}

/*
@Preview
@Composable
fun DrawerHeaderPreview() {
    AppDrawer(modifier = Modifier, appNavigationAction = AppNavigationAction())
}*/
