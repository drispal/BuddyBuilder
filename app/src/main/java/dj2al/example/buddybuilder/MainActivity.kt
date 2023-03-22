package dj2al.example.buddybuilder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dj2al.example.buddybuilder.data.home.AppRepositorys
import dj2al.example.buddybuilder.ui.commons.TopBar
import dj2al.example.buddybuilder.ui.home.HomeNavHost
import dj2al.example.buddybuilder.ui.theme.BuddyBuilderTheme

class MainActivity : ComponentActivity() {

    lateinit var repositorys: AppRepositorys

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repositorys = AppRepositorys()
        setContent {
            BuddyBuilderTheme {
                TopBar(title = R.string.app_name, icon = R.drawable.home, content = { HomeNavHost(
                    innerPadding = it
                )
                })
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BuddyBuilderTheme {
        TopBar(title = R.string.app_name, icon = R.drawable.home, content = { HomeNavHost(
            innerPadding = it
        )
        })
    }
}