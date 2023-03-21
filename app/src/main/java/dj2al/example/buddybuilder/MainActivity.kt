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
import dj2al.example.buddybuilder.ui.theme.BuddyBuilderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BuddyBuilderTheme {
                Homescreen(name = "Android")
            }
        }
    }
}

@Composable
fun Homescreen(name: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Helllo $name!")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BuddyBuilderTheme {
        Homescreen(name = "Android")
    }
}