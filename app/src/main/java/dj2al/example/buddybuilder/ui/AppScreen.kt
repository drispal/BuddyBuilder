package dj2al.example.buddybuilder.ui

import dj2al.example.buddybuilder.R

sealed class AppScreen(val route : String) {

    object Auth : AppScreen("nav_auth") {
        object Login : AppScreen("login")
        object Signup : AppScreen("signup")
    }

    object Home: AppScreen("home")
    object Sports: AppScreen("sports")
    object Events: AppScreen("events")
    object User: AppScreen("user")
}

enum class MenuItem(
    val index: Int,
    val title: String,
    val screen: AppScreen,
    val contentDescription: String,
    val icon: Int
) {
    Home (
        index = 0,
        title = "Home",
        screen = AppScreen.Home,
        contentDescription = "Home",
        icon = R.drawable.home
    ),
    Sports (
        index = 1,
        title = "Sports",
        screen = AppScreen.Sports,
        contentDescription = "All sports",
        icon = R.drawable.sports_soccer
    ),
    User(
        index = 2,
        title = "User",
        screen = AppScreen.User,
        contentDescription = "User profile",
        icon = R.drawable.account_circle
    )
}
