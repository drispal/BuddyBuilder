package dj2al.example.buddybuilder.ui.home.events

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import dj2al.example.buddybuilder.R
import dj2al.example.buddybuilder.data.Resource
import dj2al.example.buddybuilder.data.models.Event
import dj2al.example.buddybuilder.data.models.Sport
import dj2al.example.buddybuilder.ui.AppScreen
import dj2al.example.buddybuilder.ui.commons.FullScreenProgressbar
import dj2al.example.buddybuilder.ui.commons.RegularEventCard
import dj2al.example.buddybuilder.ui.commons.SportCard

@Composable
fun EventsScreen(viewModel: EventsViewModel, navController: NavController) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        println("allo")
        viewModel.getEvents(viewModel.sportId)
    }

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
                println(it.result)
                EventsData(resource = it.result, eventsViewModel = viewModel, navController = navController)
            }
        }
    }
}

@Composable
fun EventsData(resource: List<Event>, eventsViewModel: EventsViewModel, navController: NavController)
{
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(resource) { event ->
                RegularEventCard(event = event)
            }
            item {
                Button(onClick = {navController.navigate(AppScreen.Events.Add.route + "/${eventsViewModel.sportName}/${eventsViewModel.sportId}")}, modifier = Modifier.size(65.dp)) {
                    Icon(painter = painterResource(id = R.drawable.ic_add), contentDescription = "", modifier = Modifier.scale(3f))
                }
            }
            item {
                Spacer(modifier = Modifier.size(300.dp))
            }
        }
    }
}