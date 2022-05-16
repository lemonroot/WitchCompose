package net.lemonroot.witchcompose.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import net.lemonroot.witchcompose.sealed.BottomBarScreen
import net.lemonroot.witchcompose.ui.theme.*

/* VARIOUS FUNCTIONS TO SETUP NAVIGATION BARS */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarScreen(navController: NavHostController){
    // Scope & state for NavDrawer
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // icons
    val items = listOf(Icons.Default.Favorite, Icons.Default.Face, Icons.Default.Email)
    val selectedItem = remember{mutableStateOf(items[0])}

    ModalNavigationDrawer(
        drawerTonalElevation = 1.dp,
        drawerContent = {
            items.forEach { item ->
                NavigationDrawerItem(
                    icon = { Icon(item, contentDescription = null) },
                    label = { Text(item.name) },
                    selected = item == selectedItem.value,
                    onClick = {
                        scope.launch { drawerState.close() }
                        selectedItem.value = item
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
            }
        },
        modifier = Modifier,
        drawerState = drawerState
    )
    {
        Scaffold(
            topBar = { TopBar("Test", "Test2", scope, drawerState) },
            content = {},
            // Setup bottom nav bar
            bottomBar = { BottomBar(navController, scope, drawerState) },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(title: String, subTitle: String?, scope: CoroutineScope, drawerState: DrawerState){
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
                    if(drawerState.isClosed) {
                        scope.launch{drawerState.open()}}
                    else {scope.launch{drawerState.close()}}
                    //Toast.makeText(context, "Menu", Toast.LENGTH_SHORT).show()
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBar(navController: NavHostController, scope: CoroutineScope, drawerState: DrawerState) {
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalNavDrawer(scope: CoroutineScope, drawerState: DrawerState) {
    var text by remember{
        mutableStateOf("")
    }

}