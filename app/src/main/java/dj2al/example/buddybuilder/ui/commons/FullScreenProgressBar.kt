package dj2al.example.buddybuilder.ui.commons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import dj2al.example.buddybuilder.ui.theme.BuddyBuilderTheme

@Composable
fun FullScreenProgressbar() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val progressbar = createRef()
        CircularProgressIndicator(
            modifier = Modifier.constrainAs(progressbar) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FullScreenProgressBarPreview() {
    BuddyBuilderTheme() {
        FullScreenProgressbar()
    }
}