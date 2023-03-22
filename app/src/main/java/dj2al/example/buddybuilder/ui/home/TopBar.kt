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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    @StringRes title: Int,
    icon: Int,
    content: @Composable (innerPadding : PaddingValues) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = title),
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            painter = painterResource(id = drawable.menu),
                            contentDescription = "",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            painter = painterResource(id = icon),
                            contentDescription = "",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                },
            )
        },
        content = {
            content(it)
        }
    )
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar(title = string.app_name, icon =drawable.home, content = {
        Surface(
            color = colorResource(id = color.white),
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {}
    })
}