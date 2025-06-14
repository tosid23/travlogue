package com.aurora.home.ui.components

// import androidx.compose.foundation.layout.Spacer // Not used
// import androidx.compose.foundation.layout.width // Not used
// import androidx.compose.ui.unit.dp // Not used directly here anymore, but good to keep for potential future use
import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.aurora.designsystem.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeTopBar(
    modifier: Modifier = Modifier,
    onNavigateToPlan: () -> Unit
) {
    TopAppBar(
        modifier = modifier,
        title = { TopBarTitle() },
        actions = {
            IconButton(onClick = { onNavigateToPlan() }) {
                Icon(
                    imageVector = Icons.Filled.Map,
                    contentDescription = "Navigate to Plan"
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        )
    )
}

/**
 * Composable function for the title displayed in the TopAppBar.
 */
@Composable
private fun TopBarTitle() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Travlogue",
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp
            )
        }
    }
}

/**
 * Composable function for the navigation menu button.
 * @param onClick Lambda to be invoked when the button is clicked.
 */
@Composable
private fun NavigationMenuButton(onClick: () -> Unit) { // This is not used in the current TopAppBar
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Filled.Menu,
            contentDescription = "Menu"
        )
    }
}

@Preview(showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
internal fun TopBarLayoutPreview() {
    AppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            HomeTopBar(
                onNavigateToPlan = {}
            )
        }
    }
}
