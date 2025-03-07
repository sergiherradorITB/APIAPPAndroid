package com.tartita.apilist.Model

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = arrayOf(DetailFilmItem::class), version = 1)
@TypeConverters(Converters::class)

abstract class FilmsDatabase : RoomDatabase() {
    abstract fun filmDao(): Dao
}