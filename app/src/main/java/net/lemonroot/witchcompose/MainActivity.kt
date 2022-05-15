package net.lemonroot.witchcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
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
                // Setup nav controller. App only needs ONE "rememberNavController()" call!!!
                navController = rememberNavController()
                SetupNavGraph(navController)
            }
        }
    }
}