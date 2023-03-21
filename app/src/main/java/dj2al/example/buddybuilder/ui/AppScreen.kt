package dj2al.example.buddybuilder.ui

sealed class AppScreen(route : String) {

    object Auth : AppScreen("nav_auth") {
        object Login : AppScreen("login")
        object Signup : AppScreen("signup")
    }

    object Home: AppScreen("home")
    object Sports: AppScreen("sports")
    object Events: AppScreen("events")
    object User: AppScreen("user")
}