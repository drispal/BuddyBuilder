package dj2al.example.buddybuilder.ui.home.dashboard

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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
import dj2al.example.buddybuilder.ui.material3.BottomSheetScaffold
import dj2al.example.buddybuilder.ui.material3.rememberBottomSheetScaffoldState
import dj2al.example.buddybuilder.ui.theme.BuddyBuilderTheme
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.lifecycle.viewmodel.compose.viewModel
import dj2al.example.buddybuilder.data.models.User
import dj2al.example.buddybuilder.ui.AppScreen
import dj2al.example.buddybuilder.ui.commons.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(viewModel: DashboardViewModel, navController: NavController) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.getEvents()
        viewModel.getMyEvents()
    }

    Column() {

        val scaffoldState = rememberBottomSheetScaffoldState()

        val myEvents = viewModel.myEvents.collectAsState()
        val events = viewModel.events.collectAsState()
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
                        text = stringResource(id = R.string.coming),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
                // remaining items
                events.value?.let { event ->
                    when (event) {
                        is Resource.Failure -> {
                            Toast.makeText(context, event.exception.message, Toast.LENGTH_SHORT).show()
                        }
                        Resource.Loading -> {
                            FullScreenProgressbar()
                        }
                        is Resource.Success -> {
                            myEvents.value?.let {
                                when (it) {
                                    is Resource.Failure -> {
                                        Toast.makeText(context, it.exception.message, Toast.LENGTH_SHORT).show()
                                    }
                                    Resource.Loading -> {
                                        FullScreenProgressbar()
                                    }
                                    is Resource.Success -> {
                                        DashboardAllEventsData(incomingEvents = event.result, subscribedEvents = it.result, dashboardViewModel = viewModel)
                                    }
                                }
                            }
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
                        user.value?.let { user ->
                            when (user) {
                                is Resource.Failure -> {
                                    Toast.makeText(context, user.exception.message, Toast.LENGTH_SHORT).show()
                                }
                                Resource.Loading -> {
                                    FullScreenProgressbar()
                                }
                                is Resource.Success -> {
                                    DashboardMyEventsData(subscribedSports = it.result, user = user.result, dashboardViewModel = viewModel, navController = navController)
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
fun DashboardMyEventsData(subscribedSports : List<Event>, user: User, dashboardViewModel: DashboardViewModel, navController: NavController) {
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
            contentPadding = PaddingValues(10.dp, 30.dp, 10.dp, 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(subscribedSports) { event ->
                RegularEventCard(event = event, event.responsable == user.id)
            }
        }
    }
}

@Composable
fun DashboardAllEventsData(incomingEvents : List<Event>, subscribedEvents: List<Event>, dashboardViewModel: DashboardViewModel) {
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
                    val showDialog = remember { mutableStateOf(false) }
                    SmallEventCard(it, subscribedEvents.contains(it), modifier = Modifier.clickable {
                        showDialog.value = true
                    })
                    ConfirmationEventCard(event = it, isVisible = showDialog.value, onConfirmation = {
                        dashboardViewModel.addEventToUser(it)
                        showDialog.value = false }, onDismiss = {
                        dashboardViewModel.removeEventFromUser(it)
                        showDialog.value = false })
                }
            })
    }
}

