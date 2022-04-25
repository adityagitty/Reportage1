package com.example.myapplication.ModelClasses

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Article")
data class Article(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
)