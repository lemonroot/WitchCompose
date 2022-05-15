package net.lemonroot.witchcompose.navigation.nav_graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import net.lemonroot.witchcompose.navigation.AUTH_ROUTE
import net.lemonroot.witchcompose.navigation.Screen
import net.lemonroot.witchcompose.screens.Auth

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    // AUTHENTICATION NAV
        navigation(
            startDestination = Screen.Auth.route,
            route = AUTH_ROUTE
        ){
            composable(
                route = Screen.Auth.route){
                Auth(navController)
            }
        }
}