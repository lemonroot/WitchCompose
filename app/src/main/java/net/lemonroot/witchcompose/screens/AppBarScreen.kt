package net.lemonroot.witchcompose.screens

import android.graphics.Color
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.*
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset.Companion.Unspecified
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.Unspecified
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import net.lemonroot.witchcompose.sealed.BottomBarScreen
import net.lemonroot.witchcompose.ui.theme.*
import java.lang.reflect.Modifier

/* VARIOUS FUNCTIONS TO SETUP NAVIGATION BARS */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarScreen(navController: NavHostController){
    WitchComposeTheme {
        // Setup bottom navigation controller
        Scaffold(
            topBar = {TopBar("Test", "Test2")},
            // Setup bottom nav bar
            bottomBar = { BottomBar(navController = navController) }
        ) {
        }
    }
}

@Composable
fun TopBar(title: String, subTitle: String?){
    val context = LocalContext.current

    CenterAlignedTopAppBar (
        title = {
            if (subTitle != null){
                Column (horizontalAlignment = Alignment.CenterHorizontally) {
                    androidx.compose.material.Text(
                        text = title,
                        color = md_theme_light_onPrimary,
                        fontSize = 20.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis)
                    androidx.compose.material.Text(
                        text = subTitle,
                        fontSize = 10.sp,
                        color = md_theme_dark_onBackground,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis)
                }
            } else {
                androidx.compose.material.Text(
                    text = title,
                    color = md_theme_light_onPrimary,
                    fontSize = 20.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

        },
        navigationIcon = {
            androidx.compose.material.IconButton(
                onClick = {
                    Toast.makeText(context, "Menu", Toast.LENGTH_SHORT).show()
                }) {
                androidx.compose.material.Icon(
                    Icons.Rounded.Menu,
                    null,
                    tint = md_theme_light_secondaryContainer
                )
            }
        },
        actions = {},
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = md_theme_light_primary
        )
    )
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        // Call screens from the BottomBarScreen class
        BottomBarScreen.Home,
        BottomBarScreen.Profile,
        BottomBarScreen.Explore,
        BottomBarScreen.Social
    )
    NavigationBar(
        containerColor = md_theme_light_primary,
        contentColor = md_theme_light_onPrimary,
        tonalElevation = 3.dp
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        PopulateBotBar(screens, currentDestination, navController)
    }
}

@Composable
private fun RowScope.PopulateBotBar(
    screens: List<BottomBarScreen>,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    screens.forEach { screen ->
        NavigationBarItem(
            icon = { Icon(screen.icon, contentDescription = null) },
            label = { Text(stringResource(screen.resourceId)) },
            selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = md_theme_light_secondaryContainer,
                unselectedIconColor = md_theme_light_onPrimary,
                indicatorColor = md_theme_dark_primaryContainer
            ),
            onClick = {
                navController.navigate(screen.route) {
                    // Pop up to the start destination of the graph to
                    // avoid building up a large stack of destinations
                    // on the back stack as users select items
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    // Avoid multiple copies of the same destination when
                    // reselecting the same item
                    launchSingleTop = true
                    // Restore state when reselecting a previously selected item
                    restoreState = true
                }
            }
        )
    }
}