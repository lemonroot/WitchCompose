package net.lemonroot.witchcompose.navigation.nav_graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import net.lemonroot.witchcompose.navigation.ROOT_ROUTE
import net.lemonroot.witchcompose.navigation.Screen
import net.lemonroot.witchcompose.screens.Title
import net.lemonroot.witchcompose.screens.Home

@Composable
fun SetupNavGraph(navController: NavHostController){
    NavHost(
        // ROOT NAV
        navController = navController,
        startDestination = Screen.Title.route,
        route = ROOT_ROUTE
    ){
        composable(Screen.Title.route) {Title(navController)}
        authNavGraph(navController)
        bottomNavGraph(navController)
    }
}
