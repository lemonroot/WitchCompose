package net.lemonroot.witchcompose.navigation

import android.graphics.Color
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.TopAppBarDefaults.centerAlignedTopAppBarColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import net.lemonroot.witchcompose.ui.theme.*

class AppBar : ComponentActivity() {

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
                    Icon(Icons.Default.Menu, null, tint = md_theme_light_secondaryContainer)
                }
            },
            actions = {},
            colors = centerAlignedTopAppBarColors(
                containerColor = md_theme_light_primary,
                navigationIconContentColor = md_theme_light_secondaryContainer,
                titleContentColor = md_theme_light_onPrimary,
                actionIconContentColor = md_theme_light_onPrimary
            )
        )
    }

    @Preview
    @Composable
    fun Preview(){
        TopAppBar(title = "Test", subTitle = "test")
    }
}