package dj2al.example.buddybuilder.ui.home.sports


import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import dj2al.example.buddybuilder.data.Resource
import dj2al.example.buddybuilder.data.models.Sport
import dj2al.example.buddybuilder.ui.commons.FullScreenProgressbar
import dj2al.example.buddybuilder.ui.commons.SportCard


@Composable
fun SportsScreen(viewModel: SportsViewModel) {
    val context = LocalContext.current
    val sports = viewModel.sports.collectAsState()
    val mySports = viewModel.mysports.collectAsState()
    sports.value?.let {
        when (it) {
            is Resource.Failure -> {
                Toast.makeText(context, it.exception.message, Toast.LENGTH_SHORT).show()
            }
            Resource.Loading -> {
                FullScreenProgressbar()
            }
            is Resource.Success -> {
                mySports.value?.let { ms ->
                    when (ms) {
                        is Resource.Failure -> {
                            Toast.makeText(context, ms.exception.message, Toast.LENGTH_SHORT).show()
                        }
                        Resource.Loading -> {
                            FullScreenProgressbar()
                        }
                        is Resource.Success -> {
                            SportsData(allSports = it.result, checkedSports = ms.result.map { s -> s.id }, sportsViewModel = viewModel)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SportsData(allSports: List<Sport>, checkedSports : List<String>, sportsViewModel: SportsViewModel)
{
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(allSports) { sport ->
                SportCard(sport = sport, clickable = true, checked = checkedSports.contains(sport.id), onCheckedStatusChange = {
                    if(it)
                        sportsViewModel.addSportToUser(sport.id)
                    else
                        sportsViewModel.removeSportFromUser(sport.id)
                } )
            }
        }
    }
}


