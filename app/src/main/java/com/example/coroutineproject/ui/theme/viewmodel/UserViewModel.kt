package com.example.coroutineproject.ui.theme.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutineproject.api.UserService
import com.example.coroutineproject.data.UserRepository1
import com.example.coroutineproject.model.UserModelItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository1: UserRepository1) : ViewModel() {
    private val _posts = MutableStateFlow<List<UserModelItem>>(emptyList())
    val posts: StateFlow<List<UserModelItem>> = _posts
    init {
        fetchPost()
    }

    private fun fetchPost(){

//        CoroutineScope(Dispatchers.IO).launch {
//            val res = userRepository1.getPosts()
//            res.collect {
//                _posts.value = it
//            }
//        }


        viewModelScope.launch {
          val res = userRepository1.getPosts()
            res.collect {
                _posts.value = it
            }
        }
    }
}