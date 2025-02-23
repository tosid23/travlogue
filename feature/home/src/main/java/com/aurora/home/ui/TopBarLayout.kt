package com.aurora.home.ui

import android.content.res.Configuration
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.aurora.designsystem.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopBarLayout() {
    TopAppBar(
        title = {
            Text(
                text = "Hello Traveller",
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.primary
            )
        },
        actions = {

        }
    )
}

@Preview
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
internal fun TopBarLayoutPreview() {
    AppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            TopBarLayout()
        }
    }

}

