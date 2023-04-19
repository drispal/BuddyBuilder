package dj2al.example.buddybuilder.ui.commons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.Group
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dj2al.example.buddybuilder.R
import dj2al.example.buddybuilder.data.models.Event
import dj2al.example.buddybuilder.data.models.Level
import dj2al.example.buddybuilder.data.models.toLevel
import dj2al.example.buddybuilder.data.utils.getDay
import dj2al.example.buddybuilder.data.utils.toDate
import dj2al.example.buddybuilder.data.utils.toDateWithDay
import dj2al.example.buddybuilder.data.utils.toTime
import dj2al.example.buddybuilder.ui.theme.BuddyBuilderTheme


@Composable
fun SmallEventCard(event: Event, accepted : Boolean, modifier: Modifier) {
    Card(
        shape = MaterialTheme.shapes.large,
        modifier = modifier
            .width(200.dp)
            .height(77.dp),
        colors = if (!accepted) CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.error,
            contentColor = MaterialTheme.colorScheme.onError
        ) else CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Row (
            modifier = Modifier.padding(10.dp),

        verticalAlignment = Alignment.CenterVertically){
            Column(modifier = Modifier.width(128.dp)) {
                Text(text = event.sportName,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    letterSpacing = 0.5.sp,)
                Text(text = "${event.startTime.toDate()} ${event.startTime.toTime()} - ${event.endTime.toTime()}",
                    fontSize = 10.sp,)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.size(2.dp))
                    Image(painter = painterResource(id = R.drawable.ic_group), contentDescription = "", Modifier.size(25.dp))
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(text = event.nbParticipants.toString(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,)
                }
            }
            Image(painter = painterResource(id = event.level.toLevel().logo), contentDescription = "", Modifier.size(50.dp))
        }
    }
}

@Composable
fun RegularEventCard(event: Event, isOrganisator : Boolean, modifier: Modifier) {
    Card(
        shape = MaterialTheme.shapes.large,
        modifier = modifier
            .fillMaxWidth()
            .height(106.dp)
    ) {
        Column (
            modifier = Modifier.padding(10.dp),
        ){
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text(text = event.sportName, fontSize = 25.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.size(10.dp))
                if(isOrganisator)
                    Image(painter = painterResource(id = R.drawable.ic_referee), contentDescription = "", Modifier.size(25.dp))
            }
            Row(Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "${event.startTime.getDay()} ${event.startTime.toTime()} - ${event.endTime.toTime()}",
                    fontSize = 25.sp,)
                Row() {
                    Text(text = stringResource(id = R.string.lvl))
                    Image(painter = painterResource(id = event.level.toLevel().logo), contentDescription = "", Modifier.size(30.dp))
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.size(2.dp))
                Image(painter = painterResource(id = R.drawable.ic_group), contentDescription = "", Modifier.size(25.dp))
                Spacer(modifier = Modifier.size(8.dp))
                Text(text = "minimum : ${event.minParticipants.toString()}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,)
                Spacer(modifier = Modifier.size(10.dp))
                Image(painter = painterResource(id = R.drawable.ic_group), contentDescription = "", Modifier.size(25.dp))
                Spacer(modifier = Modifier.size(8.dp))
                Text(text = "maximum : ${event.maxParticipants.toString()}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,)
            }
        }
    }
}

@Composable
fun ConfirmationEventCard(
    event: Event,
    isVisible : Boolean,
    onConfirmation : () -> Unit,
    onUnconfirmation : () -> Unit,
    onDismiss : () -> Unit,
) {
    if(isVisible)
    {
        AlertDialog(onDismissRequest = {onDismiss()},
            title = {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                ){
                    Row(Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = event.sportName,
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp,
                            letterSpacing = 0.5.sp,)
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = stringResource(id = R.string.lvl))
                            Image(painter = painterResource(id = event.level.toLevel().logo), contentDescription = "", Modifier.size(40.dp))
                        }
                    }
                    Text(text = "${event.startTime.toDateWithDay()} ${event.startTime.toTime()} - ${event.endTime.toTime()}",
                        fontSize = 18.sp, modifier = Modifier.fillMaxWidth())
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                        Spacer(modifier = Modifier.size(2.dp))
                        Image(painter = painterResource(id = R.drawable.ic_group), contentDescription = "", Modifier.size(25.dp))
                        Spacer(modifier = Modifier.size(8.dp))
                        Text(text = stringResource(id = R.string.available)+ "${event.maxParticipants.toString()}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,)
                        Spacer(modifier = Modifier.size(10.dp))
                        Image(painter = painterResource(id = R.drawable.ic_group), contentDescription = "", Modifier.size(25.dp))
                        Spacer(modifier = Modifier.size(8.dp))
                        Text(text = stringResource(id = R.string.sub)+" ${event.nbParticipants.toString()}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,)
                    }
                    Text(text = stringResource(id = R.string.court) + " ${event.court}",
                        fontSize = 18.sp, modifier = Modifier.fillMaxWidth())
                }
            },
            confirmButton = {
                ElevatedButton(
                    onClick = {onConfirmation()},
                    modifier = Modifier
                        .width(width = 273.dp)
                        .height(height = 41.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.imdown),
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold)
                    )
                }
            },
            dismissButton = {
                ElevatedButton(
                    onClick = {onUnconfirmation()},
                    modifier = Modifier
                        .width(width = 273.dp)
                        .height(height = 41.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error,
                        contentColor = MaterialTheme.colorScheme.onError
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.oops),
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold)
                    )
                }
            },
        )
    }
}

@Composable
fun SmallEventList(eventList: List<Event>) {
    LazyVerticalGrid(
        contentPadding = PaddingValues(10.dp),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        content = {
            items(eventList){
                    it -> SmallEventCard(event = it, true, Modifier)
            }})
}

@Composable
fun RegularEventList(eventList: List<Event>) {
    LazyColumn(
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        content = {
            items(eventList){
                    it -> RegularEventCard(event = it, true, Modifier)
            }})
}

@Preview(showBackground = true)
@Composable
fun EventCardPreview() {
    val list: List<Event> = listOf(Event(
        "Football",
        sport = "",
        11630,
        2030,
        10,
        30,
        level = 0,
        22,
        "Beaujoire",
        "responsable"
    ),Event(
        "Football",
        sport = "",
        11630,
        2030,
        10,
        30,
        level = 2,
        22,
        "Beaujoire",
        "responsable"
    ))
    BuddyBuilderTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            SmallEventList(eventList = list)
            RegularEventList(eventList = list)
            Box(Modifier.padding(10.dp)) {
                ConfirmationEventCard(
                    event = Event(
                        "Football",
                        sport = "",
                        11630,
                        2030,
                        10,
                        30,
                        level = 3,
                        22,
                        "Beaujoire",
                        "responsable"),
                    false,
                    onConfirmation = { /*TODO*/ },
                    onDismiss = { /*TODO*/ },
                    onUnconfirmation = { /*TODO*/ })
            }
        }
    }
}

