package com.tartita.apilist.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DetailFilmEntity")
data class DetailFilmItem(
    val description: String,
    val director: String,
    @PrimaryKey
    val id: String,
    val image: String,
    val locations: List<String>,
    val movie_banner: String,
    val original_title: String,
    val original_title_romanised: String,
    val people: List<String>,
    val producer: String,
    val release_date: String,
    val rt_score: String,
    val running_time: String,
    val species: List<String>,
    val title: String,
    val url: String,
    val vehicles: List<String>
)