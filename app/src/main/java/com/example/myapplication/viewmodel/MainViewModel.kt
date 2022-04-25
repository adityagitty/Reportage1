package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.ModelClasses.News
import com.example.myapplication.Repository.NewsRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel(private val repository: NewsRepository) : ViewModel() {

    init {
        GlobalScope.launch {
            repository.getNews() }

    }

    val newsarticles: LiveData<News>
        get() = repository.news
}
