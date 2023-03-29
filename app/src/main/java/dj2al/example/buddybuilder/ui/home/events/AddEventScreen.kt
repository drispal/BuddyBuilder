package dj2al.example.buddybuilder.ui.home.events

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.navigation.NavController

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AddEventScreen(viewModel: EventsViewModel, navController: NavController) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current
    val bringIntoViewRequester = BringIntoViewRequester()

    val startTime = viewModel.startTime.collectAsState()
    val endTime = viewModel.endTime.collectAsState()
    val minParticipants = viewModel.minParticipants.collectAsState()
    val maxParticipants = viewModel.maxParticipants.collectAsState()
    val level = viewModel.level.collectAsState()
    val court = viewModel.court.collectAsState()

    val areInputsValid = viewModel.areInputsValid.collectAsState()
    val addEventResult = viewModel.manageEventResult.collectAsState()

    Text(text = "Add Events for ${viewModel.sportName}")
}