package com.example.retrofitapp.api

import com.tartita.apilist_sergiherrador.Model.DetailFilmItem
import com.tartita.apilist_sergiherrador.Model.FilmApplication

class Repository {

    private val apiInterface = APIInterface.create()
    val daoInterface = FilmApplication.database.filmDao()

    //API

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

    // Database
    suspend fun saveAsFavorite(detailFilmItem: DetailFilmItem) = daoInterface.addFilm(detailFilmItem)
    suspend fun deleteFavorite(detailFilmItem: DetailFilmItem) = daoInterface.deleteFilm(detailFilmItem)
    suspend fun isFavorite(detailFilmItem: DetailFilmItem) = daoInterface.getFilmById(detailFilmItem.id).isNotEmpty()
    suspend fun getFavorites()=daoInterface.getAllCharacters()


}
