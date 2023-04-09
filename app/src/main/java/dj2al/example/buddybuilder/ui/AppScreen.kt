package dj2al.example.buddybuilder.ui

import androidx.annotation.StringRes
import dj2al.example.buddybuilder.R

sealed class AppScreen(val route : String, @StringRes val resourceId: Int, val icon : Int) {

    object Auth : AppScreen("nav_auth", R.string.app_name, R.drawable.ic_home) {
        object Login : AppScreen("login", R.string.login, R.drawable.ic_home)
        object Signup : AppScreen("signup", R.string.signup, R.drawable.ic_home)
        object Logout : AppScreen("logout", R.string.logout, R.drawable.ic_logout)
    }

    object Home: AppScreen("home",R.string.home, R.drawable.ic_home)
    object MySports: AppScreen("my_sports", R.string.my_sport_title, R.drawable.ic_sports)
    object Sports: AppScreen("sports", R.string.sport_title, R.drawable.ic_sports)
    object Events: AppScreen("nav_events", R.string.app_name, R.drawable.ic_agenda) {
        object Home: AppScreen("home", R.string.app_name, R.drawable.ic_agenda)
        object Add: AppScreen("add_event", R.string.app_name, R.drawable.ic_agenda)
    }
    object User: AppScreen("user", R.string.user, R.drawable.ic_account_box)
}

