package net.lemonroot.witchcompose.navigation

import androidx.annotation.StringRes
import net.lemonroot.witchcompose.R

const val ROOT_ROUTE = "root"
const val HOME_ROUTE = "home"
const val AUTH_ROUTE = "authentication"
const val BOT_ROUTE = "bottom"


sealed class Screen(val route: String, @StringRes val resourceId: Int){
    object Title: Screen(route = "title", R.string.title)
    object Auth: Screen(route = "auth", R.string.auth)
    object Home: Screen(route = "home", R.string.home)
    object Profile: Screen(route = "profile", R.string.profile)
    object Explore: Screen(route = "explore", R.string.explore)
    object Social: Screen(route = "social", R.string.social)
}
