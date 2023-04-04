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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import dj2al.example.buddybuilder.R
import dj2al.example.buddybuilder.data.Resource
import dj2al.example.buddybuilder.data.models.Event
import dj2al.example.buddybuilder.data.models.Level
import dj2al.example.buddybuilder.data.models.Sport
import dj2al.example.buddybuilder.ui.commons.FullScreenProgressbar
import dj2al.example.buddybuilder.ui.commons.RegularEventCard
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

        val myEvents = viewModel.myEvents.collectAsState()
        val events = viewModel.events.collectAsState()

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
                        text = stringResource(id = R.string.coming),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
                // remaining items
                events.value?.let {
                    when (it) {
                        is Resource.Failure -> {
                            Toast.makeText(context, it.exception.message, Toast.LENGTH_SHORT).show()
                        }
                        Resource.Loading -> {
                            FullScreenProgressbar()
                        }
                        is Resource.Success -> {
                            DashboardAllEventsData(incomingEvents = it.result, dashboardViewModel = viewModel, navController = navController)
                        }
                    }
                }

            },
        ) {
            // app UI
            myEvents.value?.let {
                when (it) {
                    is Resource.Failure -> {
                        Toast.makeText(context, it.exception.message, Toast.LENGTH_SHORT).show()
                    }
                    Resource.Loading -> {
                        FullScreenProgressbar()
                    }
                    is Resource.Success -> {
                        DashboardMyEventsData(subscribedSports = it.result, dashboardViewModel = viewModel, navController = navController)
                    }
                }
            }

        }
    }
}

@Composable
fun DashboardMyEventsData(subscribedSports : List<Event>, dashboardViewModel: DashboardViewModel, navController: NavController) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (title, events) = createRefs()

        Text (
            text = stringResource(id = R.string.my_events),
            modifier = Modifier
                .padding(10.dp)
                .constrainAs(title) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                },
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.primary
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(events) {
                    top.linkTo(title.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(subscribedSports) { event ->
                RegularEventCard(event = event)
            }
        }
    }
}

@Composable
fun DashboardAllEventsData(incomingEvents : List<Event>, dashboardViewModel: DashboardViewModel, navController: NavController) {
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
                                true,
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