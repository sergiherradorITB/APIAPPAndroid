package com.example.retrofitapp.api

class Repository {

    private val apiInterface = APIInterface.create()

    suspend fun getAllCharacters() = apiInterface.getCharacters()
}
