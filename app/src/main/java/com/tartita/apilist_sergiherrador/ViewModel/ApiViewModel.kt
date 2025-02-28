package com.tartita.apilist_sergiherrador.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tartita.apilist_sergiherrador.Model.AllFilms
import com.tartita.apilist_sergiherrador.Model.DetailFilmItem
import com.tartita.apilist_sergiherrador.Model.Location
import com.tartita.apilist_sergiherrador.Model.LocationItem
import com.tartita.apilist_sergiherrador.Model.PersonaItem
import com.tartita.apilist_sergiherrador.Model.Species
import com.tartita.apilist_sergiherrador.Model.SpeciesItem
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

    // Species
    private val _species = MutableLiveData<Species>() // LiveData para almacenar personas
    val species = _species
    private val _loadingFilm2 = MutableLiveData<Boolean>()
    val loadingFilm2 = _loadingFilm2

    private val _speciesDetailed = MutableLiveData<SpeciesItem>() // LiveData para almacenar personas
    val speciesItemDetailed = _speciesDetailed

    // Locations
    private val _locations = MutableLiveData<Location>() // LiveData para almacenar personas
    val locations = _locations

    private val _locationsDetailed = MutableLiveData<LocationItem>() // LiveData para almacenar personas
    val locationItemDetailed = _locationsDetailed

    // Busqueda del text
    private val _searchText = MutableLiveData<String>()
    val searchText = _searchText

    // Database
    private val _isFavorite = MutableLiveData(false)
    val isFavorite = _isFavorite
    private val _favorites = MutableLiveData<MutableList<DetailFilmItem>>()
    val favorites = _favorites

    //Pelicules
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

    // Persones
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

    // Species
    fun getSpecies() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getSpecies()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _species.value = response.body()
                    _loadingFilm2.value = false
                } else {
                    Log.e("Error :", response.message())
                }
            }
        }
    }

    fun getSpeciesDetailed(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getSpeciesDetailed(id)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _speciesDetailed.value = response.body()
                    loading.value = false
                } else {
                    Log.e("Error :", response.message())
                }
            }
        }
    }

    // Locations

    fun getLocation() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getLocation()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _locations.value = response.body()
                    _loadingFilm2.value = false
                } else {
                    Log.e("Error :", response.message())
                }
            }
        }
    }

    fun getLocationDetailed(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getLocationDetailed(id)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _locationsDetailed.value = response.body()
                    loading.value = false
                } else {
                    Log.e("Error :", response.message())
                }
            }
        }
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun getFavorites(){
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getFavorites()
            withContext(Dispatchers.Main){
                _favorites.value = response
                _loading.value = false
            }
        }
    }

    fun isFavorite(filmItem: DetailFilmItem){
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.isFavorite(filmItem)
            withContext(Dispatchers.Main){
                _isFavorite.value = response
            }
        }
    }

    fun saveAsFavorite(filmItem: DetailFilmItem) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.saveAsFavorite(filmItem)
            _isFavorite.postValue(true) // Actualizar el LiveData
        }
    }

    fun deleteFavorite(filmItem: DetailFilmItem) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteFavorite(filmItem)
            _isFavorite.postValue(false) // Actualizar el LiveData
        }
    }
}