package com.example.apilist_sergiherrador.Model

import android.app.Application
import androidx.room.Room

class FilmApplication : Application() {
    companion object {
        lateinit var database: FilmsDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database =
            Room.databaseBuilder(this,
                FilmsDatabase::class.java,
                "DetailFilmDatabase").build()
    }
}