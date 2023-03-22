package dj2al.example.buddybuilder.ui.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dj2al.example.buddybuilder.ui.AppScreen
import dj2al.example.buddybuilder.ui.home.sports.SportsScreen
import dj2al.example.buddybuilder.ui.home.user.UserScreen

@Composable
fun HomeNavHost(innerPadding : PaddingValues) {
    val navController = rememberNavController()
    Surface (
        modifier = Modifier.padding(innerPadding).fillMaxSize()
    ) {
        NavHost(navController = navController, startDestination = AppScreen.Home.route) {
            composable(route = AppScreen.Home.route) {
                SportsScreen(hiltViewModel())
            }
            composable(route = AppScreen.User.route) {
                UserScreen(hiltViewModel())
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NavHostPreview() {
    HomeNavHost(innerPadding = PaddingValues(2.dp))
}