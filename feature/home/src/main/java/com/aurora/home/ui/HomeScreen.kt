package com.aurora.home.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aurora.home.R

@Composable
fun HomeScreen(
    onClickCreate: () -> Unit = {}
) {
    Scaffold(
        topBar = {
        },
        bottomBar = {
        },
        floatingActionButton = {
            Fab(onClickCreate)
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        EmptyState()
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {

        }

    }
}

@Composable
internal fun EmptyState() {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.empty_state),
            contentDescription = "Empty State",
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
internal fun Fab(onClickCreate: () -> Unit = {}) {
    FloatingActionButton(onClick = { onClickCreate() }) {
        Icon(Icons.Default.Add, contentDescription = "Add")
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
internal fun Preview() {
    HomeScreen()
}