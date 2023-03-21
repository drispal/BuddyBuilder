package dj2al.example.buddybuilder.ui.commons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import dj2al.example.buddybuilder.R

@Composable
fun Header(Titre: String, Previous: @Composable () -> Unit) {
    ConstraintLayout(
        modifier = Modifier.fillMaxWidth()
    ) {
        val backgroundColor = Color(0xFF3DD784)
        val refRow = createRef()

        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(color = backgroundColor)
                .padding(horizontal = 16.dp)
                .constrainAs(refRow) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
        ) {
            Row(verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = { /*GO TO Previous*/ }) {
                    Image(painter = painterResource(id = R.drawable.arrow_back), contentDescription = "")
                }
                Text(text = Titre,
                style = MaterialTheme.typography.titleLarge)
            }
            Button(onClick = { /*OPEN MENU*/ }) {
                Image(painter = painterResource(id = R.drawable.menu), contentDescription = "")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HeaderPreview() {
    Header("HOME") { }
}