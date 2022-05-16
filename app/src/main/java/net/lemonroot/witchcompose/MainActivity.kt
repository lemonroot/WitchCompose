package net.lemonroot.witchcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import net.lemonroot.witchcompose.navigation.Screen
import net.lemonroot.witchcompose.navigation.nav_graph.SetupNavGraph
import net.lemonroot.witchcompose.screens.AppBarScreen
import net.lemonroot.witchcompose.sealed.BottomBarScreen
import net.lemonroot.witchcompose.ui.theme.WitchComposeTheme

class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WitchComposeTheme {
                // Setup nav controller
                navController = rememberNavController()
                SetupNavGraph(navController)

                ToggleBars()
            }
        }
    }

    @Composable
    private fun ToggleBars() {
        // Subscribe to navBackStackEntry, required to get current route
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        // Hide appbars on Title Screen
        when (navBackStackEntry?.destination?.route) {
            "title" -> {
                // Hide bars on Title Screen
            }
            else -> {
                // Show bars
                AppBarScreen(navController)
            }
        }
    }
}