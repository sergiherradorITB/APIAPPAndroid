package com.example.apilist_sergiherrador.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apilist_sergiherrador.Model.AllFilms
import com.example.apilist_sergiherrador.Model.DetailFilmItem
import com.example.apilist_sergiherrador.Model.PersonaItem
import com.example.retrofitapp.api.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class APIViewModel : ViewModel() {
    private val repository = Repository()

    // Todas las pelis
    private val _loading = MutableLiveData<Boolean>()
    val loading = _loading
    private val _films = MutableLiveData<List<AllFilms>>()
    val films = _films

    // Gente guapa
    private val _people = MutableLiveData<List<PersonaItem>>() // LiveData para almacenar personas
    val people = _people

    // Detail Film
    private val _detailFilm = MutableLiveData<DetailFilmItem>() // LiveData para almacenar personas
    val detailFilm = _detailFilm
    private val _loadingFilm = MutableLiveData<Boolean>()
    val loadingFilm = _loadingFilm

    // Busqueda del text
    private val _searchText = MutableLiveData<String>()
    val searchText: MutableLiveData<String> = _searchText

    fun getFilms() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getAllFilms()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _films.value = response.body()
                    _loading.value = false
                } else {
                    Log.e("Error :", response.message())
                }
            }
        }
    }

    fun getPeople() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getAllPeople()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _people.value = response.body()
                    _loading.value = false
                } else {
                    Log.e("Error :", response.message())
                }
            }
        }
    }

    fun getOneFilm(id:String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getOneFilms(id)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _detailFilm.value = response.body()
                    _loadingFilm.value = false
                } else {
                    Log.e("Error :", response.message())
                }
            }
        }
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }
}

