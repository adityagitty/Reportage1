package com.yogify.kotlinprojectjetpack.Architecture_Component.MvvmWithRetrofit_NewsApp.LocalDataBase

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.*
import com.example.myapplication.ModelClasses.Article

@Database(entities = [Article::class], version = 1)
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun getDAO(): ArticleDAO

    companion object {
        @Volatile
        private var INSTANCE: ArticleDatabase? = null

        fun getDataBase(context: Context): ArticleDatabase {
            if (INSTANCE == null) {
                // Create Only single , if two class trying to create this at same time , This is Called Locking
                synchronized(this) {
                    INSTANCE =
                            Room.databaseBuilder(
                                    context,
                                    ArticleDatabase::class.java,
                                    "newsarticledatabase"
                            ).build()
                }

            }
            return INSTANCE!!
        }
    }
}