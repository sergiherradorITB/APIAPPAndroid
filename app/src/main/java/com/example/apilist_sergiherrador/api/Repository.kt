package com.example.retrofitapp.api

class Repository {

    val apiInterface = APIInterface.create()

    suspend fun getAllCharacters() = apiInterface.getCharacters()
}