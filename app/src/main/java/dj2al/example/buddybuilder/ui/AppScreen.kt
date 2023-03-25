package dj2al.example.buddybuilder.ui

import androidx.annotation.StringRes
import dj2al.example.buddybuilder.R

sealed class AppScreen(val route : String, @StringRes val resourceId: Int, val icon : Int) {

    object Auth : AppScreen("nav_auth", R.string.app_name, R.drawable.ic_home) {
        object Login : AppScreen("login", R.string.app_name, R.drawable.ic_home)
        object Signup : AppScreen("signup", R.string.app_name, R.drawable.ic_home)
    }

    object Home: AppScreen("home",R.string.home, R.drawable.ic_home)
    object Sports: AppScreen("sports", R.string.app_name, R.drawable.sports_soccer)
    object Events: AppScreen("events", R.string.app_name, R.drawable.ic_agenda)
    object User: AppScreen("user", R.string.user, R.drawable.ic_account_box)
}

