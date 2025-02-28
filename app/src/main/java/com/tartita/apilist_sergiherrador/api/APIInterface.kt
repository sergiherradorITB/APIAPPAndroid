package com.example.retrofitapp.api

import com.tartita.apilist_sergiherrador.Model.AllFilms
import com.tartita.apilist_sergiherrador.Model.DetailFilmItem
import com.tartita.apilist_sergiherrador.Model.PersonaItem
import com.tartita.apilist_sergiherrador.Model.Species
import com.tartita.apilist_sergiherrador.Model.SpeciesItem
import com.tartita.apilist_sergiherrador.Model.Location
import com.tartita.apilist_sergiherrador.Model.LocationItem
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface APIInterface {
    @GET("films")
    suspend fun getFilms(): Response<List<AllFilms>> // Método para obtener películas

    @GET("films/{id}")
    suspend fun getSpecificFilm(@Path("id") idFilm:String): Response<DetailFilmItem>

    @GET("people")
    suspend fun getPeople(): Response<List<PersonaItem>>

    @GET("species")
    suspend fun getSpecies(): Response<Species>

    @GET("species/{id}")
    suspend fun getSpeciesDetailed(@Path("id") idSpecie: String): Response<SpeciesItem>

    @GET("locations")
    suspend fun getLocations(): Response<Location>

    @GET("locations/{id}")
    suspend fun getLocationsDetailed(@Path("id") idLocation: String): Response<LocationItem>

    companion object {
        val BASE_URL = "https://ghibliapi.vercel.app/"
        fun create(): APIInterface {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(APIInterface::class.java)
        }
    }
}