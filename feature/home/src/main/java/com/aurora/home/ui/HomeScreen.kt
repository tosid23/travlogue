package com.aurora.home.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen() {
    Text(
        text = "Hello Home Screen"
    )
}

@Preview(showBackground = true)
@Composable
internal fun Preview() {
    HomeScreen()
}