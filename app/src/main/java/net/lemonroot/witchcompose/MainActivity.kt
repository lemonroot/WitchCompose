package net.lemonroot.witchcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import net.lemonroot.witchcompose.navigation.AppBar
import net.lemonroot.witchcompose.navigation.Screen
import net.lemonroot.witchcompose.navigation.nav_graph.SetupNavGraph
import net.lemonroot.witchcompose.screens.Profile
import net.lemonroot.witchcompose.sealed.BottomBarScreen
import net.lemonroot.witchcompose.ui.theme.WitchComposeTheme

class MainActivity : ComponentActivity() {
    //var appBar = AppBar()

    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //appBar = AppBar()

        setContent {
            WitchComposeTheme {
                // Setup nav controller
                navController = rememberNavController()
                SetupNavGraph(navController)
                ScaffoldCompose(navController)
            }
        }
    }
}
@Composable
fun ScaffoldCompose(navController: NavHostController) {
    val screens = listOf(
        // Call screens from the BottomBarScreen class
        BottomBarScreen.Home,
        BottomBarScreen.Profile,
        BottomBarScreen.Explore,
        BottomBarScreen.Social
    )
    Scaffold(
        //topBar = {appBar.TopAppBar("Home", null)},
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                screens.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(screen.icon, contentDescription = null) },
                        label = { Text(stringResource(screen.resourceId)) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
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
        }
    ) {}
}