package com.example.myapplication

import android.app.Application
import com.example.myapplication.Repository.NewsRepository
import com.yogify.kotlinprojectjetpack.Architecture_Component.MvvmWithRetrofit_NewsApp.API.NewsRetrofitHelper
import com.yogify.kotlinprojectjetpack.Architecture_Component.MvvmWithRetrofit_NewsApp.API.NewsService
import com.yogify.kotlinprojectjetpack.Architecture_Component.MvvmWithRetrofit_NewsApp.LocalDataBase.ArticleDatabase


class ApplicationClass : Application() {
    lateinit var newsRepository: NewsRepository
    override fun onCreate() {
        super.onCreate()
        Initialization()
    }

    fun Initialization() {
        val newsapi = NewsRetrofitHelper.getInstance().create(NewsService::class.java)
        val database = ArticleDatabase.getDataBase(applicationContext)
        newsRepository = NewsRepository(newsapi,database, applicationContext)
    }

}