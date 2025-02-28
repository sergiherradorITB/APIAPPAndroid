package com.tartita.apilist.Model

data class LocationItem(
    val climate: String,
    val films: List<String>,
    val id: String,
    val name: String,
    val residents: List<String>,
    val surface_water: String,
    val terrain: String,
    val url: String
)