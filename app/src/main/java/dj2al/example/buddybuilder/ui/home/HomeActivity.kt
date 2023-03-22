package dj2al.example.buddybuilder.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import dj2al.example.buddybuilder.R
import dj2al.example.buddybuilder.ui.commons.TopBar
import dj2al.example.buddybuilder.ui.theme.BuddyBuilderTheme

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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