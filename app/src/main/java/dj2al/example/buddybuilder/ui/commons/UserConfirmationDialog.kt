package dj2al.example.buddybuilder.ui.commons

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dj2al.example.buddybuilder.R.string
import dj2al.example.buddybuilder.ui.theme.BuddyBuilderTheme

@Composable
fun UserConfirmationDialog(
    dialogText : String,
    onComplete: (shallDelete: Boolean) -> Unit
) {
    AlertDialog(
        onDismissRequest = { onComplete.invoke(false) },
        confirmButton = {
            TextButton(
                onClick = { onComplete.invoke(true) },
                content = {
                    Text(
                        text = stringResource(id = string.ok),
                    )
                }
            )

        },
        dismissButton = {
            TextButton(
                onClick = { onComplete.invoke(false) },
                content = {
                    Text(
                        text = stringResource(id = string.cancel),
                        color = MaterialTheme.colorScheme.error
                    )
                }
            )
        },
        text = {
            Text(
                text = dialogText,
                style = MaterialTheme.typography.titleMedium
            )
        },
    )
}

@Preview(showBackground = true)
@Composable
fun UserConfirmationDialogPreview() {
    BuddyBuilderTheme() {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            UserConfirmationDialog(dialogText = "Delete your profile ?") { }
        }
    }
}