package com.tartita.apilist_sergiherrador.Model

data class SpeciesItem(
    val classification: String,
    val eye_colors: String,
    val films: List<String>,
    val hair_colors: String,
    val id: String,
    val name: String,
    val people: List<String>,
    val url: String
)