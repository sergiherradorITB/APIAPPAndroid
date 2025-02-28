package com.tartita.apilist_sergiherrador.Model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Dao {
    @Query("SELECT * FROM DetailFilmEntity")
    suspend fun getAllCharacters():MutableList<DetailFilmItem>

    @Query("SELECT * FROM DetailFilmEntity WHERE id = :filmId")
    suspend fun getFilmById(filmId:String):MutableList<DetailFilmItem>

    @Insert
    suspend fun addFilm(film: DetailFilmItem)
    @Delete
    suspend fun deleteFilm(film: DetailFilmItem)
}