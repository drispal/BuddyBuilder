package dj2al.example.buddybuilder.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dj2al.example.buddybuilder.ui.AppScreen
import dj2al.example.buddybuilder.ui.MenuItem
import dj2al.example.buddybuilder.ui.commons.AppBar
import dj2al.example.buddybuilder.ui.home.dashboard.DashboardScreen
import dj2al.example.buddybuilder.ui.home.sports.SportsScreen
import dj2al.example.buddybuilder.ui.home.user.UserScreen
import dj2al.example.buddybuilder.ui.theme.BuddyBuilderTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeNavHost() {
    val navController = rememberNavController()

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val selectedItem = remember { mutableStateOf(MenuItem.values()[0])}
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet() {
                Spacer(Modifier.height(12.dp))
                MenuItem.values().forEach { item ->
                    NavigationDrawerItem(
                        icon = {
                            Icon(
                                painter = painterResource(id = item.icon),
                                contentDescription = "")
                        },
                        label = { Text(text = item.title) },
                        selected = item.index == selectedItem.value.index,
                        onClick = {
                            scope.launch { drawerState.close() }
                            navController.navigate(item.screen.route)
                            selectedItem.value = item
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        },
        content = {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    AppBar(
                        onNavigationIconClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }
                    )
                },
                content = {
                    Surface(modifier = Modifier.padding(it)) {
                        NavHost(
                            navController = navController,
                            startDestination = AppScreen.Home.route
                        ) {
                            composable(route = AppScreen.Home.route) {
                                DashboardScreen(hiltViewModel())
                            }
                            composable(route = AppScreen.Sports.route) {
                                SportsScreen(hiltViewModel())
                            }
                            composable(route = AppScreen.User.route) {
                                UserScreen(hiltViewModel())
                            }
                        }
                    }
                },
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun NavHostPreview() {
    BuddyBuilderTheme {
        HomeNavHost()
    }
}