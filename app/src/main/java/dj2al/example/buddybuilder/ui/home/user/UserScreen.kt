package dj2al.example.buddybuilder.ui.home.user

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import dj2al.example.buddybuilder.R
import dj2al.example.buddybuilder.data.Resource
import dj2al.example.buddybuilder.data.models.User
import dj2al.example.buddybuilder.ui.commons.FullScreenProgressbar
import dj2al.example.buddybuilder.ui.theme.BuddyBuilderTheme


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

@OptIn(ExperimentalMaterial3Api::class)
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
            Box(){
                // Setting Icon
                Image(painter = painterResource(id = R.drawable.ic_settings),
                    colorFilter = ColorFilter.tint(Color.Gray),
                    contentDescription = "",
                    modifier = Modifier
                        .clickable { /*TODO*/ }
                        .align(Alignment.BottomEnd)
                        .padding(7.dp)
                        .size(30.dp))
                // User Picture
                Image(painter = painterResource(id = R.drawable.ic_account_box),
                    contentDescription = "",
                    modifier = Modifier
                        .size(150.dp)
                        .clip(shape = MaterialTheme.shapes.large))
            }
            Spacer(modifier = Modifier.size(30.dp))
            Text(text = "Mike Ross", fontFamily = FontFamily(Font(R.font.oswald_variablefont_wght)), fontSize = 30.sp, fontWeight = FontWeight.W500, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.size(30.dp))
            Text(text = "mail : ${user.mail}", fontFamily = FontFamily(Font(R.font.oswald_variablefont_wght)), fontSize = 25.sp, fontWeight = FontWeight.W400, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.size(30.dp))
            Text(text = stringResource(id = R.string.localization) + " : ${user.location}", fontFamily = FontFamily(Font(R.font.oswald_variablefont_wght)), fontSize = 25.sp, fontWeight = FontWeight.W400, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.size(15.dp))
            Text(text = stringResource(id = R.string.distance_user) + " : ${user.maxDistance} km", fontFamily = FontFamily(Font(R.font.oswald_variablefont_wght)), fontSize = 25.sp, fontWeight = FontWeight.W400, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.size(30.dp))
            Text(text = "BADGES", fontFamily = FontFamily(Font(R.font.oswald_variablefont_wght)), fontSize = 25.sp, fontWeight = FontWeight.W400, textAlign = TextAlign.Center)
        }
    }
}

@Composable
@Preview
fun UserScreenPreview() {
    BuddyBuilderTheme() {
        UserData(User("mike.ross@gmail.com", "Paris", 10, mutableListOf(), mutableListOf()))
    }
}
