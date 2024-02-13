package com.example.apilist_sergiherrador.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apilist_sergiherrador.Model.DataItem
import com.example.apilist_sergiherrador.Model.PersonaItem
import com.example.retrofitapp.api.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class APIViewModel : ViewModel() {
    private val repository = Repository()
    private val _loading = MutableLiveData<Boolean>()
    val loading = _loading
    private val _films = MutableLiveData<List<DataItem>>()
    val films = _films
    private val _people = MutableLiveData<List<PersonaItem>>() // LiveData para almacenar personas
    val people = _people
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

    fun onSearchTextChange(text: String) {
        // Actualizar el valor del texto de búsqueda cuando cambie
        _searchText.value = text
    }
}

