package com.example.retrofitapp.api

import com.example.apilist_sergiherrador.Model.DataItem
import com.example.apilist_sergiherrador.Model.PersonaItem
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface APIInterface {
    @GET("films")
    suspend fun getFilms(): Response<List<DataItem>> // Método para obtener películas

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

