package dj2al.example.buddybuilder.ui.home.sports


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import dj2al.example.buddybuilder.R
import dj2al.example.buddybuilder.data.Resource
import dj2al.example.buddybuilder.data.home.SportsRepositoryImplFake
import dj2al.example.buddybuilder.data.home.UserRepositoryImplFake
import dj2al.example.buddybuilder.data.models.Sport
import dj2al.example.buddybuilder.data.models.User
import dj2al.example.buddybuilder.ui.commons.FullScreenProgressbar
import dj2al.example.buddybuilder.ui.commons.TopBar
import dj2al.example.buddybuilder.ui.theme.PurpleGrey80


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
            val backgroundColor = PurpleGrey80
            resource.forEach {sport ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(all = 16.dp)
                        .shadow(elevation = 10.dp, shape = RoundedCornerShape(10.dp))
                        .background(backgroundColor)
                ) {
                    Image(
                        painter = painterResource(id = sport.thumbnail),
                        contentDescription = "",
                        modifier = Modifier.weight(0.5f)
                    )
                    Spacer(modifier = Modifier.size(5.dp))
                    Text(
                        text = sport.name, modifier = Modifier
                            .weight(1f)
                            .align(Alignment.CenterVertically)
                    )
                    Switch(
                        checked = user.subscribedSports.contains(sport.id), onCheckedChange = {sportsViewModel.addSportToUser(user.id, sport.id)}, modifier = Modifier
                            .weight(1f)
                            .align(Alignment.CenterVertically)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SportsScreenPreview() {
    TopBar(title = R.string.app_name, icon =R.drawable.home, content = {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            SportsScreen(viewModel = SportsViewModel(SportsRepositoryImplFake(), UserRepositoryImplFake()))
        }
    })
}