package dj2al.example.buddybuilder.ui.home.sports


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import dj2al.example.buddybuilder.R
import dj2al.example.buddybuilder.data.Resource
import dj2al.example.buddybuilder.data.home.SportsRepositoryImplFake
import dj2al.example.buddybuilder.data.home.UsersRepositoryImplFake
import dj2al.example.buddybuilder.data.models.Sport
import dj2al.example.buddybuilder.data.models.User
import dj2al.example.buddybuilder.ui.AppScreen
import dj2al.example.buddybuilder.ui.commons.FullScreenProgressbar
import dj2al.example.buddybuilder.ui.commons.SportCard


@Composable
fun MySportsScreen(viewModel: SportsViewModel, navController: NavController) {
    val context = LocalContext.current
    val sports = viewModel.mysports.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getMySports()
    }

    sports.value?.let {
        when (it) {
            is Resource.Failure -> {
                Toast.makeText(context, it.exception.message, Toast.LENGTH_SHORT).show()
            }
            Resource.Loading -> {
                FullScreenProgressbar()
            }
            is Resource.Success -> {
                MySportsData(resource = it.result, sportsViewModel = viewModel, navController = navController)
            }
        }
    }
}

@Composable
fun MySportsData(resource: List<Sport>, sportsViewModel: SportsViewModel, navController: NavController)
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
            items(resource) { sport ->
                SportCard(sport = sport, clickable = false, checked = true, onCheckedStatusChange = {
                } )
            }
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .clip(shape = RoundedCornerShape(percent = 10))
                        .background(color = MaterialTheme.colorScheme.primary)
                        .clickable(onClick = {
                            navController.navigate(AppScreen.Sports.route)
                        }),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(painter = painterResource(id = R.drawable.ic_add), contentDescription = "", modifier = Modifier.scale(3f), tint = MaterialTheme.colorScheme.onPrimary)
                }
            }
            item {
                Spacer(modifier = Modifier.size(300.dp))
            }
        }
    }
}


