package com.example.apilist_sergiherrador.Model

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [DetailFilmItem::class], version = 1)
@TypeConverters(Converters::class) // Use TypeConverters annotation

abstract class FilmsDatabase : RoomDatabase() {
    abstract fun filmDao(): Dao
}