package com.example.retrofitapp.api

import com.example.apilist_sergiherrador.Model.DataItem
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface APIInterface {

    @GET("films")
    suspend fun getCharacters(): Response<DataItem>

    companion object {
        val BASE_URL = "https://ghibliapi.vercel.app/"
        fun create(): APIInterface {
            val client = OkHttpClient.Builder().build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(APIInterface::class.java)
        }
    }

}