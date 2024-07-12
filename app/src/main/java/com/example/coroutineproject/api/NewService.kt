package com.example.coroutineproject.api

import com.example.coroutineproject.model.NewModel
import retrofit2.http.GET

interface NewService {

    @GET("/posts")
    suspend fun getPosts(): List<NewModel>
}