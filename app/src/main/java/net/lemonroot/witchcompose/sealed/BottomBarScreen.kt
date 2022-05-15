package net.lemonroot.witchcompose.sealed

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Chat
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Public
import androidx.compose.ui.graphics.vector.ImageVector
import net.lemonroot.witchcompose.R
import net.lemonroot.witchcompose.navigation.Screen

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector,
    @StringRes val resourceId: Int

) {
    object Home: BottomBarScreen(
        "home",
        "Home",
        Icons.Rounded.Home,
        R.string.title
    )
    object Profile: BottomBarScreen(
        "profile",
        "Profile",
        Icons.Rounded.Person,
        R.string.profile
    )
    object Explore: BottomBarScreen(
        "explore",
        "Explore",
        Icons.Rounded.Public,
        R.string.explore
    )
    object Social: BottomBarScreen(
        "social",
        "Social",
        Icons.Rounded.Chat,
        R.string.social
    )
}
