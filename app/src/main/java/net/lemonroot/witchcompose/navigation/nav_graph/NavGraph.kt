package net.lemonroot.witchcompose.navigation.nav_graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.lemonroot.witchcompose.navigation.HOME_ROUTE
import net.lemonroot.witchcompose.navigation.ROOT_ROUTE
import net.lemonroot.witchcompose.navigation.Screen

@Composable
fun SetupNavGraph(navController: NavHostController){
    NavHost(
        // ROOT NAV
        navController = navController,
        startDestination = Screen.Home.route
    ){
        composable(Screen.Home.route) {Screen.Home}
        authNavGraph(navController)
        bottomNavGraph(navController)
    }
}
