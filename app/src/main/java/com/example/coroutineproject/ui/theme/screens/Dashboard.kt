package com.example.coroutineproject.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.coroutineproject.navigation.EnumScreen

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Details(navController: NavController) {

	var counter by remember { mutableStateOf(0) }
	val context = LocalContext.current

	Column(modifier = Modifier.padding(top = 200.dp)) {
		Button(onClick = {
			navController.navigate(EnumScreen.Notification.name)
		}) {
			Text(text = "Screen One")
			LaunchedEffect(key1 = context) {
				// This block will be called when 'data' changes
			}
			LaunchedEffectExample(counter.toString())
		}
	}
}


@Composable
fun Dashboard(onNavigate: () -> Unit) {
	Column(
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally,
		modifier = Modifier
			.fillMaxSize()
			.padding(top = 200.dp)
	) {
		Button(onClick = { onNavigate() }) {
			Text("Go to Screen 2")
		}
	}
}

@Composable
fun Notification(navController: NavController) {
	Box(modifier = Modifier
		.fillMaxSize()
		.padding(top = 200.dp)) {
		Button(onClick = {
			navController.popBackStack()
		}) {
			Text("Notification")

		}
	}
}

@Composable
fun  LaunchedEffectExample(data: String){
	LaunchedEffect(key1 = data) {
		// This block will be called when 'data' changes
   /// context.showToast("Data changed: $data")
		// will be show task for test counter value
	}
}

@Composable
fun MyScreen() {
	val scope = rememberCoroutineScope()

	Button(onClick = {
		scope.launch {
			// Coroutine launched on button click
			// perform some action
		}
	}) {
		Text("Click me")
	}
}

@Composable
fun MySideEffect(count: Int) {
	SideEffect {
		// This block will be called after every recomposition
	}
	Text("Number: $count")
}
@Composable
fun MyDisposableEffect(data: String) {
	DisposableEffect(data) {
		// Cleanup code
		onDispose {
			println("Data disposed: $data")
		}
	}
}

@Composable
fun MyProduceState(id: Int) {
	val data = produceState(initialValue = "Loading...", id) {
		// Producer coroutine
		value = fetchData(id)
	}

	Text("Data: ${data.value}")
}

suspend fun fetchData(id: Int): String {
	delay(1000) // Simulate network delay
	return "Data for $id"
}

