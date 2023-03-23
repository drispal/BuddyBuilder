package dj2al.example.buddybuilder.ui.home.dashboard

import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import dj2al.example.buddybuilder.R
import dj2al.example.buddybuilder.data.models.Event
import dj2al.example.buddybuilder.data.models.Sport
import dj2al.example.buddybuilder.ui.commons.SportCard
import dj2al.example.buddybuilder.ui.commons.myMutableList
import dj2al.example.buddybuilder.ui.material3.BottomSheetScaffold
import dj2al.example.buddybuilder.ui.material3.rememberBottomSheetScaffoldState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(viewModel: DashboardViewModel) {
    val context = LocalContext.current
    Column() {
        val contextForToast = LocalContext.current.applicationContext

        val coroutineScope = rememberCoroutineScope()

        val scaffoldState = rememberBottomSheetScaffoldState()

        BottomSheetScaffold(
            scaffoldState = scaffoldState,
            sheetPeekHeight = 56.dp,
            sheetContent = {

                Box(
                    modifier = Modifier
                        .height(56.dp)
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.primary),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Comming soon",
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
                    // remaining items
                DashboardEventsData(incommingEvents = listOf(), dashboardViewModel = viewModel)
            },
            ) {
            // app UI
            DashboardSportsData(subscribedSports = listOf(), dashboardViewModel = viewModel)
        }
    }
}

@Composable
fun DashboardSportsData(subscribedSports : List<Sport>, dashboardViewModel: DashboardViewModel) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            content = {
                items(count = 10) {
                    SportCard(
                        Sport(
                            "PingPong",
                            R.drawable.sports_pingpong,
                            myMutableList
                        ),
                        false,
                        {
                            println("hallo")
                        }
                    )
                }
                item {
                    Button(onClick = { /*TODO*/ }) {
                        Icon(painter = painterResource(id = R.drawable.arrow_back), contentDescription = "")
                    }
                }
                item {
                    Spacer(modifier = Modifier.size(300.dp))
                }
            })
    }
}

@Composable
fun DashboardEventsData(incommingEvents : List<Event>, dashboardViewModel: DashboardViewModel) {
    ConstraintLayout(modifier = Modifier.heightIn(min = 400.dp)) {
        Text(text = "Dashboard Events")
    }
}