package net.lemonroot.witchcompose.navigation

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.TopAppBarDefaults.centerAlignedTopAppBarColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.lemonroot.witchcompose.ui.theme.*
import net.lemonroot.witchcompose.R

class AppBar : ComponentActivity() {

    val MyAppIcons = Icons.Rounded

    @Composable
    fun TopAppBar(title: String, subTitle: String?) {
        val context = LocalContext.current

        CenterAlignedTopAppBar(
            title = {
                if (subTitle != null){
                Column (horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = title,
                        color = md_theme_light_onPrimary,
                        fontSize = 20.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis)
                    Text(
                        text = subTitle,
                        fontSize = 10.sp,
                        color = md_theme_dark_onBackground,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis)
                }} else {
                    Text(
                        text = title,
                        color = md_theme_light_onPrimary,
                        fontSize = 20.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis)
                }

            },
            navigationIcon = {
                IconButton(
                    onClick = {
                    Toast.makeText(context, "Menu", Toast.LENGTH_SHORT).show()
                }){
                    Icon(MyAppIcons.Menu, null, tint = md_theme_light_secondaryContainer)
                }
            },
            actions = {},
            colors = centerAlignedTopAppBarColors(
                containerColor = md_theme_light_primary
            )
        )
    }

    @Composable
    fun BottomAppBar() {
        var selectedItem by remember { mutableStateOf(0) }

        NavigationBar(
            containerColor = md_theme_light_primary,
            tonalElevation = 3.dp
        ) {

            NavigationBarItem(
                icon = { Icon(MyAppIcons.Home, contentDescription = "Home navigation", tint = md_theme_light_onPrimary) },
                selected = true,
                onClick = {})
            NavigationBarItem(
                icon = { Icon(MyAppIcons.Person, contentDescription = "Profile navigation", tint = md_theme_light_onPrimary) },
                selected = false,
                onClick = {})
            NavigationBarItem(
                icon = { Icon(painterResource(R.drawable.ic_globe), contentDescription = "Explore navigation", tint = md_theme_light_onPrimary) },
                selected = false,
                onClick = {})
            NavigationBarItem(
                icon = { Icon(MyAppIcons.Forum, contentDescription = "Social navigation", tint = md_theme_light_onPrimary) },
                selected = false,
                onClick = {})
        }
    }

    @Preview
    @Composable
    fun PreviewTop(){
        TopAppBar(title = "Test", subTitle = "test")
    }

    @Preview
    @Composable
    fun PreviewBot(){
        BottomAppBar()
    }
}