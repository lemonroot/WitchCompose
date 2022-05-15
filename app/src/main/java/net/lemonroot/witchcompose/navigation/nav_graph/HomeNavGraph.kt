package net.lemonroot.witchcompose.navigation.nav_graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import net.lemonroot.witchcompose.navigation.HOME_ROUTE
import net.lemonroot.witchcompose.navigation.Screen
import net.lemonroot.witchcompose.screens.Home

fun NavGraphBuilder.homeNavGraph(navController: NavHostController) {
    // ROOT NAV
    navigation(
        startDestination = Screen.Home.route,
        route = HOME_ROUTE
    ){
        composable(
            route = Screen.Home.route){
            Home(navController)
        }
    }
}