package com.example.retrofitapp.api

class Repository {

    private val apiInterface = APIInterface.create()
    suspend fun getAllPeople() = apiInterface.getPeople()
    suspend fun getAllFilms() = apiInterface.getFilms()
    suspend fun getOneFilms(id:String) = apiInterface.getSpecificFilm(id)

}
