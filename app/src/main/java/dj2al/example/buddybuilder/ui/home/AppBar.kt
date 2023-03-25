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
import androidx.navigation.NavController
import dj2al.example.buddybuilder.R.*
import dj2al.example.buddybuilder.ui.theme.BuddyBuilderTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    text : String,
    NavIcon : Int,
    onNavigationClick : () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = text,
                style = MaterialTheme.typography.titleLarge
            )
        },
        navigationIcon = {
            IconButton(onClick = onNavigationClick) {
                Icon(
                    painter = painterResource(id = NavIcon),
                    contentDescription = "Nav",
                    modifier = Modifier.size(30.dp)
                )
            }
        },
    )
}
