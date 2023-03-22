package dj2al.example.buddybuilder.ui.commons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import dj2al.example.buddybuilder.R
import dj2al.example.buddybuilder.data.models.Sport
import dj2al.example.buddybuilder.ui.theme.PurpleGrey80


@Composable
fun SportCard(sport: Sport) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(all = 16.dp)) {
            val backgroundColor = PurpleGrey80
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp)
                    .shadow(elevation = 10.dp, shape = RoundedCornerShape(10.dp))
                    .background(backgroundColor)) {
                Image(painter = painterResource(id = sport.thumbnail), contentDescription = "", modifier = Modifier.weight(1f))
                Text(text = sport.name,  modifier = Modifier
                    .weight(1f)
                    .align(CenterVertically))
            }
        }
    }
}

val myMutableList = mutableListOf<String>() // instantiate an empty mutable list of strings




@Preview(showBackground = true)
@Composable
fun SportCardPreview() {
    SportCard(
        Sport(
            "PingPong",
            R.drawable.sports_pingpong,
            myMutableList
        )
    )
}