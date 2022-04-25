package com.example.myapplication.ModelClasses

import com.example.myapplication.ModelClasses.Article

data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)
