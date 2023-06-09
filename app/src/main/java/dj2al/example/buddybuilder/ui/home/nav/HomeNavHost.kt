package dj2al.example.buddybuilder.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import dj2al.example.buddybuilder.ui.AppScreen
import dj2al.example.buddybuilder.ui.commons.AppBar
import dj2al.example.buddybuilder.ui.home.dashboard.DashboardScreen
import dj2al.example.buddybuilder.ui.home.sports.SportsScreen
import dj2al.example.buddybuilder.ui.home.user.UserScreen
import dj2al.example.buddybuilder.ui.theme.BuddyBuilderTheme
import dj2al.example.buddybuilder.R
import dj2al.example.buddybuilder.data.models.Event
import dj2al.example.buddybuilder.ui.auth.Logout
import dj2al.example.buddybuilder.ui.home.events.AddEventScreen
import dj2al.example.buddybuilder.ui.home.events.EventsScreen
import dj2al.example.buddybuilder.ui.home.sports.MySportsScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeNavHost() {
    val context = LocalContext.current
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val customTitle = remember {
        mutableStateOf("")
    }

    val MenuItems = listOf<AppScreen>(
        AppScreen.Home,
        AppScreen.MySports,
        AppScreen.User,
        AppScreen.Auth.Logout
    )
    val selectedItem = remember { mutableStateOf(MenuItems[0])}

        ModalNavigationDrawer(
            drawerState = drawerState,
            gesturesEnabled = false,
            drawerContent = {
                ModalDrawerSheet(modifier = Modifier.width(200.dp)) {
                    Spacer(Modifier.height(12.dp))
                    MenuItems.forEach { item ->
                        NavigationDrawerItem(
                            icon = {
                                Icon(
                                    painter = painterResource(id = item.icon),
                                    contentDescription = "")
                            },
                            label = { Text(text = stringResource(id = item.resourceId)) },
                            selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                            onClick = {
                                scope.launch { drawerState.close() }
                                navController.navigate(item.route) {
                                    // Pop up to the start destination of the graph to
                                    // avoid building up a large stack of destinations
                                    // on the back stack as users select items
                                    popUpTo(navController.graph.findStartDestination().id)
                                    // Avoid multiple copies of the same destination when
                                    // reselecting the same item
                                    launchSingleTop = true
                                    // Restore state when reselecting a previously selected item
                                    restoreState = true
                                }
                                selectedItem.value = item
                                println(navController.backQueue.map { it -> it.destination })
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
                        if(currentDestination?.hierarchy?.any { current -> MenuItems.map { it -> it.route }.contains(current.route)} == true)
                            AppBar(
                                text = stringResource(id = selectedItem.value.resourceId),
                                NavIcon = R.drawable.ic_menu,
                                onNavigationClick = {scope.launch { drawerState.open() }}
                            )
                        else
                            AppBar(
                                text = customTitle.value,
                                NavIcon = R.drawable.ic_arrow_back,
                                onNavigationClick = {navController.popBackStack()}
                            )
                    },
                    content = {
                        Surface(modifier = Modifier.padding(it)) {
                            NavHost(
                                navController = navController,
                                startDestination = AppScreen.Home.route
                            ) {
                                composable(route = AppScreen.Home.route) {
                                    DashboardScreen(hiltViewModel(), navController)
                                }
                                composable(route = AppScreen.MySports.route) {
                                    MySportsScreen(hiltViewModel(), navController)
                                }
                                composable(route = AppScreen.Sports.route) {
                                    customTitle.value = stringResource(id = AppScreen.Sports.resourceId)
                                    SportsScreen(hiltViewModel())
                                }
                                composable(route = AppScreen.User.route) {
                                    UserScreen(hiltViewModel())
                                }
                                composable(route = AppScreen.Auth.Logout.route) {
                                    Logout(hiltViewModel(), context)
                                }

                                navigation(
                                    startDestination = AppScreen.Events.Home.route + "/{sportName}/{sportId}",
                                    route = AppScreen.Events.route + "/{sportName}/{sportId}")
                                {

                                    composable(route = AppScreen.Events.Home.route + "/{sportName}/{sportId}") {
                                        customTitle.value = it.arguments?.getString("sportName").toString()
                                        EventsScreen(hiltViewModel(), navController)
                                    }
                                    composable(route = AppScreen.Events.Add.route + "/{sportName}/{sportId}") {
                                        customTitle.value =  stringResource(id = R.string.add_eve) + " " + "${it.arguments?.getString("sportName").toString()}"
                                        AddEventScreen(hiltViewModel(), navController)
                                    }
                                    composable(
                                        route = AppScreen.Events.Edit.route + "/{sportName}/{sportId}/{event}"
                                    ) {
                                        customTitle.value =  stringResource(id = R.string.edit_my_event)
                                        AddEventScreen(hiltViewModel(), navController)
                                    }
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