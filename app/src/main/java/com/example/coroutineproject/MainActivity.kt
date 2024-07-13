package com.example.coroutineproject

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.coroutineproject.model.UserModelItem
import com.example.coroutineproject.ui.theme.CoroutineProjectTheme

import com.example.coroutineproject.ui.theme.viewmodel.UserViewModel

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
	@OptIn(ExperimentalMaterial3Api::class)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		enableEdgeToEdge()

		setContent {
			val viewModel: UserViewModel = hiltViewModel()

			CoroutineProjectTheme {
				Scaffold(modifier = Modifier
					.fillMaxSize()
					.padding(top = 10.dp), topBar = {
					TopAppBar(title = {
						Text(text = "Coroutine test project")
					})
				}) {
					Greeting(viewModel)
				}
			}
		}
	}
}

@Composable
fun Greeting(viewModel: UserViewModel) {

	val post = viewModel.posts.collectAsState()
	var isLoading = false
	viewModel.isLoading.observeForever {
		isLoading = it
	}

	if (isLoading && post.value.isEmpty().not()) {
		Box(
			modifier = Modifier
				.fillMaxWidth()
				.fillMaxHeight()
				.padding(top = 100.dp),
			contentAlignment = Alignment.Center
		) {
			CircularProgressIndicator()
		}
	}
	Box(
		modifier = Modifier
			.fillMaxSize()
			.padding(top = 100.dp)
	) {
		MyBaseView(listOfUser = post.value, onClick = {

		})
	}

}

@Composable
fun MyBaseView(listOfUser: List<UserModelItem>, onClick: () -> Unit) {

	if (listOfUser.isEmpty()) {
		Box(
			modifier = Modifier.fillMaxSize(),
			contentAlignment = Alignment.Center,
		) {
			Text(text = "No data found")
			// CircularProgressIndicator()
		}
	} else {
		LazyColumn {
			items(listOfUser.size) {
				ItemNew(
					labelId = "${listOfUser[it].id}",
					labelSubTitle = listOfUser[it].title,
					labelDec = listOfUser[it].body,
					onClick = { onClick() })
			}

		}
	}
}

@Composable
fun ItemNew(labelId: String, labelSubTitle: String, labelDec: String, onClick: () -> Unit = {}) {
	Surface(modifier = Modifier.fillMaxWidth()) {
		Card(
			modifier = Modifier
				.padding(16.dp)
				.fillMaxWidth(),
			shape = RoundedCornerShape(8.dp), // Set your desired background color here
			onClick = onClick
		) {
			Column {
				Row(modifier = Modifier.align(alignment = Alignment.Start)) {
					Text(
						text = labelId,
						modifier = Modifier.padding(top = 6.dp, start = 10.dp, bottom = 10.dp)
					)
					Text(text = labelSubTitle, modifier = Modifier.padding(12.dp))
				}
				Text(text = labelDec, modifier = Modifier.padding(top = 10.dp, start = 10.dp))
			}
		}
	}
}

@Preview(showBackground = false)
@Composable
fun GreetingPreview() {
	CoroutineProjectTheme {
		//Greeting("Android")
	}
}