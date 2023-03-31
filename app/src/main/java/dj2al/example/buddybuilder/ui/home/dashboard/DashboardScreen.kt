package dj2al.example.buddybuilder.ui.home.dashboard

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import dj2al.example.buddybuilder.R
import dj2al.example.buddybuilder.data.Resource
import dj2al.example.buddybuilder.data.models.Event
import dj2al.example.buddybuilder.data.models.Level
import dj2al.example.buddybuilder.data.models.Sport
import dj2al.example.buddybuilder.ui.AppScreen
import dj2al.example.buddybuilder.ui.commons.FullScreenProgressbar
import dj2al.example.buddybuilder.ui.commons.SmallEventCard
import dj2al.example.buddybuilder.ui.commons.SportCard
import dj2al.example.buddybuilder.ui.material3.BottomSheetScaffold
import dj2al.example.buddybuilder.ui.material3.rememberBottomSheetScaffoldState
import dj2al.example.buddybuilder.ui.theme.BuddyBuilderTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(viewModel: DashboardViewModel, navController: NavController) {
    val context = LocalContext.current
    Column() {

        val scaffoldState = rememberBottomSheetScaffoldState()

        val user = viewModel.user.collectAsState()

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
                        text = "Coming soon",
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
                // remaining items
                user.value?.let {u ->
                    when (u) {
                        is Resource.Failure -> {
                            Toast.makeText(context, u.exception.message, Toast.LENGTH_SHORT).show()
                        }
                        Resource.Loading -> {
                            FullScreenProgressbar()
                        }
                        is Resource.Success -> {
                            val events = viewModel.events.collectAsState()
                            events.value?.let {
                                when (it) {
                                    is Resource.Failure -> {
                                        Toast.makeText(context, it.exception.message, Toast.LENGTH_SHORT).show()
                                    }
                                    Resource.Loading -> {
                                        FullScreenProgressbar()
                                    }
                                    is Resource.Success -> {
                                        DashboardEventsData(incomingEvents = it.result.filter { u.result.subscribedEvents.contains(it.id) }, dashboardViewModel = viewModel, navController = navController)
                                    }
                                }
                            }
                        }
                    }
                }
            },
            ) {
            // app UI
            user.value?.let {u ->
                when (u) {
                    is Resource.Failure -> {
                        Toast.makeText(context, u.exception.message, Toast.LENGTH_SHORT).show()
                    }
                    Resource.Loading -> {
                        FullScreenProgressbar()
                    }
                    is Resource.Success -> {
                        val sports = viewModel.sports.collectAsState()
                        sports.value?.let {
                            when (it) {
                                is Resource.Failure -> {
                                    Toast.makeText(context, it.exception.message, Toast.LENGTH_SHORT).show()
                                }
                                Resource.Loading -> {
                                    FullScreenProgressbar()
                                }
                                is Resource.Success -> {
                                    DashboardSportsData(subscribedSports = it.result.filter { u.result.subscribedSports.contains(it.id) }, dashboardViewModel = viewModel, navController = navController)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DashboardSportsData(subscribedSports : List<Sport>, dashboardViewModel: DashboardViewModel, navController: NavController) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            content = {
                items(subscribedSports) {sport ->
                    SportCard(
                        sport,
                        false,
                        {
                           navController.navigate(AppScreen.Events.route + "/${sport.name}/${sport.id}")
                        }
                    )
                }
                item {
                    Button(onClick = {
                        navController.navigate(AppScreen.Sports.route)}, modifier = Modifier.size(65.dp)) {
                        Icon(painter = painterResource(id = R.drawable.ic_add), contentDescription = "", modifier = Modifier.scale(3f))
                    }
                }
                item {
                    Spacer(modifier = Modifier.size(300.dp))
                }
            })
    }
}

@Composable
fun DashboardEventsData(incomingEvents : List<Event>, dashboardViewModel: DashboardViewModel, navController: NavController) {
    ConstraintLayout(modifier = Modifier
        .heightIn(min = 400.dp, max = 600.dp)
        .fillMaxWidth()) {
        LazyVerticalGrid(
            modifier = Modifier.fillMaxWidth(),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            content = {
                items(incomingEvents) {it ->
                    SmallEventCard(it)
                }
            })
    }
}

@Preview
@Composable
fun PreviewDashBoard() {
    val sport : Boolean = true;
    val sports : List<Sport> = listOf()

    BuddyBuilderTheme() {
        if(sport) {
            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    content = {
                        items(sports) { sport ->
                            SportCard(
                                sport,
                                false,
                                {
                                    //Todo navigate to events
                                }
                            )
                        }
                        item {
                            Button(onClick = {}, modifier = Modifier.size(65.dp)) {
                                Icon(painter = painterResource(id = R.drawable.ic_add), contentDescription = "", modifier = Modifier.scale(3f))
                            }
                        }
                        item {
                            Spacer(modifier = Modifier.size(300.dp))
                        }
                    })
            }
        }
        else {
            ConstraintLayout(modifier = Modifier
                .heightIn(min = 400.dp, max = 600.dp)
                .fillMaxWidth()) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    content = {
                        items(count = 20) {it ->
                            SmallEventCard(Event(
                                "Football",
                                11630,
                                2030,
                                10,
                                30,
                                level = Level.Level2,
                                22,
                                "Beaujoire",
                                "responsable"))
                        }
                    })
            }
        }
    }
}