package dj2al.example.buddybuilder.ui.commons

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dj2al.example.buddybuilder.R

@Composable
fun UserConfirmationDialog(
    onComplete: (shallDelete: Boolean) -> Unit
) {
    AlertDialog(
        onDismissRequest = { onComplete.invoke(false) },
        confirmButton = {
            TextButton(
                onClick = { onComplete.invoke(true) },
                content = {
                    Text(
                        text = stringResource(id = R.string.ok),
                        color = MaterialTheme.colorScheme.error
                    )
                }
            )

        },
        dismissButton = {
            TextButton(
                onClick = { onComplete.invoke(false) },
                content = {
                    Text(
                        text = stringResource(id = R.string.cancel)
                    )
                }
            )
        },
        text = {
            Text(
                text = stringResource(id = R.string.ok),
                style = MaterialTheme.typography.titleMedium
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun UserConfirmationDialogPreview() {
    UserConfirmationDialog { }
}