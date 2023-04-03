package dj2al.example.buddybuilder.ui.home.events

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import com.marosseleng.compose.material3.datetimepickers.date.ui.dialog.DatePickerDialog
import com.marosseleng.compose.material3.datetimepickers.time.ui.dialog.TimePickerDialog
import dj2al.example.buddybuilder.R
import dj2al.example.buddybuilder.data.Resource
import dj2al.example.buddybuilder.data.models.Level
import dj2al.example.buddybuilder.data.utils.currentDateTime
import dj2al.example.buddybuilder.data.utils.toDate
import dj2al.example.buddybuilder.data.utils.toTime
import kotlinx.coroutines.launch
import java.time.*


@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class,
    ExperimentalMaterial3Api::class
)
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

    var isDateDialogShown: Boolean by rememberSaveable {
        mutableStateOf(false)
    }
    var isStartTimeDialogShown: Boolean by rememberSaveable {
        mutableStateOf(false)
    }
    var isEndTimeDialogShown: Boolean by rememberSaveable {
        mutableStateOf(false)
    }

    Card(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(20.dp),
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(11.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text(text = stringResource(id = R.string.date) + " ", style = MaterialTheme.typography.bodyMedium)
                Text(text = startTime.value.toDate(),
                    modifier = Modifier
                        .clickable {
                            isDateDialogShown = true
                        }
                        .border(
                            1.dp,
                            if(startTime.value > currentDateTime) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.error,
                            shape = RoundedCornerShape(5.dp),
                        )
                        .padding(5.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text(text = stringResource(id = R.string.from) + " ", style = MaterialTheme.typography.bodyMedium)
                Text(text = startTime.value.toTime(),
                    modifier = Modifier
                        .clickable {
                            isStartTimeDialogShown = true
                        }
                        .border(
                            1.dp,
                            if(endTime.value > startTime.value) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.error,
                            shape = RoundedCornerShape(5.dp)
                        )
                        .padding(5.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(text = " " + stringResource(id = R.string.to)+ " ", style = MaterialTheme.typography.bodyMedium)
                Text(text = endTime.value.toTime(),
                    modifier = Modifier
                        .clickable {
                            isEndTimeDialogShown = true
                        }
                        .border(
                            1.dp,
                            if(endTime.value > startTime.value) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.error,
                            shape = RoundedCornerShape(5.dp)
                        )
                        .padding(5.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            TextField(
                value = if(minParticipants.value == -1) "" else minParticipants.value.toString(),
                onValueChange = {
                    if(it.isNotEmpty() && it.isDigitsOnly()) {
                        viewModel.minParticipants.value = it.toInt()
                    } else {
                        viewModel.minParticipants.value = -1
                    }
                },
                label = {
                    if (minParticipants.value < 1) {
                        Text(text = stringResource(id = R.string.min_req), style = MaterialTheme.typography.bodyMedium)
                    } else {
                        Text(text = stringResource(id = R.string.min_pla), style = MaterialTheme.typography.bodyMedium)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusEvent {
                        if (it.isFocused) {
                            coroutineScope.launch {
                                bringIntoViewRequester.bringIntoView()
                            }
                        }
                    },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        viewModel.validateInputs()
                        focusManager.moveFocus(FocusDirection.Next)
                    },
                ),
                maxLines = 1,
                isError = minParticipants.value < 1,
            )

            TextField(
                value = if (maxParticipants.value == -1) "" else maxParticipants.value.toString(),
                onValueChange = {
                    if (it.isNotEmpty() && it.isDigitsOnly()) {
                        viewModel.maxParticipants.value = it.toInt()
                    } else {
                        viewModel.maxParticipants.value = -1
                    }
                },
                label = {
                    if (maxParticipants.value < minParticipants.value) {
                        Text(text = stringResource(id = R.string.max_pla_gre), style = MaterialTheme.typography.bodyMedium)
                    } else {
                        Text(text = stringResource(id = R.string.max_pla), style = MaterialTheme.typography.bodyMedium)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusEvent {
                        if (it.isFocused) {
                            coroutineScope.launch {
                                bringIntoViewRequester.bringIntoView()
                            }
                        }
                    },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        viewModel.validateInputs()
                        focusManager.moveFocus(FocusDirection.Next)
                    },
                ),
                maxLines = 1,
                isError = maxParticipants.value < minParticipants.value
            )

            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(id = level.value.logo), contentDescription = "", Modifier.size(40.dp))
                Slider(
                    value = level.value.value.toFloat(),
                    onValueChange = {
                        when(it) {
                            1F -> viewModel.level.value = Level.Level1
                            2F -> viewModel.level.value = Level.Level2
                            3F -> viewModel.level.value = Level.Level3
                        }
                        viewModel.validateInputs()
                    },
                    valueRange = 1F..3F,
                    steps = 1,
                    colors = SliderDefaults.colors(
                        thumbColor = MaterialTheme.colorScheme.primary,
                        activeTrackColor = MaterialTheme.colorScheme.primary,
                        inactiveTrackColor = MaterialTheme.colorScheme.surface
                    ),
                )
            }

            TextField(
                value = court.value,
                onValueChange = {
                    viewModel.court.value = it
                },
                label = {
                    if (court.value.isEmpty()) {
                        Text(text = stringResource(id = R.string.cou_req), style = MaterialTheme.typography.bodyMedium)
                    } else {
                        Text(text = stringResource(id = R.string.cou), style = MaterialTheme.typography.bodyMedium)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusEvent {
                        if (it.isFocused) {
                            coroutineScope.launch {
                                bringIntoViewRequester.bringIntoView()
                            }
                        }
                    },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        viewModel.validateInputs()
                        focusManager.moveFocus(FocusDirection.Next)
                    },
                ),
                maxLines = 1,
                isError = court.value.isEmpty()
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                addEventResult.value.let {
                    when (it) {
                        is Resource.Failure -> {
                            Toast.makeText(context, it.exception.message, Toast.LENGTH_LONG).show()
                        }
                        is Resource.Success -> {
                            LaunchedEffect(Unit) {
                                navController.popBackStack()
                                viewModel.resetResource()
                            }
                        }
                        Resource.Loading -> {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                        }
                        null -> Spacer(modifier = Modifier.height(20.dp))
                    }
                }
            }

            Button(
                onClick = {
                    viewModel.addEvent()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .bringIntoViewRequester(bringIntoViewRequester),
                enabled = areInputsValid.value
            ) {
                Text(
                    text = stringResource(id = R.string.let_pla),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }

    // Dialogs
    val startDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(startTime.value), ZoneId.systemDefault());
    val endDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(endTime.value), ZoneId.systemDefault());
    if (isDateDialogShown) {
        DatePickerDialog(
            initialDate = startDateTime.toLocalDate(),
            onDismissRequest = { isDateDialogShown = false },
            onDateChange = {
                viewModel.startTime.value = it.atTime(startDateTime.toLocalTime()).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
                viewModel.endTime.value = it.atTime(endDateTime.toLocalTime()).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
                isDateDialogShown = false
                viewModel.validateInputs()
            },
            title = { Text(text = stringResource(id = R.string.sel_sta_dat)) }
        )
    }
    if (isStartTimeDialogShown) {
        TimePickerDialog(
            initialTime = startDateTime.toLocalTime(),
            onDismissRequest = { isStartTimeDialogShown = false },
            onTimeChange = {
                viewModel.startTime.value = it.atDate(startDateTime.toLocalDate()).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
                isStartTimeDialogShown = false
                viewModel.validateInputs()
            },
            title = { Text(text = stringResource(id = R.string.sel_sta_tim)) }
        )
    }

    if (isEndTimeDialogShown) {
        TimePickerDialog(
            initialTime = endDateTime.toLocalTime(),
            onDismissRequest = { isEndTimeDialogShown = false },
            onTimeChange = {
                viewModel.endTime.value = it.atDate(endDateTime.toLocalDate()).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
                isEndTimeDialogShown = false
                viewModel.validateInputs()
            },
            title = { Text(text = stringResource(id = R.string.sel_end_tim)) }
        )
    }
}

