package dj2al.example.buddybuilder.ui.home.user

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import dj2al.example.buddybuilder.R
import dj2al.example.buddybuilder.data.Resource
import dj2al.example.buddybuilder.data.home.UsersRepositoryImplFake
import dj2al.example.buddybuilder.data.models.User
import dj2al.example.buddybuilder.ui.commons.FullScreenProgressbar


@Composable
fun UserScreen(viewModel: UserViewModel) {
    val context = LocalContext.current
    val user = viewModel.user.collectAsState()
    user.value?.let { u ->
        when (u) {
            is Resource.Failure -> {
                Toast.makeText(context, u.exception.message, Toast.LENGTH_SHORT).show()
            }
            Resource.Loading -> {
                FullScreenProgressbar()
            }
            is Resource.Success -> {
                UserData(user = u.result)
            }
        }
    }
}

@Composable
fun UserData(user : User)
{
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column (horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 16.dp)){
            Image(painter = painterResource(id = R.drawable.ic_account_box),
                contentDescription = "",
                modifier = Modifier.size(200.dp))
            Spacer(modifier = Modifier.size(30.dp))
            Text(text = "Mail : ${user.mail}",
                fontSize = 30.sp)
            Spacer(modifier = Modifier.size(30.dp))
            Text(text = "Localisation : ${user.location}",
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
