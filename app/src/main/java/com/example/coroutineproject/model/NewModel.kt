package com.example.coroutineproject.model

data class NewModel(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)