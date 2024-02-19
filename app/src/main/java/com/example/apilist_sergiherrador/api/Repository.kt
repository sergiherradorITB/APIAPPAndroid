package com.example.retrofitapp.api

class Repository {

    private val apiInterface = APIInterface.create()
    // Persones:
    suspend fun getAllPeople() = apiInterface.getPeople()

    //Pelicules:
    suspend fun getAllFilms() = apiInterface.getFilms()
    suspend fun getOneFilms(id:String) = apiInterface.getSpecificFilm(id)

    //Species
    suspend fun getSpecies() = apiInterface.getSpecies()
    suspend fun getSpeciesDetailed(id: String) = apiInterface.getSpeciesDetailed(id)

    // Locations
    suspend fun getLocation() = apiInterface.getLocations()
    suspend fun getLocationDetailed(id:String) = apiInterface.getLocationsDetailed(id)
}
