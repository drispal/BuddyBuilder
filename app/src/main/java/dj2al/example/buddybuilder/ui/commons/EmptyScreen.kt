package dj2al.example.buddybuilder.ui.commons

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import dj2al.example.buddybuilder.R
import dj2al.example.buddybuilder.ui.theme.BuddyBuilderTheme


@Composable
fun EmptyScreen(title: String, onRefresh: () -> Unit) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (refIcon, refTitle, refDesc, refRefresh) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = stringResource(id = R.string.empty),
            modifier = Modifier
                .size(160.dp)
                .constrainAs(refIcon) {
                    start.linkTo(parent.start, 150.dp)
                    end.linkTo(parent.end, 150.dp)
                    top.linkTo(parent.top, 150.dp)
                }
        )

        Text(
            text = title,
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            modifier = Modifier.constrainAs(refTitle) {
                top.linkTo(refIcon.bottom, 150.dp)
                start.linkTo(parent.start, 75.dp)
                end.linkTo(parent.end, 75.dp)
                width = Dimension.fillToConstraints
            }
        )

        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            modifier = Modifier.constrainAs(refDesc) {
                top.linkTo(refTitle.bottom, 75.dp)
                start.linkTo(parent.start, 75.dp)
                end.linkTo(parent.end, 75.dp)
                width = Dimension.fillToConstraints
            }
        )

        Button(
            onClick = {
                onRefresh.invoke()
            },
            modifier = Modifier.constrainAs(refRefresh) {
                start.linkTo(parent.start, 150.dp)
                top.linkTo(refDesc.bottom, 150.dp)
                end.linkTo(parent.end, 150.dp)
                width = Dimension.fillToConstraints
            }
        ) {
            Text(
                text = stringResource(id = R.string.refresh),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyScreenPreview() {
        EmptyScreen(title = "Oops! No customers found") { }
}

