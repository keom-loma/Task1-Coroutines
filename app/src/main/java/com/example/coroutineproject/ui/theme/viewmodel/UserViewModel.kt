package com.example.coroutineproject.ui.theme.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutineproject.api.UserService
import com.example.coroutineproject.data.UserRepository1
import com.example.coroutineproject.model.UserModelItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository1: UserRepository1) :
	ViewModel() {
	private val _posts = MutableStateFlow<List<UserModelItem>>(emptyList())
	val posts: StateFlow<List<UserModelItem>> = _posts

	private val _isLoading = MutableLiveData<Boolean>()
	val isLoading: LiveData<Boolean> = _isLoading


	init {
		fetchPost()
	}
	private fun fetchPost() {
		viewModelScope.launch {
			runCatching {
				withContext(Dispatchers.Main) {
					_isLoading.value = true
				}
				withContext(Dispatchers.IO) {
					val response = userRepository1.getPosts()
					response.collect {
						withContext(Dispatchers.Main) {
							_posts.value = it
							Log.i("Test simple", "fetchPost: $it")
						}
					}
				}
			}.onFailure {
				withContext(Dispatchers.Main) {
					_isLoading.value = false
					_posts.value = arrayListOf()
				}

			}.onSuccess {
				withContext(Dispatchers.Main) {
					_isLoading.value = false
				}
			}
		}
	}
}