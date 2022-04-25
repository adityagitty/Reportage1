package com.yogify.kotlinprojectjetpack.Architecture_Component.MvvmWithRetrofit_NewsApp.API

import android.provider.MediaStore
import androidx.annotation.StyleRes
import com.example.myapplication.ModelClasses.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NewsService {


    @GET(Api_Constant.TOPHEADING)
    suspend fun getAllArticals(@Query(Api_Constant.API_KRY) apikey: String): Response<News>

}