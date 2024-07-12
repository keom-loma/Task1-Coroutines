package com.example.coroutineproject.data

import com.example.coroutineproject.api.NewService
import com.example.coroutineproject.model.NewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewRepository @Inject constructor(private val newService: NewService) {
    suspend fun getPosts(): Flow<List<NewModel>> = flow {
        val res = newService.getPosts()
        emit(res)
    }
}