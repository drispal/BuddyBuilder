package dj2al.example.buddybuilder.ui.commons

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dj2al.example.buddybuilder.R.*
import dj2al.example.buddybuilder.ui.theme.BuddyBuilderTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    onNavigationIconClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = string.app_name),
                style = MaterialTheme.typography.titleLarge
            )
        },
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                Icon(
                    painter = painterResource(id = drawable.menu),
                    contentDescription = "Toggle drawer",
                    modifier = Modifier.size(30.dp)
                )
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    BuddyBuilderTheme {
        AppBar {
        }
    }
}