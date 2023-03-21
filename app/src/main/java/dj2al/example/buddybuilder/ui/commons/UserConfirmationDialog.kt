package dj2al.example.buddybuilder.ui.commons

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dj2al.example.buddybuilder.R.color
import dj2al.example.buddybuilder.R.string

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
                        color = colorResource(id = color.green_accepted)
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
                        color = colorResource(id = color.red_rejected)
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
        containerColor = colorResource(id = color.grey),

    )
}

@Preview(showBackground = true)
@Composable
fun UserConfirmationDialogPreview() {
    Surface(
        color = colorResource(id = color.green_general),
        modifier = Modifier
            .fillMaxSize()
    ) {
        UserConfirmationDialog(dialogText = "Delete your profile ?") { }
    }
}