package dj2al.example.buddybuilder.ui.home.user

import android.location.Location
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import dj2al.example.buddybuilder.R
import dj2al.example.buddybuilder.data.models.User
import dj2al.example.buddybuilder.ui.commons.TopBar
import dj2al.example.buddybuilder.ui.home.sports.SportsScreen


@Composable
fun UserScreen(user: User) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column (horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 16.dp)){
            Image(painter = painterResource(id = R.drawable.account_circle),
                contentDescription = "",
                modifier = Modifier.size(200.dp))
            Spacer(modifier = Modifier.size(30.dp))
            Text(text = "Mail : ${user.mail}",
                fontSize = 30.sp)
            Spacer(modifier = Modifier.size(30.dp))
            Text(text = "Localisation : ${user.location.provider}",
                fontSize = 20.sp)
            Spacer(modifier = Modifier.size(30.dp))
            Text(text = "Distance acceptable du lieu d'activité : ${user.maxDistance} km",
                fontSize = 15.sp)
            Spacer(modifier = Modifier.size(30.dp))
            Text(text = "Sports inscrits : ${user.subscribedSports}",
                fontSize = 20.sp)
            Spacer(modifier = Modifier.size(30.dp))
            Text(text = "Evénements enregistrées : ${user.subscribedSports}",
                fontSize = 20.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserScreenPreview() {
    TopBar(title = R.string.app_name, icon =R.drawable.home, content = {
        Surface(
            modifier = Modifier.fillMaxSize().padding(it)
        ) {
            UserScreen(User("joueur@gmail.com", Location("UQAC"), 5, mutableListOf(), mutableListOf()))
        }
    })
}