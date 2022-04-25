package com.yogify.kotlinprojectjetpack.Architecture_Component.MvvmWithRetrofit_NewsApp.LocalDataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.ModelClasses.Article

@Dao
interface ArticleDAO {
    @Insert
    suspend fun addArticles(articles: List<Article>)

    @Query("select * from article")
    fun getArticles(): List<Article>
}