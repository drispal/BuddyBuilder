package dj2al.example.buddybuilder.ui.commons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dj2al.example.buddybuilder.R
import dj2al.example.buddybuilder.data.models.Event
import dj2al.example.buddybuilder.data.models.Level
import dj2al.example.buddybuilder.ui.theme.BuddyBuilderTheme


@Composable
fun EventCard(event: Event) {
    Card(
        modifier = Modifier
            .width(200.dp)
            .wrapContentHeight()
            .shadow(elevation = 10.dp, shape = RoundedCornerShape(20.dp))
            .background(color = MaterialTheme.colorScheme.surface)
            .padding(15.dp)
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
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.background(color = MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.width(128.dp)) {
                Text(text = event.sport,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    letterSpacing = 0.5.sp)
                Text(text = "$jour ${event.startTime%10000/100}h${event.startTime%100} - ${event.endTime%10000/100}h${event.endTime%100}",
                    fontSize = 10.sp)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.size(2.dp))
                    Image(painter = painterResource(id = R.drawable.group), contentDescription = "", Modifier.size(20.dp))
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(text = event.nbParticipants.toString(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp)
                }
            }
            Spacer(modifier = Modifier.size(15.dp))
            Box(modifier = Modifier.width(27.dp)) {
                Image(painter = painterResource(id = R.drawable.level1), contentDescription = "")
            }
        }
    }
}

@Composable
fun EventList(eventList: List<Event>) {
    LazyColumn() {
        items(eventList){
                it -> EventCard(event = it)
        }
    }
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
            level = Level.Level1,
            22,
            "Beaujoire",
            "responsable"
    ))
    BuddyBuilderTheme {
        EventList(eventList = list)
    }
}

