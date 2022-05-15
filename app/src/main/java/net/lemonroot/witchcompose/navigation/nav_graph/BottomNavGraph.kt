package net.lemonroot.witchcompose.navigation.nav_graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import net.lemonroot.witchcompose.navigation.BOT_ROUTE
import net.lemonroot.witchcompose.navigation.Screen
import net.lemonroot.witchcompose.screens.*

fun NavGraphBuilder.bottomNavGraph(navController: NavHostController){
    // ROOT NAV
    navigation(
        startDestination = Screen.Home.route,
        route = BOT_ROUTE
    ){
        composable(
            route = Screen.Home.route){
            Home(navController)
        }
        composable(
            route = Screen.Profile.route){
            Profile(navController)
        }
        composable(
            route = Screen.Explore.route){
            Explore(navController)
        }
        composable(
            route = Screen.Social.route){
            Social(navController)
        }
    }
}