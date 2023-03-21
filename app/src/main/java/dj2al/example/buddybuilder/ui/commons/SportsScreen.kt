package dj2al.example.buddybuilder.ui.commons


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
fun SportsScreen(Sports: List<Sport>?, onSwitch: () -> Unit) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(Modifier.fillMaxSize()) {
            Header(Titre = "Sports") { }
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
                    Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "", modifier = Modifier.weight(1f))
                    Text(text = "Football",  modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically))
                    Switch(checked = true, onCheckedChange = { }, modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically))
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(all = 16.dp)
                        .shadow(elevation = 10.dp, shape = RoundedCornerShape(10.dp))
                        .background(backgroundColor)) {
                    Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "", modifier = Modifier.weight(1f))
                    Text(text = "Volleyball",  modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically))
                    Switch(checked = true, onCheckedChange = { }, modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically))
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(all = 16.dp)
                        .shadow(elevation = 10.dp, shape = RoundedCornerShape(10.dp))
                        .background(backgroundColor)) {
                    Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "", modifier = Modifier.weight(1f))
                    Text(text = "Hockey",  modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically))
                    Switch(checked = true, onCheckedChange = { }, modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically))
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(all = 16.dp)
                        .shadow(elevation = 10.dp, shape = RoundedCornerShape(10.dp))
                        .background(backgroundColor)) {
                    Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "", modifier = Modifier.weight(1f))
                    Text(text = "Tennis",  modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically))
                    Switch(checked = false, onCheckedChange = { }, modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically))
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(all = 16.dp)
                        .shadow(elevation = 10.dp, shape = RoundedCornerShape(10.dp))
                        .background(backgroundColor)) {
                    Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "", modifier = Modifier.weight(1f))
                    Text(text = "Rugby",  modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically))
                    Switch(checked = true, onCheckedChange = { }, modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically))
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(all = 16.dp)
                        .shadow(elevation = 10.dp, shape = RoundedCornerShape(10.dp))
                        .background(backgroundColor)) {
                    Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "", modifier = Modifier.weight(1f))
                    Text(text = "Squash",  modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically))
                    Switch(checked = false, onCheckedChange = { }, modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically))
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(all = 16.dp)
                        .shadow(elevation = 10.dp, shape = RoundedCornerShape(10.dp))
                        .background(backgroundColor)) {
                    Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "", modifier = Modifier.weight(1f))
                    Text(text = "E-Sport",  modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically))
                    Switch(checked = true, onCheckedChange = { }, modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SportsScreenPreview() {
    SportsScreen(Sports = null) { }
}