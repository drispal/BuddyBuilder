package dj2al.example.buddybuilder.ui.commons

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dj2al.example.buddybuilder.ui.theme.BuddyBuilderTheme


@Composable
fun EmptyScreen() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyScreenPreview() {
    BuddyBuilderTheme() {
        EmptyScreen()
    }
}

