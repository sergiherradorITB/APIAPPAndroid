package com.example.retrofitapp.api

import com.example.apilist_sergiherrador.Model.AllFilms
import com.example.apilist_sergiherrador.Model.DetailFilmItem
import com.example.apilist_sergiherrador.Model.PersonaItem
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface APIInterface {
    @GET("films")
    suspend fun getFilms(): Response<List<AllFilms>> // Método para obtener películas

    @GET("films/{id}")
    suspend fun getSpecificFilm(@Path("id") idFilm:String): Response<DetailFilmItem> // Método para obtener personas

    @GET("people")
    suspend fun getPeople(): Response<List<PersonaItem>> // Método para obtener personas

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