package dj2al.example.buddybuilder.ui.home.sports


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import dj2al.example.buddybuilder.R
import dj2al.example.buddybuilder.data.Resource
import dj2al.example.buddybuilder.data.home.SportsRepositoryImplFake
import dj2al.example.buddybuilder.data.home.UsersRepositoryImplFake
import dj2al.example.buddybuilder.data.models.Sport
import dj2al.example.buddybuilder.data.models.User
import dj2al.example.buddybuilder.ui.commons.FullScreenProgressbar
import dj2al.example.buddybuilder.ui.commons.SportCard


@Composable
fun SportsScreen(viewModel: SportsViewModel) {
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
                val sports = viewModel.sports.collectAsState()
                sports.value?.let {
                    when (it) {
                        is Resource.Failure -> {
                            Toast.makeText(context, it.exception.message, Toast.LENGTH_SHORT).show()
                        }
                        Resource.Loading -> {
                            FullScreenProgressbar()
                        }
                        is Resource.Success -> {
                            SportsData(resource = it.result, user = u.result, sportsViewModel = viewModel)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SportsData(resource: List<Sport>, user : User, sportsViewModel: SportsViewModel)
{
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(all = 16.dp)) {
            val backgroundColor = MaterialTheme.colorScheme.background
            resource.forEach {sport ->
                SportCard(sport = sport, checked = user.subscribedSports.contains(sport.id), onCheckedStatusChange = {
                    println("button switch")
                    if(it)
                        sportsViewModel.addSportToUser(user.id, sport.id)
                    else
                        sportsViewModel.removeSportFromUser(user.id, sport.id)
                } )
            }
        }
    }
}


