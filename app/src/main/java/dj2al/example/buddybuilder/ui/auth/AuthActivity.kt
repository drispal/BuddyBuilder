package dj2al.example.buddybuilder.ui.auth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dj2al.example.buddybuilder.ui.theme.BuddyBuilderTheme

@AndroidEntryPoint
class AuthActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BuddyBuilderTheme() {
                AuthNavHost(rememberNavController())
            }
        }
    }
}