package dj2al.example.buddybuilder.ui.home.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.constraintlayout.compose.ConstraintLayout
import dj2al.example.buddybuilder.data.models.Event
import dj2al.example.buddybuilder.data.models.Sport

@Composable
fun DashboardScreen(viewModel: DashboardViewModel) {
    val context = LocalContext.current
    Column() {
        DashboardSportsData(subscribedSports = listOf(), dashboardViewModel = viewModel)
        DashboardEventsData(incommingEvents = listOf(), dashboardViewModel = viewModel)
    }

}

@Composable
fun DashboardSportsData(subscribedSports : List<Sport>, dashboardViewModel: DashboardViewModel) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        Text(text = "Dashboard Sports")
    }
}

@Composable
fun DashboardEventsData(incommingEvents : List<Event>, dashboardViewModel: DashboardViewModel) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        Text(text = "Dashboard Events")
    }
}