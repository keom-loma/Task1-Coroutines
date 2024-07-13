package com.example.coroutineproject.ui.theme.screens
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun Home() {
	Column (modifier = Modifier.fillMaxSize()) {
		Text(text = "Home")
	}
}