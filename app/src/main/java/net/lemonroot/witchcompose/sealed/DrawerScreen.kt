package net.lemonroot.witchcompose.sealed

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.ui.graphics.vector.ImageVector
import net.lemonroot.witchcompose.R
import net.lemonroot.witchcompose.navigation.Screen

sealed class DrawerScreen(
    val route: String,
    val icon: ImageVector,
    @StringRes val resourceId: Int

    ){
    object Home: DrawerScreen(
        Screen.Home.route,
        Icons.Rounded.Home,
        R.string.title
    )
    object News: DrawerScreen(
        Screen.Home.route,
        Icons.Rounded.Home,
        R.string.title
    )
    object Settings: DrawerScreen(
        Screen.Home.route,
        Icons.Rounded.Home,
        R.string.title
    )
}
