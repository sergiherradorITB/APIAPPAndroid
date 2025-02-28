package com.tartita.apilist.Model

data class PersonaItem(
    val age: String,
    val eye_color: String,
    val films: List<String>,
    val gender: String,
    val hair_color: String,
    val id: String,
    val name: String,
    val species: String,
    val url: String
)