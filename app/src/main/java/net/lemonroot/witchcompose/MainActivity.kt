package net.lemonroot.witchcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import net.lemonroot.witchcompose.navigation.AppBar
import net.lemonroot.witchcompose.ui.theme.WitchComposeTheme

class MainActivity : ComponentActivity() {
    var appBar = AppBar()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appBar = AppBar()

        setContent {
            ScaffoldCompose(appBar)
        }
    }
}
@Composable
fun ScaffoldCompose(appBar: AppBar){
    Scaffold(
        topBar = {appBar.TopAppBar("Home", null)}
    ){}
}

@Preview
@Composable
fun Preview(){
    ScaffoldCompose(appBar = AppBar())
}