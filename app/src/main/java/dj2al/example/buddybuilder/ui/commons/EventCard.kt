package dj2al.example.buddybuilder.ui.commons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import dj2al.example.buddybuilder.R
import dj2al.example.buddybuilder.data.models.Event
import dj2al.example.buddybuilder.data.models.Level
import dj2al.example.buddybuilder.ui.theme.BuddyBuilderTheme


@Composable
fun SmallEventCard(event: Event) {
    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .width(200.dp)
            .height(77.dp)
    ) {
        val jour = when(event.startTime/10000){
            "1".toLong() -> "Lundi"
            "2".toLong() -> "Mardi"
            "3".toLong() -> "Mercredi"
            "4".toLong() -> "Jeudi"
            "5".toLong() -> "Vendredi"
            "6".toLong() -> "Samedi"
            else -> "Dimanche"
        }
        Row (
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically){
            Column(modifier = Modifier.width(128.dp)) {
                Text(text = event.sport,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    letterSpacing = 0.5.sp,)
                Text(text = "$jour ${event.startTime%10000/100}h${event.startTime%100} - ${event.endTime%10000/100}h${event.endTime%100}",
                    fontSize = 10.sp,)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.size(2.dp))
                    Image(painter = painterResource(id = R.drawable.group), contentDescription = "", Modifier.size(20.dp))
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(text = event.nbParticipants.toString(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,)
                }
            }
            Image(painter = painterResource(id = event.level.logo), contentDescription = "")
        }
    }
}

@Composable
fun RegularEventCard(event: Event) {
    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
    ) {
        val jour = when(event.startTime/10000){
            "1".toLong() -> "Lundi"
            "2".toLong() -> "Mardi"
            "3".toLong() -> "Mercredi"
            "4".toLong() -> "Jeudi"
            "5".toLong() -> "Vendredi"
            "6".toLong() -> "Samedi"
            else -> "Dimanche"
        }
        Row (
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically){
            Column(modifier = Modifier.width(128.dp)) {
                Text(text = event.sport,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    letterSpacing = 0.5.sp,)
                Text(text = "$jour ${event.startTime%10000/100}h${event.startTime%100} - ${event.endTime%10000/100}h${event.endTime%100}",
                    fontSize = 10.sp,)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.size(2.dp))
                    Image(painter = painterResource(id = R.drawable.group), contentDescription = "", Modifier.size(20.dp))
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(text = event.nbParticipants.toString(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,)
                }
            }
            Image(painter = painterResource(id = event.level.logo), contentDescription = "")
        }
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
                it -> SmallEventCard(event = it)
        }})
}

@Composable
fun RegularEventList(eventList: List<Event>) {
    LazyColumn(
        content = {
            items(eventList){
                    it -> RegularEventCard(event = it)
            }})
}

@Preview(showBackground = true)
@Composable
fun EventCardPreview() {
    val list: List<Event> = listOf(Event(
            "Football",
            11630,
            2030,
            10,
            30,
            level = Level.Level1,
            22,
            "Beaujoire",
            "responsable"
    ),Event(
            "Football",
            11630,
            2030,
            10,
            30,
            level = Level.Level2,
            22,
            "Beaujoire",
            "responsable"
    ))
    BuddyBuilderTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            SmallEventList(eventList = list)
            RegularEventList(eventList = list)
        }
    }
}

