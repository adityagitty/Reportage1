package com.example.myapplication.Repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.ModelClasses.News
import com.yogify.kotlinprojectjetpack.Architecture_Component.MvvmWithRetrofit_NewsApp.API.Api_Constant
import com.yogify.kotlinprojectjetpack.Architecture_Component.MvvmWithRetrofit_NewsApp.API.NewsService
import com.yogify.kotlinprojectjetpack.Architecture_Component.MvvmWithRetrofit_NewsApp.LocalDataBase.ArticleDatabase
import com.yogify.kotlinprojectjetpack.Architecture_Component.MvvmWithRetrofit_NewsApp.Utils.NetworkUtils

class NewsRepository(
    private val newsService: NewsService,
    private val newsDatabase: ArticleDatabase,
    val context: Context
) {
    private val newsLiveData = MutableLiveData<News>()
    val news: LiveData<News>
        get() = newsLiveData

    suspend fun getNews() {
        if (NetworkUtils.isOnline(context)) {
            //get data from api server
            Log.d("InternetConnection", "You Are Online")
            val result = newsService.getAllArticals(Api_Constant.KEY)
            if (result?.body() != null) {
                newsLiveData.postValue(result.body())
                newsDatabase.getDAO().addArticles(result.body()!!.articles)
                //this line is used to set data into news live data from interface.
            }
        } else {
            Log.d("InternetConnection", "You Are Offline")
            // Get Data from local database
            val newsdata = News(newsDatabase.getDAO().getArticles(), "200", 20)
            newsLiveData.postValue(newsdata)
        }

    }
}