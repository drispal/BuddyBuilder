package dj2al.example.buddybuilder.ui.commons

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import dj2al.example.buddybuilder.R.color


@Composable
fun EmptyScreen() {
    Surface(
        color = colorResource(id = color.green_general),
        modifier = Modifier.fillMaxSize()
    ) {
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyScreenPreview() {
        EmptyScreen()
}

