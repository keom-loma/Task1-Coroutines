package com.example.coroutineproject.api

import com.example.coroutineproject.model.UserModelItem
import retrofit2.http.GET
import retrofit2.http.Path


interface UserService {

  @GET("/posts")
  suspend fun getPosts(): List<UserModelItem>


}